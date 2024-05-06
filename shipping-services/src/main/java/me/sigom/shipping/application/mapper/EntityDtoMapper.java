package me.sigom.shipping.application.mapper;


import me.sigom.shipping.application.entity.Shipment;
import me.sigom.shipping.core.dto.ScheduleRequest;
import me.sigom.shipping.core.dto.ShipmentDto;

public class EntityDtoMapper {

    public static Shipment toShipment(ScheduleRequest request) {
        return Shipment.builder()
                       .customerId(request.customerId())
                       .orderId(request.orderId())
                       .productId(request.productId())
//                       .quantity(request.quantity())
                       .build();
    }

    public static ShipmentDto toDto(Shipment shipment) {
        return ShipmentDto.builder()
                          .shipmentId(shipment.getId())
                          .customerId(shipment.getCustomerId())
//                          .quantity(shipment.getQuantity())
                          .productId(shipment.getProductId())
                          .orderId(shipment.getOrderId())
                          .status(shipment.getStatus())
                          .expectedDelivery(shipment.getDeliveryDate())
                          .build();
    }

}
