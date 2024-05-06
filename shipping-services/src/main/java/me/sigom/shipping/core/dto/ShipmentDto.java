package me.sigom.shipping.core.dto;

import me.sigom.common.events.shipping.ShippingStatus;
import lombok.Builder;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record ShipmentDto(UUID shipmentId,
                          UUID orderId,
                          String productId,
                          String customerId,
//                          Integer quantity,
                          Instant expectedDelivery,
                          ShippingStatus status) {
}
