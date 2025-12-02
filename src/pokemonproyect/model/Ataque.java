package pokemonproyect.model;

/**
 * Representa una habilidad de combate con gestion de Puntos de Poder (PP).
 * * @author Equipo Ash
 */
public class Ataque {
    private String nombre;
    private Tipo tipo;
    private double potencia;
    private int ppMaximos;
    private int ppActuales;

    /**
     * Crea un nuevo ataque.
     * @param nombre Nombre del movimiento.
     * @param tipo Tipo elemental.
     * @param potencia Daño base.
     * @param ppMaximos Cantidad de usos permitidos.
     */
    public Ataque(String nombre, Tipo tipo, double potencia, int ppMaximos) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.potencia = potencia;
        this.ppMaximos = ppMaximos;
        this.ppActuales = ppMaximos;
    }

    /**
     * Intenta usar el ataque reduciendo sus PP en 1.
     * @throws SinPpException Si los PP actuales han llegado a 0.
     */
    public void usarAtaque() throws SinPpException {
        if (ppActuales > 0){
            ppActuales--;
        } else {
            throw new SinPpException("¡No quedan PP para " + this.nombre + "!");
        }
    }

    public String getNombre() { return nombre; }
    public Tipo getTipo() { return tipo; }
    public double getPotencia() { return potencia; }
    public int getPpActuales() { return ppActuales; }
    public int getPpMaximos() { return ppMaximos; }

    @Override
    public String toString() {
        return nombre;
    }
}