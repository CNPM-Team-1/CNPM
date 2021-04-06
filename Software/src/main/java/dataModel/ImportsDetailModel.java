package dataModel;

import entities.ImportsDetail;

public class ImportsDetailModel {
    private ImportsDetail importsDetail;
    private String merchandiseName;
    private Integer quantity;
    private String amount;
    private String finalAmount;

    public ImportsDetailModel() {
    }

    public ImportsDetailModel(ImportsDetail importsDetail, String merchandiseName, Integer quantity, String amount, String finalAmount) {
        this.importsDetail = importsDetail;
        this.merchandiseName = merchandiseName;
        this.quantity = quantity;
        this.amount = amount;
        this.finalAmount = finalAmount;
    }

    @Override
    public String toString() {
        return "ImportsDetailModel{" +
                "importsDetail=" + importsDetail +
                ", merchandiseName='" + merchandiseName + '\'' +
                ", quantity=" + quantity +
                ", amount='" + amount + '\'' +
                ", finalAmount='" + finalAmount + '\'' +
                '}';
    }

    public ImportsDetail getImportsDetail() {
        return importsDetail;
    }

    public void setImportsDetail(ImportsDetail importsDetail) {
        this.importsDetail = importsDetail;
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
