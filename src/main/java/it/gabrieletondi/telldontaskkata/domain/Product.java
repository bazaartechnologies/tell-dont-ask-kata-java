package it.gabrieletondi.telldontaskkata.domain;
import it.gabrieletondi.telldontaskkata.useCase.SellItemRequest;
import it.gabrieletondi.telldontaskkata.useCase.SellItemsRequest;

import static java.math.RoundingMode.HALF_UP;


import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal calculateTaxAmt(SellItemRequest request) {

        BigDecimal unitaryTax = calculateUnitaryTax();
        BigDecimal quantity = BigDecimal.valueOf(request.getQuantity());
        BigDecimal taxAmount = unitaryTax.multiply(quantity);
        return taxAmount;
    }
    public BigDecimal calculateTaxedAmt(SellItemRequest request){
        BigDecimal unitaryTax = calculateUnitaryTax();
        BigDecimal unitaryTaxedAmount = this.getPrice().add(unitaryTax).setScale(2,HALF_UP);
        return unitaryTaxedAmount.multiply(BigDecimal.valueOf(request.getQuantity())).setScale(2, HALF_UP);

    }
    private BigDecimal calculateUnitaryTax(){
        BigDecimal percentage =  this.price.divide(BigDecimal.valueOf(100));

        BigDecimal taxPercentage=this.category.getTaxPercentage();
        BigDecimal unitaryTax= percentage.multiply(taxPercentage).setScale(2,HALF_UP);
        return unitaryTax;
    }

}
