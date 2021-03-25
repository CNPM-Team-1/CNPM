package dataModel;

import entities.Orders;

import java.util.Date;

public class ReceiptOrdersModel {

    private String id;
    private String type;
    private String customerId;
    private String employeeId;
    private String description;
    private String status;
    private Date createdDate;
    private Date updatedDate;
    private String employeeName;

    public ReceiptOrdersModel(Orders orders) {
        this.id = orders.getId();
        this.type = orders.getType();
        this.customerId = orders.getCustomerId();
        this.employeeId = orders.getEmployeeId();
        this.description = orders.getDescription();
        this.status = orders.getStatus();
        this.createdDate = orders.getCreatedDate();
        this.updatedDate = orders.getUpdatedDate();
    }

    public ReceiptOrdersModel() {
    }

    public ReceiptOrdersModel(String id, String type, String customerId, String employeeId, String description, String status, Date createdDate, Date updatedDate, String employeeName) {
        this.id = id;
        this.type = type;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.employeeName = employeeName;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
