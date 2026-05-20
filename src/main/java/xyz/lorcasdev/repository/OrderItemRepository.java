package xyz.lorcasdev.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.lorcasdev.exception.ElementNotFoundException;
import xyz.lorcasdev.model.CatalogItem;
import xyz.lorcasdev.model.Order;
import xyz.lorcasdev.model.OrderItem;

public class OrderItemRepository {

    private final Map<Integer, OrderItem> orderItems = new HashMap<>();
    private int counter = 1;

    public OrderItem save(Order order, CatalogItem catalogItem, int quantity, int unitPrice, int lineTotal, double estimatedHours) {
        int id = counter++;
        OrderItem orderItem = new OrderItem(id, order, catalogItem, quantity, unitPrice, lineTotal, estimatedHours);
        orderItems.put(id, orderItem);
        return orderItem;
    }

    public OrderItem findById(int id) {
        OrderItem orderItem = orderItems.get(id);
        if (orderItem == null) {
            throw new ElementNotFoundException(id);
        }
        return orderItem;
    }

    public List<OrderItem> findAll() {
        return new ArrayList<>(orderItems.values());
    }

    public OrderItem update(OrderItem orderItem) {
        orderItems.put(orderItem.getId(), orderItem);
        return orderItem;
    }

    public void delete(int id) {
        orderItems.remove(id);
    }
}
