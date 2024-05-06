package me.sigom.shipping.core.service;

import me.sigom.shipping.core.dto.ScheduleRequest;
import me.sigom.shipping.core.dto.ShipmentDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ShippingService {

    Mono<Void> addShipment(ScheduleRequest request);

    Mono<Void> cancel(UUID orderId);

    Mono<ShipmentDto> schedule(UUID orderId);

}

