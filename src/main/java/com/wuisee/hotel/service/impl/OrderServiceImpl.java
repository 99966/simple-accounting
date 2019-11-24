package com.wuisee.hotel.service.impl;

import com.wuisee.hotel.converter.OrderMaster2OrderDTOConverter;
import com.wuisee.hotel.dataobject.OrderDetail;
import com.wuisee.hotel.dataobject.OrderMaster;
import com.wuisee.hotel.dataobject.RoomInfo;
import com.wuisee.hotel.dto.OrderDTO;
import com.wuisee.hotel.enums.OrderStatusEnum;
import com.wuisee.hotel.enums.ResultEnum;
import com.wuisee.hotel.enums.RoomStatusEnum;
import com.wuisee.hotel.exception.HotelException;
import com.wuisee.hotel.repository.OrderDetailRepository;
import com.wuisee.hotel.repository.OrderMasterRepository;
import com.wuisee.hotel.repository.RoomInfoRepository;
import com.wuisee.hotel.service.OrderService;
import com.wuisee.hotel.service.RoomService;
import com.wuisee.hotel.utils.KeyUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RoomService roomService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private RoomInfoRepository roomInfoRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        RoomInfo roomInfo = roomService.findById(orderDTO.getRoomId());

        if (roomInfo == null) {
            throw new HotelException(ResultEnum.ROOM_NOT_EXIST);
        }

        if (roomInfo.getRoomStatus() == RoomStatusEnum.FULL.getCode()) {
            throw new HotelException(ResultEnum.ROOM_STETUS_ERROR);
        }

        // 1 计算总价
        if (orderDTO.getRealPrice() != null && orderDTO.getRealPrice().compareTo(BigDecimal.ZERO) > 0) {
            orderAmount = orderDTO.getRealPrice()
                    .multiply(new BigDecimal(orderDTO.getIntoDays()))
                    .add(orderAmount);
        } else {
            orderAmount = roomInfo.getRoomPrice()
                    .multiply(new BigDecimal(orderDTO.getIntoDays()))
                    .add(orderAmount);
        }

        if (orderDTO.getOrderDetailList()==null) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(roomInfo, orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setRoomId(orderDTO.getRoomId());
            orderDetail.setIntoDays(orderDTO.getIntoDays());
            if (orderDTO.getRealPrice() != null && orderDTO.getRealPrice().compareTo(BigDecimal.ZERO) > 0) {
                orderDetail.setRealPrice(orderDTO.getRealPrice());
            } else {
                orderDetail.setRealPrice(roomInfo.getRoomPrice());
            }
            orderDTO.setOrderDetailList(Arrays.asList(orderDetail));
            orderDetailRepository.save(orderDetail);
        }else {
            for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
                // 2 入订单详情库
                BeanUtils.copyProperties(roomInfo, orderDetail);
                orderDetail.setDetailId(KeyUtil.genUniqueKey());
                orderDetail.setOrderId(orderId);
                orderDetail.setRoomId(orderDTO.getRoomId());
                orderDetail.setIntoDays(orderDTO.getIntoDays());
                if (orderDTO.getRealPrice() != null && orderDTO.getRealPrice().compareTo(BigDecimal.ZERO) > 0) {
                    orderDetail.setRealPrice(orderDTO.getRealPrice());
                } else {
                    orderDetail.setRealPrice(roomInfo.getRoomPrice());
                }
                orderDetailRepository.save(orderDetail);
            }
        }

        // 3 写入订单库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterRepository.save(orderMaster);

        // 4 下单成功,修改房间状态
        List<String> roomIdList = orderDTO.getOrderDetailList()
                .stream()
                .map(e -> e.getRoomId())
                .collect(Collectors.toList());
        roomService.changeRoomFull(roomIdList);
        return orderDTO;
    }

    @Override
    public OrderDTO findByOrderId(String orderId) {
        OrderDTO orderDTO = new OrderDTO();

        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);

        if (orderMaster == null) {
            throw new HeadlessException(ResultEnum.ORDER_NOT_EXIST.getMessage());
        }
        OrderDetail orderDetail = orderDetailRepository.findOrderDetailByOrderId(orderMaster.getOrderId());
        if (orderDetail == null) {
            throw new HeadlessException(ResultEnum.ORDER_DETAIL_EMPTY.getMessage());
        }
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(Arrays.asList(orderDetail));
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Integer page, Integer limit) {
        if (page > 0) {
            page = page - 1;
        }
        List<RoomInfo> roomInfoList = roomInfoRepository.findAll();
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageRequest);
//        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.addRoomNameConvert(orderMasterPage.getContent());
//        String s = roomInfoList.stream().filter(e -> e.getRoomId().equals(n.getRoomId())).findAny().orElse(null).getRoomName();
        List<OrderDTO> orderDTOList = orderMasterPage.getContent()
                .stream()
                .map(n -> OrderMaster2OrderDTOConverter.convert(
                        n,
                        roomInfoList.stream().
                                filter(e -> e.getRoomId().equals(n.getRoomId()))
                                .findAny().orElse(null).getRoomName()))
                .collect(Collectors.toList());
        return new PageImpl<OrderDTO>(orderDTOList, pageRequest, orderMasterPage.getTotalElements());
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }
}
