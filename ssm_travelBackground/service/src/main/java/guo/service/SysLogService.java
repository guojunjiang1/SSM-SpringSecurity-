package guo.service;

import daomain.SysLog;

import java.util.List;

public interface SysLogService {
    void insert(SysLog sysLog) throws Exception;

    List<SysLog> findAll(int page,int size) throws Exception;
}
