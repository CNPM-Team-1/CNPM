package dataModel;

import entities.Receipt;

import java.util.Date;

public class ReceiptModel {
    private String ordersId;
    private Date createdDate;
    private String customerName;
    private String description;
    private Integer sumQuantity;
    private String sumAmount;

    public ReceiptModel() {
    }

    public ReceiptModel(String ordersId, Date createdDate, String customerName, String description, Integer sumQuantity, String sumAmount) {
        this.ordersId = ordersId;
        this.createdDate = createdDate;
        this.customerName = customerName;
        this.description = description;
        this.sumQuantity = sumQuantity;
        this.sumAmount = sumAmount;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(Integer sumQuantity) {
        this.sumQuantity = sumQuantity;
    }

    public String getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(String sumAmount) {
        this.sumAmount = sumAmount;
    }

    @Override
    public String toString() {
        return "ReceiptModel{" +
                "ordersId='" + ordersId + '\'' +
                ", createdDate=" + createdDate +
                ", customerName='" + customerName + '\'' +
                ", description='" + description + '\'' +
                ", sumQuantity=" + sumQuantity +
                ", sumAmount='" + sumAmount + '\'' +
                '}';
    }
}
