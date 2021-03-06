package entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "employee_id")
    private String employeeId;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "update_date")
    private Date updateDate;

    public Receipt() {

    }

    public Receipt(String id, String orderId, String employeeId, Date createdDate, Date updateDate) {
        this.id = id;
        this.orderId = orderId;
        this.employeeId = employeeId;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String order_id) {
        this.orderId = orderId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employee_id) {
        this.employeeId = employeeId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
