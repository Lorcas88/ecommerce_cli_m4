package xyz.lorcasdev.service;

import java.time.LocalDateTime;

import xyz.lorcasdev.enums.QuoteStatus;
import xyz.lorcasdev.exception.EmptyCartException;
import xyz.lorcasdev.model.Customer;
import xyz.lorcasdev.model.Order;
import xyz.lorcasdev.model.Quote;
import xyz.lorcasdev.model.QuoteItem;
import xyz.lorcasdev.model.CatalogItem;
import xyz.lorcasdev.repository.QuoteItemRepository;
import xyz.lorcasdev.repository.QuoteRepository;

public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final QuoteItemRepository quoteItemRepository;

    public QuoteService(QuoteRepository quoteRepository, QuoteItemRepository quoteItemRepository) {
        this.quoteRepository = quoteRepository;
        this.quoteItemRepository = quoteItemRepository;
    }

    public Quote createQuote(Customer customer) {
        return createQuote(customer, null, null, null, null);
    }

    public Quote createGuestQuote(String guestName, String guestEmail, String guestPhone) {
        return createQuote(null, guestName, guestEmail, guestPhone, null);
    }

    public Quote createQuote(Customer customer, String guestName, String guestEmail, String guestPhone, QuoteStatus status) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validUntil = now.plusDays(15); // Cotización válida por 15 días por defecto

        return quoteRepository.save(
                customer, guestName, guestEmail, guestPhone,
                status, null, 0, 0, 0, 0, now, validUntil
        );
    }

    public QuoteItem addItemToQuote(int quoteId, CatalogItem catalogItem, int quantity) {
        Quote quote = quoteRepository.findById(quoteId);

        // Calculamos usando el override de precio si existe, si no, el base
        int unitPrice = catalogItem.getPriceOverride() != null ? catalogItem.getPriceOverride() : catalogItem.getItem().getBaseUnitPrice();
        double estimatedHours = catalogItem.getHoursOverride() != null ? catalogItem.getHoursOverride() : catalogItem.getItem().getBaseEstimatedHours();
        int lineTotal = unitPrice * quantity;

        QuoteItem quoteItem = quoteItemRepository.save(quote, catalogItem, quantity, unitPrice, estimatedHours, lineTotal);

        // Actualizamos los totales de la cotización
        quote.setSubtotal((int) quote.getSubtotal() + lineTotal);
        quote.setTotal((int) quote.getTotal() + lineTotal);
        quoteRepository.update(quote);

        return quoteItem;
    }

    public void removeItemFromQuote(int quoteItemId) {
        quoteItemRepository.delete(quoteItemId);
    }

    public QuoteItem updateItemQuantity(int quoteItemId, int newQuantity) {
        QuoteItem quoteItem = quoteItemRepository.findById(quoteItemId);
        quoteItem.setQuantity(newQuantity);
        quoteItem.setLineTotal((int) (quoteItem.getUnitPrice() * newQuantity));
        return quoteItemRepository.update(quoteItem);
    }

    public void enableOptionalItem(int quoteItemId) {
        throw new UnsupportedOperationException("El modelo actual de QuoteItem no soporta ítems opcionales.");
    }

    public void disableOptionalItem(int quoteItemId) {
        throw new UnsupportedOperationException("El modelo actual de QuoteItem no soporta ítems opcionales.");
    }

    public int calculateSubtotal(int quoteId) {
        return quoteItemRepository.findAll().stream()
                .filter(qi -> qi.getQuote().getId() == quoteId)
                .mapToInt(QuoteItem::getLineTotal)
                .sum();
    }

    public double calculateEstimatedHours(int quoteId) {
        return quoteItemRepository.findAll().stream()
                .filter(qi -> qi.getQuote().getId() == quoteId)
                .mapToDouble(qi -> qi.getEstimatedHours() * qi.getQuantity())
                .sum();
    }

    public int calculateTotal(int quoteId) {
        Quote quote = quoteRepository.findById(quoteId);
        return (int) quote.getSubtotal() - (quote.getDiscountAmount() != null ? quote.getDiscountAmount() : 0);
    }

    public void sendQuote(int quoteId) {
        // Asume que QuoteStatus.SENT existe en tu enum
        // quoteRepository.findById(quoteId).setStatus(QuoteStatus.SENT);
    }

    public void approveQuote(int quoteId) {
        // quoteRepository.findById(quoteId).setStatus(QuoteStatus.APPROVED);
    }

    public void rejectQuote(int quoteId) {
        // quoteRepository.findById(quoteId).setStatus(QuoteStatus.REJECTED);
    }

    public void expireQuote(int quoteId) {
        // quoteRepository.findById(quoteId).setStatus(QuoteStatus.EXPIRED);
    }

    public Order convertToOrder(int quoteId) {
        throw new UnsupportedOperationException("Por favor utilice OrderService.createOrderFromQuote(...) para evitar dependencias circulares.");
    }

    public void checkCartNotEmpty(int quoteId) {
        if (quoteItemRepository.findAll().stream().noneMatch(item -> item.getQuote().getId() == quoteId)) {
            throw new EmptyCartException();
        }
    }
}
