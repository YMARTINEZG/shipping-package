package me.sigom.common.events.shipping;

import me.sigom.common.events.DomainEvent;
import me.sigom.common.events.OrderSaga;
import lombok.Builder;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

public sealed interface ShippingEvent extends DomainEvent, OrderSaga {

    @Builder
    record ShippingScheduled(UUID orderId,
                             UUID shipmentId,
                             Instant expectedDelivery,
                             Instant createdAt) implements ShippingEvent {
    }


}
