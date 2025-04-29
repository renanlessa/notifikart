package dev.java10x.order.message;

import dev.java10x.order.entity.OrderEvent;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {

    private String orderId;
    private String message;
    private OrderEvent event;

}
