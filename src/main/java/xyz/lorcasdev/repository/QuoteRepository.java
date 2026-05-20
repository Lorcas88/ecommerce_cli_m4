package xyz.lorcasdev.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.lorcasdev.enums.DiscountType;
import xyz.lorcasdev.enums.QuoteStatus;
import xyz.lorcasdev.exception.ElementNotFoundException;
import xyz.lorcasdev.model.Customer;
import xyz.lorcasdev.model.Quote;

public class QuoteRepository {

    private final Map<Integer, Quote> quotes = new HashMap<>();
    private int counter = 1;

    public Quote save(Customer customer, String guestName, String guestEmail, String guestPhone, QuoteStatus status, DiscountType discountType, int discountValue, int discountAmount, int subtotal, int total, LocalDateTime createdAt, LocalDateTime validUntil) {
        int id = counter++;
        Quote quote = new Quote(id, customer, guestName, guestEmail, guestPhone, status, discountType, discountValue, discountAmount, subtotal, total, createdAt, validUntil);
        quotes.put(id, quote);
        return quote;
    }

    public Quote findById(int id) {
        Quote quote = quotes.get(id);
        if (quote == null) {
            throw new ElementNotFoundException(id);
        }
        return quote;
    }

    public List<Quote> findAll() {
        return new ArrayList<>(quotes.values());
    }

    public Quote update(Quote quote) {
        quotes.put(quote.getId(), quote);
        return quote;
    }

    public void delete(int id) {
        quotes.remove(id);
    }
}
