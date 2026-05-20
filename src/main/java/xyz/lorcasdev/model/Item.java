package xyz.lorcasdev.model;

public class Item {

    private final int id;
    private String name;
    private String description;
    private int baseUnitPrice;
    private double baseEstimatedHours;
    private boolean isActive;

    // Constructor
    public Item(int id, String name, String description, int baseUnitPrice, double baseEstimatedHours, boolean isActive) {
        Validator.positive(id, "id");
        Validator.notEmpty(name, "nombre");
        Validator.positive(baseUnitPrice, "precio unitario base");
        Validator.notNegative(baseEstimatedHours, "horas estimadas base");

        this.id = id;
        this.name = name;
        this.description = description;
        this.baseUnitPrice = baseUnitPrice;
        this.baseEstimatedHours = baseEstimatedHours;
        this.isActive = isActive;
    }

    // Setters
    public void setName(String name) {
        Validator.notEmpty(name, "nombre");
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBaseUnitPrice(int baseUnitPrice) {
        Validator.positive(baseUnitPrice, "precio unitario base");
        this.baseUnitPrice = baseUnitPrice;
    }

    public void setBaseEstimatedHours(double baseEstimatedHours) {
        Validator.notNegative(baseEstimatedHours, "horas estimadas base");
        this.baseEstimatedHours = baseEstimatedHours;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getBaseUnitPrice() {
        return baseUnitPrice;
    }

    public double getBaseEstimatedHours() {
        return baseEstimatedHours;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | $%d | %.1f hrs | %s", id, name, baseUnitPrice, baseEstimatedHours, isActive ? "Activo" : "Inactivo");
    }
}
