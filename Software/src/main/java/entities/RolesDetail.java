package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles_detail")
public class RolesDetail {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "permission_code")
    private String permissionCode;

    public RolesDetail() {
    }

    public RolesDetail(String id, String roleId, String permissionCode) {
        this.id = id;
        this.roleId = roleId;
        this.permissionCode = permissionCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
