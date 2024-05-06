package me.sigom.shipping.application.entity;

import me.sigom.common.events.shipping.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    @Id
    private UUID id;
    private UUID orderId;
    private String productId;
    private String customerId;
//    private Integer quantity;
    private Instant deliveryDate;
    private ShippingStatus status;

}
