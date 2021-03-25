package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "type")
    private String type;
    @Column(name = "employee_id")
    private String employeeId;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;

    public Orders() {
    }

    public Orders(String id, String type,String employeeId, String customerId, String status,String description, Date createdDate, Date updatedDate) {
        this.id = id;
        this.type = type;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.status = status;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
