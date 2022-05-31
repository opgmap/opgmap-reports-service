package ru.opgmap.reports.rest.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.opgmap.reports.models.dao.Report;
import ru.opgmap.reports.services.interfaces.ReportsService;
import ru.opgmap.reports.utils.PageableUtils;

import java.security.Principal;
import java.util.UUID;

import static ru.opgmap.reports.rest.ApiPath.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@SecurityRequirement(name = "security_auth")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping(REPORTS)
public class ReportsAdminController {

    private final ReportsService reportsService;

    @GetMapping(USER_ID_PATH)
    Page<Report> getAllReportsByUser(@RequestParam(defaultValue = "0") int cursor,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy,
                                     @RequestParam(defaultValue = "0") int asc,
                                     @PathVariable UUID userId) {

        Pageable pageable = PageableUtils
                .getPageable(cursor, size, sortBy, asc);

        return reportsService.getAllReportsByUserId(userId, pageable);
    }

    @GetMapping(ROOT_PATH)
    Page<Report> getAllReports(@RequestParam(defaultValue = "0") int cursor,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy,
                               @RequestParam(defaultValue = "0") int asc) {

        Pageable pageable = PageableUtils
                .getPageable(cursor, size, sortBy, asc);

        return reportsService.getAllReports(pageable);
    }

    @GetMapping(REPORT_ID)
    Report getReport(@PathVariable UUID reportId) {
        return reportsService.findById(reportId);
    }

    @DeleteMapping(REPORT_ID)
    ResponseEntity<HttpStatus> deleteReport(@PathVariable UUID reportId) {
        reportsService.deleteReport(reportId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(RESPONSE_TO_REPORT_PATH)
    Report responseToReport(@PathVariable UUID reportId, Principal principal) {
        return new Report();
    }
}
