package ru.opgmap.reports.configs.kafka.model;

import org.springframework.util.concurrent.ListenableFuture;

public interface EventChannel<E> {
    ListenableFuture send(E event);
}
