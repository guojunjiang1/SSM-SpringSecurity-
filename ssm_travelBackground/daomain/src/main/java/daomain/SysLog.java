package daomain;

import guo.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
public class SysLog {
    private String id;//主键无意义
    private Date visitTime;//访问的时间
    private String visitTimeStr;
    private String username;//访问的用户名
    private String ip;//访问的ip
    private String url;//访问的url
    private Long executionTime;//访问的时间
    private String method;//访问的全限定类名+方法名

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeStr() {
        if(getVisitTime()!=null){
            visitTimeStr=DateUtils.DateToString(getVisitTime(),"yyyy-MM-dd HH:mm");
        }
        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
