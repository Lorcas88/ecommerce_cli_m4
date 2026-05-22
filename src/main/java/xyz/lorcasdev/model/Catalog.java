package xyz.lorcasdev.model;

public class Catalog {

    private final int id;
    private String name;
    private String description;
    private int price;
    private boolean isActive;
    private double estimatedBaseHours;
    
    public Catalog(int id, String name, String description, boolean isActive) {
        Validator.positive(id, "id");
        Validator.notEmpty(name, "nombre");
        
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }

    public void setName(String name) {
        Validator.notEmpty(name, "nombre");
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
    	Validator.notNegative(price, "precio");
        this.price= price;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void setEstimatedBaseHours(double estimatedBaseHours) {
        Validator.notNegative(estimatedBaseHours, "horas base estimadas");
        this.estimatedBaseHours = estimatedBaseHours;
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

    public void toggleActive() {
        isActive = !isActive;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | $%,d | %.1f hrs | %s",
                id, name, price, estimatedBaseHours,
                isActive ? "Activo" : "Inactivo");
    }
}
