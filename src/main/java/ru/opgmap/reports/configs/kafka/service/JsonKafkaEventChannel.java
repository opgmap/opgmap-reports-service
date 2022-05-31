package ru.opgmap.reports.configs.kafka.service;

import org.springframework.util.concurrent.ListenableFuture;
import ru.opgmap.reports.configs.kafka.model.EventChannel;
import ru.opgmap.reports.configs.kafka.model.KafkaEvent;

public class JsonKafkaEventChannel<E extends KafkaEvent> implements EventChannel<E> {

    private final String kafkaTopic;
    private final JsonKafkaTemplate kafkaTemplate;

    public JsonKafkaEventChannel(String kafkaTopic, JsonKafkaTemplate kafkaTemplate) {
        this.kafkaTopic = kafkaTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public ListenableFuture send(E event) {
        return this.kafkaTemplate.send(this.kafkaTopic, event);
    }
}
