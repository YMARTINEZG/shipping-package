package me.sigom.order.core.dto;

import lombok.Builder;

@Builder
public record OrderCreateRequest(String customerId,
                                 String productId) {
}
