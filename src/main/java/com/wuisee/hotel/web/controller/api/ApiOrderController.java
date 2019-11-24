package com.wuisee.hotel.web.controller.api;

import com.wuisee.hotel.dto.OrderDTO;
import com.wuisee.hotel.enums.PayStatusEnum;
import com.wuisee.hotel.forminfo.OrderForm;
import com.wuisee.hotel.service.OrderService;
import com.wuisee.hotel.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/orders")
public class ApiOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    @ResponseBody
    public ResultVO getOrderList(
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit
    ) {

        System.out.println(page);
        System.out.println(limit);
        ResultVO resultVo = new ResultVO<>();

        Page<OrderDTO> result = orderService.findList(page, limit);
        Map map = new HashMap();
        map.put("total", result.getTotalElements());
        map.put("items", result.getContent());
        resultVo.setData(map);
        resultVo.setCode(20000);
        resultVo.setMsg("成功");
        return resultVo;
    }

    @PostMapping("/create")
    @ResponseBody
    public ResultVO create(@RequestBody OrderForm orderForm) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderForm,orderDTO);
        orderDTO.setPayType(0);
        orderDTO.setRealPrice(orderForm.getPrice());
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderDTO result = orderService.create(orderDTO);

        ResultVO resultVO = new ResultVO();

        resultVO.setData(result);
        resultVO.setMsg("成功");
        resultVO.setCode(20000);

        return resultVO;
    }

    @GetMapping()
    @ResponseBody
    public ResultVO getOrderInfo(@RequestParam("orderId") String orderId) {
        ResultVO resultVO = new ResultVO();
        OrderDTO orderDTO = orderService.findByOrderId(orderId);
        resultVO.setMsg("成功");
        resultVO.setCode(20000);
        resultVO.setData(orderDTO);
        return resultVO;
    }
}
