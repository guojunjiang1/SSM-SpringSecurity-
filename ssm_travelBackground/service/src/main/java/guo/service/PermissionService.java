package guo.service;

import daomain.Permission;

import java.util.List;

public interface PermissionService {
    //查询所有权限
    List<Permission> findAll(int page,int size) throws Exception;
    //新建权限
    void save(Permission permission) throws Exception;
}
