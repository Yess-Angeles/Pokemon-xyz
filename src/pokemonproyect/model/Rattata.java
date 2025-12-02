package pokemonproyect.model;

/**
 * Representa un Pokemon comun de tipo Normal.
 * Suele aparecer como enemigo salvaje frecuente al inicio de la aventura.
 * @author Equipo Ash
 */
public class Rattata extends Pokemon {

    /**
     * Crea un Rattata salvaje con estadisticas balanceadas.
     * @param nivel Nivel inicial del Pokemon.
     */
    public Rattata(int nivel) {
        super("Rattata", nivel, Tipo.NORMAL, 20, 3);
        inicializarAtaques();
    }

    /**
     * Configura los ataques rapidos y de mordedura tipicos de este Pokemon.
     */
    private void inicializarAtaques() {
        agregarAtaque(new Ataque("Placaje", Tipo.NORMAL, 4, 10));
        agregarAtaque(new Ataque("Ataque Rápido", Tipo.NORMAL, 6, 5));
        agregarAtaque(new Ataque("Mordisco", Tipo.NORMAL, 8, 3));
    }

    @Override
    public String obtenerDescripcion() {
        return "Rattata - El Pokémon ratón. Muy común y fácil de encontrar.";
    }
}