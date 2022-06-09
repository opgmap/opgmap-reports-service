package ru.opgmap.reports.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.opgmap.reports.models.dao.Report;
import ru.opgmap.reports.models.dao.ReportHistory;
import ru.opgmap.reports.models.enums.ReportHandleStatus;

import java.util.UUID;

public interface ReportsHistoryService {

    ReportHistory addHistory(Report report, ReportHandleStatus newStatus, UUID userId);

    Page<ReportHistory> getAllReportHistoryByReportId(UUID reportId, Pageable pageable);
}
