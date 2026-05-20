package xyz.lorcasdev.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.lorcasdev.enums.ComplexityLevel;
import xyz.lorcasdev.exception.ElementNotFoundException;
import xyz.lorcasdev.model.Catalog;
import xyz.lorcasdev.model.CatalogItem;
import xyz.lorcasdev.model.Item;

public class CatalogItemRepository {

    private final Map<Integer, CatalogItem> catalogItems = new HashMap<>();
    private int counter = 1;

    public CatalogItem save(Catalog catalog, Item item,
            boolean isDefault, boolean isActive,
            ComplexityLevel complexityLevel,
            Integer priceOverride, Double hoursOverride) {
        int id = counter++;
        CatalogItem catalogItem = new CatalogItem(id, catalog, item, isDefault, isActive, complexityLevel, priceOverride, hoursOverride);
        catalogItems.put(id, catalogItem);
        return catalogItem;
    }

    public CatalogItem findById(int id) {
        CatalogItem catalogItem = catalogItems.get(id);
        if (catalogItem == null) {
            throw new ElementNotFoundException(id);
        }
        return catalogItem;
    }

    public List<CatalogItem> findAll() {
        return new ArrayList<>(catalogItems.values());
    }

    public List<CatalogItem> findByCatalogId(int catalogId) {
        return catalogItems.values().stream()
                .filter(serviceItem
                        -> serviceItem.getCatalog()
                        .getId() == catalogId)
                .toList();
    }

    public CatalogItem update(CatalogItem catalogItem) {
        catalogItems.put(catalogItem.getId(), catalogItem);
        return catalogItem;
    }

    public void delete(int id) {
        catalogItems.remove(id);
    }
}
