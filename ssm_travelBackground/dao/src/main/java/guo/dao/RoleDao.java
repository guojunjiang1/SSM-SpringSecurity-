package guo.dao;

import daomain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//关于角色
@Repository
public interface RoleDao {
    //根据用户id查询对应的角色表
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "guo.dao.PermissinoDao.findById"))
    })
    @Select("select * from role where id in(select roleId from users_role where userId=#{uid})")
    Role findById(String uid) throws Exception;

    //查询所有角色
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    //新建角色
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    //根据角色ID查询权限(详情)
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "guo.dao.PermissinoDao.findById"))
    })
    @Select("select * from role where id =#{id}")
    Role findById1(String id) throws Exception;

    //为角色添加权限
    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void insert(@Param("roleId") String roleId, @Param("permissionId") String permissionsId) throws Exception;

    //根据用户Id查询用户没有的角色
    @Select("select * from role where id not in(select roleId from users_role where userId=#{id})")
    List<Role> findOtherRoles(String id) throws Exception;
}
