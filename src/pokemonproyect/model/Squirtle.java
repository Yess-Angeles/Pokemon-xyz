package pokemonproyect.model;

/**
 * Representa al Pokemon inicial de tipo Agua.
 * Es una pequeña tortuga que lanza agua a presion y se protege con su caparazon.
 * @author Equipo Ash
 */
public class Squirtle extends Pokemon {

    /**
     * Crea una instancia de Squirtle lista para el combate.
     * @param nivel Nivel inicial del Pokemon.
     */
    public Squirtle(int nivel) {
        super("Squirtle", nivel, Tipo.AGUA, 25, Tipo.PLANTA, 2);
        inicializarAtaques();
    }

    /**
     * Configura los movimientos iniciales: Pistola Agua, Placaje e Hidrobomba.
     */
    private void inicializarAtaques() {
        agregarAtaque(new Ataque("Pistola Agua", Tipo.AGUA, 5, 10));
        agregarAtaque(new Ataque("Placaje", Tipo.NORMAL, 7, 5));
        agregarAtaque(new Ataque("Hidrobomba", Tipo.AGUA, 10, 3));
    }

    @Override
    public String obtenerDescripcion() {
        return "Squirtle - El Pokémon tortuga. Se protege con su caparazón y ataca con agua.";
    }
}