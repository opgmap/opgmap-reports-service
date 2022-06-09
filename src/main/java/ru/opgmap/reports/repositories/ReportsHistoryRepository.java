package ru.opgmap.reports.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.opgmap.reports.models.dao.ReportHistory;

import java.util.UUID;

@Repository
public interface ReportsHistoryRepository extends JpaRepository<ReportHistory, UUID> {
    Page<ReportHistory> findAllByReportId(UUID reportId, Pageable pageable);
}
