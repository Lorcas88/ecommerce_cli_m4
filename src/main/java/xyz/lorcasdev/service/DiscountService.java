package xyz.lorcasdev.service;

import java.util.List;

import xyz.lorcasdev.enums.DiscountType;
import xyz.lorcasdev.model.Quote;

public class DiscountService {

    public List<DiscountType> getActiveDiscounts() {
        // Retornamos todos los tipos de descuentos disponibles como ejemplo
        return List.of(DiscountType.values());
    }

    public void applyDiscounts(Quote quote, DiscountType type, int discountValue) {
        quote.setDiscountType(type);
        quote.setDiscountValue(discountValue);
        int discountAmount = calculateDiscountAmount(quote.getSubtotal(), type, discountValue);
        quote.setDiscountAmount(discountAmount);
        quote.setTotal((int) quote.getSubtotal() - discountAmount);
    }

    public int calculateDiscountAmount(double subtotal, DiscountType type, int value) {
        if (type == null) {
            return 0;
        }
        // Asumimos la existencia de enums PERCENTAGE y FIXED (o similares)
        if (type.name().contains("PERCENT")) {
            return (int) (subtotal * value / 100.0);
        }
        return value; // FIXED discount
    }
}
