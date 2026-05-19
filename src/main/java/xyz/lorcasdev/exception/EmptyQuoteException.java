package xyz.lorcasdev.exception;

/**
 * Se lanza cuando el usuario intenta confirmar una cotización vacía, o realizar
 * alguna operación que requiere al menos un ítem en la cotización.
 */
public class EmptyQuoteException extends RuntimeException {

    public EmptyQuoteException() {
        super("La cotización está vacía. Agregue al menos un servicio antes de continuar.");
    }
}
