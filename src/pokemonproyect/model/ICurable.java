package pokemonproyect.model;

/**
 * Interfaz que define el comportamiento de entidades que pueden restaurar salud.
 */
public interface ICurable {

    /**
     * Restaura una cantidad especifica de salud.
     * @param cantidad Puntos de HP a recuperar.
     */
    public void curar(int cantidad);

    /**
     * Restaura la salud al maximo posible.
     */
    public void curarPorCompleto();
}
