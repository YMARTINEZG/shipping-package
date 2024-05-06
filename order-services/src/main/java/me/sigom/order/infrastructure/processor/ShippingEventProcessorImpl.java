package me.sigom.order.infrastructure.processor;

import me.sigom.common.processor.ShippingEventProcessor;
import me.sigom.order.infrastructure.mapper.ShippingEventMapper;
import lombok.RequiredArgsConstructor;
import me.sigom.common.events.order.OrderEvent;
import me.sigom.common.events.shipping.ShippingEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import me.sigom.order.core.service.shipping.ShippingComponentStatusListener;
@Service
@RequiredArgsConstructor
public class ShippingEventProcessorImpl implements ShippingEventProcessor<OrderEvent> {

    private final ShippingComponentStatusListener statusListener;

    @Override
    public Mono<OrderEvent> handle(ShippingEvent.ShippingScheduled event) {
        var dto = ShippingEventMapper.toDto(event);
        return this.statusListener.onSuccess(dto)
                .then(Mono.empty());
    }

}
