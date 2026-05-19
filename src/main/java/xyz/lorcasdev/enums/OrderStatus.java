package xyz.lorcasdev.enums;

public enum OrderStatus {
	PENDING("Pendiente"),
	IN_PROGRESS("En progreso"),
    COMPLETED("Completada"),
    CONFIRMED("COnfirmada"),
    CANCELLED("Cancelada");
 
    private final String tag;
 
    OrderStatus(String tag) {
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
