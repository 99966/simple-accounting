package com.wuisee.hotel.repository;

import com.wuisee.hotel.dataobject.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface RoomInfoRepository extends JpaRepository<RoomInfo, String> {
    @Transactional
    @Modifying
    @Query("update RoomInfo room set room.roomStatus=:roomStatus where room.roomId =:roomId")
    int updateRoomStatus(@Param("roomStatus") Integer roomStatus, @Param("roomId") String roomId);
}
