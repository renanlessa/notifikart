package dev.java10x.notification.message;

import dev.java10x.notification.entity.OrderEvent;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {

    private String orderId;
    private String message;
    private OrderEvent event;

}
