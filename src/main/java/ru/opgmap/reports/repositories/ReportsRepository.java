package ru.opgmap.reports.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.opgmap.reports.models.dao.Report;
import ru.opgmap.reports.models.enums.ReportType;
import ru.opgmap.reports.models.enums.SubjectType;

import java.util.UUID;

@Repository
public interface ReportsRepository extends
        JpaRepository<Report, UUID>, PagingAndSortingRepository<Report, UUID> {
    Page<Report> getAllByUserId(UUID userId, Pageable pageable);

    @Query(value = "SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END" +
            " FROM Report AS r WHERE r.reportType = :type and r.subjectType = :subjectType" +
            " and r.subjectId = :subjectId and r.userId = :userId and r.text = :text")
    boolean existsReport(
            ReportType type,
            UUID subjectId,
            UUID userId,
            SubjectType subjectType,
            String text);
}
