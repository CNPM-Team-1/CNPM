package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_detail")
public class OrdersDetail {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "merchandise_id")
    private String merchandiseId;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "amount")
    private int amount;

    public OrdersDetail() {
    }

    public OrdersDetail(String id, String orderId, String merchandiseId, int quantity, int amount) {
        this.id = id;
        this.orderId = orderId;
        this.merchandiseId = merchandiseId;
        this.quantity = quantity;
        this.amount = amount;
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

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(String merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
