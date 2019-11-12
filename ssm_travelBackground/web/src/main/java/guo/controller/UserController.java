package guo.controller;

import com.github.pagehelper.PageInfo;
import daomain.Product;
import daomain.Role;
import daomain.UserInfo;
import guo.dao.RoleDao;
import guo.service.RoleService;
import guo.service.UserService;
import guo.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    //查询用户
    @RequestMapping("findAll.do")
    @RolesAllowed({"ADMIN","USER"})
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("user-list");
        List<UserInfo> list=userService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(list);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

    //新建用户
    @RequestMapping("save.do")
    @RolesAllowed("ADMIN")
    public void saveUser(UserInfo userInfo, HttpServletResponse response, HttpServletRequest request) throws Exception{
        userService.saveUser(userInfo);
        response.sendRedirect(request.getContextPath()+"/user/findAll.do");
    }

    //根据id查看用户详情
    @RequestMapping("findById.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findById(@RequestParam(name="id",required = true)String id) throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        UserInfo userInfo=userService.findById(id);
        modelAndView.setViewName("user-show");
        modelAndView.addObject("user",userInfo);
        return modelAndView;
    }

    @Autowired
    private RoleDao roleDao;
    //为某一用户添加角色
    @RequestMapping("userUpRole.do")
    @RolesAllowed("ADMIN")
    public ModelAndView userUpRole(@RequestParam(name="id",required = true)String id) throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        UserInfo userInfo=userService.findById(id);
        modelAndView.addObject("user",userInfo);
        List<Role> all=roleDao.findOtherRoles(id);
        modelAndView.addObject("role",all);
        modelAndView.setViewName("user-update");
        return modelAndView;
    }

    //添加用户的角色
    @RequestMapping("update.do")
    @RolesAllowed("ADMIN")
    public void update(String userId,String roleId,HttpServletResponse response, HttpServletRequest request) throws Exception {
        userService.insert(userId,roleId);
        response.sendRedirect(request.getContextPath()+"/user/findAll.do");
    }
}
