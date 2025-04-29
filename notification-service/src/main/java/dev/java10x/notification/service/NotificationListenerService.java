package dev.java10x.notification.service;

import dev.java10x.notification.message.NotificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationListenerService {

    @KafkaListener(topics = "${kafka.topic}")
    public void consumeMessage(NotificationMessage notificationMesssage) {
        log.info("Mensagem recebida: {}", notificationMesssage);
    }
}
