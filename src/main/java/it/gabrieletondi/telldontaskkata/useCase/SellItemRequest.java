package it.gabrieletondi.telldontaskkata.useCase;

import static java.math.RoundingMode.HALF_UP;

public class SellItemRequest {
    private int quantity;
    private String productName;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public java.math.BigDecimal getTaxAmount(java.math.BigDecimal unitaryTax) {
        return unitaryTax.multiply(java.math.BigDecimal.valueOf(getQuantity()));
    }

    java.math.BigDecimal getTaxedAmount(java.math.BigDecimal unitaryTaxedAmount) {
        return unitaryTaxedAmount.multiply(java.math.BigDecimal.valueOf(getQuantity())).setScale(2, HALF_UP);
    }
}
