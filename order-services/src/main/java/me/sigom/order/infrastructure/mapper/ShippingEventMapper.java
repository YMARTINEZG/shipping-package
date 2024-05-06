package me.sigom.order.infrastructure.mapper;


import me.sigom.common.events.shipping.ShippingEvent;
import me.sigom.order.core.dto.OrderShipmentSchedule;

public class ShippingEventMapper {

    public static OrderShipmentSchedule toDto(ShippingEvent.ShippingScheduled event) {
        return OrderShipmentSchedule.builder()
                                       .orderId(event.orderId())
                                       .deliveryDate(event.expectedDelivery())
                                       .build();
    }

}
