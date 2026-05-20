package xyz.lorcasdev.model;

public class OrderItem {

    private final int id;
    private final Order order;
    private final CatalogItem catalogItem;
    private int quantity;
    private int unitPrice;
    private int lineTotal;
    private double estimatedHours;

    public OrderItem(int id, Order order, CatalogItem catalogItem, int quantity, int unitPrice, int lineTotal, double estimatedHours) {
        Validator.positive(id, "id");
        Validator.notNegative(quantity, "quantity");
        Validator.positive(unitPrice, "unit_price");
        Validator.positive(lineTotal, "line_total");

        this.id = id;
        this.order = order;
        this.catalogItem = catalogItem;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
        this.estimatedHours = estimatedHours;
    }

    public void setQuantity(int quantity) {
        Validator.notNegative(quantity, "quantity");
        this.quantity = quantity;
    }

    public void setUnitPrice(int unitPrice) {
        Validator.positive(unitPrice, "unit_price");
        this.unitPrice = unitPrice;
    }

    public void setLineTotal(int lineTotal) {
        Validator.positive(lineTotal, "line_total");
        this.lineTotal = lineTotal;
    }

    public void setEstimatedHours(double estimatedHours) {
        Validator.notNegative(estimatedHours, "estimated_hours");
        this.estimatedHours = estimatedHours;
    }

    public int getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public CatalogItem getServiceItem() {
        return catalogItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public double getEstimatedHours() {
        return estimatedHours;
    }

    @Override
    public String toString() {
        return String.format("OrderItem[%d] - Order: %d | Cantidad: %d | Total: $%d", id, order.getId(), quantity, lineTotal);
    }
}
