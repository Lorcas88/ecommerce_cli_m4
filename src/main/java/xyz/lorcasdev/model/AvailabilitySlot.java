package xyz.lorcasdev.model;

import java.time.LocalDate;

public class AvailabilitySlot {

    private final int id;
    private LocalDate slotDate;
    private double totalHours;
    private double reservedHours;
    private int priorityLevel;

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

    public void updateAvailabilitySlot(LocalDate slotDate, Double totalHours, Double reservedHours, Integer priorityLevel) {
        if (slotDate != null) {
            this.slotDate = slotDate;
        }
        if (totalHours != null) {
            Validator.notNegative(totalHours, "total_hours");
            this.totalHours = totalHours;
        }
        if (reservedHours != null) {
            Validator.notNegative(reservedHours, "reserved_hours");
            this.reservedHours = reservedHours;
        }
        if (priorityLevel != null) {
            this.priorityLevel = priorityLevel;
        }
    }

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
