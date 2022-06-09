package ru.opgmap.reports.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.opgmap.reports.models.dao.Report;
import ru.opgmap.reports.models.dao.ReportHistory;
import ru.opgmap.reports.models.enums.ReportHandleStatus;
import ru.opgmap.reports.repositories.ReportsHistoryRepository;
import ru.opgmap.reports.services.interfaces.ReportsHistoryService;
import ru.opgmap.reports.utils.TimeUtils;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ReportsHistoryServiceImpl implements ReportsHistoryService {

    private final ReportsHistoryRepository reportsHistoryRepository;

    @Override
    public ReportHistory addHistory(Report report, ReportHandleStatus newStatus, UUID userId) {

        ReportHistory reportHistory = new ReportHistory();
        reportHistory.setReportId(report.getId());
        reportHistory.setCreateTime(TimeUtils.now());
        reportHistory.setUserId(userId);
        reportHistory.setBeforeStatus(report.getStatus());
        reportHistory.setAfterStatus(newStatus);

        return reportsHistoryRepository.save(reportHistory);
    }


    @Override
    public Page<ReportHistory> getAllReportHistoryByReportId(UUID reportId, Pageable pageable) {
        return reportsHistoryRepository.findAllByReportId(reportId, pageable);
    }
}
