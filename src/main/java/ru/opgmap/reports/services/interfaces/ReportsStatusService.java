package ru.opgmap.reports.services.interfaces;

import ru.opgmap.reports.models.dao.Report;
import ru.opgmap.reports.models.enums.ReportHandleStatus;

import java.util.UUID;

public interface ReportsStatusService {

    Report changeReportStatus(UUID reportId, ReportHandleStatus reportStatus, UUID userId);

}
