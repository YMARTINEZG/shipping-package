package me.sigom.common.events.order;

import lombok.Builder;
import me.sigom.common.events.DomainEvent;
import me.sigom.common.events.OrderSaga;

import java.time.Instant;
import java.util.UUID;

public sealed interface OrderEvent extends DomainEvent, OrderSaga {

    @Builder
    record OrderCreated(UUID orderId,
                        String productId,
                        String customerId,
//                        Integer quantity,
//                        Integer unitPrice,
//                        Integer totalAmount,
                        Instant createdAt) implements OrderEvent {
    }

    @Builder
    record OrderCancelled(UUID orderId,
                          Instant createdAt) implements OrderEvent {
    }

    @Builder
    record OrderCompleted(UUID orderId,
                          Instant createdAt) implements OrderEvent {
    }

}
