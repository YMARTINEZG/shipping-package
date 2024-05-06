package me.sigom.order.core.dto;

import lombok.Builder;
import me.sigom.common.events.order.OrderStatus;

@Builder
public record OrderItem(String id,
                        String detail,
                        String carrier,
                        String parcel,
                        OrderStatus status) {
}
