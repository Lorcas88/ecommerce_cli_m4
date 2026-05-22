package xyz.lorcasdev.model;

import java.time.LocalDateTime;
import java.util.List;

import xyz.lorcasdev.enums.OrderStatus;

public class Order {

    private final int id;
    private final Customer customer;
    private final Quote quote;
    private OrderStatus status;
    private int subtotal;
    private int discountAmount;
    private int total;
    private final LocalDateTime createdAt;
    private LocalDateTime startDate;
    private LocalDateTime estimatedDeliveryDate;

    public Order(int id, Customer customer, Quote quote, OrderStatus status, int subtotal, int discountAmount, int total, LocalDateTime createdAt, LocalDateTime startDate, LocalDateTime estimatedDeliveryDate) {
        Validator.positive(id, "id");
        Validator.notNegative(subtotal, "subtotal");
        Validator.notNegative(total, "total");
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

    // public void setSubtotal(int subtotal) {
    //     Validator.notNegative(subtotal, "subtotal");
    //     this.subtotal = subtotal;
    // }
    public void setDiscountAmount(int discountAmount) {
        Validator.notNegative(discountAmount, "discount_amount");
        this.discountAmount = discountAmount;
    }

    // public void setTotal(int total) {
    //     Validator.notNegative(total, "total");
    //     this.total = total;
    // }
    public void recalculateTotals(List<OrderItem> items) {
        subtotal = items.stream()
                .mapToInt(OrderItem::getLineTotal)
                .sum();

        total = subtotal - discountAmount;
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

    public int getSubtotal() {
        return subtotal;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public int getTotal() {
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
        String customerName = customer != null ? customer.getName() : quote.getGuestName();
        return String.format("Order[%d] - Customer: %s | Status: %s | Total: $%d", id, customerName, status, total);
    }
}
