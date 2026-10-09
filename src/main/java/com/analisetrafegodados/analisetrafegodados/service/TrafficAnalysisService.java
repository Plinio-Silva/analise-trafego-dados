package com.analisetrafegodados.analisetrafegodados.service;

import com.analisetrafegodados.analisetrafegodados.dto.TrafficAnalysisResult;
import com.analisetrafegodados.analisetrafegodados.dto.TrafficRateDto;
import com.analisetrafegodados.analisetrafegodados.model.TrafficPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrafficAnalysisService {

    private static final double LINK_CAPACITY_Mbps = 1000.0;
    private static final double WARNING_LEVEL = 90.0;
    private static final double CRITICAL_LEVEL = 95.0;

    public TrafficAnalysisResult analyze(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Selecione um arquivo CSV para análise.");
        }

        List<TrafficPoint> points = parseCsv(file);
        if (points.size() < 2) {
            throw new IllegalArgumentException("O arquivo CSV deve conter pelo menos dois pontos de dados.");
        }

        List<TrafficPoint> ordered = points.stream()
                .sorted(Comparator.comparingInt(TrafficPoint::getHour))
                .toList();

        double peakTraffic = ordered.stream().mapToDouble(TrafficPoint::getMbps).max().orElse(0.0);
        int peakHour = ordered.stream()
                .filter(point -> point.getMbps() == peakTraffic)
                .findFirst()
                .map(TrafficPoint::getHour)
                .orElse(ordered.getFirst().getHour());

        double minTraffic = ordered.stream().mapToDouble(TrafficPoint::getMbps).min().orElse(0.0);
        int minHour = ordered.stream()
                .filter(point -> point.getMbps() == minTraffic)
                .findFirst()
                .map(TrafficPoint::getHour)
                .orElse(ordered.getFirst().getHour());

        double averageTraffic = ordered.stream().mapToDouble(TrafficPoint::getMbps).average().orElse(0.0);
        double maxUtilization = (peakTraffic / LINK_CAPACITY_Mbps) * 100.0;
        double minUtilization = (minTraffic / LINK_CAPACITY_Mbps) * 100.0;

        List<String> growthIntervals = buildTrendIntervals(ordered, true);
        List<String> declineIntervals = buildTrendIntervals(ordered, false);
        List<String> localMaxima = buildLocalExtrema(ordered, true);
        List<String> localMinima = buildLocalExtrema(ordered, false);
        List<TrafficRateDto> growthRates = calculateGrowthRates(ordered);
        List<TrafficPoint> interpolatedPoints = interpolateMissingHours(ordered);
        String alertLevel = resolveAlertLevel(maxUtilization);
        List<String> alerts = buildAlerts(maxUtilization, peakHour, peakTraffic);
        List<String> recommendations = buildRecommendations(maxUtilization, growthIntervals.size(), peakHour);
        String summary = createSummary(ordered, peakHour, peakTraffic, maxUtilization, alertLevel);

        return new TrafficAnalysisResult(
                ordered,
                interpolatedPoints,
                growthRates,
                growthIntervals,
                declineIntervals,
                localMaxima,
                localMinima,
                alerts,
                recommendations,
                peakTraffic,
                peakHour,
                minTraffic,
                minHour,
                averageTraffic,
                maxUtilization,
                minUtilization,
                alertLevel,
                summary,
                ordered.stream().map(TrafficPoint::getHour).collect(Collectors.toList()),
                ordered.stream().map(TrafficPoint::getMbps).collect(Collectors.toList())
        );
    }

    private List<TrafficPoint> parseCsv(MultipartFile file) {
        List<TrafficPoint> points = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String clean = line.trim();
                if (clean.isEmpty()) {
                    continue;
                }

                String lower = clean.toLowerCase(Locale.ROOT);
                if (lower.contains("hora") || lower.contains("time") || lower.contains("hour") || lower.contains("mbps") || lower.contains("traffic") || lower.contains("valor")) {
                    continue;
                }

                String[] columns = clean.split("[;,]");
                if (columns.length < 2) {
                    continue;
                }

                try {
                    int hour = Integer.parseInt(columns[0].trim());
                    double value = Double.parseDouble(columns[1].trim().replace(",", "."));
                    points.add(new TrafficPoint(hour, value));
                } catch (NumberFormatException ignored) {
                    // Ignora linhas inválidas do CSV.
                }
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("Não foi possível ler o arquivo CSV enviado.", ex);
        }

        if (points.isEmpty()) {
            throw new IllegalArgumentException("Nenhum dado válido foi encontrado no arquivo CSV.");
        }

        return points;
    }

    private List<String> buildTrendIntervals(List<TrafficPoint> points, boolean growing) {
        List<String> intervals = new ArrayList<>();

        if (points.size() < 2) {
            return intervals;
        }

        int startHour = points.getFirst().getHour();
        int endHour = points.getFirst().getHour();
        boolean active = false;

        for (int index = 1; index < points.size(); index++) {
            TrafficPoint current = points.get(index);
            TrafficPoint previous = points.get(index - 1);
            boolean currentTrend = Double.compare(current.getMbps(), previous.getMbps()) > 0;

            if (growing && currentTrend) {
                if (!active) {
                    startHour = previous.getHour();
                    active = true;
                }
                endHour = current.getHour();
            } else if (!growing && Double.compare(current.getMbps(), previous.getMbps()) < 0) {
                if (!active) {
                    startHour = previous.getHour();
                    active = true;
                }
                endHour = current.getHour();
            } else if (active) {
                String label = formatInterval(startHour, endHour, growing ? "crescimento" : "decrescimento");
                intervals.add(label);
                active = false;
            }
        }

        if (active) {
            String label = formatInterval(startHour, endHour, growing ? "crescimento" : "decrescimento");
            intervals.add(label);
        }

        return intervals;
    }

    private List<String> buildLocalExtrema(List<TrafficPoint> points, boolean maxima) {
        List<String> extrema = new ArrayList<>();

        for (int index = 1; index < points.size() - 1; index++) {
            TrafficPoint previous = points.get(index - 1);
            TrafficPoint current = points.get(index);
            TrafficPoint next = points.get(index + 1);

            boolean isMaximum = current.getMbps() > previous.getMbps() && current.getMbps() > next.getMbps();
            boolean isMinimum = current.getMbps() < previous.getMbps() && current.getMbps() < next.getMbps();

            if ((maxima && isMaximum) || (!maxima && isMinimum)) {
                extrema.add("Hora " + current.getHour() + "h: " + String.format(Locale.US, "%.1f", current.getMbps()) + " Mbps");
            }
        }

        return extrema;
    }

    private List<TrafficRateDto> calculateGrowthRates(List<TrafficPoint> points) {
        List<TrafficRateDto> rates = new ArrayList<>();

        for (int index = 1; index < points.size(); index++) {
            TrafficPoint previous = points.get(index - 1);
            TrafficPoint current = points.get(index);
            double timeDifference = current.getHour() - previous.getHour();
            if (timeDifference == 0) {
                continue;
            }

            double rate = (current.getMbps() - previous.getMbps()) / timeDifference;
            rates.add(new TrafficRateDto(previous.getHour(), current.getHour(), rate));
        }

        return rates;
    }

    private List<TrafficPoint> interpolateMissingHours(List<TrafficPoint> points) {
        if (points.size() < 2) {
            return new ArrayList<>(points);
        }

        Map<Integer, Double> observed = new HashMap<>();
        for (TrafficPoint point : points) {
            observed.put(point.getHour(), point.getMbps());
        }

        int startHour = points.stream().mapToInt(TrafficPoint::getHour).min().orElse(0);
        int endHour = points.stream().mapToInt(TrafficPoint::getHour).max().orElse(0);
        List<TrafficPoint> interpolated = new ArrayList<>();

        for (int hour = startHour; hour <= endHour; hour++) {
            if (observed.containsKey(hour)) {
                interpolated.add(new TrafficPoint(hour, observed.get(hour)));
                continue;
            }

            Integer previousHour = null;
            Integer nextHour = null;
            for (int candidate = hour - 1; candidate >= startHour; candidate--) {
                if (observed.containsKey(candidate)) {
                    previousHour = candidate;
                    break;
                }
            }
            for (int candidate = hour + 1; candidate <= endHour; candidate++) {
                if (observed.containsKey(candidate)) {
                    nextHour = candidate;
                    break;
                }
            }

            if (previousHour != null && nextHour != null) {
                double previousValue = observed.get(previousHour);
                double nextValue = observed.get(nextHour);
                double fraction = (double) (hour - previousHour) / (nextHour - previousHour);
                double interpolatedValue = previousValue + (nextValue - previousValue) * fraction;
                interpolated.add(new TrafficPoint(hour, interpolatedValue));
            } else if (previousHour != null) {
                interpolated.add(new TrafficPoint(hour, observed.get(previousHour)));
            }
        }

        return interpolated;
    }

    private String resolveAlertLevel(double maxUtilization) {
        if (maxUtilization >= CRITICAL_LEVEL) {
            return "CRÍTICO";
        }
        if (maxUtilization >= WARNING_LEVEL) {
            return "ALTO";
        }
        return "NORMAL";
    }

    private List<String> buildAlerts(double maxUtilization, int peakHour, double peakTraffic) {
        List<String> alerts = new ArrayList<>();

        if (maxUtilization >= CRITICAL_LEVEL) {
            alerts.add("Utilização crítica em " + peakHour + "h: " + String.format(Locale.US, "%.1f", maxUtilization) + "% da capacidade do link.");
        } else if (maxUtilization >= WARNING_LEVEL) {
            alerts.add("Utilização elevada em " + peakHour + "h: " + String.format(Locale.US, "%.1f", maxUtilization) + "% da capacidade do link.");
        } else {
            alerts.add("Tráfego está dentro do limite operacional recomendado.");
        }

        if (peakTraffic > 0.85 * LINK_CAPACITY_Mbps) {
            alerts.add("Pico segue próximo à capacidade máxima da rede e pode influenciar desempenho geral.");
        }

        return alerts;
    }

    private List<String> buildRecommendations(double maxUtilization, int growthIntervalCount, int peakHour) {
        List<String> recommendations = new ArrayList<>();

        if (maxUtilization >= CRITICAL_LEVEL) {
            recommendations.add("Avaliar aumento da capacidade do link para reduzir risco de saturação.");
        }
        if (maxUtilization >= WARNING_LEVEL) {
            recommendations.add("Redistribuir serviços de alto consumo para horários fora do pico.");
        }
        if (growthIntervalCount > 0) {
            recommendations.add("Investigar o comportamento do tráfego no horário de pico para ajustar escalonamento e priorização.");
        } else {
            recommendations.add("Manter a capacidade atual e monitorar tendências de crescimento semanal.");
        }
        recommendations.add("Agendar revisão operacional para " + peakHour + "h, período com maior risco de congestionamento.");

        return recommendations;
    }

    private String createSummary(List<TrafficPoint> points, int peakHour, double peakTraffic, double maxUtilization, String alertLevel) {
        double average = points.stream().mapToDouble(TrafficPoint::getMbps).average().orElse(0.0);
        return "Durante o período analisado, o tráfego médio foi de "
                + String.format(Locale.US, "%.1f", average) + " Mbps, com pico de "
                + String.format(Locale.US, "%.1f", peakTraffic) + " Mbps às " + peakHour + "h. "
                + "A utilização máxima chegou a " + String.format(Locale.US, "%.1f", maxUtilization)
                + "% da capacidade do link, indicando nível " + alertLevel + ".";
    }

    private String formatInterval(int startHour, int endHour, String direction) {
        return "De " + startHour + "h até " + endHour + "h: " + direction;
    }
}
