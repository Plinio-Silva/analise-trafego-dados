package com.analisetrafegodados.analisetrafegodados.controller;

import com.analisetrafegodados.analisetrafegodados.dto.TrafficAnalysisResult;
import com.analisetrafegodados.analisetrafegodados.service.TrafficAnalysisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TrafficController {

    private final TrafficAnalysisService trafficAnalysisService;

    public TrafficController(TrafficAnalysisService trafficAnalysisService) {
        this.trafficAnalysisService = trafficAnalysisService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("analysis", null);
        return "index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            TrafficAnalysisResult analysis = trafficAnalysisService.analyze(file);
            model.addAttribute("analysis", analysis);
            return "index";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("analysis", null);
            return "index";
        }
    }
}
