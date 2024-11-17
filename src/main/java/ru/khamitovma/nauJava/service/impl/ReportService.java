package ru.khamitovma.nauJava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.khamitovma.nauJava.model.entity.Report;
import ru.khamitovma.nauJava.model.enums.ReportStatusEnum;
import ru.khamitovma.nauJava.repository.ReportRepository;
import ru.khamitovma.nauJava.service.TestSolutionService;
import ru.khamitovma.nauJava.service.UserService;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class ReportService {

    private final UserService userService;
    private final TestSolutionService testSolutionService;
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(UserService userService, TestSolutionService testSolutionService,
                         ReportRepository reportRepository) {
        this.userService = userService;
        this.testSolutionService = testSolutionService;
        this.reportRepository = reportRepository;
    }

    public Report createReport() {
        Report report = new Report();
        report.setStatus(ReportStatusEnum.CREATED);
        reportRepository.save(report);
        generateReportAsync(report.getId());
        return report;
    }

    public Optional<Report> getReport(Long id) {
        return reportRepository.findById(id);
    }

    @Async
    public void generateReportAsync(Long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Callable<Long> task1 = userService::getUsersCount;
        Callable<Long> task2 = testSolutionService::getTestSolutionsCount;

        try {
            long startTime = System.currentTimeMillis();
            Future<Long> future1 = executor.submit(task1);
            Future<Long> future2 = executor.submit(task2);
            long elapsed = (System.currentTimeMillis() - startTime);
            report.setStatus(ReportStatusEnum.COMPLETED);
            String result = String.format("Пользователи: %s, Тесты: %s, Время: %s", future1.get(), future2.get(),
                    elapsed);
            report.setPayload(result);
            reportRepository.save(report);
        } catch (InterruptedException | ExecutionException e) {
            report.setStatus(ReportStatusEnum.ERROR);
            report.setPayload(e.getMessage());
            reportRepository.save(report);
        } finally {
            executor.shutdown();
        }
    }
}
