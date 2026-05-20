package xyz.lorcasdev.model;

import xyz.lorcasdev.enums.ComplexityLevel;

public class ServiceItem {

    private final int id;
    private final Service service;
    private final Item item;
    private boolean isDefault;
    private final ComplexityLevel complexityLevel;
    private Integer priceOverride;
    private Double hoursOverride;

    public ServiceItem(int id, Service service, Item item, boolean isDefault, ComplexityLevel complexityLevel, Integer priceOverride, Double hoursOverride) {
        Validator.positive(id, "id");
        if (priceOverride != null) {
            Validator.positive(priceOverride, "sobreescritura de precio");
        }
        if (hoursOverride != null) {
            Validator.notNegative(hoursOverride, "sobreescritura de horas");
        }

        this.id = id;
        this.service = service;
        this.item = item;
        this.isDefault = isDefault;
        this.complexityLevel = complexityLevel;
        this.priceOverride = priceOverride;
        this.hoursOverride = hoursOverride;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
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

    public Service getService() {
        return service;
    }

    public Item getItem() {
        return item;
    }

    public boolean isDefault() {
        return isDefault;
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

    @Override
    public String toString() {
        return String.format("ServiceItem[%d] - Service: %s, Item: %s (%s)", id, service.getName(), item.getName(), complexityLevel);
    }
}
