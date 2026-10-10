package com.analisetrafegodados.analisetrafegodados.controller;

import com.analisetrafegodados.analisetrafegodados.dto.InterpolationResultDto;
import com.analisetrafegodados.analisetrafegodados.dto.TrafficAnalysisResult;
import com.analisetrafegodados.analisetrafegodados.model.TrafficPoint;
import com.analisetrafegodados.analisetrafegodados.service.TrafficAnalysisService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class TrafficController {

    private final TrafficAnalysisService trafficAnalysisService;

    public TrafficController(TrafficAnalysisService trafficAnalysisService) {
        this.trafficAnalysisService = trafficAnalysisService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model, HttpSession session) {
        TrafficAnalysisResult analysis = (TrafficAnalysisResult) session.getAttribute("currentAnalysis");
        model.addAttribute("analysis", analysis);
        model.addAttribute("interpolationResult", session.getAttribute("interpolationResult"));
        model.addAttribute("interpolationError", session.getAttribute("interpolationError"));
        return "index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model, HttpSession session) {
        try {
            TrafficAnalysisResult analysis = trafficAnalysisService.analyze(file);
            List<TrafficPoint> points = analysis.getPoints();
            session.setAttribute("currentAnalysis", analysis);
            session.setAttribute("currentPoints", points);
            session.removeAttribute("interpolationResult");
            session.removeAttribute("interpolationError");
            model.addAttribute("analysis", analysis);
            model.addAttribute("interpolationResult", null);
            model.addAttribute("interpolationError", null);
            return "index";
        } catch (IllegalArgumentException ex) {
            session.removeAttribute("currentAnalysis");
            session.removeAttribute("currentPoints");
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("analysis", null);
            model.addAttribute("interpolationResult", null);
            model.addAttribute("interpolationError", null);
            return "index";
        }
    }

    @PostMapping("/interpolacao")
    public String interpolacao(@RequestParam("horario") String horario, Model model, HttpSession session) {
        TrafficAnalysisResult analysis = (TrafficAnalysisResult) session.getAttribute("currentAnalysis");
        List<TrafficPoint> points = (List<TrafficPoint>) session.getAttribute("currentPoints");

        model.addAttribute("analysis", analysis);

        if (analysis == null || points == null || points.isEmpty()) {
            model.addAttribute("interpolationError", "Carregue um CSV antes de consultar a interpolação.");
            model.addAttribute("interpolationResult", null);
            return "index";
        }

        try {
            InterpolationResultDto interpolationResult = trafficAnalysisService.calculateInterpolation(horario, points);
            model.addAttribute("interpolationResult", interpolationResult);
            model.addAttribute("interpolationError", null);
            session.setAttribute("interpolationResult", interpolationResult);
            session.setAttribute("interpolationError", null);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("interpolationError", ex.getMessage());
            model.addAttribute("interpolationResult", null);
            session.setAttribute("interpolationResult", null);
            session.setAttribute("interpolationError", ex.getMessage());
        }

        return "index";
    }
}
