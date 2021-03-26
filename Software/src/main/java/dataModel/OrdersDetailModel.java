package dataModel;

public class OrdersDetailModel {
    private String merchandiseName;
    private Integer quantity;
    private String amount;
    private String finalAmount;

    public OrdersDetailModel() {
    }

    public OrdersDetailModel(String merchandiseName, Integer quantity, String amount, String finalAmount) {
        this.merchandiseName = merchandiseName;
        this.quantity = quantity;
        this.amount = amount;
        this.finalAmount = finalAmount;
    }

    public String getMerchandiseName() {
        return merchandiseName;
    }

    public void setMerchandiseName(String merchandiseName) {
        this.merchandiseName = merchandiseName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        this.finalAmount = finalAmount;
    }
}
