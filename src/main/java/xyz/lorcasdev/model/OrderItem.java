package xyz.lorcasdev.model;

public class OrderItem {

    private final int id;
    private int orderId;
    private int serviceItemId;
    private int quantity;
    private int unitPrice;
    private int lineTotal;
    private double estimatedHours;

    public OrderItem(int id, int orderId, int serviceItemId, int quantity, int unitPrice, int lineTotal, double estimatedHours) {
        Validator.positive(id, "id");
        Validator.positive(orderId, "order_id");
        Validator.positive(serviceItemId, "service_item_id");
        Validator.notNegative(quantity, "quantity");
        Validator.positive(unitPrice, "unit_price");
        Validator.positive(lineTotal, "line_total");

        this.id = id;
        this.orderId = orderId;
        this.serviceItemId = serviceItemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
        this.estimatedHours = estimatedHours;
    }

    public void updateOrderItem(Integer quantity, Integer unitPrice, Integer lineTotal, Double estimatedHours) {
        if (quantity != null) {
        	Validator.positive(quantity, "quantity");
            this.quantity = quantity;
        }
        if (unitPrice != null) {
        	Validator.positive(unitPrice, "unit_price");
            this.unitPrice = unitPrice;
        }
        if (lineTotal != null) {
        	Validator.positive(lineTotal, "line_total");
            this.lineTotal = lineTotal;
        }
        if (estimatedHours != null) {
        	Validator.notNegative(estimatedHours, "estimated_hours");
            this.estimatedHours = estimatedHours;
        }
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
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

    public double getLineTotal() {
        return lineTotal;
    }

    public double getEstimatedHours() {
        return estimatedHours;
    }

    @Override
    public String toString() {
        return String.format("OrderItem[%d] - Order: %d | Cantidad: %d | Total: $%.2f", id, orderId, quantity, lineTotal);
    }
}
