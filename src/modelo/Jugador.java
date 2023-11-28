package modelo;

import java.util.ArrayList;

public class Jugador implements  Ijugador {
    private String nombre;
    private ArrayList<Carta> manoRonda;
    private ArrayList<Carta> ordenDeJugada;
    private boolean canteTruco = false;
    private boolean canteReTruco = false;
    private boolean canteValeCuatro = false;
    int estadoenvido =0;
    int puntosGloba =0;


    private ArrayList<Integer> opcionesDisponibles = new ArrayList<Integer>();

    public boolean puedeCantarEnvido(){
        if (opcionesDisponibles.get(0) == 0){
            return true;
        }else {
            return false;
        }
    }
    private boolean puedeCantarRealEnvido(){
        if (opcionesDisponibles.get(1) == 0){
            return true;
        }else {
            return false;
        }
    }

    private boolean puedeCantarFaltaEnvido(){
        if (opcionesDisponibles.get(2) == 0){
            return true;
        }else {
            return false;
        }
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        manoRonda = new ArrayList<>();
        ordenDeJugada= new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            opcionesDisponibles.add(0);
        }
    }

    public void cambiarCanteTruco(){
        canteTruco=true;
    }

    public void cambiarCanteReTruco(){
        canteReTruco=true;
    }
    public void cambiarCanteValeCuatro() {
        this.canteValeCuatro=true;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }


    public String getNombre() {
        return this.nombre;
    }

    public void añadirCartaMano(Carta c){
        manoRonda.add(c);
    }
    public ArrayList<Carta> getManoRonda(){
        return manoRonda;
    }

    public void eliminarCartaMano(int c){
        manoRonda.remove(c);
    }

    public void añadirCartaOrdenJugada(Carta c){
        ordenDeJugada.add(c);
    }

    public ArrayList<Carta> getOrdenDeJugada(){
        return  ordenDeJugada;
    }

    public int getPuntosGloba(){return this.puntosGloba;}
    public void sumarPuntosGlobal(int pts){this.puntosGloba += pts; }

    public void limiarManos(){
        ordenDeJugada.clear();
        manoRonda.clear();
    }
}
