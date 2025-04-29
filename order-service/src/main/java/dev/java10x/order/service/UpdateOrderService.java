package dev.java10x.order.service;

import dev.java10x.order.entity.Order;
import dev.java10x.order.entity.OrderEvent;
import dev.java10x.order.entity.OrderStatus;
import dev.java10x.order.message.NotificationMessage;
import dev.java10x.order.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class UpdateOrderService {

    private final OrderRepository repository;
    private final OrderStateService orderStateService;
    private final NotificationProducerService notificationProducerService;

    public UpdateOrderService(OrderRepository repository, OrderStateService orderStateService, NotificationProducerService notificationProducerService) {
        this.repository = repository;
        this.orderStateService = orderStateService;
        this.notificationProducerService = notificationProducerService;
    }

    @Transactional
    public Order execute(String orderId, OrderEvent event) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        OrderStatus orderStatus = orderStateService.processEvent(order.getStatus(), event);

        order.setStatus(orderStatus);
        order.setUpdatedAt(LocalDateTime.now());

        Order updatedOrder = repository.save(order);

        notificationProducerService.send(NotificationMessage.builder()
                .orderId(order.getId())
                .message("Order status updated to " + orderStatus)
                .event(event)
                .build());

        return updatedOrder;
    }
}
