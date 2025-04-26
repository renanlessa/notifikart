package dev.java10x.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consumeMessage(String message) {
        log.info("Mensagem recebida: {}", message);
    }
}
