package guo.dao;

import daomain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
//日志信息
@Repository
public interface SysLogDao {
    //添加日志信息
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog) throws Exception;
    //查询所有日志信息
    @Select("select * from syslog")
    List<SysLog> findAll() throws Exception;
}
