package me.sigom.shipping.core.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ScheduleRequest(UUID orderId,
                              String productId,
                              String customerId) {
}
