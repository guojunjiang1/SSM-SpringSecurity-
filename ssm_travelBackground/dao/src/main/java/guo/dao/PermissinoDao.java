package guo.dao;

import daomain.Permission;
import daomain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//关于角色
@Repository
public interface PermissinoDao {

    //根据角色id查询对应的权限
    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{rid})")
    Permission findById(String rid) throws Exception;

    //查询所有的权限
    @Select("select * from Permission")
    List<Permission> findAll() throws Exception;

    //新建权限
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    //根据角色Id查询角色没有的权限
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherPermission(String id) throws Exception;
}
