package xyz.lorcasdev.exception;

/**
 * Se lanza cuando se busca por un ID que no existe en
 * el catálogo.
 */
public class ElementNotFoundException extends RuntimeException {

    private final int searchedId;

    public ElementNotFoundException(int id) {
        super(String.format(
                "No se encontró ningún elemento con el ID: '%s'.", id
        ));
        this.searchedId = id;
    }

    public int getSearchedId() {
        return searchedId;
    }
}
