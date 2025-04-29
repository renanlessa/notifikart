package dev.java10x.order.mapper;

import dev.java10x.order.controller.request.CreateOrderRequest;
import dev.java10x.order.controller.response.OrderResponse;
import dev.java10x.order.entity.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderMapper {

    public static Order toOrder(CreateOrderRequest request) {
        return Order.builder()
                .customerId(request.getCustomerId())
                .basketId(request.getBasketId())
                .amount(request.getAmount())
                .shippingCost(request.getShippingCost())
                .build();
    }

    public static OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .basketId(order.getBasketId())
                .totalAmount(order.totalAmount())
                .status(order.getStatus())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }
}
