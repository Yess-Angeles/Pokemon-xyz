package pokemonproyect.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor del inventario del jugador.
 * Controla el almacenamiento de objetos {@link Item} y restringe la capacidad de la mochila.
 * * @author Equipo Ash
 * @version 1.0
 */
public class Mochila {
    private List<Item> bolsillos;

    public Mochila() {
        this.bolsillos = new ArrayList<>();
    }

    /**
     * Cuenta cuantos Pokemon hay actualmente en la mochila (dentro de Pokebolas ocupadas).
     * @return Cantidad de Pokemon en el equipo.
     */
    public int contarPokemones() {
        int contador = 0;
        for (Item i : bolsillos) {
            if (i instanceof Pokebola) {
                Pokebola p = (Pokebola) i;
                if (!p.estaVacia()) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public Item obtenerItem(int indice) {
        if (indice >= 0 && indice < bolsillos.size()) {
            return bolsillos.get(indice);
        }
        return null;
    }

    /**
     * Extrae y elimina un objeto de la mochila.
     * @param indice Posición del objeto a sacar.
     * @return El objeto removido, o null si el índice es invalido.
     */
    public Item sacarObjeto(int indice) {
        if (indice >= 0 && indice < bolsillos.size()) {
            return bolsillos.remove(indice);
        }
        return null;
    }

    public void listarMochila() {
        System.out.println("--- MOCHILA (" + bolsillos.size() + "/6) ---");
        for (int i = 0; i < bolsillos.size(); i++) {
            System.out.println(i + ". " + bolsillos.get(i).toString());
        }
    }

    /**
     * Intenta guardar un objeto en la mochila.
     * Valida dos reglas:
     * 1. La mochila no puede tener mas de 6 objetos en total.
     * 2. No se pueden cargar mas de 3 Pokémon (Pokebolas ocupadas) simultaneamente.
     * * @param item El objeto a guardar.
     */
    public void guardarObjeto(Item item) {
        if (bolsillos.size() >= 6) {
            System.out.println("¡Mochila llena! No cabe " + item.getNombre());
            return;
        }

        // Validación de máximo 3 Pokemones
        if (item instanceof Pokebola) {
            Pokebola ball = (Pokebola) item;
            if (!ball.estaVacia()) {
                if (contarPokemones() >= 3) {
                    System.out.println("¡No puedes llevar más de 3 Pokémon! Deja uno en la caja.");
                    return;
                }
            }
        }

        bolsillos.add(item);
        System.out.println("Guardado: " + item.toString());
    }

    public List<Item> getItems() {
        return bolsillos;
    }
}