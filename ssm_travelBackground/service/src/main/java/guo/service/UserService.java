package guo.service;

import daomain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

//该接口实现springSecurity提供的一个接口(到时候配置文件会访问该service)
public interface UserService extends UserDetailsService {
    //查询所有用户
    List<UserInfo> findAll(int page,int size) throws Exception;
    //添加用户
    void saveUser(UserInfo userInfo) throws Exception;
    //根据id查询用户
    UserInfo findById(String id) throws Exception;
    //为用户添加角色
    void insert(String userId, String roleId) throws Exception;
}
