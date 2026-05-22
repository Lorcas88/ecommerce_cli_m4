package xyz.lorcasdev.ui.menu;

import java.util.List;
import java.util.Scanner;

import xyz.lorcasdev.model.Catalog;
import xyz.lorcasdev.service.CatalogService;

public class CatalogMenu {

    private final CatalogService catalogService;
    private final Scanner scanner;

    public CatalogMenu(CatalogService catalogService, Scanner scanner) {
        this.catalogService = catalogService;
        this.scanner = scanner;
    }

    public void display() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Menú de Cátalogo ===");
            System.out.println("1. Listar catálogo de servicios");
            System.out.println("2. Buscar catálogo por ID");
            System.out.println("3. Crear nuevo catálogo");
            System.out.println("4. Editar catálogo");
            System.out.println("5. Cambiar estado de catálogo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1" ->
                        listCatalog();
                    case "2" ->
                        searchCatalog();
                    case "3" ->
                        createCatalog();
                    case "4" ->
                        editCatalog();
                    case "5" ->
                        changeStatusCatalog();
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

    private void listCatalog() {
        System.out.println("\n--- Lista de Catálogos ---");
        List<Catalog> catalogs = catalogService.findAllCatalogs();
        if (catalogs.isEmpty()) {
            System.out.println("No hay catálogos registrados.");
        } else {
            catalogs.forEach(System.out::println);
        }
    }

    private void searchCatalog() {
        System.out.print("Ingrese el ID del catálogo a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Catalog catalog = catalogService.findCatalogById(id);
        System.out.println("Catálogo encontrado: " + catalog);
    }

    private void createCatalog() {
        System.out.println("\n--- Crear Nuevo Catálogo ---");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Descripción: ");
        String description = scanner.nextLine();
        
        Catalog catalog = catalogService.createCatalog(name, description, true);
        System.out.println("Catálogo creado exitosamente: " + catalog);
    }

    private void editCatalog() {
        System.out.println("\n--- Editar Catálogo ---");
        System.out.print("Ingrese el ID del catálogo a editar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Catalog current = catalogService.findCatalogById(id);

        System.out.print("Nuevo nombre [" + current.getName() + "]: ");
        String name = scanner.nextLine();
        name = name.isBlank() ? null : name;

        System.out.print("Nueva descripción [" + current.getDescription() + "]: ");
        String description = scanner.nextLine();
        description = description.isBlank() ? null : description;

        Catalog catalog = catalogService.updateCatalog(id, name, description, current.isActive());
        System.out.println("Catálogo editado exitosamente: " + catalog);
    }

    private void changeStatusCatalog() {
        System.out.print("Ingrese el ID del catálogo: ");
        int id = Integer.parseInt(scanner.nextLine());

        Catalog current = catalogService.findCatalogById(id);
        // Si está activo, se desactivará y visceversa
        String status = current.isActive() ? "desactivado" : "activado";

        catalogService.toggleCatalogStatus(id);
        System.out.println("Catálogo " + status + " exitosamente.");
    }
}
