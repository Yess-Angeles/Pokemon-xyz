package pokemonproyect.model;

/**
 * Implementacion del patron de diseño Factory (Fabrica).
 * Centraliza la creación de instancias de Pokémon para no llenar el codigo principal de 'new'.
 */
public class PokemonFactory {

    /**
     * Genera un Pokemon basado en un codigo numerico.
     * @param opcion ID del Pokémon a crear (1=Charmander, 2=Squirtle, etc.).
     * @return Una nueva instancia del Pokemon seleccionado o un Rattata por defecto.
     */
    public static Pokemon crearPokemon(int opcion) {
        switch (opcion) {
            case 1: return new Charmander(1);
            case 2: return new Squirtle(1);
            case 3: return new Bulbasaur(1);
            case 4: return new Rattata(1);
            default: return new Rattata(1);
        }
    }
}