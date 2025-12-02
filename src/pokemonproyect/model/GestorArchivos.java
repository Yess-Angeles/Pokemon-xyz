package pokemonproyect.model;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * Utilidad estatica para persistencia de datos.
 * Se encarga de guardar el historial de partidas en archivos de texto local.
 */
public class GestorArchivos {

    /**
     * Escribe el resultado final de la partida en 'historial_batallas.txt'.
     * Si el archivo no existe, lo crea. Si existe, agrega una nueva linea al final (append).
     * @param nombreGanador Texto con el reporte o resumen de la partida.
     */
    public static void guardarGanador(String nombreGanador) {
        try (FileWriter archivo = new FileWriter("historial_batallas.txt", true);
             PrintWriter escritor = new PrintWriter(archivo)) {

            escritor.println("[" + LocalDate.now() + "] Ganador: " + nombreGanador);
            System.out.println(">> SISTEMA: Resultado guardado exitosamente en 'historial_batallas.txt'");
        } catch (Exception e) {
            System.out.println("Error guardando archivo: " + e.getMessage());
        }
    }
}