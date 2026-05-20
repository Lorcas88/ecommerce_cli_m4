package xyz.lorcasdev.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.lorcasdev.exception.ElementNotFoundException;
import xyz.lorcasdev.model.CatalogItem;
import xyz.lorcasdev.model.Quote;
import xyz.lorcasdev.model.QuoteItem;

public class QuoteItemRepository {

    private final Map<Integer, QuoteItem> quoteItems = new HashMap<>();
    private int counter = 1;

    public QuoteItem save(Quote quote, CatalogItem catalogItem, int quantity, int unitPrice, double estimatedHours, int lineTotal) {
        int id = counter++;
        QuoteItem quoteItem = new QuoteItem(id, quote, catalogItem, quantity, unitPrice, estimatedHours, lineTotal);
        quoteItems.put(id, quoteItem);
        return quoteItem;
    }

    public QuoteItem findById(int id) {
        QuoteItem quoteItem = quoteItems.get(id);
        if (quoteItem == null) {
            throw new ElementNotFoundException(id);
        }
        return quoteItem;
    }

    public List<QuoteItem> findAll() {
        return new ArrayList<>(quoteItems.values());
    }

    public QuoteItem update(QuoteItem quoteItem) {
        quoteItems.put(quoteItem.getId(), quoteItem);
        return quoteItem;
    }

    public void delete(int id) {
        quoteItems.remove(id);
    }
}
