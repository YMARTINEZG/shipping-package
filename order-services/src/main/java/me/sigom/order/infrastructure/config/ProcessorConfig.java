package me.sigom.order.infrastructure.config;

import lombok.RequiredArgsConstructor;
import me.sigom.common.publisher.EventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import me.sigom.common.events.order.OrderEvent;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
@RequiredArgsConstructor
public class ProcessorConfig extends AbstractOrderEventRouterConfig {

    private final EventPublisher<OrderEvent> eventPublisher;

    @Bean
    public Supplier<Flux<Message<OrderEvent>>> orderEventProducer() {
        return () -> this.eventPublisher.publish()
                                        .map(this::toMessage);
    }

}
