package xyz.lorcasdev.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.lorcasdev.exception.ElementNotFoundException;
import xyz.lorcasdev.model.AvailabilitySlot;

public class AvailabilitySlotRepository {

    private final Map<Integer, AvailabilitySlot> slots = new HashMap<>();
    private int counter = 1;

    public AvailabilitySlot save(LocalDate slotDate, double totalHours, double reservedHours, int priorityLevel) {
        int id = counter++;
        AvailabilitySlot slot = new AvailabilitySlot(id, slotDate, totalHours, reservedHours, priorityLevel);
        slots.put(id, slot);
        return slot;
    }

    public AvailabilitySlot findById(int id) {
        AvailabilitySlot slot = slots.get(id);
        if (slot == null) {
            throw new ElementNotFoundException(id);
        }
        return slot;
    }

    public List<AvailabilitySlot> findAll() {
        return new ArrayList<>(slots.values());
    }

    public AvailabilitySlot update(AvailabilitySlot slot) {
        slots.put(slot.getId(), slot);
        return slot;
    }

    public void delete(int id) {
        slots.remove(id);
    }
}
