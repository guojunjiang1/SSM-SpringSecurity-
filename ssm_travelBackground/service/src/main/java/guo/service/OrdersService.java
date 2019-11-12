package guo.service;

import daomain.Orders;
import daomain.Product;

import java.util.List;

//关于订单
public interface OrdersService {
    //查找所有的订单
    List<Orders> findAllOrders(int page,int size) throws Exception;
    //根据id查看订单的详细情况
    Orders findById(String ordersId) throws Exception;
}
