package com.wuisee.hotel.service.impl;

import com.wuisee.hotel.dataobject.RoomInfo;
import com.wuisee.hotel.enums.RoomStatusEnum;
import com.wuisee.hotel.repository.RoomInfoRepository;
import com.wuisee.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceServiceImpl implements RoomService {

    @Autowired
    private RoomInfoRepository repository;

    @Override
    public RoomInfo findById(String roomId) {
        return repository.findById(roomId).orElse(null);
    }

    @Override
    @Transactional
    public void changeRoomFull(List<String> roomIdList) {
        changeRoomStatus(RoomStatusEnum.FULL.getCode(),roomIdList);
    }

    @Override
    @Transactional
    public void changeRoomEmpty(List<String> roomIdList) {
        changeRoomStatus(RoomStatusEnum.EMPTY.getCode(),roomIdList);
    }

    @Override
    public List<RoomInfo> findRoomAll() {
        List<RoomInfo> roomInfoList = repository.findAll();
        return roomInfoList;
    }

    private void changeRoomStatus(Integer status, List<String> roomIdList) {
        for (String roomid: roomIdList) {
            repository.updateRoomStatus(status,roomid);
        }
    }
}
