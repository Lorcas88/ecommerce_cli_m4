package xyz.lorcasdev.ui.menu;

import java.util.List;
import java.util.Scanner;

import xyz.lorcasdev.enums.ComplexityLevel;
import xyz.lorcasdev.model.CatalogItem;
import xyz.lorcasdev.service.CatalogService;

public class CatalogItemMenu {

    private final CatalogService catalogService;
    private final Scanner scanner;

    public CatalogItemMenu(CatalogService catalogService, Scanner scanner) {
        this.catalogService = catalogService;
        this.scanner = scanner;
    }

    public void display() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Menú de Ítems de Catálogo ===");
            System.out.println("1. Listar ítems de catálogo (disponibles)");
            System.out.println("2. Buscar ítem de catálogo por ID");
            System.out.println("3. Crear nuevo ítem de catálogo");
            System.out.println("4. Editar ítem de catálogo");
            System.out.println("5. Cambiar estado de catálogo");
            System.out.println("6. Ordenar ítems por precio");
            System.out.println("7. Ordenar ítems por nombre");
            System.out.println("8. Ordenar ítems por complejidad");
            System.out.println("0. Volver al menú de administración");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1" ->
                        listCatalogItems();
                    case "2" ->
                        searchCatalogItem();
                    case "3" ->
                        createCatalogItem();
                    case "4" ->
                        editCatalogItem();
                    case "5" ->
                        changeStatusCatalogItem();
                    case "6" ->
                        sortCatalogItemsByPrice();
                    case "7" ->
                        sortCatalogItemsByName();
                    case "0" ->
                        exit = true;
                    default ->
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada inválida. Asegúrese de ingresar un número (ej. un ID o precio) y no dejar el campo vacío.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void listCatalogItems() {
        System.out.println("\n--- Lista de Ítems de Catálogo ---");
        List<CatalogItem> items = catalogService.findAvailableCatalogItems();
        if (items.isEmpty()) {
            System.out.println("No hay ítems de catálogo disponibles.");
        } else {
            items.forEach(System.out::println);
        }
    }

    private void searchCatalogItem() {
        System.out.print("Ingrese el ID del ítem de catálogo a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());
        CatalogItem item = catalogService.findCatalogItemById(id);
        System.out.println("Ítem de catálogo encontrado: " + item);
    }

    private void createCatalogItem() {
        System.out.println("\n--- Crear Nuevo Ítem de Catálogo ---");
        System.out.print("ID del Catálogo (Service): ");
        
        int catalogId = Integer.parseInt(scanner.nextLine());
        System.out.print("ID del Ítem: ");
        int itemId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("¿Es opcional? (true/false [defecto]): ");
        Boolean isOptional = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Nivel de complejidad (LOW, MEDIUM [defecto], HIGH): ");
        String cplxLevel = scanner.nextLine().toUpperCase();
        ComplexityLevel complexity = cplxLevel.isBlank() ? null : ComplexityLevel.valueOf(cplxLevel);

        System.out.print("Sobreescritura de precio (dejar vacío para usar base): ");
        String priceStr = scanner.nextLine();
        Integer priceOverride = priceStr.isBlank() ? null : Integer.parseInt(priceStr);

        System.out.print("Sobreescritura de horas (dejar vacío para usar base): ");
        String hoursStr = scanner.nextLine();
        Double hoursOverride = hoursStr.isBlank() ? null : Double.parseDouble(hoursStr);

        CatalogItem catalogItem = catalogService.createCatalogItem(catalogId, itemId, null, isOptional, complexity, priceOverride, hoursOverride);
        System.out.println("Ítem de catálogo creado exitosamente: " + catalogItem);
    }

    private void editCatalogItem() {
        System.out.println("\n--- Editar Ítem de Catálogo ---");
        System.out.print("Ingrese el ID del ítem de catálogo a editar: ");
        int id = Integer.parseInt(scanner.nextLine());

        CatalogItem current = catalogService.findCatalogItemById(id);
        
        System.out.print("Nuevo nivel de complejidad [" + current.getPriceOverride() + "] (LOW, MEDIUM, HIGH, dejar vacío para no cambiar): ");
        String cplxLevel = scanner.nextLine().toUpperCase();
        ComplexityLevel complexity = ComplexityLevel.valueOf(cplxLevel);
        
        System.out.print("¿Es opcional? [" + current.isOptional() + "] (true/false, dejar vacío para no cambiar): ");
        String optStr = scanner.nextLine();
        Boolean isOptional = optStr.isBlank() ? null : Boolean.parseBoolean(optStr);

        System.out.print("Nuevo precio override [" + current.getPriceOverride() + "] (dejar vacío para no cambiar): ");
        String priceStr = scanner.nextLine();
        Integer priceOverride = priceStr.isBlank() ? null : Integer.parseInt(priceStr);

        System.out.print("Nuevas horas override [" + current.getHoursOverride() + "] (dejar vacío para no cambiar): ");
        String hoursStr = scanner.nextLine();
        Double hoursOverride = hoursStr.isBlank() ? null : Double.parseDouble(hoursStr);

        CatalogItem catalogItem = catalogService.updateCatalogItem(id, isOptional, current.isActive(), complexity, priceOverride, hoursOverride);
        System.out.println("Ítem de catálogo editado exitosamente: " + catalogItem);
    }

    private void changeStatusCatalogItem() {
        System.out.print("Ingrese el ID del ítem: ");
        int id = Integer.parseInt(scanner.nextLine());

        CatalogItem current = catalogService.findCatalogItemById(id);
        // Si está activo, se desactivará y visceversa
        String status = current.isActive() ? "desactivado" : "activado";

        catalogService.toggleCatalogItemStatus(id);
        System.out.println("Item " + status + " exitosamente.");
    }

    private void sortCatalogItemsByPrice() {
        System.out.println("\n--- Ítems de Catálogo Ordenados por Precio ---");
        catalogService.sortCatalogItemsByPrice().forEach(System.out::println);
    }

    private void sortCatalogItemsByName() {
        System.out.println("\n--- Ítems de Catálogo Ordenados por Nombre ---");
        catalogService.sortCatalogItemsByName().forEach(System.out::println);
    }

}
