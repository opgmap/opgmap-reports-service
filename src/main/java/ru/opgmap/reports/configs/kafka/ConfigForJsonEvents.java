package ru.opgmap.reports.configs.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;
import ru.opgmap.reports.configs.kafka.model.KafkaEvent;
import ru.opgmap.reports.configs.kafka.service.JsonKafkaTemplate;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigForJsonEvents {
    private static final long ATTEMPT_TIME_INTERVAL = 2L;
    private static final long MAX_RETRY_ATTEMPTS = 2L;
    @Value("${spring.kafka.producer.batch-delay}")
    private String kafkaBatchDelay;
    @Value("${spring.kafka.producer.batch-size}")
    private String kafkaBatchSize;
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    @Value("${spring.kafka.request-timeout-ms}")
    public String requestTimeoutMs;
    @Value("${spring.kafka.reply-timeout-ms}")
    private Long replyTimeoutMs;
    @Value("${spring.application.name}")
    private String serviceName;

    public ConfigForJsonEvents() {
    }

    @Bean
    public ProducerFactory<String, KafkaEvent> jsonProducerFactory() {
        Map<String, Object> configProps = new HashMap();
        configProps.put("bootstrap.servers", this.bootstrapServers);
        configProps.put("request.timeout.ms", this.requestTimeoutMs);
        configProps.put("key.serializer", StringSerializer.class);
        configProps.put("value.serializer", JsonSerializer.class);
        configProps.put("linger.ms", this.kafkaBatchDelay);
        configProps.put("batch.size", Integer.parseInt(this.kafkaBatchSize));
        configProps.put("client.id", this.serviceName);
        return new DefaultKafkaProducerFactory(configProps);
    }

    @Bean
    public JsonKafkaTemplate jsonKafkaTemplate() {
        return new JsonKafkaTemplate(this.jsonProducerFactory());
    }

    @Bean
    public ConsumerFactory<String, KafkaEvent> jsonConsumerFactory() {
        Map<String, Object> props = new HashMap();
        props.put("bootstrap.servers", this.bootstrapServers);
        props.put("group.id", this.groupId);
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", 100);
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", JsonDeserializer.class);
        props.put("spring.json.trusted.packages", "*");
        return new DefaultKafkaConsumerFactory(props);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, KafkaEvent>> jsonKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafkaEvent> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(this.jsonConsumerFactory());
        factory.setErrorHandler(new SeekToCurrentErrorHandler(new FixedBackOff(2L, 2L)));
        return factory;
    }
}
