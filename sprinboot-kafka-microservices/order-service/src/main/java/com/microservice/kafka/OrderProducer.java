package com.microservice.kafka;

import com.microservice.basedomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    private NewTopic topic;

    private KafkaTemplate<String, OrderEvent> orderEvent;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> orderEvent) {
        this.topic = topic;
        this.orderEvent = orderEvent;
    }

    public void sendMessage(OrderEvent event){
        LOGGER.info(String.format("Order event => "+event.toString()));

        Message<OrderEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        orderEvent.send(message);
    }
}
