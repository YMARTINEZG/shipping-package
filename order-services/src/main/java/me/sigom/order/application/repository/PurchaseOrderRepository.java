package me.sigom.order.application.repository;

import me.sigom.common.events.order.OrderStatus;
import me.sigom.order.application.domain.PurchaseOrder;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface PurchaseOrderRepository extends ReactiveCrudRepository<PurchaseOrder, UUID> {

    Mono<PurchaseOrder> findByOrderIdAndStatus(UUID orderId, OrderStatus status);

    @Query("""
                SELECT po.*
                FROM   purchase_order po
                WHERE po.status = 'PENDING'
                       AND po.order_id = :orderId
            """)
    Mono<PurchaseOrder> getWhenOrderComponentsCompleted(UUID orderId);

}
