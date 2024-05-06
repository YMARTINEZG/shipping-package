package me.sigom.shipping.infrastructure.mapper;


import me.sigom.common.events.order.OrderEvent;
import me.sigom.common.events.shipping.ShippingEvent;
import me.sigom.shipping.core.dto.ScheduleRequest;
import me.sigom.shipping.core.dto.ShipmentDto;

import java.time.Instant;

public class MessageDtoMapper {

    public static ScheduleRequest toScheduleRequest(OrderEvent.OrderCreated event) {
        return ScheduleRequest.builder()
                              .customerId(event.customerId())
                              .productId(event.productId())
//                              .quantity(event.quantity())
                              .orderId(event.orderId())
                              .build();
    }

    public static ShippingEvent toShippingScheduledEvent(ShipmentDto dto) {
        return ShippingEvent.ShippingScheduled.builder()
                                              .shipmentId(dto.shipmentId())
                                              .orderId(dto.orderId())
                                              .createdAt(Instant.now())
                                              .expectedDelivery(dto.expectedDelivery())
                                              .build();
    }

}

