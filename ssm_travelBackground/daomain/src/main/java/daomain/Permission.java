package daomain;

import java.io.Serializable;
import java.util.List;
//权限表
public class Permission implements Serializable {
    private String id;//主键
    private String permissionName;//权限名
    private String url;//权限路径
    private List<Role> roles;//权限对应的角色(多对多)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
