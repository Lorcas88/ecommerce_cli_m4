package xyz.lorcasdev.exception;

/**
 * Se lanza cuando el usuario ingresa una cantidad que no es un entero > 0.
 * Cubre los casos: cantidad <= 0, o valor no numérico capturado antes de llamar
 * esta excepción.
 */
public class InvalidAmountException extends RuntimeException {
	
	private final int enteredAmount;

    public InvalidAmountException(int cantidad) {
        super(String.format(
                "Cantidad inválida: %d. Debe ser un número entero mayor a 0.", cantidad
        ));
        this.enteredAmount = cantidad;
    }

    public int getInvalidAmount() {
        return enteredAmount;
    }
}
