package me.sigom.shipping.application.service;

import lombok.RequiredArgsConstructor;
import me.sigom.common.events.shipping.ShippingStatus;
import me.sigom.common.util.DuplicateEventValidator;
import me.sigom.shipping.application.mapper.EntityDtoMapper;
import me.sigom.shipping.application.repository.ShipmentRepository;
import me.sigom.shipping.core.dto.ScheduleRequest;
import me.sigom.shipping.core.dto.ShipmentDto;
import me.sigom.shipping.application.entity.Shipment;
import me.sigom.shipping.core.service.ShippingService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {

    private final ShipmentRepository repository;

    @Override
    public Mono<Void> addShipment(ScheduleRequest request) {
        return DuplicateEventValidator.validate(
                this.repository.existsByOrderId(request.orderId()),
                Mono.defer(() -> this.add(request))
        );
    }

    private Mono<Void> add(ScheduleRequest request) {
        var shipment = EntityDtoMapper.toShipment(request);
        shipment.setStatus(ShippingStatus.PENDING);
        return this.repository.save(shipment)
                              .then();
    }

    @Override
    public Mono<Void> cancel(UUID orderId) {
        return this.repository.deleteByOrderId(orderId);
    }

    @Override
    public Mono<ShipmentDto> schedule(UUID orderId) {
        return this.repository.findByOrderIdAndStatus(orderId, ShippingStatus.PENDING)
                              .flatMap(this::schedule);
    }

    private Mono<ShipmentDto> schedule(Shipment shipment) {
        shipment.setDeliveryDate(Instant.now().plus(Duration.ofDays(3)));
        shipment.setStatus(ShippingStatus.SCHEDULED);
        return this.repository.save(shipment)
                              .map(EntityDtoMapper::toDto);
    }

}
