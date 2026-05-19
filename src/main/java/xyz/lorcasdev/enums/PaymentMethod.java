package xyz.lorcasdev.enums;

public enum PaymentMethod {
	CASH("Efectivo"),
    BANK_TRANSFER("Transferencia"),
    DEBIT_CARD("Tarjeta débito"),
    CREDIT_CARD("Tarjeta crédito"),
    OTHER("Otro");
 
    private final String tag;
 
    PaymentMethod(String tag) {
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
