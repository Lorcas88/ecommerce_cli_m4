package xyz.lorcasdev;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import xyz.lorcasdev.enums.DiscountType;
import xyz.lorcasdev.model.Quote;
import xyz.lorcasdev.service.DiscountService;

class DiscountServiceTest {

    private DiscountService discountService;
    private Quote quote;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService();

        // Simulamos un carrito con subtotal de 1000
        quote = new Quote(1, null, "Guest", "guest@test.com", "123", null, null, 0, 0, 1000, 1000, LocalDateTime.now(), LocalDateTime.now().plusDays(15));
    }

    @Test
    void testCalculateDiscountAmountNullType() {
        // Regla: si no hay un tipo de descuento seleccionado, el monto descontado es 0
        assertEquals(0, discountService.calculateDiscountAmount(1000, null, 20));
    }

    @Test
    void testApplyDiscountsUpdatesQuoteAndTotals() {
        // Obtenemos un tipo de descuento del sistema dinámicamente para la prueba
        if (DiscountType.values().length > 0) {
            DiscountType activeType = DiscountType.values()[0];
            int discountValueToApply = 15;

            discountService.applyDiscounts(quote, activeType, discountValueToApply);

            // Comprobar que los metadatos del descuento se guardaron
            assertEquals(activeType, quote.getDiscountType(), "El tipo de descuento debe haberse asignado");
            assertEquals(discountValueToApply, quote.getDiscountValue(), "El valor del descuento debe haberse asignado");

            // Comprobar la resta en el total
            int expectedDiscount = discountService.calculateDiscountAmount(1000, activeType, discountValueToApply);

            assertEquals(expectedDiscount, quote.getDiscountAmount(), "El monto en dinero descontado debe estar calculado");
            assertEquals(1000 - expectedDiscount, quote.getTotal(), "El total debe ser el subtotal menos el descuento aplicado");
        }
    }
}
