package pokemonproyect;

import pokemonproyect.model.*;
import java.util.Scanner;
import java.util.List;

/**
 * Clase principal (Main) que ejecuta la logica del juego de consola.
 * Controla el flujo de la aventura, menús, combates y gestion de turnos.
 * @author Equipo Ash
 * @version 1.0 Final
 */

public class PokemonProyect {

    static StringBuilder reportePartida = new StringBuilder();
    /**
     * Metodo principal que inicia la aplicacion.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        Mochila mochila = new Mochila();

        Pokedex pokedex = new Pokedex();

        pokedex.registrarPokemon(PokemonFactory.crearPokemon(1));
        pokedex.registrarPokemon(PokemonFactory.crearPokemon(2));
        pokedex.registrarPokemon(PokemonFactory.crearPokemon(3));

        reportePartida.append("Partida iniciada. ");
        System.out.println("=== BIENVENIDO AL MUNDO POKEMON ===");

        int opcionElegida = 0;
        boolean seleccionConfirmada = false;

        while (!seleccionConfirmada) {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Consultar Pokedex (Ver información de los iniciales)");
            System.out.println("2. Elegir compañero y comenzar aventura");
            System.out.print("Opción: ");
            int menuInicial = scanner.nextInt();

            if (menuInicial == 1) {
                // Sub-menú de Pokedex
                System.out.println("\n--- POKEDEX ---");
                System.out.println("Selecciona el ID del Pokemon para ver sus datos:");
                System.out.println("0. Charmander");
                System.out.println("1. Bulbasaur");
                System.out.println("2. Squirtle");
                System.out.print("ID a consultar: ");
                int idConsulta = scanner.nextInt();

                Pokemon pInfo = pokedex.buscarPorID(idConsulta);
                if (pInfo != null) {
                    System.out.println("\n------------------------------------------------");
                    System.out.println("DATOS DE POKEDEX:");
                    System.out.println(pInfo.obtenerDescripcion());
                    System.out.println("Tipo: " + pInfo.getTipo());
                    System.out.println("------------------------------------------------");
                } else {
                    System.out.println("ID no encontrado en la base de datos local.");
                }

            } else if (menuInicial == 2) {
                // Selección definitiva
                System.out.println("\nElige tu compañero inicial definitivo:");
                System.out.println("1. Charmander");
                System.out.println("2. Squirtle");
                System.out.println("3. Bulbasaur");
                System.out.print("Opción: ");
                opcionElegida = scanner.nextInt();

                if (opcionElegida >= 1 && opcionElegida <= 3) {
                    seleccionConfirmada = true;
                } else {
                    System.out.println("Opción inválida. Intenta de nuevo.");
                }
            }
        }

        // Creación del Pokemon elegido
        Pokemon miPokemon = PokemonFactory.crearPokemon(opcionElegida);
        reportePartida.append("Eligio a: ").append(miPokemon.getNombre()).append(". ");
        System.out.println("¡Has elegido a " + miPokemon.getNombre() + "!");

        System.out.println("\n>> El profesor te entrega tu equipo de viaje...");
        mochila.guardarObjeto(new Pokebola("Pokebola de " + miPokemon.getNombre(), miPokemon));
        mochila.guardarObjeto(new Pokebola("Pokebola Basica A"));
        mochila.guardarObjeto(new Pokebola("Pokebola Basica B"));
        mochila.guardarObjeto(new Pocion("Super Pocion", 25));
        mochila.guardarObjeto(new Pocion("Pocion Normal", 15));

        // 4. EXPLORACIÓN
        System.out.println("\n¿Quieres salir a explorar? (1: Si / 2: No)");
        int decision = scanner.nextInt();

        if (decision != 1) {
            System.out.println("Has decidido terminar tu viaje muy pronto. Fin del juego.");
            reportePartida.append("Jugador decidio no salir. Fin.");
            GestorArchivos.guardarGanador(reportePartida.toString());
            return;
        }

        Pokemon enemigo1 = PokemonFactory.crearPokemon(4);
        System.out.println("\n¡Un " + enemigo1.getNombre() + " salvaje apareció!");

        pelearySobrevivir(scanner, miPokemon, enemigo1, mochila);

        if (enemigo1.estaDebilitado()) {
            System.out.println("\n>>> ¡VICTORIA! Has derrotado a " + enemigo1.getNombre() + ".");

            System.out.println("¿Quieres atrapar a Rattata? (1: Si / 2: No)");
            if (scanner.nextInt() == 1) {
                Pokebola bolaVacia = buscarPokebolaVacia(mochila);
                if (bolaVacia != null) {
                    bolaVacia.capturar(enemigo1);

                    // --- CORRECCIÓN IMPORTANTE: CURAR AL ATRAPAR ---
                    enemigo1.curarPorCompleto();

                    System.out.println("¡Has atrapado a Rattata y se ha recuperado para unirse a tu equipo!");
                    reportePartida.append("Atrapo a Rattata. ");
                }
            } else {
                reportePartida.append("No quiso atrapar a Rattata. ");
            }
        } else {
            System.out.println("\n>>> DERROTA... No te quedan Pokemon para combatir.");
            System.out.println("Fin de la aventura.");
            reportePartida.append("Perdio contra Rattata (Equipo eliminado). ");
            GestorArchivos.guardarGanador(reportePartida.toString());
            return;
        }

        System.out.println("\n>> ¡Debes curar a tu equipo antes de seguir!");
        curarObligatoriamente(scanner, mochila, miPokemon);

        System.out.println("\n¿Seguir explorando? (1: Si / 2: Salir)");
        if (scanner.nextInt() == 1) {
            int idEnemigo = (opcionElegida == 1) ? 2 : 1;
            Pokemon enemigoFinal = PokemonFactory.crearPokemon(idEnemigo);

            System.out.println("\n¡Te encontraste con un " + enemigoFinal.getNombre() + " rival!");

            Pokemon combatiente = miPokemon;
            if (miPokemon.estaDebilitado()) {
                System.out.println("Tu pokemon inicial esta debilitado. Elige a otro para empezar.");
                combatiente = elegirRelevoManual(scanner, mochila, null);
            }

            if (combatiente != null) {
                pelearySobrevivir(scanner, combatiente, enemigoFinal, mochila);

                System.out.println("\n=== RESULTADO FINAL ===");
                if (enemigoFinal.estaDebilitado()) {
                    System.out.println("¡FELICIDADES! Has superado todos los retos.");
                    reportePartida.append("Resultado: VICTORIA FINAL.");
                } else {
                    System.out.println("Perdiste... Todo tu equipo ha sido derrotado.");
                    reportePartida.append("Resultado: DERROTA FINAL.");
                }
            } else {
                 System.out.println("No tienes pokemones vivos para iniciar el combate. Fin.");
            }
        } else {
            reportePartida.append("Se retiro tras el primer combate.");
        }

        GestorArchivos.guardarGanador(reportePartida.toString());
    }

    /**
     * Gestiona el ciclo de combate por turnos entre dos Pokemon.
     * @param sc Scanner para leer input del usuario.
     * @param pokemonActivo Tu Pokemon actual.
     * @param rival El Pokemon enemigo.
     * @param mochila Acceso al inventario durante la batalla.
     */

