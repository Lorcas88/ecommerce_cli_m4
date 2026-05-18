package xyz.lorcasdev.model;

public class Validator {
	private Validator() {} // evita que se instancie

    public static void validId(int id) {
        if (id < 1)
            throw new IllegalArgumentException("El id no puede ser menor que 1");
    }

    public static void notNull(Object obj, String field) {
        if (obj == null) {
            throw new IllegalArgumentException("El campo " + field + " es obligatorio");
        }
    }
    
    public static void notEmpty(String value, String field) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("El campo " + field + " es obligatorio");
    }

    public static void positive(int value, String field) {
        if (value <= 0)
            throw new IllegalArgumentException("El campo " + field + " debe ser mayor a 0");
    }

    public static void notNegative(double value, String field) {
        if (value < 0)
            throw new IllegalArgumentException("El campo " + field + " no puede ser negativo");
    }
}
