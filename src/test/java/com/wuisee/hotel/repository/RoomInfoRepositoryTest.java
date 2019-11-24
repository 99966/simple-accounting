package com.wuisee.hotel.repository;

import com.wuisee.hotel.dataobject.RoomInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RoomInfoRepositoryTest {

    @Autowired
    private RoomInfoRepository repository;

    @Test
    public void save() {
        RoomInfo roomInfo = new RoomInfo();
        roomInfo.setRoomId("333");
        roomInfo.setRoomName("201房间");
        roomInfo.setRoomPrice(new BigDecimal(12));
        roomInfo.setRoomStatus(0);
        roomInfo.setCategoryType(1);

        RoomInfo result = repository.save(roomInfo);
        assertNotNull(result);
    }

    @Test
    public void find() {

        String str = "123";

        List<RoomInfo> result = repository.findAll();
        String roomName = result.stream().filter(e -> e.getRoomId().equals(str)).findAny().orElse(null).getRoomName();
        log.info(":LJLJ result={}",roomName);
        assertNotNull(result);
    }
}