package guo.service;

import daomain.Role;

import java.util.List;

public interface RoleService {
    //查询所有角色
    List<Role> findAll(int page,int size) throws Exception;
    //新建角色
    void save(Role role) throws Exception;
    //根据ID查询角色详细信息
    Role findById(String id) throws Exception;
    //为角色添加权限
    void insert(String roleId, String permissionsId) throws Exception;
}
