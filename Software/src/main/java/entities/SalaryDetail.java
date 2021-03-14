package entities;

import enums.StatusEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "salary_detail")

public class SalaryDetail {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "employee_id")
    private String employeeId;
    @Column(name = "amount")
    private int amount;
    @Column(name = "payment_status")
    private StatusEnum paymentstatus;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;

    public SalaryDetail() {
    }

    public SalaryDetail(String id, String employeeId, int amount, StatusEnum paymentstatus, Date createdDate, Date updatedDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.amount = amount;
        this.paymentstatus = paymentstatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public StatusEnum getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(StatusEnum paymentstatus) {
        this.paymentstatus = paymentstatus;
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

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}
