package dev.java10x.order.controller;

import dev.java10x.order.controller.request.CreateOrderRequest;
import dev.java10x.order.controller.request.UpdateOrderRequest;
import dev.java10x.order.controller.response.OrderResponse;
import dev.java10x.order.entity.Order;
import dev.java10x.order.mapper.OrderMapper;
import dev.java10x.order.service.CreateOrderService;
import dev.java10x.order.service.UpdateOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    private final CreateOrderService createOrderService;
    private final UpdateOrderService updateOrderService;

    public OrderController(CreateOrderService createOrderService, UpdateOrderService updateOrderService) {
        this.createOrderService = createOrderService;
        this.updateOrderService = updateOrderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@RequestBody CreateOrderRequest request) {
        log.info("Creating order with request: {}", request);
        Order savedOrder = createOrderService.execute(OrderMapper.toOrder(request));
        return OrderMapper.toOrderResponse(savedOrder);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse updateOrder(@RequestBody UpdateOrderRequest request) {
        log.info("Updating order with request: {}", request);
        Order savedOrder = updateOrderService.execute(request.getOrderId(), request.getOrderEvent());
        return OrderMapper.toOrderResponse(savedOrder);
    }
}
