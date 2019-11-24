package com.wuisee.hotel.service.impl;

import com.wuisee.hotel.dataobject.OrderDetail;
import com.wuisee.hotel.dto.OrderDTO;
import com.wuisee.hotel.enums.PayStatusEnum;
import com.wuisee.hotel.enums.PayTypeEnum;
import com.wuisee.hotel.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String ROOM_ID = "123";

    @Test
    @Transactional
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setRoomId(ROOM_ID);
//        orderDTO.setRealPrice(new BigDecimal(200));
        orderDTO.setIntoDays(8);
        orderDTO.setPayType(PayTypeEnum.MONEY.getCode());
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());

        //入住详情
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setTravelerName("张三");
        o1.setTravelerPhone("13333333333");
        o1.setBz("这个是一个备注");
        orderDetailList.add(o1);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);

        log.info("创建订单 result={}", result);
    }

    @Test
    public void findByOrderId() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {


    }

//    @Test
//    public void teset111(){
//        List<String> list = java.util.Arrays.asList("a","b","c");
//        Stream.iterate(0, i -> i + 1).limit(list.size()).forEach(i -> {
//            System.out.println(String.valueOf(i) + list.get(i));
//        });
//    }
}