package guo.controller;

import com.github.pagehelper.PageInfo;
import daomain.Orders;
import guo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    //查看订单
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        List<Orders> allOrders = ordersService.findAllOrders(page,size);
        //PageHelper分页对象中的一个对象，可以自动封装数据如当前页数，总记录数，总页数，当前记录数，list集合
        PageInfo pageInfo=new PageInfo(allOrders);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }
    //查看订单详情
    @RequestMapping("findById.do")
    public ModelAndView findById(@RequestParam(name="id",required = true)String ordersId) throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        Orders orders=ordersService.findById(ordersId);
        modelAndView.setViewName("orders-show");
        modelAndView.addObject("orders",orders);
        return modelAndView;
    }
}
