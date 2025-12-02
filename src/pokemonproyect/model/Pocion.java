package pokemonproyect.model;

/**
 * Item consumible que restaura la salud (HP) de un Pokemon.
 */
public class Pocion extends Item {
    private int puntosCuracion;

    /**
     * Crea una nueva pocion.
     * @param nombre Nombre comercial (ej. "Super Pocion").
     * @param puntosCuracion Cantidad de HP que recupera al usarse.
     */
    public Pocion(String nombre, int puntosCuracion) {
        super(nombre);
        this.puntosCuracion = puntosCuracion;
    }

    /**
     * Aplica el efecto curativo sobre un Pokemon objetivo.
     * @param p El Pokémon al que se le aplicara la pocion.
     */
    public void usar(Pokemon p) {
        if (p != null) {
            p.curar(puntosCuracion);
            System.out.println("Usaste " + nombre + ". " + p.getNombre() + " recuperó " + puntosCuracion + " HP.");
        }
    }

    @Override
    public String toString() {
        return nombre + " (Recupera " + puntosCuracion + " HP)";
    }
}