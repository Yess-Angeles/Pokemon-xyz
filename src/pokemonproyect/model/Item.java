package pokemonproyect.model;

/**
 * Clase abstracta que representa cualquier objeto fisico en el juego.
 * Sirve de base para Pokebolas, Pociones y futuros items.
 * @author Equipo Ash
 */
public abstract class Item {
    protected String nombre;

    /**
     * Constructor base para un item.
     * @param nombre El nombre identificador del objeto.
     */
    public Item(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}