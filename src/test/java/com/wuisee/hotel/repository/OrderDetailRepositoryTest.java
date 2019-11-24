package com.wuisee.hotel.repository;

import com.wuisee.hotel.dataobject.OrderDetail;
import com.wuisee.hotel.dataobject.RoomInfo;
import com.wuisee.hotel.enums.ResultEnum;
import com.wuisee.hotel.enums.RoomStatusEnum;
import com.wuisee.hotel.exception.HotelException;
import com.wuisee.hotel.service.RoomService;
import com.wuisee.hotel.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Autowired
    private RoomService roomService;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567");
        orderDetail.setOrderId("11011011");
        orderDetail.setRoomId("1");
        orderDetail.setRoomName("102房间");
        orderDetail.setIntoDays(2);

        OrderDetail result = repository.save(orderDetail);

        assertNotNull(result);
    }

    @Test
    public void findTest() {
//        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
//        RoomInfo roomInfo = roomService.findById("1234");
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrderId("11011011");
//        orderDetail.setRoomId("1234");
//        orderDetail.setRoomName("102房间");
//        orderDetail.setIntoDays(2);
//        if (roomInfo == null) {
//            throw new HotelException(ResultEnum.ROOM_NOT_EXIST);
//        }
//
//        if (roomInfo.getRoomStatus() == RoomStatusEnum.FULL.getCode()) {
//            throw new HotelException(ResultEnum.ROOM_STETUS_ERROR);
//        }
//
//        // 1 计算总价
//        if (orderDetail.getRoomPrice().compareTo(BigDecimal.ZERO) > 0) {
//            roomInfo.setRoomPrice(orderDetail.getRoomPrice());
//            orderAmount = orderDetail.getRoomPrice()
//                    .multiply(new BigDecimal(orderDetail.getIntoDays()))
//                    .add(orderAmount);
//        } else {
//            orderAmount = roomInfo.getRoomPrice()
//                    .multiply(new BigDecimal(orderDetail.getIntoDays()))
//                    .add(orderAmount);
//        }
//        // 2 入订单详情库
//        BeanUtils.copyProperties(roomInfo, orderDetail);
//        orderDetail.setDetailId(KeyUtil.genUniqueKey());
//
//        System.out.println(orderAmount);
//        OrderDetail result = repository.save(orderDetail);
//        List<String> stringList = new ArrayList<>();
//        stringList.add("1234");
//        roomService.changeRoomFull(stringList);
    }
}