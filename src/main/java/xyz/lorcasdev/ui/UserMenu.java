package xyz.lorcasdev.ui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import xyz.lorcasdev.model.CatalogItem;
import xyz.lorcasdev.model.Order;
import xyz.lorcasdev.model.Quote;
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
            System.out.println("1. Explorar catálogo de servicios disponibles");
            System.out.println("2. Agregar servicio a la cotización");
            System.out.println("3. Ver mi cotización actual");
            System.out.println("4. Eliminar servicio de la cotización");
            System.out.println("5. Ver descuentos activos");
            System.out.println("6. Confirmar cotización (Crear Orden)");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1" ->
                        browseCatalog();
                    case "2" ->
                        addToQuote();
                    case "3" ->
                        viewQuote();
                    case "4" ->
                        removeFromQuote();
                    case "5" ->
                        viewDiscounts();
                    case "6" ->
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
        System.out.println("\n--- Catálogo de Servicios Disponibles ---");
        List<CatalogItem> items = catalogService.findAvailableCatalogItems();
        if (items.isEmpty()) {
            System.out.println("No hay servicios disponibles en este momento.");
        } else {
            items.forEach(System.out::println);
        }
    }

    private void addToQuote() {
        if (currentQuote == null) {
            System.out.print("Ingrese su nombre para iniciar la cotización: ");
            String name = scanner.nextLine();
            currentQuote = quoteService.createGuestQuote(name, "guest@example.com", "0000000000");
            System.out.println("¡Cotización iniciada para " + name + "!");
        }

        System.out.print("Ingrese el ID del servicio (CatalogItem) que desea agregar: ");
        int itemId = Integer.parseInt(scanner.nextLine());
        CatalogItem item = catalogService.findCatalogItemById(itemId);

        System.out.print("Ingrese la cantidad: ");
        int qty = Integer.parseInt(scanner.nextLine());

        quoteService.addItemToQuote(currentQuote.getId(), item, qty);
        System.out.println("¡Servicio agregado a su cotización!");
    }

    private void viewQuote() {
        if (currentQuote == null) {
            System.out.println("Aún no tiene una cotización activa.");
            return;
        }
        System.out.println("\n--- Mi Cotización ---");
        System.out.println(currentQuote);
        System.out.println("Subtotal (sin descuentos): $" + quoteService.calculateSubtotal(currentQuote.getId()));
        System.out.println("Horas estimadas: " + quoteService.calculateEstimatedHours(currentQuote.getId()) + " hrs");
    }

    private void removeFromQuote() {
        if (currentQuote == null) {
            return;
        }
        System.out.print("Ingrese el ID del ítem en su cotización (QuoteItem) que desea eliminar: ");
        int quoteItemId = Integer.parseInt(scanner.nextLine());
        quoteService.removeItemFromQuote(quoteItemId);
        System.out.println("Ítem eliminado correctamente.");
    }

    private void viewDiscounts() {
        System.out.println("\n--- Descuentos Activos ---");
        discountService.getActiveDiscounts().forEach(System.out::println);
    }

    private void confirmQuote() {
        if (currentQuote == null) {
            System.out.println("No tiene una cotización activa para confirmar.");
            return;
        }
        System.out.println("Confirmando su cotización y generando la orden de compra...");
        // Pasamos 'null' como status asumiendo que el constructor de Order acepta null o que no hay un OrderStatus.PENDING definido en contexto
        Order order = tiendaService.confirmQuoteAndCreateOrder(currentQuote.getId(), null, LocalDateTime.now().plusDays(7));
        System.out.println("¡Orden generada con éxito!");
        System.out.println(order);

        // Reiniciamos la sesión del usuario
        currentQuote = null;
    }
}
