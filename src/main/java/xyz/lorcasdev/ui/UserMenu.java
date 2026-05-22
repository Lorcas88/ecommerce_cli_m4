package xyz.lorcasdev.ui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import xyz.lorcasdev.enums.OrderStatus;
import xyz.lorcasdev.model.Catalog;
import xyz.lorcasdev.model.CatalogItem;
import xyz.lorcasdev.model.Order;
import xyz.lorcasdev.model.Quote;
import xyz.lorcasdev.model.QuoteItem;
import xyz.lorcasdev.service.CatalogService;
import xyz.lorcasdev.service.DiscountService;
import xyz.lorcasdev.service.QuoteService;
import xyz.lorcasdev.service.TiendaService;

public class UserMenu {

    private final CatalogService catalogService;
    private final QuoteService quoteService;
    private final TiendaService tiendaService;
    private final DiscountService discountService;
    private final Scanner scanner;

    private Quote currentQuote;

    public UserMenu(CatalogService catalogService, QuoteService quoteService, TiendaService tiendaService, DiscountService discountService, Scanner scanner) {
        this.catalogService = catalogService;
        this.quoteService = quoteService;
        this.tiendaService = tiendaService;
        this.discountService = discountService;
        this.scanner = scanner;
    }

    public void display() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Menú de Usuario (Tienda) ===");
            System.out.println("1. Explorar catálogos de servicios");
            System.out.println("2. Agregar servicio (Catálogo) a la cotización");
            System.out.println("3. Ver mi cotización actual");
            System.out.println("4. Añadir ítem opcional a un servicio cotizado");
            System.out.println("5. Eliminar ítem de la cotización");
            System.out.println("6. Ver descuentos activos");
            System.out.println("7. Confirmar cotización (Crear Orden)");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1" ->
                        browseCatalog();
                    case "2" ->
                        addCatalogToQuote();
                    case "3" ->
                        viewQuote();
                    case "4" ->
                        addOptionalItemToQuote();
                    case "5" ->
                        removeFromQuote();
                    case "6" ->
                        viewDiscounts();
                    case "7" ->
                        confirmQuote();
                    case "0" ->
                        exit = true;
                    default ->
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato numérico inválido.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void browseCatalog() {
        System.out.println("\n--- Catálogos de Servicios Disponibles ---");
        List<Catalog> catalogs = catalogService.findActiveCatalogs();
        if (catalogs.isEmpty()) {
            System.out.println("No hay catálogos disponibles en este momento.");
        } else {
            catalogs.forEach(System.out::println);
        }
    }

    private void addCatalogToQuote() {
        if (currentQuote == null) {
            System.out.print("Ingrese su nombre para iniciar la cotización: ");
            String name = scanner.nextLine();
            currentQuote = quoteService.createGuestQuote(name, "guest@example.com", "0000000000");
            System.out.println("¡Cotización iniciada para " + name + "!");
        }

        System.out.print("Ingrese el ID del servicio (Catálogo) que desea agregar: ");
        int catalogId = Integer.parseInt(scanner.nextLine());
        Catalog catalog = catalogService.findCatalogById(catalogId);

        System.out.print("Ingrese la cantidad de este servicio: ");
        int qty = Integer.parseInt(scanner.nextLine());

        List<CatalogItem> mandatoryItems = catalogService.searchCatalogItemsByCatalog(catalog.getId())
                .stream()
                .filter(ci -> !ci.isOptional() && ci.isActive())
                .toList();

        if (mandatoryItems.isEmpty()) {
            System.out.println("Este servicio no tiene ítems obligatorios activos para agregar.");
            return;
        }

        for (CatalogItem item : mandatoryItems) {
            quoteService.addItemToQuote(currentQuote.getId(), item, qty);
        }
        System.out.println("¡Servicio '" + catalog.getName() + "' y sus ítems obligatorios agregados a su cotización!");
    }

