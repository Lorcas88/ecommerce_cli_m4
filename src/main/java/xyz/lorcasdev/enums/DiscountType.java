package xyz.lorcasdev.enums;

public enum DiscountType {
	PERCENTAGE("Porcentaje"),
    FIXED("Monto fijo"),
    NONE("Sin descuento");
 
    private final String tag;
 
    DiscountType(String tag) {
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
