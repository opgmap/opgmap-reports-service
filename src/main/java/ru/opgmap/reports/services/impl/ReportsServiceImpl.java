package ru.opgmap.reports.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.opgmap.reports.models.dao.Report;
import ru.opgmap.reports.models.dto.events.ReportEvent;
import ru.opgmap.reports.repositories.ReportsRepository;
import ru.opgmap.reports.services.interfaces.ReportsService;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReportsServiceImpl implements ReportsService {


    private ReportsRepository reportsRepository;

    @Override
    public Report addReport(ReportEvent model) {
        final boolean isExists = reportsRepository
                .existsReport(
                        model.getReportType(),
                        model.getSubjectId(),
                        model.getUserId(),
                        model.getSubjectType(),
                        model.getText());
        if (isExists) {
            throw new IllegalArgumentException("Report exist!!!");
        } else {
            return reportsRepository.save(new Report(model));
        }
    }

    @Override
    public Page<Report> getAllReports(final Pageable pageable) {
        return reportsRepository.findAll(pageable);
    }

    @Override
    public Report findById(UUID reportId) {
        return reportsRepository.findById(reportId).orElseThrow(() -> {
            throw new EntityNotFoundException("Report with id: " + reportId + " not found");
        });
    }

    @Override
    public void deleteReport(UUID reportId) {
        reportsRepository.deleteById(reportId);
    }

    @Override
    public Page<Report> getAllReportsByUserId(UUID userId, Pageable pageable) {
        return reportsRepository.getAllByUserId(userId, pageable);
    }

    @Override
    public Report update(Report report) {
        return reportsRepository.save(report);
    }
}
