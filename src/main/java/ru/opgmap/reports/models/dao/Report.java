package ru.opgmap.reports.models.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.opgmap.reports.models.dto.events.ReportEvent;
import ru.opgmap.reports.models.enums.ReportHandleStatus;
import ru.opgmap.reports.models.enums.ReportType;
import ru.opgmap.reports.models.enums.SubjectType;
import ru.opgmap.reports.utils.TimeUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "reports")
@NoArgsConstructor
public class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "type")
    private ReportType reportType;

    @Column(name = "subject_type")
    private SubjectType subjectType;

    @Column(name = "subject_id")
    private UUID subjectId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "text")
    private String text;

    @Column(name = "status")
    private ReportHandleStatus status;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    public Report(final ReportEvent reportEvent) {
        this.reportType = reportEvent.getReportType();
        this.subjectType = reportEvent.getSubjectType();
        this.subjectId = reportEvent.getSubjectId();
        this.userId = reportEvent.getUserId();
        this.text = reportEvent.getText();
        this.status = ReportHandleStatus.TO_REVIEW;
        this.createTime = TimeUtils.now();
    }
}
