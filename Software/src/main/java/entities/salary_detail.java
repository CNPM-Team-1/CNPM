package entities;

import enums.StatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "salary_detail")

public class salary_detail {
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

    public salary_detail() {
    }

    public salary_detail(String id, String employeeId, int amount, StatusEnum paymentstatus, Date createdDate, Date updatedDate) {
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
}
