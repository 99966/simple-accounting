package com.wuisee.hotel.web.controller.api;

import com.wuisee.hotel.forminfo.UserInfo;
import com.wuisee.hotel.vo.ResultVO;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/users")
public class ApiUserController {

    @GetMapping()
    @ResponseBody
    public String index() {
        return "1111";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultVO login(@RequestBody UserInfo userInfo){
        ResultVO resultVO = new ResultVO<>();

        resultVO.setCode(20000);
        resultVO.setMsg("成功");
        resultVO.setData("token");
        return resultVO;
    }

//    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @GetMapping("/info")
    @ResponseBody
    public ResultVO getUserInfo(){
        Map<Object, Object> map = new HashMap<>();
        List<String> roles = new ArrayList<>();
        ResultVO resultVO = new ResultVO<>();

        roles.add("admin");

        map.put("roles", roles);
        map.put("introduction", "I am a super administrator");
        map.put("name", "super");
        resultVO.setCode(20000);
        resultVO.setMsg("成功");
        resultVO.setData(map);
        return resultVO;
    }
}
