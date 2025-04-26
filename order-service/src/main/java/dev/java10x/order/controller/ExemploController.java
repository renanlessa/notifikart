package dev.java10x.order.controller;

import dev.java10x.order.service.KafkaProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class ExemploController {

    private final KafkaProducerService kafkaProducerService;

    public ExemploController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/send")
    public String sendMessage() {
        kafkaProducerService.send("Mensagem de teste do order-service");
        return "Mensagem enviada!";
    }
}
