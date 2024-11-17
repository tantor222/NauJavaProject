package ru.khamitovma.nauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khamitovma.nauJava.model.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
