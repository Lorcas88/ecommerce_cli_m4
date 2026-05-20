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

    public Catalog createCatalog(String name, String description,
            int price, boolean isActive, double estimatedBaseHours) {
        return catalogRepository.save(name, description, price, isActive, estimatedBaseHours);
    }

    public Catalog updateCatalog(Integer id, String newName,
            String newDescription, Integer newPrice,
            boolean newState, double newEstimatedBaseHours) {
        Catalog catalog = catalogRepository.findById(id);

        if (newName != null && !newName.isBlank()) {
            catalog.setName(newName);
        }

        if (newPrice != null && newPrice <= 0) {
            throw new IllegalArgumentException(
                    "Precio inválido"
            );
        }

        if (newPrice != null) {
            catalog.setPrice(newPrice);
        }

        return catalogRepository.update(catalog);
    }

    public void disableCatalog(int catalogId) {

        Catalog catalog = catalogRepository.findById(catalogId);
        if (!catalog.isActive()) {
            throw new IllegalStateException(
                    "El catálogo ya está desactivado"
            );
        }

        catalog.setActive(false);

        catalogRepository.update(catalog);
    }

    public Catalog findCatalogById(int id) {
        return catalogRepository.findById(id);
    }

    public List<Catalog> findAllCatalogs() {
        return catalogRepository.findAll();
    }

    public List<Catalog> findActiveCatalogs() {
        return catalogRepository.findAll().stream()
                .filter(Catalog::isActive)
                .toList();
    }

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

    public void disableItem(int id) {
        Item item = itemRepository.findById(id);
        if (!item.isActive()) {
            throw new IllegalStateException("El item ya está desactivado");
        }
        item.setActive(false);
        itemRepository.update(item);
    }

    public Item findItemById(int id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public CatalogItem createCatalogItem(int catalogId, int itemId,
            boolean isDefault, boolean isActive, ComplexityLevel complexityLevel,
            Integer priceOverride, Double hoursOverride) {
        Catalog catalog = catalogRepository.findById(catalogId);
        Item item = itemRepository.findById(itemId);

        return catalogItemRepository.save(catalog, item, isDefault, isActive, complexityLevel, priceOverride, hoursOverride);
    }

    public CatalogItem updateCatalogItem(int id, Boolean isDefault, Boolean isActive, Integer priceOverride, Double hoursOverride) {
        CatalogItem catalogItem = catalogItemRepository.findById(id);
        if (isDefault != null) {
            catalogItem.setDefault(isDefault);
        }
        if (isActive != null) {
            catalogItem.setActive(isActive);
        }
        if (priceOverride != null) {
            catalogItem.setPriceOverride(priceOverride);
        }
        if (hoursOverride != null) {
            catalogItem.setHoursOverride(hoursOverride);
        }
        return catalogItemRepository.update(catalogItem);
    }

    public CatalogItem findCatalogItemById(int id) {
        return catalogItemRepository.findById(id);
    }

    public List<CatalogItem> findAvailableCatalogItems() {
        return catalogItemRepository.findAll().stream()
                .filter(CatalogItem::isAvailable)
                .toList();
    }

    public List<CatalogItem> searchCatalogItemsByName(String name) {
        String lowerName = name.toLowerCase();
        return catalogItemRepository.findAll().stream()
                .filter(ci -> ci.getItem().getName().toLowerCase().contains(lowerName)
                || ci.getCatalog().getName().toLowerCase().contains(lowerName))
                .toList();
    }

    public List<CatalogItem> searchCatalogItemsByCatalog(int catalogId) {
        return catalogItemRepository.findByCatalogId(catalogId);
    }

    public List<CatalogItem> searchCatalogItemsByComplexity(ComplexityLevel level) {
        return catalogItemRepository.findAll().stream()
                .filter(ci -> ci.getComplexityLevel() == level)
                .toList();
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

    public List<CatalogItem> sortCatalogItemsByComplexity() {
        return catalogItemRepository.findAll().stream()
                .sorted(CatalogItem.BY_COMPLEXITY)
                .toList();

    }
}
