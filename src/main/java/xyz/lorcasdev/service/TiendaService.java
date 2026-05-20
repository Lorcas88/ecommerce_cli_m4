package xyz.lorcasdev.service;

import java.time.LocalDateTime;
import java.util.List;

import xyz.lorcasdev.enums.OrderStatus;
import xyz.lorcasdev.model.Order;
import xyz.lorcasdev.model.Quote;
import xyz.lorcasdev.model.QuoteItem;
import xyz.lorcasdev.repository.QuoteItemRepository;
import xyz.lorcasdev.repository.QuoteRepository;

public class TiendaService {

    private final QuoteService quoteService;
    private final OrderService orderService;
    private final QuoteRepository quoteRepository;
    private final QuoteItemRepository quoteItemRepository;

    public TiendaService(QuoteService quoteService, OrderService orderService, QuoteRepository quoteRepository, QuoteItemRepository quoteItemRepository) {
        this.quoteService = quoteService;
        this.orderService = orderService;
        this.quoteRepository = quoteRepository;
        this.quoteItemRepository = quoteItemRepository;
    }

    public Order confirmQuoteAndCreateOrder(int quoteId, OrderStatus initialOrderStatus, LocalDateTime estimatedDeliveryDate) {
        // 1. Validar que la cotización tenga al menos un ítem
        quoteService.checkCartNotEmpty(quoteId);

        // 2. Obtener la cotización original
        Quote quote = quoteRepository.findById(quoteId);

        // 3. Crear la orden principal
        Order order = orderService.createOrderFromQuote(quote, initialOrderStatus, estimatedDeliveryDate);

        // 4. Obtener los ítems de la cotización y agregarlos a la orden
        List<QuoteItem> quoteItems = quoteItemRepository.findAll().stream()
                .filter(qi -> qi.getQuote().getId() == quoteId)
                .toList();

        for (QuoteItem quoteItem : quoteItems) {
            orderService.addOrderItem(order.getId(), quoteItem.getCatalogItem(), quoteItem.getQuantity());
        }

        // 5. Marcar la cotización original como aprobada
        quoteService.approveQuote(quoteId);

        return order;
    }
}
