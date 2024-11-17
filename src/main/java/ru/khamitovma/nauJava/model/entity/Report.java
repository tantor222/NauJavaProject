package ru.khamitovma.nauJava.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.khamitovma.nauJava.model.enums.ReportStatusEnum;

import java.io.Serializable;

@Entity
@Table(name = "report")
public class Report implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private ReportStatusEnum status;

    @Enumerated(EnumType.STRING)
    private String payload;

    public Report(ReportStatusEnum status, String payload) {
        this.status = status;
        this.payload = payload;
    }

    public Report() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReportStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ReportStatusEnum status) {
        this.status = status;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
