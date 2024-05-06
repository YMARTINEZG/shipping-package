package me.sigom.order.infrastructure.publisher;


import lombok.RequiredArgsConstructor;
import me.sigom.common.events.order.OrderEvent;
import me.sigom.common.publisher.EventPublisher;
import me.sigom.order.core.dto.PurchaseOrderDto;
import me.sigom.order.core.service.OrderEventListener;
import me.sigom.order.infrastructure.mapper.OrderEventMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

//@Service
@RequiredArgsConstructor
public class OrderEventListenerImpl implements OrderEventListener, EventPublisher<OrderEvent> {

    private final Sinks.Many<OrderEvent> sink;
    private final Flux<OrderEvent> flux;

    @Override
    public Flux<OrderEvent> publish() {
        return this.flux;
    }

    @Override
    public void emitOrderCreated(PurchaseOrderDto dto) {
        var event = OrderEventMapper.toOrderCreatedEvent(dto);
        this.sink.emitNext(
                event,
                Sinks.EmitFailureHandler.busyLooping(Duration.ofSeconds(1))
        );
    }

}
