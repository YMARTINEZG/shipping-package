package me.sigom.shipping.infrastructure.processor;

import lombok.RequiredArgsConstructor;
import me.sigom.common.events.order.OrderEvent;
import me.sigom.common.events.shipping.ShippingEvent;
import me.sigom.common.exception.EventAlreadyProcessedException;
import me.sigom.common.processor.OrderEventProcessor;
import me.sigom.shipping.core.service.ShippingService;
import me.sigom.shipping.infrastructure.mapper.MessageDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class OrderEventProcessorImpl implements OrderEventProcessor<ShippingEvent> {

    private static final Logger log = LoggerFactory.getLogger(OrderEventProcessorImpl.class);
    private final ShippingService service;

    @Override
    public Mono<ShippingEvent> handle(OrderEvent.OrderCreated event) {
        return this.service.addShipment(MessageDtoMapper.toScheduleRequest(event))
                           .transform(exceptionHandler())
                           .then(Mono.empty());
    }

    @Override
    public Mono<ShippingEvent> handle(OrderEvent.OrderCancelled event) {
        return this.service.cancel(event.orderId())
                           .then(Mono.empty());
    }

    @Override
    public Mono<ShippingEvent> handle(OrderEvent.OrderCompleted event) {
        return this.service.schedule(event.orderId())
                           .map(MessageDtoMapper::toShippingScheduledEvent)
                           .doOnNext(e -> log.info("shipping scheduled {}", e));
    }

    private <T> UnaryOperator<Mono<T>> exceptionHandler() {
        return mono -> mono.onErrorResume(EventAlreadyProcessedException.class, ex -> Mono.empty())
                           .doOnError(ex -> log.error(ex.getMessage()));
    }

}
