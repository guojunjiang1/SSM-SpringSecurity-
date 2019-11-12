package guo.dao;

import daomain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
//关于会员
public interface MemberDao {
    //根据id查找会员
    @Select("select * from member where id=#{id}")
    Member findById(String id) throws Exception;
}
