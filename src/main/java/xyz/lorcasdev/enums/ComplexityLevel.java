package xyz.lorcasdev.enums;

public enum ComplexityLevel {
    LOW("Baja"),
    MEDIUM("Media"),
    HIGH("Alta");

    private final String tag;

    ComplexityLevel(String etiqueta) {
        this.tag = etiqueta;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return tag;
    }
}