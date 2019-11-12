package guo.service.impl;

import com.github.pagehelper.PageHelper;
import daomain.Permission;
import guo.dao.PermissinoDao;
import guo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissinoDao permissinoDao;
    //查询所有权限
    @Override
    public List<Permission> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return permissinoDao.findAll();
    }

    //新建权限
    @Override
    public void save(Permission permission) throws Exception{
        permissinoDao.save(permission);
    }
}
