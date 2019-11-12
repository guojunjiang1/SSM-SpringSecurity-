package guo.service.impl;

import com.github.pagehelper.PageHelper;
import daomain.Orders;
import daomain.Product;
import guo.dao.OrdersDao;
import guo.dao.ProductDao;
import guo.service.OrdersService;
import guo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao orders;

    @Override
    public List<Orders> findAllOrders(int page,int size) throws Exception {
        //添加分页插件对象(必须在sql语句前)
        PageHelper.startPage(page,size);
        return orders.findAllOrdersDao();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return orders.findById(ordersId);
    }
}
