package me.sigom.order.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Mono;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDetails {
    private List<OrderItem> orders;
}
