package xyz.lorcasdev.model;

public class QuoteItem {

    private final int id;
    private final Quote quote;
    private final CatalogItem catalogItem;
    private int quantity;
    private int unitPrice;
    private double estimatedHours;
    private int lineTotal;

    public QuoteItem(int id, Quote quote, CatalogItem catalogItem, int quantity, int unitPrice, double estimatedHours, int lineTotal) {
        Validator.positive(id, "id");
        Validator.notNegative(quantity, "quantity");
        Validator.positive(unitPrice, "unit_price");
        Validator.positive(lineTotal, "line_total");

        this.id = id;
        this.quote = quote;
        this.catalogItem = catalogItem;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.estimatedHours = estimatedHours;
        this.lineTotal = lineTotal;
    }

    public void setQuantity(int quantity) {
        Validator.notNegative(quantity, "quantity");
        this.quantity = quantity;
    }

    public void setUnitPrice(int unitPrice) {
        Validator.positive(unitPrice, "unit_price");
        this.unitPrice = unitPrice;
    }

    public void setEstimatedHours(double estimatedHours) {
        Validator.notNegative(estimatedHours, "estimated_hours");
        this.estimatedHours = estimatedHours;
    }

    public void setLineTotal(int lineTotal) {
        Validator.positive(lineTotal, "line_total");
        this.lineTotal = lineTotal;
    }

    public int getId() {
        return id;
    }

    public Quote getQuote() {
        return quote;
    }

    public CatalogItem getCatalogItem() {
        return catalogItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public double getEstimatedHours() {
        return estimatedHours;
    }

    public int getLineTotal() {
        return lineTotal;
    }

    @Override
    public String toString() {
        return String.format("QuoteItem[%d] - Servicio: %s, Ítem: %s | Cantidad: %d | Total Línea: $%d",
                id, catalogItem.getCatalog().getName(), catalogItem.getItem().getName(), quantity, lineTotal);
    }
}
