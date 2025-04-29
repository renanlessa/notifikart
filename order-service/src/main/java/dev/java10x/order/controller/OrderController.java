package dev.java10x.order.controller;

import dev.java10x.order.controller.request.CreateOrderRequest;
import dev.java10x.order.controller.response.OrderResponse;
import dev.java10x.order.entity.Order;
import dev.java10x.order.mapper.OrderMapper;
import dev.java10x.order.service.CreateOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    private final CreateOrderService createOrderService;

    public OrderController(CreateOrderService createOrderService) {
        this.createOrderService = createOrderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@RequestBody CreateOrderRequest request) {
        log.info("Creating order with request: {}", request);
        Order savedOrder = createOrderService.execute(OrderMapper.toOrder(request));
        return OrderMapper.toOrderResponse(savedOrder);
    }

    @PutMapping
    public void update(@RequestBody CreateOrderRequest request) {

    }
}
