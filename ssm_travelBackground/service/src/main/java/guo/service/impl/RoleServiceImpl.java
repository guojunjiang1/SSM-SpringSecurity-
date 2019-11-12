package guo.service.impl;

import com.github.pagehelper.PageHelper;
import daomain.Role;
import guo.dao.RoleDao;
import guo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    //查询所有角色
    public List<Role> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    //新建角色
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    //根据ID查询角色详情
    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById1(id);
    }

    //为角色添加权限
    @Override
    public void insert(String roleId, String permissionsId) throws Exception{
        roleDao.insert(roleId,permissionsId);
    }
}
