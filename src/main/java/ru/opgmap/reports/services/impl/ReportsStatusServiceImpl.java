package ru.opgmap.reports.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.opgmap.reports.models.dao.Report;
import ru.opgmap.reports.models.enums.ReportHandleStatus;
import ru.opgmap.reports.services.interfaces.ReportsHistoryService;
import ru.opgmap.reports.services.interfaces.ReportsService;
import ru.opgmap.reports.services.interfaces.ReportsStatusService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ReportsStatusServiceImpl implements ReportsStatusService {

    private final ReportsService reportsService;
    private final ReportsHistoryService reportsHistoryService;

    @Override
    public Report changeReportStatus(UUID reportId, ReportHandleStatus reportStatus, UUID userId) {
        Report report = reportsService.findById(reportId);

        reportsHistoryService.addHistory(report, reportStatus, userId);
        report.setStatus(reportStatus);
        return reportsService.update(report);
    }
}
