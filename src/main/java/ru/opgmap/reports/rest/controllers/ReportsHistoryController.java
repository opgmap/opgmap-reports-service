package ru.opgmap.reports.rest.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.opgmap.reports.models.dao.ReportHistory;
import ru.opgmap.reports.services.interfaces.ReportsHistoryService;
import ru.opgmap.reports.utils.PageableUtils;

import java.util.UUID;

import static ru.opgmap.reports.rest.ApiPath.REPORTS;
import static ru.opgmap.reports.rest.ApiPath.REPORT_ID_HISTORY_PATH;

@RestController
@CrossOrigin("*")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@SecurityRequirement(name = "security_auth")
@RequestMapping(REPORTS)
@AllArgsConstructor
public class ReportsHistoryController {

    private final ReportsHistoryService reportsHistoryService;
    private final PagedResourcesAssembler<ReportHistory> pagedResourcesAssembler;

    @GetMapping(REPORT_ID_HISTORY_PATH)
    public PagedModel<?> getAllReportHistory(@RequestParam(defaultValue = "0") int cursor,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "id") String sortBy,
                                                  @RequestParam(defaultValue = "0") int asc,
                                                  @PathVariable UUID reportId) {

        Pageable pageable = PageableUtils
                .getPageable(cursor, size, sortBy, asc);

        return pagedResourcesAssembler.toModel(reportsHistoryService
                .getAllReportHistoryByReportId(reportId, pageable));
    }
}
