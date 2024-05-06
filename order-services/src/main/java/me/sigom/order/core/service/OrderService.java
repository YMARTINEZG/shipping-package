package me.sigom.order.core.service;

import me.sigom.order.core.dto.OrderCreateRequest;
import me.sigom.order.core.dto.OrderDetails;
import me.sigom.order.core.dto.PurchaseOrderDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface OrderService {
    Mono<PurchaseOrderDto> placeOrder(OrderCreateRequest request);

    Mono<OrderDetails> getAllOrders();

    Mono<OrderDetails> getOrderDetails(UUID orderId);
}
