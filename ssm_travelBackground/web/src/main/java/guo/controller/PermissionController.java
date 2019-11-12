package guo.controller;

import com.github.pagehelper.PageInfo;
import daomain.Permission;
import guo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    //查询所有权限
    @RequestMapping("findAll.do")
    @RolesAllowed({"ADMIN","USER"})
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("permission-list");
        List<Permission> all = permissionService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(all);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

    //新建权限
    @RequestMapping("save.do")
    @RolesAllowed({"ADMIN"})
    public void save(Permission permission, HttpServletRequest request, HttpServletResponse response) throws Exception{
        permissionService.save(permission);
        response.sendRedirect(request.getContextPath()+"/permission/findAll.do");
    }
}
