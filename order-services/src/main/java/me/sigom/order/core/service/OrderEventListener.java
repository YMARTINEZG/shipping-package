package me.sigom.order.core.service;


import me.sigom.order.core.dto.PurchaseOrderDto;

public interface OrderEventListener {

    void emitOrderCreated(PurchaseOrderDto dto);

}
