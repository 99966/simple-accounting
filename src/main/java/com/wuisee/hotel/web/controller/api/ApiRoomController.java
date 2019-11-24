package com.wuisee.hotel.web.controller.api;

import com.wuisee.hotel.dataobject.RoomInfo;
import com.wuisee.hotel.service.RoomService;
import com.wuisee.hotel.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/rooms")
public class ApiRoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping()
    @ResponseBody
    public ResultVO getRoomList() {
        ResultVO resultVO = new ResultVO<>();
        List<RoomInfo> roomInfoList = roomService.findRoomAll();
        resultVO.setCode(20000);
        resultVO.setMsg("成功");
        resultVO.setData(roomInfoList);
        return resultVO;
    }

    @GetMapping("/update")
    @ResponseBody
    public ResultVO updateRoomStatus(@RequestParam("roomId") String roomId) {
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(20000);
        resultVO.setMsg("成功");
        roomService.changeRoomFull(Arrays.asList(roomId));
        return resultVO;
    }
}
