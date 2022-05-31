package ru.opgmap.reports.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.opgmap.reports.models.dao.Report;
import ru.opgmap.reports.models.dto.events.ReportEvent;

import java.util.UUID;

public interface ReportsService {

    Report addReport(ReportEvent model);

    Page<Report> getAllReports(final Pageable pageable);

    Report findById(UUID reportId);

    void deleteReport(UUID reportId);

    Page<Report> getAllReportsByUserId(UUID userId, Pageable pageable);

    Report update(Report report);
}
