package xyz.lorcasdev.exception;

/**
 * Se lanza cuando se busca un Service o ServiceItem por un ID que no existe en
 * el catálogo.
 */
public class ServiceNotFoundException extends RuntimeException {

    private final String searchedId;

    public ServiceNotFoundException(String id) {
        super(String.format(
                "No se encontró ningún servicio con el ID: '%s'.", id
        ));
        this.searchedId = id;
    }

    public String getSearchedId() {
        return searchedId;
    }
}
