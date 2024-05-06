package me.sigom.common.publisher;

import reactor.core.publisher.Flux;
import me.sigom.common.events.DomainEvent;
public interface EventPublisher<T extends DomainEvent> {

    Flux<T> publish();

}
