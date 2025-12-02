package pokemonproyect.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa la base de cualquier criatura en el juego.
 * Implementa la interfaz {@link ICurable} para manejo de salud y {@link Comparable} para ordenamiento.
 * Gestiona estadísticas vitales, tipos elementales y el sistema de combate.
 * * @author Equipo Ash
 * @version 1.0
 */
public abstract class Pokemon implements ICurable, Comparable<Pokemon>{
    protected String nombre;
    protected int nivel;
    protected Tipo tipo;
    protected Tipo debilidad;
    protected double hpBase;
    protected double hpActual;
    protected double hpMaximo;
    protected List<Ataque> ataques;
    protected int numeroPokedex;

    /**
     * Constructor para Pokemons que tienen una debilidad elemental específica.
     * * @param nombre Nombre de la especie (ej. "Charmander").
     * @param nivel Nivel actual de poder.
     * @param tipo Elemento principal del Pokémon.
     * @param hpBase Estadisticas base de vida.
     * @param debilidad Tipo elemental que le causa más daño (1.25x).
     * @param numeroPokedex ID unico en la base de datos.
     */
    public Pokemon(String nombre, int nivel, Tipo tipo, double hpBase, Tipo debilidad, int numeroPokedex) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
        this.debilidad = debilidad;
        this.hpMaximo = hpBase*nivel*Math.pow(1.2,nivel-1);
        this.hpActual = hpMaximo;
        this.ataques = new ArrayList<>();
        this.numeroPokedex = numeroPokedex;
    }

    /**
     * Constructor para Pokemons sin debilidad elemental definida.
     * * @param nombre Nombre de la especie.
     * @param nivel Nivel actual.
     * @param tipo Elemento principal.
     * @param hpBase Estadisticas base.
     * @param numeroPokedex ID unico.
     */
    public Pokemon(String nombre, int nivel, Tipo tipo, double hpBase, int numeroPokedex) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
        this.hpMaximo = hpBase*nivel*Math.pow(1.2,nivel-1);
        this.hpActual = hpMaximo;
        this.ataques = new ArrayList<>();
        this.numeroPokedex = numeroPokedex;
    }

    /**
     * Ejecuta un ataque especifico de la lista de movimientos del Pokemon.
     * Gestiona las excepciones si el ataque no tiene PP (Puntos de Poder).
     * * @param indiceAtaque Posición del ataque en la lista (0-2).
     * @return El objeto {@link Ataque} utilizado, o null si falló.
     */
    public Ataque lanzarAtaque(int indiceAtaque) {
        if (indiceAtaque < 0 || indiceAtaque >= ataques.size()) {
            System.err.println("Error: No existe un ataque en la posición " + indiceAtaque);
            return null;
        }

        Ataque ataqueSeleccionado = ataques.get(indiceAtaque);

        try {
            ataqueSeleccionado.usarAtaque();
            System.out.println(nombre + " usó " + ataqueSeleccionado.getNombre() + "!");
            return ataqueSeleccionado;
        } catch (SinPpException e) {
            System.err.println(nombre + " intentó usar " + ataqueSeleccionado.getNombre() + ", pero falló:");
            System.err.println("==> " + e.getMessage());
            return null;
        }
    }

    /**
     * Agrega un nuevo ataque a la lista si hay espacio (maximo 3).
     * @param ataque El objeto ataque a aprender.
     */
    public void agregarAtaque(Ataque ataque) {
        if (ataques.size() < 3) {
            ataques.add(ataque);
        }
    }

    /**
     * Calcula y aplica el daño recibido, considerando debilidades elementales.
     * * @param danio Cantidad de daño base.
     * @param tipoDanio Tipo elemental del ataque recibido.
     * @return El daño final aplicado después de calcular debilidades.
     */
    public double recibirDanio(double danio, Tipo tipoDanio) {
        if (tipoDanio == debilidad){
            danio = danio * 1.25;
        }
        hpActual -= danio;

        if (hpActual < 0) {
            hpActual = 0;
        }
        return danio;
    }

    /**
     * Recupera una cantidad especifica de puntos de salud (HP).
     * @param cantidad Cantidad de puntos a restaurar.
     */
    @Override
    public void curar(int cantidad) {
        hpActual += cantidad;
        if (hpActual > hpMaximo) {
            hpActual = hpMaximo;
        }
    }

    /**
     * Restaura la salud del Pokemon al 100%.
     */
    @Override
    public void curarPorCompleto() {
        this.hpActual = this.hpMaximo;
    }

    /**
     * Sobrecarga del metodo curar para objetos especificos por nombre.
     * @param nombreObjeto Nombre del item usado (ej. "SuperPocion").
     */
    public void curar(String nombreObjeto) {
        if(nombreObjeto.equals("SuperPocion")) {
            this.curar(50);
        }
    }

    /**
     * Verifica si el Pokemon ha perdido toda su salud.
     * @return true si HP es 0, false en caso contrario.
     */
    public boolean estaDebilitado() {
        return hpActual <= 0;
    }

    public abstract String obtenerDescripcion();

    // Getters
    public String getNombre() { return nombre; }
    public int getNivel() { return nivel; }
    public Tipo getTipo() { return tipo; }
    public double getHpBase() { return hpBase; }
    public double getHpActual() { return hpActual; }
    public double getHpMaximo() { return hpMaximo; }
    public List<Ataque> getAtaques() { return ataques; }

    public double getPorcentajeHP() {
        return (hpActual/(hpMaximo)) * 100;
    }

    @Override
    public String toString() {
        return nombre + " (Nv." + nivel + ") - HP: " + hpActual + "/" + hpMaximo;
    }

    /**
     * Compara dos Pokemon basandose en su nivel.
     */
    @Override
    public int compareTo(Pokemon otroPokemon) {
        return Integer.compare(this.nivel, otroPokemon.nivel);
    }

    public int getNumeroPokedex() {
        return numeroPokedex;
    }
}