package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

public class Category {
    private String name;
    private BigDecimal taxPercentage;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTaxPercentage() {
        return this.taxPercentage;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }
}
