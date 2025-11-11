package com.prabs.account.query.infrastructure.consumers;

import com.prabs.account.common.events.AccountClosedEvent;
import com.prabs.account.common.events.AccountOpenedEvent;
import com.prabs.account.common.events.TaskAddedEvent;
import com.prabs.account.query.infrastructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class AccountEventConsumer implements EventConsumer {
    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload AccountOpenedEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "TaskAddedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload TaskAddedEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }


    @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload AccountClosedEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }
}
