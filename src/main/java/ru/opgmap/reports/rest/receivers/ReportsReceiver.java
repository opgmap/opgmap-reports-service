package ru.opgmap.reports.rest.receivers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.opgmap.reports.configs.kafka.annotations.JsonKafkaListener;
import ru.opgmap.reports.models.dto.events.ReportEvent;
import ru.opgmap.reports.services.interfaces.ReportsService;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ReportsReceiver {

    private final ReportsService reportsService;

    @JsonKafkaListener(topics = "${spring.kafka.topic.addReport}")
    public void saveReport(ReportEvent reportEvent) {
        if (Objects.isNull(reportEvent)) return;
        reportsService.addReport(reportEvent);
    }
}
