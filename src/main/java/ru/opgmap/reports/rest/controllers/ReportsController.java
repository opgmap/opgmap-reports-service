package ru.opgmap.reports.rest.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.opgmap.reports.configs.kafka.service.JsonKafkaTemplate;
import ru.opgmap.reports.models.dto.ReportCreateUpdateDto;
import ru.opgmap.reports.models.dto.events.ReportEvent;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

import static ru.opgmap.reports.rest.ApiPath.*;

@RestController
@RequestMapping(REPORTS)
@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = "security_auth")
@CrossOrigin("*")
public class ReportsController {

    @Value(value = "${spring.kafka.topic.addReport}")
    private String reportTopic;

    private final ModelMapper modelMapper;
    private final JsonKafkaTemplate kafkaTemplate;

    public ReportsController(ModelMapper modelMapper, JsonKafkaTemplate kafkaTemplate) {
        this.modelMapper = modelMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(ROOT_PATH)
    ResponseEntity<HttpStatus> addReport(@Valid @RequestBody ReportCreateUpdateDto model, Principal principal){
        ReportEvent reportEvent = modelMapper.map(model, ReportEvent.class);
        reportEvent.setUserId(UUID.fromString(principal.getName()));
        kafkaTemplate.send(reportTopic, reportEvent);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
