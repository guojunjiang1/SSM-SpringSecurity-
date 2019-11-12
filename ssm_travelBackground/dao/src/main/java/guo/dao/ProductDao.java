package guo.dao;

import daomain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//关于商品
@Repository
public interface ProductDao {
    //查询产品
    @Select("select * from product")
    List<Product> findAllProductDao() throws Exception;
    //添加产品
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void add(Product product) throws Exception;
    //删除产品
    @Delete("delete from product where ID=#{id}")
    void delete(String id) throws Exception;
    //根据Id查询产品
    @Select("select * from product where ID=#{id}")
    Product findByID(String id) throws Exception;
    //修改产品
    @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}")
    void upDate(Product product) throws Exception;
}
