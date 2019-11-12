package guo.dao;

import daomain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

//关于用户
@Repository
public interface UserDao {
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "guo.dao.RoleDao.findById"))
    })
    //根据姓名查询用户表，关联查询角色表
    @Select("select * from users where username=#{name}")
    UserInfo findByName(String name) throws Exception;

    //查询所有用户
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    //保存用户
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo) throws Exception;

    //根据Id查询用户(详情)
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "guo.dao.RoleDao.findById"))
    })
    @Select("select * from users where id=#{id}")
    UserInfo findById(String id);

    //给用户添加角色
    @Insert("insert into users_role values(#{userId},#{roleId})")
    void inset(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
