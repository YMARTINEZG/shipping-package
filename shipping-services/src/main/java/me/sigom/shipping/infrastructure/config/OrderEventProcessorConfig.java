package me.sigom.shipping.infrastructure.config;


import lombok.RequiredArgsConstructor;
import me.sigom.common.events.order.OrderEvent;
import me.sigom.common.events.shipping.ShippingEvent;
import me.sigom.common.processor.OrderEventProcessor;
import me.sigom.common.util.MessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class OrderEventProcessorConfig {

    private static final Logger log = LoggerFactory.getLogger(OrderEventProcessorConfig.class);
    private final OrderEventProcessor<ShippingEvent> eventProcessor;

    @Bean
    public Function<Flux<Message<OrderEvent>>, Flux<Message<ShippingEvent>>> processor() {
        return flux -> flux.map(MessageConverter::toRecord)
                           .doOnNext(r -> log.info("shipping service received {}", r.message()))
                           .concatMap(r -> this.eventProcessor.process(r.message())
                                                              .doOnSuccess(e -> r.acknowledgement().acknowledge())
                           )
                           .map(this::toMessage);
    }

    private Message<ShippingEvent> toMessage(ShippingEvent event) {
        return MessageBuilder.withPayload(event)
                             .setHeader(KafkaHeaders.KEY, event.orderId().toString())
                             .build();
    }

}
