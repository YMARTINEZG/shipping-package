package me.sigom.order.infrastructure.config;

import me.sigom.common.events.DomainEvent;
import me.sigom.common.events.order.OrderEvent;
import me.sigom.common.processor.EventProcessor;
import me.sigom.common.util.MessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public abstract class AbstractOrderEventRouterConfig {

    private static final Logger log = LoggerFactory.getLogger(AbstractOrderEventRouterConfig.class);
    private static final String DESTINATION_HEADER = "spring.cloud.stream.sendto.destination";
    private static final String ORDER_EVENTS_CHANNEL = "order-events-channel";

    protected <T extends DomainEvent> Function<Flux<Message<T>>, Flux<Message<OrderEvent>>> processor(EventProcessor<T, OrderEvent> eventProcessor) {
        return flux -> flux.map(MessageConverter::toRecord)
                           .doOnNext(r -> log.info("order service received {}", r.message()))
                           .concatMap(r -> eventProcessor.process(r.message())
                                                              .doOnSuccess(e -> r.acknowledgement().acknowledge())
                           )
                           .map(this::toMessage);
    }

    protected Message<OrderEvent> toMessage(OrderEvent event) {
        log.info("order service produced {}", event);
        return MessageBuilder.withPayload(event)
                             .setHeader(KafkaHeaders.KEY, event.orderId().toString())
                             .setHeader(DESTINATION_HEADER, ORDER_EVENTS_CHANNEL)
                             .build();
    }
    
}
