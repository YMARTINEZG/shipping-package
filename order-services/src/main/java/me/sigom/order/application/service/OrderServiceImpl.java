package me.sigom.order.application.service;

import lombok.RequiredArgsConstructor;
import me.sigom.order.application.mapper.EntityDtoMapper;
import me.sigom.order.application.repository.PurchaseOrderRepository;
import me.sigom.order.core.dto.OrderCreateRequest;
import me.sigom.order.core.dto.OrderDetails;
import me.sigom.order.core.dto.PurchaseOrderDto;
import me.sigom.order.core.service.OrderEventListener;
import me.sigom.order.core.service.OrderService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final PurchaseOrderRepository repository;
    private final OrderEventListener eventListener;
    @Override
    public Mono<PurchaseOrderDto> placeOrder(OrderCreateRequest request) {
        var entity = EntityDtoMapper.toPurchaseOrder(request);

        return this.repository.save(entity)
                .map(EntityDtoMapper::toPurchaseOrderDto)
                .doOnNext(eventListener::emitOrderCreated);
    }

    @Override
    public Mono<OrderDetails> getAllOrders() {
        return this.repository.findAll().map(EntityDtoMapper::toOrderItem).collectList().map(OrderDetails::new);
    }

    @Override
    public Mono<OrderDetails> getOrderDetails(UUID orderId) {
        return null;
    }
}
