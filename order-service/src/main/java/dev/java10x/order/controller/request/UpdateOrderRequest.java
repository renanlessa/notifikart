package dev.java10x.order.controller.request;

import dev.java10x.order.entity.OrderEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateOrderRequest {

    private String orderId;
    private OrderEvent orderEvent;
}
