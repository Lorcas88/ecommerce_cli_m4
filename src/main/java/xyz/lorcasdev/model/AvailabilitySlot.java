package xyz.lorcasdev.model;

import java.time.LocalDate;

public class AvailabilitySlot {

    private final int id;
    private LocalDate slotDate;
    private double totalHours;
    private double reservedHours;
    private int priorityLevel;

    // Constructor
    public AvailabilitySlot(int id, LocalDate slotDate, double totalHours, double reservedHours, int priorityLevel) {
        Validator.positive(id, "id");
        Validator.notNull(slotDate, "slot_date");
        Validator.notNegative(totalHours, "total_hours");
        Validator.notNegative(reservedHours, "reserved_hours");

        this.id = id;
        this.slotDate = slotDate;
        this.totalHours = totalHours;
        this.reservedHours = reservedHours;
        this.priorityLevel = priorityLevel;
    }

    // Setters
    public void setSlotDate(LocalDate slotDate) {
        Validator.notNull(slotDate, "slot_date");
        this.slotDate = slotDate;
    }

    public void setTotalHours(double totalHours) {
        Validator.notNegative(totalHours, "total_hours");
        this.totalHours = totalHours;
    }

    public void setReservedHours(double reservedHours) {
        Validator.notNegative(reservedHours, "reserved_hours");
        this.reservedHours = reservedHours;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    // Getters
    public int getId() {
        return id;
    }

    public LocalDate getSlotDate() {
        return slotDate;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public double getReservedHours() {
        return reservedHours;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    @Override
    public String toString() {
        return String.format("AvailabilitySlot[%d] - Date: %s | Reserved: %.1f/%.1f", id, slotDate, reservedHours, totalHours);
    }
}
