package xyz.lorcasdev.ui.menu;

import java.util.List;
import java.util.Scanner;

import xyz.lorcasdev.model.Item;
import xyz.lorcasdev.service.CatalogService;

public class ItemMenu {

    private final CatalogService catalogService;
    private final Scanner scanner;

    public ItemMenu(CatalogService catalogService, Scanner scanner) {
        this.catalogService = catalogService;
        this.scanner = scanner;
    }

    public void display() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Menú de Ítems ===");
            System.out.println("1. Listar ítems");
            System.out.println("2. Buscar ítem por ID");
            System.out.println("3. Crear nuevo ítem");
            System.out.println("4. Editar ítem");
            System.out.println("0. Volver al menú de administración");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1" ->
                        listItem();
                    case "2" ->
                        searchItem();
                    case "3" ->
                        createItem();
                    case "4" ->
                        editItem();
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

    private void listItem() {
        System.out.println("\n--- Lista de Ítems ---");
        List<Item> items = catalogService.findAllItems();
        if (items.isEmpty()) {
            System.out.println("No hay ítems registrados.");
        } else {
            items.forEach(System.out::println);
        }
    }

    private void searchItem() {
        System.out.print("Ingrese el ID del ítem a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Item item = catalogService.findItemById(id);
        System.out.println("Ítem encontrado: " + item);
    }

    private void createItem() {
        System.out.println("\n--- Crear Nuevo Ítem ---");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Descripción: ");
        String description = scanner.nextLine();
        System.out.print("Precio unitario base: ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.print("Horas estimadas base: ");
        double hours = Double.parseDouble(scanner.nextLine());

        Item item = catalogService.createItem(name, description, price, hours, true);
        System.out.println("Ítem creado exitosamente: " + item);
    }

    private void editItem() {
        System.out.println("\n--- Editar Ítem ---");
        System.out.print("Ingrese el ID del ítem a editar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Item current = catalogService.findItemById(id);

        System.out.print("Nuevo nombre [" + current.getName() + "]: ");
        String name = scanner.nextLine();
        name = name.isBlank() ? null : name;

        System.out.print("Nueva descripción [" + current.getDescription() + "]: ");
        String description = scanner.nextLine();
        description = description.isBlank() ? null : description;

        System.out.print("Nuevo precio unitario base [" + current.getBaseUnitPrice() + "]: ");
        String priceStr = scanner.nextLine();
        Integer price = priceStr.isBlank() ? null : Integer.parseInt(priceStr);

        System.out.print("Nuevas horas estimadas base [" + current.getBaseEstimatedHours() + "]: ");
        String hoursStr = scanner.nextLine();
        Double hours = hoursStr.isBlank() ? null : Double.parseDouble(hoursStr);

        Item item = catalogService.updateItem(id, name, description, price, hours);
        System.out.println("Ítem editado exitosamente: " + item);
    }

}
