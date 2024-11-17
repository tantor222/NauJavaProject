package ru.khamitovma.nauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.khamitovma.nauJava.model.entity.Report;
import ru.khamitovma.nauJava.service.impl.ReportService;

import java.util.Optional;

@Controller
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/")
    public ResponseEntity<Report> createReport() {
        return ResponseEntity.ok(reportService.createReport());
    }

    @GetMapping("/view/{id}")
    public String userListView(Model model, @PathVariable Long id) {
        Optional<Report> report = reportService.getReport(id);
        model.addAttribute("report", report);
        return "report";
    }
}
