package dev.java10x.order.service;

import dev.java10x.order.message.NotificationMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducerService {

    @Value("${kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;

    public NotificationProducerService(KafkaTemplate<String, NotificationMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(NotificationMessage message) {
        kafkaTemplate.send(topic, message);
    }
}
