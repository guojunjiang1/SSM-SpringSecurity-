package guo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import daomain.SysLog;
import guo.dao.SysLogDao;
import guo.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void insert(SysLog sysLog) throws Exception{
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }
}
