package pokemonproyect.model;

/**
 * Representa al Pokemon inicial de tipo Planta.
 * Se caracteriza por llevar un bulbo en su espalda que crece con el.
 * @author Equipo Ash
 */
public class Bulbasaur extends Pokemon {

    /**
     * Crea una instancia de Bulbasaur con sus estadisticas base y ataques de tipo Planta/Normal.
     * @param nivel Nivel inicial del Pokémon.
     */
    public Bulbasaur(int nivel) {
        super("Bulbasaur", nivel, Tipo.PLANTA, 25, Tipo.FUEGO, 1);
        inicializarAtaques();
    }

    /**
     * Configura los movimientos iniciales: Latigo Cepa, Placaje y Hoja Afilada.
     */
    private void inicializarAtaques() {
        agregarAtaque(new Ataque("Látigo Cepa", Tipo.PLANTA, 5, 10));
        agregarAtaque(new Ataque("Placaje", Tipo.NORMAL, 7, 5));
        agregarAtaque(new Ataque("Hoja Afilada", Tipo.PLANTA, 10, 3));
    }

    @Override
    public String obtenerDescripcion() {
        return "Bulbasaur - El Pokémon semilla. Lleva un bulbo en su espalda desde que nace.";
    }
}