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
    @Column(name = "roles_id")
    private String rolesId;
    @Column(name = "employee_id")
    private String employeeId;

    public RolesDetail() {

    }

    public RolesDetail(String id, String rolesId, String employeeId) {
        this.id = id;
        this.employeeId = employeeId;
        this.rolesId = rolesId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolesId() {
        return rolesId;
    }

    public void setRolesId(String rolesId) {
        this.rolesId = rolesId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
