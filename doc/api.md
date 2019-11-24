###创建订单

```
POST /sell/buyer/order/create
```

参数

```
{
    roomId: "12313",
    intoDays: 3,
    price: 100,
    payType: 0,
    items: [{
        traveler_name: "张三",
        traveler_phone: "18868822111",
    }]
}

```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": {
      "orderId": "147283992738221" 
  }
}
```