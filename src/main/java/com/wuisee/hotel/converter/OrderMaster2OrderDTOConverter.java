package com.wuisee.hotel.converter;

import com.wuisee.hotel.dataobject.OrderMaster;
import com.wuisee.hotel.dataobject.RoomInfo;
import com.wuisee.hotel.dto.OrderDTO;
import com.wuisee.hotel.repository.RoomInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTOConverter {

    @Autowired
    private RoomInfoRepository repository;

    public static OrderDTO convert(OrderMaster orderMaster, String roomName) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setRoomName(roomName);
        return orderDTO;
    }

    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return  orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
