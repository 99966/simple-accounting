package com.wuisee.hotel.service;

import com.wuisee.hotel.dataobject.RoomInfo;

import java.util.List;

public interface RoomService {

    RoomInfo findById(String roomId);

    //修改房间状态为在住
    void changeRoomFull(List<String> roomIdList);

    //修改房间状态为空
    void changeRoomEmpty(List<String> roomIdList);

    List<RoomInfo> findRoomAll();
}
