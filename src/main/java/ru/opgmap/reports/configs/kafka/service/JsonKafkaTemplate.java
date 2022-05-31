package ru.opgmap.reports.configs.kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import ru.opgmap.reports.configs.kafka.model.KafkaEvent;

public class JsonKafkaTemplate extends KafkaTemplate<String, KafkaEvent> {
    public JsonKafkaTemplate(ProducerFactory<String, KafkaEvent> producerFactory) {
        super(producerFactory);
    }
}
