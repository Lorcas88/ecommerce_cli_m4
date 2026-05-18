package xyz.lorcasdev.model;

public class ServiceItem {

    private final int id;
    private int serviceId;
    private int itemId;
    private boolean isDefault;
    private String complexityLevel;
    private Integer priceOverride;
    private Double hoursOverride;

    public ServiceItem(int id, int serviceId, int itemId, boolean isDefault, String complexityLevel, Integer priceOverride, Double hoursOverride) {
        Validator.positive(id, "id");
        Validator.positive(serviceId, "service_id");
        Validator.positive(itemId, "item_id");
        Validator.notEmpty(complexityLevel, "nivel de complejidad");
        if (priceOverride != null) {
            Validator.positive(priceOverride, "sobreescritura de precio");
        }
        if (hoursOverride != null) {
            Validator.notNegative(hoursOverride, "sobreescritura de horas");
        }

        this.id = id;
        this.serviceId = serviceId;
        this.itemId = itemId;
        this.isDefault = isDefault;
        this.complexityLevel = complexityLevel;
        this.priceOverride = priceOverride;
        this.hoursOverride = hoursOverride;
    }

    public void updateServiceItem(Integer serviceId, Integer itemId, Boolean isDefault, String complexityLevel, Integer priceOverride, Double hoursOverride) {
        if (serviceId != null) {
        	Validator.positive(serviceId, "service_id");
            this.serviceId = serviceId;
        }
        if (itemId != null) {
        	Validator.positive(itemId, "item_id");
            this.itemId = itemId;
        }
        if (isDefault != null) {
            this.isDefault = isDefault;
        }
        if (complexityLevel != null) {
        	Validator.notEmpty(complexityLevel, "nivel de complejidad");
            this.complexityLevel = complexityLevel;
        }
        if (priceOverride != null) {
        	Validator.positive(priceOverride, "sobreescritura de precio");
            this.priceOverride = priceOverride;
        }
        if (hoursOverride != null) {
        	Validator.notNegative(hoursOverride, "sobreescritura de horas");
            this.hoursOverride = hoursOverride;
        }
    }

    public int getId() {
        return id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public int getItemId() {
        return itemId;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public String getComplexityLevel() {
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
        return String.format("ServiceItem[%d] - Service: %d, Item: %d (%s)", id, serviceId, itemId, complexityLevel);
    }
}
