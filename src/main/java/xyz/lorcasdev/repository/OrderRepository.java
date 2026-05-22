package xyz.lorcasdev.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.lorcasdev.enums.OrderStatus;
import xyz.lorcasdev.exception.ElementNotFoundException;
import xyz.lorcasdev.model.Customer;
import xyz.lorcasdev.model.Order;
import xyz.lorcasdev.model.Quote;

public class OrderRepository {

    private final Map<Integer, Order> orders = new HashMap<>();
    private int counter = 1;

    public Order save(Customer customer, Quote quote, OrderStatus status, int subtotal, int discountAmount, int total, LocalDateTime createdAt, LocalDateTime startDate, LocalDateTime estimatedDeliveryDate) {
        int id = counter++;
        Order order = new Order(id, customer, quote, status, subtotal, discountAmount, total, createdAt, startDate, estimatedDeliveryDate);
        orders.put(id, order);
        return order;
    }

    public Order findById(int id) {
        Order order = orders.get(id);
        if (order == null) {
            throw new ElementNotFoundException(id);
        }
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public Order update(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    public void delete(int id) {
        orders.remove(id);
    }
}
