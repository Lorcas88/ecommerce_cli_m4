package xyz.lorcasdev.model;

public class OrderItemSlot {

    private final int id;
    private int orderItemId;
    private int slotId;
    private double assignedHours;

    public OrderItemSlot(int id, int orderItemId, int slotId, double assignedHours) {
        Validator.positive(id, "id");
        Validator.positive(orderItemId, "order_item_id");
        Validator.positive(slotId, "slot_id");
        Validator.notNegative(assignedHours, "assigned_hours");

        this.id = id;
        this.orderItemId = orderItemId;
        this.slotId = slotId;
        this.assignedHours = assignedHours;
    }

    public void updateOrderItemSlot(Double assignedHours) {
        if (assignedHours != null) {
            Validator.notNegative(assignedHours, "assigned_hours");
            this.assignedHours = assignedHours;
        }
    }

    public int getId() {
        return id;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public int getSlotId() {
        return slotId;
    }

    public double getAssignedHours() {
        return assignedHours;
    }

    @Override
    public String toString() {
        return String.format("OrderItemSlot[%d] - OrderItem: %d | Slot: %d | Hours: %.1f", id, orderItemId, slotId, assignedHours);
    }
}
