package xyz.lorcasdev.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.lorcasdev.exception.ElementNotFoundException;
import xyz.lorcasdev.model.Catalog;

public class CatalogRepository {

    private final Map<Integer, Catalog> catalogs = new HashMap<>();

    // Contadores para generar IDs automáticos
    private int serviceCounter = 1;

    public Catalog save(String name, String description, int price, boolean isActive, double estimatedBaseHours) {
        int id = serviceCounter++;
        Catalog s = new Catalog(id, name, description, price, true, estimatedBaseHours);
        catalogs.put(id, s);
        return s;
    }

    public Catalog findById(int id) {
        Catalog s = catalogs.get(id);
        if (s == null) {
            throw new ElementNotFoundException(id);
        }
        return s;
    }

    public List<Catalog> findAll() {
        return new ArrayList<>(catalogs.values());
    }

    public Catalog update(Catalog catalog) {
        catalogs.put(catalog.getId(), catalog);
        return catalog;
    }

    /**
     * Desactiva un catalogo de servicios (no lo elimina físicamente).
     */
    public void deactivate(Integer id) {
        findById(id).setActive(false);
    }
}
