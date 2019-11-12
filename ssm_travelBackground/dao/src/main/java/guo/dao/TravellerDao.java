package guo.dao;

import daomain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//关于游客
@Repository
public interface TravellerDao {
    //根据id查询旅客
    @Select("select * from traveller where id in(select travellerid from order_traveller where orderid=#{id})")
    List<Traveller> findById(String id);
}
