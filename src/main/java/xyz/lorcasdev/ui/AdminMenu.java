package xyz.lorcasdev.ui;

import java.util.List;
import java.util.Scanner;

import xyz.lorcasdev.model.Catalog;
import xyz.lorcasdev.service.CatalogService;

public class AdminMenu {

    private final CatalogService catalogService;
    private final Scanner scanner;

    public AdminMenu(CatalogService catalogService, Scanner scanner) {
        this.catalogService = catalogService;
        this.scanner = scanner;
    }

    public void display() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Menú de Administración ===");
            System.out.println("1. Listar servicios (Catálogos)");
            System.out.println("2. Buscar servicio por ID");
            System.out.println("3. Crear nuevo servicio");
            System.out.println("4. Editar servicio");
            System.out.println("5. Eliminar (desactivar) servicio");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1" -> listServices();
                    case "2" -> searchService();
                    case "3" -> createService();
                    case "4" -> editService();
                    case "5" -> deleteService();
                    case "0" -> exit = true;
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato de número inválido.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void listServices() {
        System.out.println("\n--- Lista de Servicios ---");
        List<Catalog> catalogs = catalogService.findAllCatalogs();
        if (catalogs.isEmpty()) {
            System.out.println("No hay servicios registrados.");
        } else {
            catalogs.forEach(System.out::println);
        }
    }

    private void searchService() {
        System.out.print("Ingrese el ID del servicio a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Catalog catalog = catalogService.findCatalogById(id);
        System.out.println("Servicio encontrado: " + catalog);
    }

    private void createService() {
        System.out.println("\n--- Crear Nuevo Servicio ---");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Descripción: ");
        String description = scanner.nextLine();
        System.out.print("Precio base: ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.print("Horas estimadas base: ");
        double hours = Double.parseDouble(scanner.nextLine());

        Catalog catalog = catalogService.createCatalog(name, description, price, true, hours);
        System.out.println("Servicio creado exitosamente: " + catalog);
    }

    private void editService() {
        System.out.println("\n--- Editar Servicio ---");
        System.out.print("Ingrese el ID del servicio a editar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Catalog current = catalogService.findCatalogById(id);

        System.out.print("Nuevo nombre [" + current.getName() + "]: ");
        String name = scanner.nextLine();
        name = name.isBlank() ? current.getName() : name;

        System.out.print("Nueva descripción [" + current.getDescription() + "]: ");
        String description = scanner.nextLine();
        description = description.isBlank() ? current.getDescription() : description;

        System.out.print("Nuevo precio base [" + current.getPrice() + "]: ");
        String priceStr = scanner.nextLine();
        Integer price = priceStr.isBlank() ? current.getPrice() : Integer.parseInt(priceStr);

        System.out.print("Nuevas horas estimadas [" + current.getEstimatedBaseHours() + "]: ");
        String hoursStr = scanner.nextLine();
        double hours = hoursStr.isBlank() ? current.getEstimatedBaseHours() : Double.parseDouble(hoursStr);

        Catalog catalog = catalogService.updateCatalog(id, name, description, price, current.isActive(), hours);
        System.out.println("Servicio editado exitosamente: " + catalog);
    }

    private void deleteService() {
        System.out.print("Ingrese el ID del servicio a eliminar (desactivar): ");
        int id = Integer.parseInt(scanner.nextLine());
        catalogService.disableCatalog(id);
        System.out.println("Servicio desactivado exitosamente.");
    }
}
