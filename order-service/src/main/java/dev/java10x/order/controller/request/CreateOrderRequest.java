package dev.java10x.order.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CreateOrderRequest {

    private String customerId;
    private String basketId;
    private BigDecimal amount;
    private BigDecimal shippingCost;

}
