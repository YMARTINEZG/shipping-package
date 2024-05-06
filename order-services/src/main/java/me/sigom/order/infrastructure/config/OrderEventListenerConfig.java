package me.sigom.order.infrastructure.config;

import me.sigom.common.events.order.OrderEvent;
import me.sigom.order.core.service.OrderEventListener;
import me.sigom.order.infrastructure.publisher.OrderEventListenerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class OrderEventListenerConfig {

    @Bean
    public OrderEventListener orderEventListener(){
        var sink = Sinks.many().unicast().<OrderEvent>onBackpressureBuffer();
        var flux = sink.asFlux();
        return new OrderEventListenerImpl(sink, flux);
    }

}
