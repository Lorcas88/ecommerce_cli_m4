package xyz.lorcasdev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import xyz.lorcasdev.enums.ComplexityLevel;
import xyz.lorcasdev.model.Catalog;
import xyz.lorcasdev.model.CatalogItem;
import xyz.lorcasdev.model.Item;
import xyz.lorcasdev.model.Quote;
import xyz.lorcasdev.model.QuoteItem;
import xyz.lorcasdev.repository.QuoteItemRepository;
import xyz.lorcasdev.repository.QuoteRepository;
import xyz.lorcasdev.service.QuoteService;

class QuoteServiceTest {

    private QuoteService quoteService;
    private QuoteRepository quoteRepository;
    private QuoteItemRepository quoteItemRepository;

    @BeforeEach
    void setUp() {
        quoteRepository = new QuoteRepository();
        quoteItemRepository = new QuoteItemRepository();
        quoteService = new QuoteService(quoteRepository, quoteItemRepository);
    }

    @Test
    void testCalculateCartTotals() {
        Quote quote = quoteService.createGuestQuote("Guest User", "guest@test.com", "123456789");

        // Setup de elementos simulados
        Catalog catalog = new Catalog(1, "Catálogo Prueba", "Desc", 100, true, 10);
        Item item = new Item(1, "Item Prueba", "Desc", 50, 5, true);
        CatalogItem cItem1 = new CatalogItem(1, catalog, item, true, true, ComplexityLevel.LOW, 100, null);
        CatalogItem cItem2 = new CatalogItem(2, catalog, item, true, true, ComplexityLevel.LOW, 250, null);

        // Agregamos ítems a la cotización
        quoteService.addItemToQuote(quote.getId(), cItem1, 2); // 100 * 2 = 200
        quoteService.addItemToQuote(quote.getId(), cItem2, 1); // 250 * 1 = 250

        // Comprobamos los cálculos
        assertEquals(450, quoteService.calculateSubtotal(quote.getId()), "El subtotal debe ser la suma exacta de los totales de línea");
        assertEquals(450, quoteService.calculateTotal(quote.getId()), "El total debe ser igual al subtotal si aún no hay descuentos aplicados");
    }

    @Test
    void testUpdateQuantityValidation() {
        Quote quote = quoteService.createGuestQuote("Guest User", "guest@test.com", "123456789");
        Catalog catalog = new Catalog(1, "Catálogo Prueba", "Desc", 100, true, 10);
        Item item = new Item(1, "Item Prueba", "Desc", 50, 5, true);
        CatalogItem cItem = new CatalogItem(1, catalog, item, true, true, ComplexityLevel.LOW, 100, null);

        QuoteItem quoteItem = quoteService.addItemToQuote(quote.getId(), cItem, 1);

        // Validar que se lanza una excepción al intentar colocar una cantidad negativa
        assertThrows(RuntimeException.class, () -> quoteService.updateItemQuantity(quoteItem.getId(), -5), "Debe lanzar excepción al setear una cantidad negativa");

        // Actualizar a una cantidad válida y verificar el recálculo
        QuoteItem updatedItem = quoteService.updateItemQuantity(quoteItem.getId(), 5);
        assertEquals(5, updatedItem.getQuantity(), "La cantidad debe actualizarse a 5");
        assertEquals(500, updatedItem.getLineTotal(), "El total de línea debe recalcularse a 500");
    }
}
