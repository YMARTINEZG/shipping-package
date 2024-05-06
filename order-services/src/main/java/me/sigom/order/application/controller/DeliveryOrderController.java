package me.sigom.order.application.controller;

import lombok.RequiredArgsConstructor;
import me.sigom.order.core.dto.OrderCreateRequest;
import me.sigom.order.core.dto.OrderDetails;
import me.sigom.order.core.dto.PurchaseOrderDto;
import me.sigom.order.core.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class DeliveryOrderController {

    private final OrderService service;

    @PostMapping
    public Mono<ResponseEntity<PurchaseOrderDto>> placeOrder(@RequestBody Mono<OrderCreateRequest> mono) {
        return mono.flatMap(this.service::placeOrder)
                .map(ResponseEntity.accepted()::body);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public Mono<OrderDetails> getAllOrders(){
        return this.service.getAllOrders();
    }

    @GetMapping("{orderId}")
    public Mono<OrderDetails> getOrderDetails(@PathVariable UUID orderId){
        return this.service.getOrderDetails(orderId);
    }
}
