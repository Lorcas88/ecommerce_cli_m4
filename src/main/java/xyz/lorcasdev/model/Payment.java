package xyz.lorcasdev.model;

import java.time.LocalDateTime;

public class Payment {

    private final int id;
    private int orderId;
    private LocalDateTime paymentDate;
    private final int amount;
    private String paymentMethod;
    private String status;
    private String transactionReference;

    public Payment(int id, int orderId, LocalDateTime paymentDate, int amount, String paymentMethod, String status, String transactionReference) {
        Validator.positive(id, "id");
        Validator.positive(orderId, "order_id");
        Validator.notNull(paymentDate, "payment_date");
        Validator.positive(amount, "amount");
        Validator.notEmpty(paymentMethod, "payment_method");
        Validator.notEmpty(status, "status");

        this.id = id;
        this.orderId = orderId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.transactionReference = transactionReference;
    }

    public void updatePayment(LocalDateTime paymentDate, String paymentMethod, String status, String transactionReference) {
        if (paymentDate != null) {
            this.paymentDate = paymentDate;
        }
        if (paymentMethod != null) {
        	Validator.notEmpty(paymentMethod, "payment_method");
            this.paymentMethod = paymentMethod;
        }
        if (status != null) {
        	Validator.notEmpty(status, "status");
            this.status = status;
        }
        if (transactionReference != null) {
            this.transactionReference = transactionReference;
        }
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    @Override
    public String toString() {
        return String.format("Payment[%d] - Order: %d | Amount: $%.2f | Status: %s", id, orderId, amount, status);
    }
}