    private void viewQuote() {
        if (currentQuote == null) {
            System.out.println("Aún no tiene una cotización activa.");
            return;
        }
        System.out.println("\n--- Mi Cotización ---");
        System.out.println(currentQuote);

        List<QuoteItem> items = quoteService.getQuoteItems(currentQuote.getId());
        if (items.isEmpty()) {
            System.out.println("La cotización está vacía.");
        } else {
            System.out.println("Ítems en la cotización:");
            items.forEach(System.out::println);
        }

        System.out.println("Subtotal (sin descuentos): $" + currentQuote.getSubtotal());
        System.out.println("Horas estimadas: " + currentQuote.getEstimatedHours() + " hrs");
    }

    private void addOptionalItemToQuote() {
        if (currentQuote == null) {
            System.out.println("Aún no tiene una cotización activa.");
            return;
        }

        System.out.print("Ingrese el ID del servicio (Catálogo) al cual desea agregarle ítems opcionales: ");
        int catalogId = Integer.parseInt(scanner.nextLine());

        List<CatalogItem> optionalItems = catalogService.searchCatalogItemsByCatalog(catalogId)
                .stream()
                .filter(ci -> ci.isOptional() && ci.isActive())
                .toList();

        if (optionalItems.isEmpty()) {
            System.out.println("Este servicio no tiene ítems opcionales disponibles.");
            return;
        }

        System.out.println("--- Ítems Opcionales Disponibles ---");
        optionalItems.forEach(System.out::println);

        System.out.print("Ingrese el ID del ítem opcional (CatalogItem) que desea agregar: ");
        int itemId = Integer.parseInt(scanner.nextLine());
        CatalogItem itemToAdd = catalogService.findCatalogItemById(itemId);

        if (!itemToAdd.isOptional() || itemToAdd.getCatalog().getId() != catalogId) {
            System.out.println("El ítem seleccionado no es un ítem opcional válido para este servicio.");
            return;
        }

        System.out.print("Ingrese la cantidad: ");
        int qty = Integer.parseInt(scanner.nextLine());

        quoteService.addItemToQuote(currentQuote.getId(), itemToAdd, qty);
        System.out.println("¡Ítem opcional agregado a su cotización!");
    }

    private void removeFromQuote() {
        if (currentQuote == null) {
            System.out.println("Aún no tiene una cotización activa.");
            return;
        }

        List<QuoteItem> items = quoteService.getQuoteItems(currentQuote.getId());
        List<QuoteItem> removableItems = items.stream()
                .filter(qi -> qi.getCatalogItem().isOptional())
                .toList();

        if (removableItems.isEmpty()) {
            System.out.println("No hay ítems opcionales en su cotización que puedan ser eliminados.");
            return;
        }

        System.out.println("\n--- Ítems opcionales que puede eliminar ---");
        removableItems.forEach(System.out::println);

        System.out.print("Ingrese el ID del ítem en su cotización (QuoteItem) que desea eliminar: ");
        int quoteItemId = Integer.parseInt(scanner.nextLine());
        quoteService.removeItemFromQuote(quoteItemId);
        System.out.println("Ítem eliminado correctamente.");
    }

    private void viewDiscounts() {
        System.out.println("\n--- Descuentos Activos ---");
        discountService.getActiveDiscountRules().forEach(System.out::println);
    }

    private void confirmQuote() {
        if (currentQuote == null) {
            System.out.println("No tiene una cotización activa para confirmar.");
            return;
        }
        System.out.println("Confirmando su cotización y generando la orden de compra...");

        Order order = tiendaService.confirmQuoteAndCreateOrder(currentQuote.getId(), OrderStatus.IN_PROGRESS, LocalDateTime.now().plusDays(7));
        System.out.println("¡Orden generada con éxito!");
        System.out.println(order);

        // Reiniciamos la sesión del usuario
        currentQuote = null;
    }
}
