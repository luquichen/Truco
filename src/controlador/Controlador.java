package controlador;

import modelo.*;
import vista.Ivista;
import vista.VistaDos;

import java.util.ArrayList;

public class Controlador implements Iobservador {
    private final Ivista vista;
    private GestorTruco modelo;

    public Controlador(Ivista vista) {
        this.vista=vista;
        vista.setControlador(this);
    }

    @Override
    public void setModelo(GestorTruco modelo) {
        this.modelo = modelo;
        modelo.agregarObservador(this);
    }

    //Metodo para patron Observer.
    @Override
    public void notificar(Cambios cambiosModelo) {
        switch (cambiosModelo){
            case LISTOPARAINICIAR:
                    vista.menuPrincipal();
                    break;
            case LISTAJUGADORESACTUALIZADA:
                    vista.menuListarJugadores();
                    vista.menuPrincipal();
                    break;
            case INICIOLAPARTIDA:
                    vista.iniciarVistaPartida();
                    break;
            case SEGUIJUGANDO:
                vista.menuAuxiliar(modelo.getTurno(),modelo.estadoTruc(), modelo.estadoEnvido());
                break;
            case NOTIFICARESTADOENVIO:
                vista.notificarEnvido(modelo.estadoEnvido());
                break;
            case NOTIFICARESTADOTRUCO:
                vista.notificarTruco(modelo.estadoTruc());
                vista.menuAuxiliar(modelo.getTurno(),modelo.estadoTruc(), modelo.estadoEnvido());
                break;
            case RONDATERMINADA:
                vista.notificarRondaTerminada(modelo.getGanadorRonda(), modelo.getGanadorEnvido());
                modelo.iniciarPartida();
                break;
        }
    }

    //METODOS PARA LA VISA.
    public void iniciarJuego(){
        modelo.iniciarJuego();
    }

    public void nuevoJugador(String nombreJugador) {
        modelo.nuvoJugador(nombreJugador);
    }

    public ArrayList<Jugador> listarJugadores(){
        return modelo.getJugadores();
    }

    public void iniciarPartida() {
        modelo.iniciarPartida();
    }

    public ArrayList<Carta> listarManoJugador() {
        return  modelo.getManoJugador();
    }

    public int getTurno() {
        return modelo.getTurno();
    }


    public void Jugo(int op) {
        modelo.jugo(op);
    }
}
