package ru.opgmap.reports.configs.kafka.annotations;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class EnableKafkaSelector implements ImportSelector {
    public EnableKafkaSelector() {
    }

    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"ru.opgmap.reports.configs.kafka.ConfigForJsonEvents"};
    }
}
