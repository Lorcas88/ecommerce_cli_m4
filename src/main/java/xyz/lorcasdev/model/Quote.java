package xyz.lorcasdev.model;

import java.time.LocalDateTime;

public class Quote {

    private final int id;
    private Integer customerId;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private String status;
    private String discountType;
    private Integer discountValue;
    private Integer discountAmount;
    private int subtotal;
    private int total;
    private final LocalDateTime createdAt;
    private LocalDateTime validUntil;

    public Quote(int id, Integer customerId, String guestName, String guestEmail, String guestPhone, String status, String discountType, int discountValue, int discountAmount, int subtotal, int total, LocalDateTime createdAt, LocalDateTime validUntil) {
        Validator.positive(id, "id");
        Validator.notEmpty(status, "status");
        Validator.positive(subtotal, "subtotal");
        Validator.positive(total, "total");
        Validator.notNull(createdAt, "created_at");

        this.id = id;
        this.customerId = customerId;
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

    public void updateQuote(String status, String discountType, Integer discountValue, Integer discountAmount, Integer subtotal, Integer total, LocalDateTime validUntil) {
        if (status != null) {
            Validator.notEmpty(status, "status");
            this.status = status;
        }
        if (discountType != null) {
            this.discountType = discountType;
        }
        if (discountValue != null) {
            this.discountValue = discountValue;
        }
        if (discountAmount != null) {
            this.discountAmount = discountAmount;
        }
        if (subtotal != null) {
            Validator.positive(subtotal, "subtotal");
            this.subtotal = subtotal;
        }
        if (total != null) {
            Validator.positive(total, "total");
            this.total = total;
        }
        if (validUntil != null) {
            this.validUntil = validUntil;
        }
    }

    public int getId() {
        return id;
    }

    public Integer getCustomerId() {
        return customerId;
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

    public String getStatus() {
        return status;
    }

    public String getDiscountType() {
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
        return String.format("Quote[%d] - Status: %s | Total: $%.2f", id, status, total);
    }
}
