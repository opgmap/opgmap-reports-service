package ru.opgmap.reports.rest.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.opgmap.reports.rest.ApiPath;

import static ru.opgmap.reports.rest.ApiPath.REPORTS;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "security_auth")
@RequestMapping(REPORTS)
public class ReportsHistoryController {
}
