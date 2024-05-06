package me.sigom.order.core.service.shipping;

import me.sigom.order.core.dto.OrderShipmentSchedule;
import me.sigom.order.core.service.OrderComponentStatusListener;

public interface ShippingComponentStatusListener extends OrderComponentStatusListener<OrderShipmentSchedule> {
}
