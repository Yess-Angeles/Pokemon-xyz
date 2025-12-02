package pokemonproyect.model;

/**
 * Item específico diseñado para contener instancias de {@link Pokemon}.
 * Puede estar vacía o ocupada.
 */
public class Pokebola extends Item {
    private Pokemon pokemonAtrapado;

    public Pokebola(String nombre) {
        super(nombre);
        this.pokemonAtrapado = null;
    }

    public Pokebola(String nombre, Pokemon p) {
        super(nombre);
        this.pokemonAtrapado = p;
    }

    public boolean estaVacia() {
        return pokemonAtrapado == null;
    }

    /**
     * Intenta capturar un Pokemon salvaje.
     * @param salvaje El Pokémon objetivo.
     * @return true si la captura fue exitosa (la bola estaba vacía), false si ya estaba ocupada.
     */
    public boolean capturar(Pokemon salvaje) {
        if (estaVacia()) {
            this.pokemonAtrapado = salvaje;
            return true;
        }
        return false;
    }

    /**
     * Libera al Pokemon contenido para combatir.
     * @return El objeto Pokemon almacenado.
     */
    public Pokemon lanzarAlCombate() {
        return pokemonAtrapado;
    }

    public void liberarPokemon() {
        this.pokemonAtrapado = null;
    }

    @Override
    public String toString() {
        if (estaVacia()) {
            return nombre + " [Vacia]";
        } else {
            return nombre + " [Ocupada]";
        }
    }
}