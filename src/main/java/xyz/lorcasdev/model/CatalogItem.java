package xyz.lorcasdev.model;

import java.util.Comparator;

import xyz.lorcasdev.enums.ComplexityLevel;

public class CatalogItem {

    public static final Comparator<CatalogItem> BY_PRICE
            = Comparator.comparingInt(
                    CatalogItem::getFinalPrice
            );

    public static final Comparator<CatalogItem> BY_NAME
            = Comparator.comparing(
                    ci -> ci.getItem()
                            .getName()
                            .toLowerCase()
            );

    private final int id;
    private final Catalog catalog;
    private final Item item;
    private boolean isOptional;
    private boolean isActive;
    private ComplexityLevel complexityLevel;
    private Integer priceOverride;
    private Double hoursOverride;

    public CatalogItem(int id, Catalog catalog, Item item, Boolean isOptional, Boolean isActive, ComplexityLevel complexityLevel, Integer priceOverride, Double hoursOverride) {
        Validator.positive(id, "id");
        if (priceOverride != null) {
            Validator.positive(priceOverride, "sobreescritura de precio");
        }
        if (hoursOverride != null) {
            Validator.notNegative(hoursOverride, "sobreescritura de horas");
        }

        this.id = id;
        this.catalog = catalog;
        this.item = item;
        this.isOptional = isOptional != null
                ? isOptional
                : false;
        this.isActive = isActive != null
                ? isActive
                : true;
        this.complexityLevel = complexityLevel != null
                ? complexityLevel
                : ComplexityLevel.MEDIUM;
        this.priceOverride = priceOverride;
        this.hoursOverride = hoursOverride;
    }

    public void setIsOptional(boolean isOptional) {
        this.isOptional = isOptional;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setComplexityLevel(ComplexityLevel complexityLevel) {
        this.complexityLevel = complexityLevel;
    }

    public void setPriceOverride(Integer priceOverride) {
        if (priceOverride != null) {
            Validator.positive(priceOverride, "sobreescritura de precio");
        }
        this.priceOverride = priceOverride;
    }

    public void setHoursOverride(Double hoursOverride) {
        if (hoursOverride != null) {
            Validator.notNegative(hoursOverride, "sobreescritura de horas");
        }
        this.hoursOverride = hoursOverride;
    }

    public int getId() {
        return id;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public Item getItem() {
        return item;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public boolean isActive() {
        return isActive;
    }

    public ComplexityLevel getComplexityLevel() {
        return complexityLevel;
    }

    public Integer getPriceOverride() {
        return priceOverride;
    }

    public Double getHoursOverride() {
        return hoursOverride;
    }

    /**
     * Método para verificar el estado de el catalogo o el item. Esto será usado
     * para verificar si será posible agregar un item
     */
    public boolean isAvailable() {
        return isActive
                && catalog.isActive()
                && item.isActive();
    }

    public int getFinalPrice() {
        return priceOverride != null
                ? priceOverride
                : item.getBaseUnitPrice();
    }

    public double getFinalEstimatedHours() {
        return hoursOverride != null
                ? hoursOverride
                : item.getBaseEstimatedHours();
    }

    public void toggleActive() {
        isActive = !isActive;
    }

    @Override
    public String toString() {
        return String.format("CatalogItem[%d] - Service: %s, Item: %s (%s) | %,d | %.1f hrs | %s | %s",
                id, catalog.getName(), item.getName(), complexityLevel, getFinalPrice(), getFinalEstimatedHours(),
                isOptional ? "Opcional" : "Obligatorio",
                isActive ? "Activo" : "Inactivo");
    }
}
