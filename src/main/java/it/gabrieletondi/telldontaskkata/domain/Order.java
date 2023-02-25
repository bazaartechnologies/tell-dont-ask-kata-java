package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;
import java.util.List;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.CREATED;

public class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private OrderStatus status;
    private int id;

    public BigDecimal getTotal() {
        return total;
    }


    private void setTotal(java.math.BigDecimal total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    private void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    private void setItems(java.util.List<it.gabrieletondi.telldontaskkata.domain.OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    private void setTax(java.math.BigDecimal tax) {
        this.tax = tax;
    }

    private OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void addTotal(BigDecimal value) {
        total = total.add(value);
    }

    public void addTax(java.math.BigDecimal taxAmount) {
        tax = tax.add(taxAmount);
    }

    public Order initializeOrder() {
        setStatus(OrderStatus.CREATED);
        setItems(new java.util.ArrayList<>());
        setCurrency("EUR");
        setTotal(new java.math.BigDecimal("0.00"));
        setTax(new java.math.BigDecimal("0.00"));
        return this;
    }

    public boolean isShipped() {
        return getStatus().equals(OrderStatus.SHIPPED);
    }

    public boolean isRejected() {
        return getStatus().equals(OrderStatus.REJECTED);
    }

    public boolean isApproved() {
        return getStatus().equals(OrderStatus.APPROVED);
    }

    public boolean isCreated() {
        return getStatus().equals(CREATED);
    }
}
