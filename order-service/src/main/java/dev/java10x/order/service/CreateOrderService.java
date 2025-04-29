package dev.java10x.order.service;

import dev.java10x.order.entity.Order;
import dev.java10x.order.entity.OrderStatus;
import dev.java10x.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateOrderService {

    private final OrderRepository repository;

    public CreateOrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order execute(Order order) {
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        return repository.save(order);
    }
}
