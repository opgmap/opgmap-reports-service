package ru.opgmap.reports.models.dto.events;

import lombok.Data;
import ru.opgmap.reports.configs.kafka.model.KafkaEvent;
import ru.opgmap.reports.models.enums.ReportType;
import ru.opgmap.reports.models.enums.SubjectType;

import java.util.UUID;

@Data
public class ReportEvent implements KafkaEvent {

    private UUID userId;

    private ReportType reportType;


    private SubjectType subjectType;


    private UUID subjectId;


    private String text;
}
