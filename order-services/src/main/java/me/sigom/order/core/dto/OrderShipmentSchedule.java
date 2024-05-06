package me.sigom.order.core.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record OrderShipmentSchedule(UUID orderId,
                                    Instant deliveryDate) {
}
