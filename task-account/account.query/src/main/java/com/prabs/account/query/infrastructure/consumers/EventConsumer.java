package com.prabs.account.query.infrastructure.consumers;

import com.prabs.account.common.events.AccountClosedEvent;
import com.prabs.account.common.events.AccountOpenedEvent;
import com.prabs.account.common.events.TaskAddedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);
    void consume(@Payload TaskAddedEvent event, Acknowledgment ack);
    void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
