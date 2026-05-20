package xyz.lorcasdev.model;

import java.time.LocalDateTime;

import xyz.lorcasdev.enums.DiscountType;
import xyz.lorcasdev.enums.QuoteStatus;

public class Quote {

    private final int id;
    private final Customer customer;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private final QuoteStatus status;
    private final DiscountType discountType;
    private Integer discountValue;
    private Integer discountAmount;
    private int subtotal;
    private int total;
    private final LocalDateTime createdAt;
    private LocalDateTime validUntil;

    public Quote(int id, Customer customer, String guestName, String guestEmail, String guestPhone, QuoteStatus status, DiscountType discountType, int discountValue, int discountAmount, int subtotal, int total, LocalDateTime createdAt, LocalDateTime validUntil) {
        Validator.positive(id, "id");
        Validator.positive(subtotal, "subtotal");
        Validator.positive(total, "total");
        Validator.notNull(createdAt, "created_at");

        this.id = id;
        this.customer = customer;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.guestPhone = guestPhone;
        this.status = status;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.discountAmount = discountAmount;
        this.subtotal = subtotal;
        this.total = total;
        this.createdAt = createdAt;
        this.validUntil = validUntil;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public void setDiscountValue(Integer discountValue) {
        this.discountValue = discountValue;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setSubtotal(int subtotal) {
        Validator.positive(subtotal, "subtotal");
        this.subtotal = subtotal;
    }

    public void setTotal(int total) {
        Validator.positive(total, "total");
        this.total = total;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public QuoteStatus getStatus() {
        return status;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public Integer getDiscountValue() {
        return discountValue;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    @Override
    public String toString() {
        return String.format("Quote[%d] - Status: %s | Total: $%d", id, status, total);
    }
}
