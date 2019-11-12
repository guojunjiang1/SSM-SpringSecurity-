package guo.controller;

import com.github.pagehelper.PageInfo;
import daomain.Permission;
import daomain.Role;
import daomain.UserInfo;
import guo.dao.PermissinoDao;
import guo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("role")
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    //查询所有角色
    @RequestMapping("findAll.do")
    @RolesAllowed({"ADMIN","USER"})
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("role-list");
        List<Role> all = roleService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(all);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

    //新建角色
    @RequestMapping("save.do")
    @RolesAllowed({"ADMIN"})
    public void save(Role role, HttpServletResponse response, HttpServletRequest request) throws Exception {
        roleService.save(role);
        response.sendRedirect(request.getContextPath()+"/role/findAll.do");
    }

    //根据角色ID查看详情
    @RequestMapping("findById.do")
    @RolesAllowed({"ADMIN"})
    public ModelAndView findById(@RequestParam(name="id",required = true)String id) throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        Role role=roleService.findById(id);
        modelAndView.setViewName("role-show");
        modelAndView.addObject("role",role);
        return modelAndView;
    }

    //为某一角色添加权限
    @Autowired
    private PermissinoDao permissinoDao;
    @RequestMapping("roleUpPermission.do")
    @RolesAllowed({"ADMIN"})
    public ModelAndView roleUpPermission(@RequestParam(name="id",required = true)String id) throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        Role role=roleService.findById(id);
        List<Permission> permissions = permissinoDao.findOtherPermission(id);
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissions",permissions);
        modelAndView.setViewName("role-update");
        return modelAndView;
    }

    //添加角色的权限
    @RequestMapping("update.do")
    @RolesAllowed({"ADMIN"})
    public void update(String roleId,String permissionsId,HttpServletResponse response, HttpServletRequest request) throws Exception {
        roleService.insert(roleId,permissionsId);
        response.sendRedirect(request.getContextPath()+"/role/findAll.do");
    }
}
