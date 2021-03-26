package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders_detail")
public class OrdersDetail {
    @Id
    @Column(name = "id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "merchandise_id")
    private Merchandise merchandise;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "amount")
    private int amount;

    public OrdersDetail() {
    }

    public OrdersDetail(String id, Orders orders, Merchandise merchandise, int quantity, int amount) {
        this.id = id;
        this.orders = orders;
        this.merchandise = merchandise;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
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
