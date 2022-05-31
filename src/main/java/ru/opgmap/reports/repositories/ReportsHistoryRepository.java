package ru.opgmap.reports.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.opgmap.reports.models.dao.ReportHistory;

import java.util.UUID;

@Repository
public interface ReportsHistoryRepository extends JpaRepository<ReportHistory, UUID> {
}
