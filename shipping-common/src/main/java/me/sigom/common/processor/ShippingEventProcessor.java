package me.sigom.common.processor;

import me.sigom.common.events.DomainEvent;
import me.sigom.common.events.shipping.ShippingEvent;
import me.sigom.common.util.MessageHandler;
import reactor.core.publisher.Mono;

public interface ShippingEventProcessor<R extends DomainEvent> extends EventProcessor<ShippingEvent, R> {

    @Override
    default Mono<R> process(ShippingEvent event) {
        return MessageHandler.<ShippingEvent, Mono<R>>create(event)
                .onMessage(ShippingEvent.ShippingScheduled.class, this::handle)
                .handle();
    }

    Mono<R> handle(ShippingEvent.ShippingScheduled event);

}
