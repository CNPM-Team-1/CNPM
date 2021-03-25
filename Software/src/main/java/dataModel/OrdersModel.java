package dataModel;

import java.util.Date;

public class OrdersModel {
    private Date createdDate;
    private String customerName;
    private Integer totalQuantity;
    private Integer totalAmount;
    private String status;
    private String ordersType;
    private String ordersDescription;

    public OrdersModel() {
    }

    public OrdersModel(Date createdDate, String customerName, Integer totalQuantity, Integer totalAmount, String ordersType, String ordersDescription) {
        this.createdDate = createdDate;
        this.customerName = customerName;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.ordersType = ordersType;
        this.ordersDescription = ordersDescription;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrdersType() {
        return ordersType;
    }

    public void setOrdersType(String ordersType) {
        this.ordersType = ordersType;
    }

    public String getOrdersDescription() {
        return ordersDescription;
    }

    public void setOrdersDescription(String ordersDescription) {
        this.ordersDescription = ordersDescription;
    }
}
