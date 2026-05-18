package xyz.lorcasdev.model;

import java.time.LocalDateTime;

public class Order {

    private final int id;
    private int customerId;
    private int quoteId;
    private String status;
    private int subtotal;
    private double discountAmount;
    private int total;
    private final LocalDateTime createdAt;
    private LocalDateTime startDate;
    private LocalDateTime estimatedDeliveryDate;

    public Order(int id, int customerId, int quoteId, String status, int subtotal, double discountAmount, int total, LocalDateTime createdAt, LocalDateTime startDate, LocalDateTime estimatedDeliveryDate) {
        Validator.positive(id, "id");
        Validator.notNegative(customerId, "customer_id");
        Validator.notEmpty(status, "status");
        Validator.positive(subtotal, "subtotal");
        Validator.positive(total, "total");
        Validator.notNull(createdAt, "created_at");

        this.id = id;
        this.customerId = customerId;
        this.quoteId = quoteId;
        this.status = status;
        this.subtotal = subtotal;
        this.discountAmount = discountAmount;
        this.total = total;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public void updateOrder(String status, Integer subtotal, Double discountAmount, Integer total, LocalDateTime startDate, LocalDateTime estimatedDeliveryDate) {
        if (status != null) {
            Validator.notEmpty(status, "status");
            this.status = status;
        }
        if (subtotal != null) {
        	Validator.positive(subtotal, "subtotal");
            this.subtotal = subtotal;
        }
        if (discountAmount != null) {
        	Validator.notNegative(discountAmount, "discount_amount");
            this.discountAmount = discountAmount;
        }
        if (total != null) {
        	Validator.positive(total, "total");
            this.total = total;
        }
        if (startDate != null) {
            this.startDate = startDate;
        }
        if (estimatedDeliveryDate != null) {
            this.estimatedDeliveryDate = estimatedDeliveryDate;
        }
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public String getStatus() {
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
        return String.format("Order[%d] - Customer: %d | Status: %s | Total: $%.2f", id, customerId, status, total);
    }
}
