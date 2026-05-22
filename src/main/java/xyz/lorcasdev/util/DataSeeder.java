package xyz.lorcasdev.util;

import xyz.lorcasdev.enums.ComplexityLevel;
import xyz.lorcasdev.model.Catalog;
import xyz.lorcasdev.model.Item;
import xyz.lorcasdev.service.CatalogService;

public class DataSeeder {

    public static void cargarDatosEjemplo(CatalogService catalogService) {
        // ── Catalogs (Services) ──────────────────────────────────────────────
        Catalog desarrolloWeb = catalogService.createCatalog("Desarrollo Web", "Diseño y desarrollo de sitios web modernos, landing pages y aplicaciones web.", true);
        Catalog autProcesos = catalogService.createCatalog("Automatización de procesos", "Automatización de tareas repetitivas mediante scripts e integraciones entre sistemas.", false);
//        Catalog desarrollo = catalogService.createCatalog("Desarrollo Backend", "APIs REST y microservicios", 1_200_000, true, 60);
//        Catalog marketing = catalogService.createCatalog("Marketing Digital", "Estrategia y gestión de redes sociales", 500_000, true, 20);
//        Catalog branding = catalogService.createCatalog("Branding", "Identidad visual corporativa", 650_000, true, 30);

        // ── Items ─────────────────────────────────────────────────────────────
        Item kickoffMeeting = catalogService.createItem("Kickoff meeting", "Reunión inicial para alinear objetivos, alcance, plazos y forma de trabajo del proyecto.", 20000, 1, true);
        Item levantarReq = catalogService.createItem("Levantamiento de requerimientos", "Análisis y recopilación de necesidades funcionales y técnicas del cliente.", 70000, 4, true);
        Item landing = catalogService.createItem("Landing page", "Página única orientada a presentar un producto, servicio o campaña específica.", 150000, 10.00, true);
        Item garantia = catalogService.createItem("Garantía", "Cobertura de corrección de errores posteriores a la entrega dentro de un período definido.", 50000, 8.00, true);
        Item integracionAPI = catalogService.createItem("Integración con API", "Conexión con servicios externos mediante API para intercambio de información o automatización.", 120000, 6.00, true);
        Item automScripts = catalogService.createItem("Automatización con scripts", "Desarrollo de scripts para ejecutar tareas repetitivas de forma automática.", 90000, 4.00, true);
        Item limpiezaDatos = catalogService.createItem("Limpieza y preparación de datos", "Depuración, transformación y estructuración de datos para análisis o automatización.", 100000, 5.00, true);
//        Item paginaInterna = catalogService.createItem("Página Interna", "Página adicional del sitio", 80_000, 5, true);
//        Item apiRest = catalogService.createItem("API REST", "Endpoints CRUD con autenticación", 200_000, 15, true);
//        Item dashboard = catalogService.createItem("Dashboard Admin", "Panel de administración con gráficos", 350_000, 25, true);

        // ── CatalogItems (unidades vendibles) ─────────────────────────────────
        // Desarrollo Web
        catalogService.createCatalogItem(desarrolloWeb.getId(), kickoffMeeting.getId(), false, true, ComplexityLevel.LOW, null, null);
        catalogService.createCatalogItem(desarrolloWeb.getId(), levantarReq.getId(), false, true, null, null, null);
        catalogService.createCatalogItem(desarrolloWeb.getId(), landing.getId(), false, true, null, null, null);
        catalogService.createCatalogItem(desarrolloWeb.getId(), garantia.getId(), true, true, ComplexityLevel.LOW, null, 4.0);
        catalogService.createCatalogItem(desarrolloWeb.getId(), integracionAPI.getId(), true, true, ComplexityLevel.LOW, null, null);

        // Automatización de procesos
        catalogService.createCatalogItem(autProcesos.getId(), kickoffMeeting.getId(), false, true, ComplexityLevel.LOW, null, null);
        catalogService.createCatalogItem(autProcesos.getId(), levantarReq.getId(), false, true, null, null, null);
        catalogService.createCatalogItem(autProcesos.getId(), integracionAPI.getId(), true, true, ComplexityLevel.HIGH, null, null);
        catalogService.createCatalogItem(autProcesos.getId(), automScripts.getId(), false, true, null, null, null);
        catalogService.createCatalogItem(autProcesos.getId(), limpiezaDatos.getId(), true, true, null, null, null);

        System.out.println("Datos de prueba cargados exitosamente.");
    }
}
