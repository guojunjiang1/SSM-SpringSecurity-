package guo.dao;

import daomain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//关于订单
@Repository
public interface OrdersDao {
    //查询订单，关联查询产品
    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "guo.dao.ProductDao.findByID")),
    })
    List<Orders> findAllOrdersDao() throws Exception;
    //根据id查询订单详细情况
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "guo.dao.ProductDao.findByID")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select ="guo.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,many = @Many(select="guo.dao.TravellerDao.findById"))
    })
    Orders findById(String ordersId);
}
