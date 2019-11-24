package com.wuisee.hotel.repository;

import com.wuisee.hotel.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    OrderDetail findOrderDetailByOrderId(String orderId);
}
