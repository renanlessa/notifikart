package dev.java10x.order.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "order")
public class Order {

    @Id
    private String id;

    private String customerId;

    // a basket contem os produtos
    private String basketId;

    private BigDecimal amount;

    private BigDecimal shippingCost;

    private OrderStatus status;

    private PaymentMethod paymentMethod;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BigDecimal totalAmount() {
        return amount.add(shippingCost);
    }
}
