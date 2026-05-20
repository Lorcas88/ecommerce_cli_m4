package xyz.lorcasdev.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.lorcasdev.exception.ElementNotFoundException;
import xyz.lorcasdev.model.Item;

public class ItemRepository {

    private final Map<Integer, Item> items = new HashMap<>();
    private int itemCounter = 1;

    public Item save(String name, String description, int baseUnitPrice, double baseEstimatedHours, boolean isActive) {
        int id = itemCounter++;
        Item item = new Item(id, name, description, baseUnitPrice, baseEstimatedHours, isActive);
        items.put(id, item);
        return item;
    }

    public Item findById(int id) {
        Item item = items.get(id);
        if (item == null) {
            throw new ElementNotFoundException(id);
        }
        return item;
    }

    public List<Item> findAll() {
        return new ArrayList<>(items.values());
    }

    public Item update(Item item) {
        items.put(item.getId(), item);
        return item;
    }

    public void deactivate(int id) {
        findById(id).setActive(false);
    }
}
