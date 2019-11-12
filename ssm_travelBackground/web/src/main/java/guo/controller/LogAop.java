package guo.controller;

import daomain.SysLog;
import guo.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

//日志信息
@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    @Pointcut("execution(* guo.controller.*.*(.. ))")
    public void ps1(){}

    private Date visitTime;//开始时间
    private Class clazz;//访问的类的字节码文件
    private Method method;//访问的方法
    private Long time;//访问的时间
    private String url;//获取访问的url
    private String ip;//获取访问的ip
    //前置通知，获取访问的方法和访问的时间
    @Before("ps1()")
    public void beforePrintLog(JoinPoint jp) throws Exception {
        visitTime=new Date();
        Object[] args = jp.getArgs();//访问的方法的参数
        clazz = jp.getTarget().getClass();//获取访问的类的字节码文件
        String name = jp.getSignature().getName();//获取访问的方法名称
        if(args.length==0||args==null) {
            //获取无参的方法
            method = clazz.getMethod(name);
        }else {
            //将参数存放到Class数组当中
            Class[] classes=new Class[args.length];
            for(int i=0;i<classes.length;i++){
                classes[i]=args[i].getClass();
            }
            method=clazz.getMethod(name,classes);
        }
    }

    //后置通知
    @After("ps1()")
    public void afterPrintLog(JoinPoint jp) throws Exception{
        time=new Date().getTime()-visitTime.getTime();//获取访问时长
        if(clazz!=null&&method!=null&&clazz!=LogAop.class&&clazz!=SysLogController.class){
            RequestMapping clazzUrl = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if(clazzUrl!=null){
                String[] value = clazzUrl.value();//获取类的注解值
                RequestMapping methodUrl = method.getAnnotation(RequestMapping.class);
                if(methodUrl!=null){
                    String[] value1 = methodUrl.value();//获取方法的注解值
                    url="/"+value[0]+"/"+value1[0];
                }
            }
            ip=request.getRemoteAddr();//获取访问者的ip
            SecurityContext context = SecurityContextHolder.getContext();
            //通过SpringSecurity获取当时存入session中的用户对象名
            String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();
            SysLog sysLog=new SysLog();
            sysLog.setIp(ip);
            sysLog.setExecutionTime(time);
            sysLog.setMethod("【访问的类】:"+clazz.getName()+"【访问的方法】:"+method.getName());
            sysLog.setVisitTime(visitTime);
            sysLog.setUrl(url);
            sysLog.setUsername(username);
            sysLogService.insert(sysLog);
        }
    }
}
