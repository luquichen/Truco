package vista;

import controlador.Controlador;
import modelo.Carta;
import modelo.Jugador;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaDos implements Ivista {

    Controlador controlador;

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void iniciarVisa() {
        controlador.iniciarJuego();
    }
    @Override
    public void menuListarJugadores() {
        System.out.println("LISTADO JUGADORES: ");
        ArrayList<Jugador> aux = controlador.listarJugadores();
        for(Jugador j : aux){
            System.out.println(j.getNombre());
        }
        System.out.println("--------");
    }

    @Override
    public void iniciarVistaPartida() {
        System.out.println("PARTIDA INICIADA:");
        menuAuxiliar(0,0,0);
    }

    @Override
    public void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        String opcion;
        System.out.println("Bienvenidos al Truco");
        System.out.println("1. Agregar Jugador");
        System.out.println("2. Comenzar Partida");
        System.out.println("3. Salir");
        System.out.println("Ingrese una opción: ");
        opcion = scanner.nextLine();
        switch (opcion) {
            case "1":
                menuAgregarJugador();
                break;
            case "2":
                controlador.iniciarPartida();
                break;
            case "3":

                break;
        }

    }
    private void menuAgregarJugador(){
        System.out.println("  ");
        System.out.println("  ");
        Scanner scanner = new Scanner(System.in);

        System.out.println("MENU AGREGAR JUGADORES:");
        System.out.print("Ingrese el nombre del jugador: ");
        String nombreJugador = scanner.nextLine();
        controlador.nuevoJugador(nombreJugador);
    }

    private void mostrarManoJugadores(ArrayList<Carta> aux){
        System.out.println("SUS CARTAS: ");
        int cont = 0;
        for (Carta c : aux){
            System.out.println(cont +" - "+ c.toString());
            cont++;
        }
    }

    private void menuMostrarPuntos() {
        ArrayList<Jugador> aux = controlador.listarJugadores();
        System.out.println("El JUGADOR: "+aux.get(0).getNombre() +" TIENE: "+ aux.get(0).getPuntosGloba() );
        System.out.println("El JUGADOR: "+aux.get(1).getNombre() +" TIENE: "+ aux.get(1).getPuntosGloba() );
        System.out.println("---------------------------------");
    }

    @Override
   public void menuAuxiliar(int turno, int estadotruco, int estadoenvido){
        System.out.println("PUNTOS");
        menuMostrarPuntos();
        ArrayList<Carta> manoJugador = controlador.listarManoJugador();
        this.mostrarManoJugadores(manoJugador);

        if(estadotruco==0 && estadoenvido==0){
            System.out.println("3. TRUCO");
            System.out.println("4. ENVIDO");
            System.out.println("5. REA ENVIDO");
            System.out.println("6. FALTA ENVIDO");
            System.out.println("7. MAZO");
        } else if (estadotruco==0 && estadoenvido ==1) {
            System.out.println("4. ENVIDO");
            System.out.println("5. REA ENVIDO");
            System.out.println("6. FALTA ENVIDO");
            System.out.println("8. QUIERO");
            System.out.println("9. NO QIERO");
        }else if (estadotruco==0 && estadoenvido ==2){
            System.out.println("5. REA ENVIDO");
            System.out.println("6. FALTA ENVIDO");
            System.out.println("8. QUIERO");
            System.out.println("9. NO QIERO");
        }else if (estadotruco==0 && estadoenvido ==3){
            System.out.println("6. FALTA ENVIDO");
            System.out.println("8. QUIERO");
            System.out.println("9. NO QIERO");
        }else if (estadotruco==0 && estadoenvido ==4){
            System.out.println("8. QUIERO");
            System.out.println("9. NO QIERO");
        }else if (estadotruco==1 && estadoenvido ==0){
            System.out.println("3. TRUCO");
            System.out.println("4. ENVIDO");
            System.out.println("5. REA ENVIDO");
            System.out.println("6. FALTA ENVIDO");
            System.out.println("7. MAZO");
        }else if(estadotruco==2){
            System.out.println("10. RE TRUCO");
            System.out.println("11. QUIERO TRUCO");
            System.out.println("7. MAZO");
        }else if(estadotruco==3){
            System.out.println("12. VALECUATRO");
            System.out.println("11. QUIERO TRUCO");
            System.out.println("7. MAZO");
        }else if(estadotruco==4){
            System.out.println("11. QUIERO TRUCO");
            System.out.println("7. MAZO");
        }else if(estadotruco==1 && estadoenvido==-1){
            System.out.println("3. TRUCO");
            System.out.println("7. MAZO");
        }
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();
        controlador.Jugo(op);
    }

    @Override
    public void notificarEnvido(int i) {
        switch (i){
            case 1:
                System.out.println("ENVIDO EN JUEGO");
                break;
            case 2:
                System.out.println("ENVIDO-ENVIDO EN JUEGO");
                break;
            case 3:
                System.out.println("REALENVIDO EN JUEGO");
                break;
            case 4:
                System.out.println("FALTAENVIDO EN JUEGO");
                break;
        }
        menuAuxiliar(controlador.getTurno(),1,-1);
    }

    @Override
    public void notificarTruco(int i) {
        System.out.println(i);
        switch (i){
            case 1:
                System.out.println("TRUCO EN JUEGO");
                break;
            case 2:
                System.out.println("TRUCO EN JUEGO");
                break;
            case 3:
                System.out.println("RETRUCO EN JUEGO");
                break;
            case 4:
                System.out.println("VALECUATO EN JUEGO");
                break;

        }
    }

    @Override
    public void notificarRondaTerminada(int ganadorRonda, int ganadorEnvido) {
        System.out.println("LA RONDA TERMINI");
        System.out.println("EL GANADOR DEL ENVIDO FUE: "+ ganadorEnvido);
        System.out.println("El GANADOR DE LA RONDA FUE " + ganadorRonda);
        menuMostrarPuntos();
    }
}
