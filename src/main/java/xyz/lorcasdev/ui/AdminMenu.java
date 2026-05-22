package xyz.lorcasdev.ui;

import java.util.Scanner;

import xyz.lorcasdev.service.CatalogService;
import xyz.lorcasdev.ui.menu.CatalogItemMenu;
import xyz.lorcasdev.ui.menu.CatalogMenu;
import xyz.lorcasdev.ui.menu.ItemMenu;

public class AdminMenu {

    private final CatalogService catalogService;
    private final Scanner scanner;

    public AdminMenu(CatalogService catalogService, Scanner scanner) {
        this.catalogService = catalogService;
        this.scanner = scanner;
    }

    public void display() {
        CatalogMenu catalogMenu = new CatalogMenu(catalogService, this.scanner);
        ItemMenu itemMenu = new ItemMenu(catalogService, this.scanner);
        CatalogItemMenu catalogItemMenu = new CatalogItemMenu(catalogService, this.scanner);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== Menú de Administración ===");
            System.out.println("1. Menú de catálogos");
            System.out.println("2. Menú de ítems");
            System.out.println("3. Menú de ítems de catálogo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String option = this.scanner.nextLine();
            switch (option) {
                case "1" ->
                    catalogMenu.display();
                case "2" ->
                    itemMenu.display();
                case "3" ->
                    catalogItemMenu.display();
                case "0" ->
                    exit = true;
                default ->
                    System.out.println("Opción no válida.");
            }
        }
    }
}
