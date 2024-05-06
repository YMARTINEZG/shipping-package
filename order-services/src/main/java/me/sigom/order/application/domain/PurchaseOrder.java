package me.sigom.order.application.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.sigom.common.events.order.OrderStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;


import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    @Id
    private UUID orderId;
    private String customerId;
    private String productId;
    private OrderStatus status;
    private ZonedDateTime deliveryDate;
    @Version
    private long version;
}