    public static void pelearySobrevivir(Scanner sc, Pokemon pokemonActivo, Pokemon rival, Mochila mochila) {

        while (!rival.estaDebilitado()) {

            if (pokemonActivo.estaDebilitado()) {
                System.out.println(pokemonActivo.getNombre() + " no puede continuar...");

                Pokemon relevo = elegirRelevoManual(sc, mochila, pokemonActivo);

                if (relevo != null) {
                    System.out.println("¡Adelante " + relevo.getNombre() + "!");
                    pokemonActivo = relevo;
                } else {
                    System.out.println("¡No tienes más Pokemon disponibles para luchar!");
                    break;
                }
            }

            System.out.println("\n------------------------------------------");
            System.out.println(pokemonActivo.getNombre() + " HP: " + (int)pokemonActivo.getHpActual() + "/" + (int)pokemonActivo.getHpMaximo());
            System.out.println("Rival (" + rival.getNombre() + ") HP: " + (int)rival.getHpActual() + "/" + (int)rival.getHpMaximo());

            System.out.println("Ataques disponibles:");
            List<Ataque> ataques = pokemonActivo.getAtaques();
            for (int i = 0; i < ataques.size(); i++) {
                Ataque a = ataques.get(i);
                System.out.println(i + ": " + a.getNombre() + " (PP: " + a.getPpActuales() + ")");
            }

            System.out.print("Elige ataque (número): ");
            int atqIdx = sc.nextInt();

            Ataque ataqueUsado = pokemonActivo.lanzarAtaque(atqIdx);

            if (ataqueUsado != null) {
                double danio = ataqueUsado.getPotencia();
                rival.recibirDanio(danio, ataqueUsado.getTipo());
            }

            if (!rival.estaDebilitado()) {
                System.out.println(">> El rival contraataca!");
                int indexAtack = 2;
                if(ataques.get(indexAtack).getPpActuales()>0){
                    pokemonActivo.recibirDanio(rival.lanzarAtaque(indexAtack).getPotencia(), rival.getTipo());
                }else if ((ataques.get(indexAtack-1).getPpActuales()>0)){
                    pokemonActivo.recibirDanio(rival.lanzarAtaque(indexAtack-1).getPotencia(), rival.getTipo());
                }else{
                    pokemonActivo.recibirDanio(rival.lanzarAtaque(0).getPotencia(), rival.getTipo());                    
                }
            }
        }
    }

