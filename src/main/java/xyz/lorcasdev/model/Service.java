package xyz.lorcasdev.model;

public class Service {

    private final int id;
    private String name;
    private String description;
    private int price;
    private boolean isActive;
    private double estimatedBaseHours;

    public Service(int id, String name, String description, int price, boolean isActive, double estimatedBaseHours) {
        notNegativeInt(id, "id");
        notEmptyString(name, "nombre");
        notNegativeInt(price, "precio");
        notNegativeDouble(estimatedBaseHours, "horas estimadas");

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isActive = isActive;
        this.estimatedBaseHours = estimatedBaseHours;
    }

    private static void notNegativeInt(int i, String field) {
        if (i <= 0) {
            throw new IllegalArgumentException("El campo " + field + " no puede ser 0, ni negativo");
        }
    }

    private static void notEmptyString(String string, String field) {
        if (string == null || string.isBlank()) {
            throw new IllegalArgumentException("El campo " + field + " es obligatorio");
        }
    }

    private static void notNegativeDouble(double i, String field) {
        if (i < 0) {
            throw new IllegalArgumentException("El campo " + field + " no puede ser negativo");
        }
    }

    public void updateService(String name, String description, Integer price, Boolean isActive, Double estimatedBaseHours) {
        if (name != null) {
            notEmptyString(name, "nombre");
            this.name = name;
        }

        if (description != null) {
            this.description = description;
        }

        if (price != null) {
            notNegativeInt(price, "precio");
            this.price = price;
        }

        if (isActive != null) {
            this.isActive = isActive;
        }

        if (estimatedBaseHours != null) {
            notNegativeDouble(estimatedBaseHours, name);
            this.estimatedBaseHours = estimatedBaseHours;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public boolean isActive() {
        return isActive;
    }

    public double getEstimatedBaseHours() {
        return estimatedBaseHours;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | $%d | %.1f hrs | %s",
                id, name, price, estimatedBaseHours,
                isActive ? "Activo" : "Inactivo");
    }
}
