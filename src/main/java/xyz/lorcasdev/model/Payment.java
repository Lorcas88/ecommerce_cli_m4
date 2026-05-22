package xyz.lorcasdev.model;

import java.time.LocalDateTime;

import xyz.lorcasdev.enums.PaymentMethod;
import xyz.lorcasdev.enums.PaymentStatus;

public class Payment {

    private final int id;
    private final Order order;
    private LocalDateTime paymentDate;
    private final int amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private String transactionReference;

    public Payment(int id, Order order, LocalDateTime paymentDate, int amount, PaymentMethod paymentMethod, PaymentStatus status, String transactionReference) {
        Validator.positive(id, "id");
        Validator.notNull(paymentDate, "payment_date");
        Validator.positive(amount, "amount");

        this.id = id;
        this.order = order;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.transactionReference = transactionReference;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        Validator.notNull(paymentDate, "payment_date");
        this.paymentDate = paymentDate;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public int getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public int getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    @Override
    public String toString() {
        return String.format("Payment[%d] - Order: %d | Amount: $%d | Status: %s", id, order.getId(), amount, status);
    }
}
