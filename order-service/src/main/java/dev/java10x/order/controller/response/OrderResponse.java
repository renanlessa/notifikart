package dev.java10x.order.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.java10x.order.entity.OrderStatus;
import dev.java10x.order.entity.PaymentMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class OrderResponse {

    private String id;
    private String customerId;
    private String basketId;
    private BigDecimal totalAmount;
    private OrderStatus status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaymentMethod paymentMethod;
}
