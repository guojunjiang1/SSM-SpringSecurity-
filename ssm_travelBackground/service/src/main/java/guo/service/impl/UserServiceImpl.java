package guo.service.impl;

import com.github.pagehelper.PageHelper;
import daomain.Role;
import daomain.UserInfo;
import guo.dao.UserDao;
import guo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    //用户登录SpringSecurity框架验证
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建springSecurity提供的User对象(它是UserDetails的实现类),封装该对象
        //构造参数,第一个用户名，第二个密码，第三个该用户的权限集合
        User user=new User(userInfo.getUsername(),userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            //遍历获取该用户对应的角色名
           list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    //查询所有用户
    @Override
    public List<UserInfo> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    //保存用户
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void saveUser(UserInfo userInfo) throws Exception{
        //对密码进行编码加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.saveUser(userInfo);
    }

    //根据Id查询用户
    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    //给用户添加角色
    @Override
    public void insert(String userId, String roleId) throws Exception{
        userDao.inset(userId,roleId);
    }
}
