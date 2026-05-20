package xyz.lorcasdev;

import java.util.Scanner;

import xyz.lorcasdev.repository.CatalogItemRepository;
import xyz.lorcasdev.repository.CatalogRepository;
import xyz.lorcasdev.repository.ItemRepository;
import xyz.lorcasdev.repository.OrderItemRepository;
import xyz.lorcasdev.repository.OrderRepository;
import xyz.lorcasdev.repository.QuoteItemRepository;
import xyz.lorcasdev.repository.QuoteRepository;
import xyz.lorcasdev.service.CatalogService;
import xyz.lorcasdev.service.DiscountService;
import xyz.lorcasdev.service.OrderService;
import xyz.lorcasdev.service.QuoteService;
import xyz.lorcasdev.service.TiendaService;
import xyz.lorcasdev.util.DataSeeder;
import xyz.lorcasdev.ui.AdminMenu;
import xyz.lorcasdev.ui.UserMenu;

public class Main {

    public static void main(String[] args) {
        // 1. Inicializar repositorios
        CatalogRepository catalogRepository = new CatalogRepository();
        ItemRepository itemRepository = new ItemRepository();
        CatalogItemRepository catalogItemRepository = new CatalogItemRepository();
        QuoteRepository quoteRepository = new QuoteRepository();
        QuoteItemRepository quoteItemRepository = new QuoteItemRepository();
        OrderRepository orderRepository = new OrderRepository();
        OrderItemRepository orderItemRepository = new OrderItemRepository();

        // 2. Inicializar servicios de negocio
        CatalogService catalogService = new CatalogService(catalogRepository, itemRepository, catalogItemRepository);
        QuoteService quoteService = new QuoteService(quoteRepository, quoteItemRepository);
        OrderService orderService = new OrderService(orderRepository, orderItemRepository);
        TiendaService tiendaService = new TiendaService(quoteService, orderService, quoteRepository, quoteItemRepository);
        DiscountService discountService = new DiscountService();

        // 3. Cargar datos de prueba
        DataSeeder.cargarDatosEjemplo(catalogService);

        // 3. Iniciar la interfaz de usuario
        try (Scanner scanner = new Scanner(System.in)) {
            AdminMenu adminMenu = new AdminMenu(catalogService, scanner);
            UserMenu userMenu = new UserMenu(catalogService, quoteService, tiendaService, discountService, scanner);

            boolean exit = false;
            while (!exit) {
                System.out.println("\n=== Bienvenido al Sistema E-Commerce ===");
                System.out.println("1. Menú de Administración");
                System.out.println("2. Menú de Usuario (Tienda)");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                String option = scanner.nextLine();
                switch (option) {
                    case "1" ->
                        adminMenu.display();
                    case "2" ->
                        userMenu.display();
                    case "0" ->
                        exit = true;
                    default ->
                        System.out.println("Opción no válida.");
                }
            }
            System.out.println("¡Hasta luego!");
        }
    }
}