    public static Pokemon elegirRelevoManual(Scanner sc, Mochila mochila, Pokemon actualMuerto) {
        boolean hayVivos = false;
        for (Item i : mochila.getItems()) {
            if (i instanceof Pokebola) {
                Pokebola pb = (Pokebola) i;
                if (!pb.estaVacia() && !pb.lanzarAlCombate().estaDebilitado() && pb.lanzarAlCombate() != actualMuerto) {
                    hayVivos = true;
                    break;
                }
            }
        }

        if (!hayVivos) return null;

        while (true) {
            System.out.println("\n=== ELIGE UN POKEMON ===");
            List<Item> items = mochila.getItems();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) instanceof Pokebola) {
                    Pokebola ball = (Pokebola) items.get(i);
                    String estado = "Vacia";
                    if (!ball.estaVacia()) {
                        Pokemon p = ball.lanzarAlCombate();
                        estado = p.getNombre() + " (HP: " + (int)p.getHpActual() + ")";
                        if (p.estaDebilitado()) estado += " [DEBILITADO]";
                        if (p == actualMuerto) estado += " [ACTUAL]";
                    }
                    System.out.println(i + ". " + ball.toString() + " -> " + estado);
                }
            }

            System.out.print("Elige el número de la pokebola: ");
            int index = sc.nextInt();
            Item item = mochila.obtenerItem(index);

            if (item instanceof Pokebola) {
                Pokebola ballSeleccionada = (Pokebola) item;
                if (!ballSeleccionada.estaVacia()) {
                    Pokemon p = ballSeleccionada.lanzarAlCombate();
                    if (!p.estaDebilitado() && p != actualMuerto) {
                        return p;
                    } else {
                        System.out.println("¡Ese Pokemon no puede pelear! Elige otro.");
                    }
                } else {
                    System.out.println("Esa pokebola está vacía.");
                }
            } else {
                System.out.println("Selección inválida.");
            }
        }
    }

    public static Pokebola buscarPokebolaVacia(Mochila mochila) {
        for (Item i : mochila.getItems()) {
            if (i instanceof Pokebola) {
                Pokebola p = (Pokebola) i;
                if (p.estaVacia()) {
                    return p;
                }
            }
        }
        System.out.println("No tienes pokebolas vacías...");
        return null;
    }

    public static void curarObligatoriamente(Scanner sc, Mochila mochila, Pokemon p) {
        boolean curado = false;
        while (!curado) {
            mochila.listarMochila();
            System.out.print("Elige el número de una POCIÓN para curar a " + p.getNombre() + ": ");
            int index = sc.nextInt();

            Item itemSeleccionado = mochila.obtenerItem(index);

            if (itemSeleccionado instanceof Pocion) {
                Item itemSacado = mochila.sacarObjeto(index);
                ((Pocion) itemSacado).usar(p);
                curado = true;
                reportePartida.append("Uso pocion tras combate. ");
            } else {
                System.out.println("¡Eso no es una poción! Elige un objeto válido.");
            }
        }
    }
}
