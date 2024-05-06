package me.sigom.common.events;

import java.time.Instant;

public interface DomainEvent {

    Instant createdAt();

}
