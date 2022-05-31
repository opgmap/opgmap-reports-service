package ru.opgmap.reports.models.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.opgmap.reports.models.enums.ReportHandleStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "reports_history")
@NoArgsConstructor
public class ReportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "before_status")
    private ReportHandleStatus beforeStatus;

    @Column(name = "after_status")
    private ReportHandleStatus afterStatus;

    @Column(name = "report_id")
    private UUID reportId;

    @Column(name = "create_time")
    private LocalDateTime createTime;
}
