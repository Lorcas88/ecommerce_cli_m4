package xyz.lorcasdev.util;

import xyz.lorcasdev.enums.ComplexityLevel;
import xyz.lorcasdev.model.Catalog;
import xyz.lorcasdev.model.Item;
import xyz.lorcasdev.service.CatalogService;

public class DataSeeder {

    public static void cargarDatosEjemplo(CatalogService catalogService) {
        // ── Catalogs (Services) ──────────────────────────────────────────────
        Catalog disenioWeb = catalogService.createCatalog("Diseño Web", "Creación de sitios web a medida", 800_000, true, 40);
        Catalog desarrollo = catalogService.createCatalog("Desarrollo Backend", "APIs REST y microservicios", 1_200_000, true, 60);
        Catalog marketing = catalogService.createCatalog("Marketing Digital", "Estrategia y gestión de redes sociales", 500_000, true, 20);
        Catalog branding = catalogService.createCatalog("Branding", "Identidad visual corporativa", 650_000, true, 30);

        // ── Items ─────────────────────────────────────────────────────────────
        Item landing = catalogService.createItem("Página Landing", "Diseño responsivo de página principal", 120_000, 8, true);
        Item paginaInterna = catalogService.createItem("Página Interna", "Página adicional del sitio", 80_000, 5, true);
        Item apiRest = catalogService.createItem("API REST", "Endpoints CRUD con autenticación", 200_000, 15, true);
        Item dashboard = catalogService.createItem("Dashboard Admin", "Panel de administración con gráficos", 350_000, 25, true);
        Item postMensual = catalogService.createItem("Pack Posts Mensual", "12 posts diseñados para redes", 90_000, 6, true);
        Item logoSvg = catalogService.createItem("Logo SVG", "Logo vectorial con manual de marca", 180_000, 10, true);
        Item manual = catalogService.createItem("Manual de Marca", "Guía de uso de identidad visual", 120_000, 8, true);

        // ── CatalogItems (unidades vendibles) ─────────────────────────────────
        // Diseño Web
        catalogService.createCatalogItem(disenioWeb.getId(), landing.getId(), true, true, ComplexityLevel.LOW, null, null);
        catalogService.createCatalogItem(disenioWeb.getId(), landing.getId(), false, true, ComplexityLevel.HIGH, 1_100_000, 55.0);
        catalogService.createCatalogItem(disenioWeb.getId(), paginaInterna.getId(), true, true, ComplexityLevel.LOW, null, null);

        // Desarrollo Backend
        catalogService.createCatalogItem(desarrollo.getId(), apiRest.getId(), true, true, ComplexityLevel.MEDIUM, null, null);
        catalogService.createCatalogItem(desarrollo.getId(), dashboard.getId(), false, true, ComplexityLevel.HIGH, 1_800_000, 80.0);

        // Marketing Digital
        catalogService.createCatalogItem(marketing.getId(), postMensual.getId(), true, true, ComplexityLevel.LOW, null, null);

        // Branding
        catalogService.createCatalogItem(branding.getId(), logoSvg.getId(), true, true, ComplexityLevel.MEDIUM, null, null);
        catalogService.createCatalogItem(branding.getId(), manual.getId(), false, true, ComplexityLevel.MEDIUM, null, null);

        System.out.println("Datos de prueba cargados exitosamente.");
    }
}
