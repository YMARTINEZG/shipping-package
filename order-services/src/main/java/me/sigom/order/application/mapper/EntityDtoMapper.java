package me.sigom.order.application.mapper;

import me.sigom.common.events.order.OrderStatus;
import me.sigom.order.application.domain.PurchaseOrder;
import me.sigom.order.core.dto.OrderCreateRequest;
import me.sigom.order.core.dto.OrderDetails;
import me.sigom.order.core.dto.OrderItem;
import me.sigom.order.core.dto.PurchaseOrderDto;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class EntityDtoMapper {

    public static PurchaseOrder toPurchaseOrder(OrderCreateRequest request) {
        return PurchaseOrder.builder()
                            .status(OrderStatus.PENDING)
                            .customerId(request.customerId())
                            .productId(request.productId())
//                            .version(ThreadLocalRandom.current().nextLong(100))
                            .build();
    }

    public static PurchaseOrderDto toPurchaseOrderDto(PurchaseOrder purchaseOrder) {
        return PurchaseOrderDto.builder()
                               .orderId(purchaseOrder.getOrderId())
                               .customerId(purchaseOrder.getCustomerId())
                               .productId(purchaseOrder.getProductId())
                               .status(purchaseOrder.getStatus())
                               .deliveryDate(ZonedDateTime.now())
                               .build();
    }
    public static OrderItem toOrderItem(PurchaseOrder order){
        return OrderItem.builder()
                .id(order.getOrderId().toString())
                .detail(order.getCustomerId())
                .carrier(order.getProductId())
                .parcel("Boxes")
                .status(order.getStatus())
                .build();
    }

//    public static OrderDetails toOrderDetails(PurchaseOrderDto orderDto,
//                                              OrderPaymentDto paymentDto,
//                                              OrderInventoryDto inventoryDto) {
//        return OrderDetails.builder()
//                           .order(orderDto)
//                           .payment(paymentDto)
//                           .inventory(inventoryDto)
//                           .build();
//    }

}
