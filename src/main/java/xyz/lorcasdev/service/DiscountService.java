package xyz.lorcasdev.service;

import java.util.List;

import xyz.lorcasdev.enums.DiscountType;
import xyz.lorcasdev.model.Quote;

public class DiscountService {

    public List<String> getActiveDiscountRules() {
        return List.of(
                "1. 10% de descuento en compras a partir de $1,000,000",
                "2. 5% de descuento en compras a partir de $500,000"
        );
    }

    public void applyAutomaticDiscounts(Quote quote) {
        double subtotal = quote.getSubtotal();
        int discountAmount = 0;
        int discountValue = 0;
        String ruleApplied = "";

        // Evaluamos las reglas de mayor a menor beneficio
        if (subtotal >= 1_000_000) {
            discountValue = 10;
            discountAmount = (int) (subtotal * discountValue / 100.0);
            ruleApplied = "10% de descuento por compras a partir de $1,000,000";
        } else if (subtotal >= 500_000) {
            discountValue = 5;
            discountAmount = (int) (subtotal * discountValue / 100.0);
            ruleApplied = "5% de descuento por compras a partir de $500,000";
        }

        if (discountAmount > 0) {
            quote.setDiscountValue(discountValue);
            quote.setDiscountAmount(discountAmount);
            quote.setTotal((int) subtotal - discountAmount);
            System.out.println("\n*** DESCUENTO APLICADO ***");
            System.out.println("Regla: " + ruleApplied);
            System.out.println("Descuento: -$" + discountAmount);
        } else {
            quote.setDiscountAmount(0);
            quote.setTotal((int) subtotal);
        }
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
