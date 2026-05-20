package xyz.lorcasdev.exception;

public class EmptyCartException extends RuntimeException {

    public EmptyCartException() {
        super("El carrito está vacío. Agregue al menos un ítem antes de continuar.");
    }

    public EmptyCartException(String message) {
        super(message);
    }
}
