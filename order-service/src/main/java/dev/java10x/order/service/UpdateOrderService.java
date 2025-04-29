package dev.java10x.order.service;

import dev.java10x.order.entity.Order;
import dev.java10x.order.entity.OrderEvent;
import dev.java10x.order.entity.OrderStatus;
import dev.java10x.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateOrderService {

    private final OrderRepository repository;
    private final OrderStateService orderStateService;

    public UpdateOrderService(OrderRepository repository, OrderStateService orderStateService) {
        this.repository = repository;
        this.orderStateService = orderStateService;
    }

    public Order execute(String orderId, OrderEvent event) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        OrderStatus orderStatus = orderStateService.processEvent(order.getStatus(), event);

        order.setStatus(orderStatus);
        order.setUpdatedAt(LocalDateTime.now());

        return repository.save(order);
    }
}
