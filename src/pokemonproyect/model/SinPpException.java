package pokemonproyect.model;

/**
 * Excepcion personalizada que se lanza cuando un Pokemon intenta atacar sin Puntos de Poder (PP).
 */
public class SinPpException extends Exception{
    public SinPpException(String mensaje){
        super(mensaje);
    }
}