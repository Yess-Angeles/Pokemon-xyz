package pokemonproyect.model;

/**
 * Representa al Pokemon inicial de tipo Fuego.
 * Especializado en ataques especiales de alta potencia.
 * @author Equipo Ash
 */
public class Charmander extends Pokemon {

    /**
     * Inicializa un Charmander con sus estadisticas base y movimientos.
     * @param nivel Nivel inicial.
     */
    public Charmander(int nivel) {
        super("Charmander", nivel, Tipo.FUEGO, 25, Tipo.AGUA, 0);
        inicializarAtaques();
    }

    /**
     * Configura los ataques iniciales: Ascuas, Arañazo y Lanzallamas.
     */
    private void inicializarAtaques() {
        agregarAtaque(new Ataque("Ascuas", Tipo.FUEGO, 5, 10));
        agregarAtaque(new Ataque("Arañazo", Tipo.NORMAL, 7, 5));
        agregarAtaque(new Ataque("Lanzallamas", Tipo.FUEGO, 10, 3));
    }

    @Override
    public String obtenerDescripcion() {
        return "Charmander - El Pokémon lagartija de fuego. La llama de su cola indica su fuerza vital.";
    }
}