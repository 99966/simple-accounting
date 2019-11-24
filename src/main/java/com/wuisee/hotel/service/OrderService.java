package com.wuisee.hotel.service;

import com.wuisee.hotel.dto.OrderDTO;
import org.springframework.data.domain.Page;


public interface OrderService {

    //创建订单

    OrderDTO create(OrderDTO orderDTO);

    //查询单个订单详情
    OrderDTO findByOrderId(String orderId);

    //查询订单列表(分页)
    Page<OrderDTO> findList(Integer page, Integer limit);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //完结订单
    OrderDTO finish(OrderDTO orderDTO);
}
