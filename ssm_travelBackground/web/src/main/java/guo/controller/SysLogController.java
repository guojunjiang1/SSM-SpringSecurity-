package guo.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import daomain.SysLog;
import guo.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;
    //查询所有日志信息
    @RequestMapping("findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "8") Integer size) throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("syslog-list");
        List<SysLog> list=sysLogService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(list);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }
}
