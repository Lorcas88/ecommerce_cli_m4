package xyz.lorcasdev.model;

public class QuoteItem {

    private final int id;
    private int quoteId;
    private int serviceItemId;
    private int quantity;
    private int unitPrice;
    private double estimatedHours;
    private int lineTotal;

    public QuoteItem(int id, int quoteId, int serviceItemId, int quantity, int unitPrice, double estimatedHours, int lineTotal) {
        Validator.positive(id, "id");
        Validator.positive(quoteId, "quote_id");
        Validator.positive(serviceItemId, "service_item_id");
        Validator.notNegative(quantity, "quantity");
        Validator.positive(unitPrice, "unit_price");
        Validator.positive(lineTotal, "line_total");

        this.id = id;
        this.quoteId = quoteId;
        this.serviceItemId = serviceItemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.estimatedHours = estimatedHours;
        this.lineTotal = lineTotal;
    }

    public void updateQuoteItem(Integer quantity, Integer unitPrice, Double estimatedHours, Integer lineTotal) {
        if (quantity != null) {
            Validator.positive(quantity, "quantity");
            this.quantity = quantity;
        }
        if (unitPrice != null) {
            Validator.positive(unitPrice, "unit_price");
            this.unitPrice = unitPrice;
        }
        if (estimatedHours != null) {
            Validator.notNegative(estimatedHours, "estimated_hours");
            this.estimatedHours = estimatedHours;
        }
        if (lineTotal != null) {
            Validator.positive(lineTotal, "line_total");
            this.lineTotal = lineTotal;
        }
    }

    public int getId() {
        return id;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public int getServiceItemId() {
        return serviceItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getEstimatedHours() {
        return estimatedHours;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    @Override
    public String toString() {
        return String.format("QuoteItem[%d] - Quote: %d | Cantidad: %d | Total Línea: $%.2f", id, quoteId, quantity, lineTotal);
    }
}
