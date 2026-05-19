package xyz.lorcasdev.enums;

public enum QuoteStatus {
	DRAFT("Borrador"),
    SENT("Enviada"),
    REJECTED("Rechazada"),
    EXPIRED("Expirada"),
    APPROVED("Aprobada");
 
    private final String tag;
 
    QuoteStatus(String tag) {
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
