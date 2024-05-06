package me.sigom.order.core.dto;

import lombok.Builder;
import me.sigom.common.events.order.OrderStatus;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record PurchaseOrderDto(UUID orderId,
                               String customerId,
                               String productId,
                               OrderStatus status,
                               ZonedDateTime deliveryDate) {
}
