package com.analisetrafegodados.analisetrafegodados.dto;

import com.analisetrafegodados.analisetrafegodados.model.TrafficPoint;

import java.util.List;

public class TrafficAnalysisResult {
    private List<TrafficPoint> points;
    private List<TrafficPoint> interpolatedPoints;
    private List<TrafficRateDto> growthRates;
    private List<String> growthIntervals;
    private List<String> declineIntervals;
    private List<String> localMaxima;
    private List<String> localMinima;
    private List<String> alerts;
    private List<String> recommendations;
    private double peakTraffic;
    private int peakHour;
    private double minTraffic;
    private int minHour;
    private double averageTraffic;
    private double maxUtilization;
    private double minUtilization;
    private String alertLevel;
    private String summary;
    private List<Integer> chartLabels;
    private List<Double> chartValues;

    public TrafficAnalysisResult() {
    }

    public TrafficAnalysisResult(List<TrafficPoint> points, List<TrafficPoint> interpolatedPoints,
                                List<TrafficRateDto> growthRates, List<String> growthIntervals,
                                List<String> declineIntervals, List<String> localMaxima,
                                List<String> localMinima, List<String> alerts,
                                List<String> recommendations, double peakTraffic, int peakHour,
                                double minTraffic, int minHour, double averageTraffic,
                                double maxUtilization, double minUtilization, String alertLevel,
                                String summary, List<Integer> chartLabels, List<Double> chartValues) {
        this.points = points;
        this.interpolatedPoints = interpolatedPoints;
        this.growthRates = growthRates;
        this.growthIntervals = growthIntervals;
        this.declineIntervals = declineIntervals;
        this.localMaxima = localMaxima;
        this.localMinima = localMinima;
        this.alerts = alerts;
        this.recommendations = recommendations;
        this.peakTraffic = peakTraffic;
        this.peakHour = peakHour;
        this.minTraffic = minTraffic;
        this.minHour = minHour;
        this.averageTraffic = averageTraffic;
        this.maxUtilization = maxUtilization;
        this.minUtilization = minUtilization;
        this.alertLevel = alertLevel;
        this.summary = summary;
        this.chartLabels = chartLabels;
        this.chartValues = chartValues;
    }

    public List<TrafficPoint> getPoints() {
        return points;
    }

    public void setPoints(List<TrafficPoint> points) {
        this.points = points;
    }

    public List<TrafficPoint> getInterpolatedPoints() {
        return interpolatedPoints;
    }

    public void setInterpolatedPoints(List<TrafficPoint> interpolatedPoints) {
        this.interpolatedPoints = interpolatedPoints;
    }

    public List<TrafficRateDto> getGrowthRates() {
        return growthRates;
    }

    public void setGrowthRates(List<TrafficRateDto> growthRates) {
        this.growthRates = growthRates;
    }

    public List<String> getGrowthIntervals() {
        return growthIntervals;
    }

    public void setGrowthIntervals(List<String> growthIntervals) {
        this.growthIntervals = growthIntervals;
    }

    public List<String> getDeclineIntervals() {
        return declineIntervals;
    }

    public void setDeclineIntervals(List<String> declineIntervals) {
        this.declineIntervals = declineIntervals;
    }

    public List<String> getLocalMaxima() {
        return localMaxima;
    }

    public void setLocalMaxima(List<String> localMaxima) {
        this.localMaxima = localMaxima;
    }

    public List<String> getLocalMinima() {
        return localMinima;
    }

    public void setLocalMinima(List<String> localMinima) {
        this.localMinima = localMinima;
    }

    public List<String> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<String> alerts) {
        this.alerts = alerts;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }

    public double getPeakTraffic() {
        return peakTraffic;
    }

    public void setPeakTraffic(double peakTraffic) {
        this.peakTraffic = peakTraffic;
    }

    public int getPeakHour() {
        return peakHour;
    }

    public void setPeakHour(int peakHour) {
        this.peakHour = peakHour;
    }

    public double getMinTraffic() {
        return minTraffic;
    }

    public void setMinTraffic(double minTraffic) {
        this.minTraffic = minTraffic;
    }

    public int getMinHour() {
        return minHour;
    }

    public void setMinHour(int minHour) {
        this.minHour = minHour;
    }

    public double getAverageTraffic() {
        return averageTraffic;
    }

    public void setAverageTraffic(double averageTraffic) {
        this.averageTraffic = averageTraffic;
    }

    public double getMaxUtilization() {
        return maxUtilization;
    }

    public void setMaxUtilization(double maxUtilization) {
        this.maxUtilization = maxUtilization;
    }

    public double getMinUtilization() {
        return minUtilization;
    }

    public void setMinUtilization(double minUtilization) {
        this.minUtilization = minUtilization;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Integer> getChartLabels() {
        return chartLabels;
    }

    public void setChartLabels(List<Integer> chartLabels) {
        this.chartLabels = chartLabels;
    }

    public List<Double> getChartValues() {
        return chartValues;
    }

    public void setChartValues(List<Double> chartValues) {
        this.chartValues = chartValues;
    }
}
