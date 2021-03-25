package entities;

import dataModel.ReceiptOrdersModel;

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
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "employee_id")
    private String employeeId;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_date")
    private Date updatedDate;

    public Orders() {
    }

    public Orders(ReceiptOrdersModel receiptOrdersModel) {
        this.id = receiptOrdersModel.getId();
        this.type = receiptOrdersModel.getType();
        this.customerId = receiptOrdersModel.getCustomerId();
        this.employeeId = receiptOrdersModel.getEmployeeId();
        this.description = receiptOrdersModel.getDescription();
        this.status = receiptOrdersModel.getStatus();
        this.createdDate = receiptOrdersModel.getCreatedDate();
        this.updatedDate = receiptOrdersModel.getUpdatedDate();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Orders(String id, String type, String customerId, String employeeId, String description, String status, Date createdDate, Date updatedDate) {
        this.id = id;
        this.type = type;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", customerId='" + customerId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
