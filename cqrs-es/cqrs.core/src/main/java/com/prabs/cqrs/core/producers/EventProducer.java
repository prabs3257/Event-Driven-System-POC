package com.prabs.cqrs.core.producers;

import com.prabs.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
