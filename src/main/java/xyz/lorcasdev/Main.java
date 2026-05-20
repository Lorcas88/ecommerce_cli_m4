package xyz.lorcasdev;

import java.util.Scanner;

import xyz.lorcasdev.repository.CatalogItemRepository;
import xyz.lorcasdev.repository.CatalogRepository;
import xyz.lorcasdev.repository.ItemRepository;
import xyz.lorcasdev.service.CatalogService;
import xyz.lorcasdev.ui.AdminMenu;

public class Main {

    public static void main(String[] args) {
        // 1. Inicializar repositorios
        CatalogRepository catalogRepository = new CatalogRepository();
        ItemRepository itemRepository = new ItemRepository();
        CatalogItemRepository catalogItemRepository = new CatalogItemRepository();

        // 2. Inicializar servicios de negocio
        CatalogService catalogService = new CatalogService(catalogRepository, itemRepository, catalogItemRepository);

        // 3. Iniciar la interfaz de usuario (Menú de Administración)
        try (Scanner scanner = new Scanner(System.in)) {
            AdminMenu adminMenu = new AdminMenu(catalogService, scanner);
            adminMenu.display();
        }
    }
}
