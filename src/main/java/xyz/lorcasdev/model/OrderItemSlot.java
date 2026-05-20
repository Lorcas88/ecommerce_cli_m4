package xyz.lorcasdev.model;

public class OrderItemSlot {

    private final int id;
    private final OrderItem orderItem;
    private final AvailabilitySlot slot;
    private double assignedHours;

    public OrderItemSlot(int id, OrderItem orderItem, AvailabilitySlot slot, double assignedHours) {
        Validator.positive(id, "id");
        Validator.notNegative(assignedHours, "assigned_hours");

        this.id = id;
        this.orderItem = orderItem;
        this.slot = slot;
        this.assignedHours = assignedHours;
    }

    public void setAssignedHours(double assignedHours) {
        Validator.notNegative(assignedHours, "assigned_hours");
        this.assignedHours = assignedHours;
    }

    public int getId() {
        return id;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public AvailabilitySlot getSlot() {
        return slot;
    }

    public double getAssignedHours() {
        return assignedHours;
    }

    @Override
    public String toString() {
        return String.format("OrderItemSlot[%d] - OrderItem: %d | Slot: %d | Hours: %.1f", id, orderItem.getId(), slot.getId(), assignedHours);
    }
}
