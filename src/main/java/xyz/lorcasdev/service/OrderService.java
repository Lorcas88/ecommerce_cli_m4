package xyz.lorcasdev.service;

import java.time.LocalDateTime;
import java.util.List;

import xyz.lorcasdev.enums.OrderStatus;
import xyz.lorcasdev.exception.EmptyCartException;
import xyz.lorcasdev.model.CatalogItem;
import xyz.lorcasdev.model.Order;
import xyz.lorcasdev.model.OrderItem;
import xyz.lorcasdev.model.Quote;
import xyz.lorcasdev.repository.OrderItemRepository;
import xyz.lorcasdev.repository.OrderRepository;

public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Order createOrderFromQuote(Quote quote, OrderStatus status, LocalDateTime estimatedDeliveryDate) {
        LocalDateTime now = LocalDateTime.now();

        return orderRepository.save(
                quote.getCustomer(),
                quote,
                status,
                (int) quote.getSubtotal(),
                quote.getDiscountAmount(),
                (int) quote.getTotal(),
                now,
                now, // fecha de inicio
                estimatedDeliveryDate
        );
    }

    public OrderItem addOrderItem(int orderId, CatalogItem catalogItem, int quantity) {
        Order order = orderRepository.findById(orderId);

        int unitPrice = catalogItem.getPriceOverride() != null ? catalogItem.getPriceOverride() : catalogItem.getItem().getBaseUnitPrice();
        double estimatedHours = catalogItem.getHoursOverride() != null ? catalogItem.getHoursOverride() : catalogItem.getItem().getBaseEstimatedHours();
        int lineTotal = unitPrice * quantity;

        return orderItemRepository.save(order, catalogItem, quantity, unitPrice, lineTotal, estimatedHours);
    }

    public Order findOrderById(int id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(int id, OrderStatus status) {
        Order order = orderRepository.findById(id);
        order.setStatus(status);
        return orderRepository.update(order);
    }

    public LocalDateTime calculateEstimatedDelivery(int orderId) {
        Order order = orderRepository.findById(orderId);
        double totalHours = getOrderItems(orderId).stream()
                .mapToDouble(oi -> oi.getEstimatedHours() * oi.getQuantity())
                .sum();

        // Asumimos un día laborable de 8 horas para estimar la fecha
        long daysRequired = (long) Math.ceil(totalHours / 8.0);
        return order.getStartDate().plusDays(daysRequired);
    }

    public Order assignStartDate(int orderId, LocalDateTime startDate) {
        Order order = orderRepository.findById(orderId);
        order.setStartDate(startDate);
        return orderRepository.update(order);
    }

    public List<OrderItem> getOrderItems(int orderId) {
        return orderItemRepository.findAll().stream()
                .filter(oi -> oi.getOrder().getId() == orderId)
                .toList();
    }

    public void checkOrderNotEmpty(int orderId) {
        if (getOrderItems(orderId).isEmpty()) {
            throw new EmptyCartException("La orden está vacía. Agregue al menos un ítem antes de continuar.");
        }
    }
}
