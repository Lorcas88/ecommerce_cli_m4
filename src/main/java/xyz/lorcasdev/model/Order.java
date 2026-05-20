package xyz.lorcasdev.model;

import java.time.LocalDateTime;

import xyz.lorcasdev.enums.OrderStatus;

public class Order {

    private final int id;
    private final Customer customer;
    private final Quote quote;
    private OrderStatus status;
    private int subtotal;
    private double discountAmount;
    private int total;
    private final LocalDateTime createdAt;
    private LocalDateTime startDate;
    private LocalDateTime estimatedDeliveryDate;

    public Order(int id, Customer customer, Quote quote, OrderStatus status, int subtotal, double discountAmount, int total, LocalDateTime createdAt, LocalDateTime startDate, LocalDateTime estimatedDeliveryDate) {
        Validator.positive(id, "id");
        Validator.positive(subtotal, "subtotal");
        Validator.positive(total, "total");
        Validator.notNull(createdAt, "created_at");

        this.id = id;
        this.customer = customer;
        this.quote = quote;
        this.status = status;
        this.subtotal = subtotal;
        this.discountAmount = discountAmount;
        this.total = total;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setSubtotal(int subtotal) {
        Validator.positive(subtotal, "subtotal");
        this.subtotal = subtotal;
    }

    public void setDiscountAmount(double discountAmount) {
        Validator.notNegative(discountAmount, "discount_amount");
        this.discountAmount = discountAmount;
    }

    public void setTotal(int total) {
        Validator.positive(total, "total");
        this.total = total;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Quote getQuote() {
        return quote;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    @Override
    public String toString() {
        return String.format("Order[%d] - Customer: %s | Status: %s | Total: $%d", id, customer.getName(), status, total);
    }
}
