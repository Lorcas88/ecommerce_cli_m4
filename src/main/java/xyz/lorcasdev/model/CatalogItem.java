package xyz.lorcasdev.model;

import java.util.Comparator;

import xyz.lorcasdev.enums.ComplexityLevel;

public class CatalogItem {

    public static final Comparator<CatalogItem> BY_PRICE
            = Comparator.comparingDouble(
                    CatalogItem::getFinalPrice
            );

    public static final Comparator<CatalogItem> BY_NAME
            = Comparator.comparing(
                    ci -> ci.getItem()
                            .getName()
                            .toLowerCase()
            );

    public static final Comparator<CatalogItem> BY_COMPLEXITY
            = Comparator.comparing(
                    CatalogItem::getComplexityLevel
            );

    private final int id;
    private final Catalog catalog;
    private final Item item;
    private boolean isDefault;
    private boolean isActive;
    private final ComplexityLevel complexityLevel;
    private Integer priceOverride;
    private Double hoursOverride;

    public CatalogItem(int id, Catalog catalog, Item item, boolean isDefault, boolean isActive, ComplexityLevel complexityLevel, Integer priceOverride, Double hoursOverride) {
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
        this.isDefault = isDefault;
        this.isActive = isActive;
        this.complexityLevel = complexityLevel;
        this.priceOverride = priceOverride;
        this.hoursOverride = hoursOverride;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
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

    public boolean isDefault() {
        return isDefault;
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

    public double getFinalPrice() {
        return priceOverride != null
                ? priceOverride
                : item.getBaseUnitPrice();
    }

    public double getFinalEstimatedHours() {
        return hoursOverride != null
                ? hoursOverride
                : item.getBaseEstimatedHours();
    }

    @Override
    public String toString() {
        return String.format("CatalogItem[%d] - Service: %s, Item: %s (%s)", id, catalog.getName(), item.getName(), complexityLevel);
    }
}
