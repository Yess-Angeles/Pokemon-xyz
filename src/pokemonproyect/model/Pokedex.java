package pokemonproyect.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sistema de registro y consulta de información de Pokemon.
 * Utiliza un HashMap para almacenar los datos de manera eficiente por ID.
 * * @author Equipo Ash
 * @version 1.0
 */
public class Pokedex {
    private Map<Integer, Pokemon> baseDeDatos;

    public Pokedex() {
        this.baseDeDatos = new HashMap<>();
    }

    /**
     * Registra un nuevo Pokemon en la base de datos local.
     * @param p El objeto Pokemon a registrar.
     */
    public void registrarPokemon(Pokemon p) {
        if (p != null) {
            baseDeDatos.put(p.getNumeroPokedex(), p);
        }
    }

    /**
     * Busca un Pokemon especifico por su número de identificación.
     * @param id Número de la Pokedex.
     * @return El Pokemon encontrado o null si no existe.
     */
    public Pokemon buscarPorID(int id) {
        return baseDeDatos.get(id);
    }

    /**
     * Obtiene una lista de todos los Pokémon registrados ordenada por su ID numerico.
     * @return Lista ordenada de Pokémon.
     */
    public List<Pokemon> getListaOrdenadaPorID() {
        List<Pokemon> lista = new ArrayList<>(baseDeDatos.values());
        Collections.sort(lista); // Usa el compareTo definido en Pokemon (por nivel, o modificable)
        return lista;
    }

    /**
     * Obtiene una lista ordenada alfabeticamente por nombre.
     * Utiliza una expresion lambda para el comparador.
     * @return Lista ordenada por nombre.
     */
    public List<Pokemon> getListaOrdenadaPorNombre() {
        List<Pokemon> lista = new ArrayList<>(baseDeDatos.values());
        Collections.sort(lista, (p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
        return lista;
    }

    public void mostrarInformeCompleto() {
        System.out.println("\n=== REPORTE DE POKEDEX (Ordenado por ID) ===");
        for (Pokemon p : getListaOrdenadaPorID()) {
            System.out.println("#" + p.getNumeroPokedex() + " " + p.getNombre() +
                    " | Tipo: " + p.getTipo());
        }
    }
}