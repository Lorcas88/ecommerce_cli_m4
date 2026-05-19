package xyz.lorcasdev.enums;

public enum PaymentStatus {
	PENDING("Pendiente"),
    PAID("Pagado"),
    FAILED("Fallido"),
	REFUNDED("Reembolsado");
 
    private final String tag;
 
    PaymentStatus(String tag) {
        this.tag = tag;
    }
 
    public String getTag() {
        return tag;
    }
 
    @Override
    public String toString() {
        return tag;
    }
}
