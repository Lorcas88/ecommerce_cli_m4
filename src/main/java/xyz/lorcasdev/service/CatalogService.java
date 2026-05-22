package xyz.lorcasdev.service;

import java.util.List;

import xyz.lorcasdev.enums.ComplexityLevel;
import xyz.lorcasdev.model.Catalog;
import xyz.lorcasdev.model.CatalogItem;
import xyz.lorcasdev.model.Item;
import xyz.lorcasdev.repository.CatalogItemRepository;
import xyz.lorcasdev.repository.CatalogRepository;
import xyz.lorcasdev.repository.ItemRepository;

public class CatalogService {

    private final CatalogRepository catalogRepository;
    private final ItemRepository itemRepository;
    private final CatalogItemRepository catalogItemRepository;

    public CatalogService(
            CatalogRepository catalogRepository,
            ItemRepository itemRepository,
            CatalogItemRepository catalogItemRepository) {
        this.catalogRepository = catalogRepository;
        this.itemRepository = itemRepository;
        this.catalogItemRepository = catalogItemRepository;
    }

    // Catalog
    public Catalog createCatalog(String name, String description, boolean isActive) {
        return catalogRepository.save(name, description, isActive);
    }

    public Catalog updateCatalog(Integer id, String newName, String newDescription, Boolean newState) {
        Catalog catalog = catalogRepository.findById(id);

        if (newName != null && !newName.isBlank()) {
            catalog.setName(newName);
        }

        if (newDescription != null) {
            catalog.setDescription(newDescription);
        }

        return catalogRepository.update(catalog);
    }

    public void toggleCatalogStatus(Integer id) {

        Catalog catalog = catalogRepository.findById(id);

        catalog.toggleActive();
    }

    public Catalog findCatalogById(int id) {
        return catalogRepository.findById(id);
    }

    public List<Catalog> findAllCatalogs() {
        return catalogRepository.findAll();
    }

    public void recalculateCatalogPrice(Catalog catalog) {

        int total = catalogItemRepository.findByCatalogId(catalog.getId())
                .stream()
                .filter(ci -> ci.isActive() && !ci.isOptional())
                .mapToInt(CatalogItem::getFinalPrice)
                .sum();

        catalog.setPrice(total);
    }

    public void recalculateCatalogHours(Catalog catalog) {

        double total = catalogItemRepository.findByCatalogId(catalog.getId())
                .stream()
                .filter(ci -> ci.isActive() && !ci.isOptional())
                .mapToDouble(CatalogItem::getFinalEstimatedHours)
                .sum();

        catalog.setEstimatedBaseHours(total);
    }

    public List<Catalog> findActiveCatalogs() {
        return catalogRepository.findAll().stream()
                .filter(Catalog::isActive)
                .toList();
    }

    // Item
    public Item createItem(String name, String description, int baseUnitPrice, double baseEstimatedHours, boolean isActive) {
        return itemRepository.save(name, description, baseUnitPrice, baseEstimatedHours, isActive);
    }

    public Item updateItem(int id, String newName, String newDescription, Integer newBaseUnitPrice, Double newBaseEstimatedHours) {
        Item item = itemRepository.findById(id);
        if (newName != null && !newName.isBlank()) {
            item.setName(newName);
        }
        if (newDescription != null) {
            item.setDescription(newDescription);
        }
        if (newBaseUnitPrice != null) {
            item.setBaseUnitPrice(newBaseUnitPrice);
        }
        if (newBaseEstimatedHours != null) {
            item.setBaseEstimatedHours(newBaseEstimatedHours);
        }
        return itemRepository.update(item);
    }

    public Item findItemById(int id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> findActiveItems() {
        return itemRepository.findAll().stream()
                .filter(Item::isActive)
                .toList();
    }

    // CatalogItem
    public CatalogItem createCatalogItem(int catalogId, int itemId,
            Boolean isOptional, Boolean isActive, ComplexityLevel complexityLevel,
            Integer priceOverride, Double hoursOverride) {

        Catalog catalog = catalogRepository.findById(catalogId);
        Item item = itemRepository.findById(itemId);

        CatalogItem catalogItem = catalogItemRepository.save(catalog, item, isOptional, isActive, complexityLevel, priceOverride, hoursOverride);

        recalculateCatalogPrice(catalog);
        recalculateCatalogHours(catalog);

        return catalogItem;
    }

    public CatalogItem updateCatalogItem(int id, Boolean isOptional, Boolean isActive, ComplexityLevel complexityLevel, Integer priceOverride, Double hoursOverride) {
        CatalogItem catalogItem = catalogItemRepository.findById(id);
        if (isActive != null) {
            catalogItem.setActive(isActive);
        }
        if (complexityLevel != null) {
            catalogItem.setComplexityLevel(complexityLevel);
        }
        if (priceOverride != null) {
            catalogItem.setPriceOverride(priceOverride);
        }
        if (hoursOverride != null) {
            catalogItem.setHoursOverride(hoursOverride);
        }

        CatalogItem updatedCatalogItem = catalogItemRepository.update(catalogItem);

        recalculateCatalogPrice(catalogItem.getCatalog());
        recalculateCatalogHours(catalogItem.getCatalog());

        return updatedCatalogItem;
    }

    public CatalogItem findCatalogItemById(int id) {
        return catalogItemRepository.findById(id);
    }

    public List<CatalogItem> findAvailableCatalogItems() {
        return catalogItemRepository.findAll().stream()
                .filter(CatalogItem::isAvailable)
                .toList();
    }

    public void toggleCatalogItemStatus(Integer id) {

        CatalogItem catalogItem = catalogItemRepository.findById(id);

        catalogItem.toggleActive();

        recalculateCatalogPrice(catalogItem.getCatalog());
        recalculateCatalogHours(catalogItem.getCatalog());
    }

//    public List<CatalogItem> searchCatalogItemsByName(String name) {
//        String lowerName = name.toLowerCase();
//        return catalogItemRepository.findAll().stream()
//                .filter(ci -> ci.getItem().getName().toLowerCase().contains(lowerName)
//                || ci.getCatalog().getName().toLowerCase().contains(lowerName))
//                .toList();
//    }
//
    public List<CatalogItem> searchCatalogItemsByCatalog(int catalogId) {
        return catalogItemRepository.findByCatalogId(catalogId);
    }

    public List<CatalogItem> sortCatalogItemsByPrice() {
        return catalogItemRepository.findAll().stream()
                .sorted(CatalogItem.BY_PRICE)
                .toList();
    }

    public List<CatalogItem> sortCatalogItemsByName() {
        return catalogItemRepository.findAll().stream()
                .sorted(CatalogItem.BY_NAME)
                .toList();
    }
}
