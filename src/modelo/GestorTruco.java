package modelo;

import java.util.ArrayList;

public class GestorTruco {
    ArrayList<Iobservador> observaores = new ArrayList<Iobservador>();
    public void agregarObservador(Iobservador o){
        observaores.add(o);
    }
    public void notificar(Cambios n){
        for(Iobservador o : observaores){
            o.notificar(n);
        }
    }

    //MODELO GESTOR JUEGO
    ArrayList<Jugador> jugadores = new ArrayList<>();
    EstadoJuego  estadoJuego;
    Mazo mazoPartida=new Mazo();
    int turno = 0;
    int turnoAnterior;

    int puntajeEnjuegoEnvido=0;
    int estadoEnvido =-1;
    int ganadorEnvido=0;

    int estadoTruco=0;
    int puntajeEnTruco =0;
    int ganadorTruco=0;

    int envidoEnvido=0;
    int ronda=0;
    private  int cator = -1;

    public void iniciarJuego(){
        turnoAnterior = turno;
        estadoJuego = EstadoJuego.CONFIGURANDO;
        notificar(Cambios.LISTOPARAINICIAR);
    }
    private void  cambiarTurno(){
        if (turno==0){
            turno=1;
        }else {
            turno=0;
        }
    }
    public int getTurno(){return turno;}


    public void nuvoJugador(String nombreJugador) {
        jugadores.add(new Jugador(nombreJugador));
        notificar(Cambios.LISTAJUGADORESACTUALIZADA);

    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void iniciarPartida() {
        mazoPartida.mezclar();
        estadoJuego = EstadoJuego.JUGANDO;
        puntajeEnjuegoEnvido=1;
        turno=0;
        for (Jugador j : jugadores){
            j.añadirCartaMano(mazoPartida.obtenerPrimeraCarta());
            j.añadirCartaMano(mazoPartida.obtenerPrimeraCarta());
            j.añadirCartaMano(mazoPartida.obtenerPrimeraCarta());
        }
        ganadorEnvido = calcularGanadorEnvido();
        notificar(Cambios.INICIOLAPARTIDA);
    }

    public ArrayList<Carta> getManoJugador() {
        return  jugadores.get(turno).getManoRonda();
    }

    public int estadoEnvido() {
        return estadoEnvido;
    }

    private int calcularGanadorEnvido() {
        int auxJ0 = contetoCartas(0);
        int auxJ1= contetoCartas(1);
        if(auxJ0>auxJ1){
            return 0;
        }else {
            return 1;
        }

    }
    private int contetoCartas(int jugador){
            Carta c1 = jugadores.get(jugador).getManoRonda().get(0);
            Carta c2 = jugadores.get(jugador).getManoRonda().get(1);
            Carta c3 = jugadores.get(jugador).getManoRonda().get(2);
            int puntoAuxiliar = 0;
            //LAS TRES CARTAS TIENE EL MISMO PALO
            if(c1.getPalo() == c3.getPalo() && c1.getPalo() == c2.getValor()) {
                puntoAuxiliar=100;

            } else if (c1.getPalo() == c2.getPalo()) {
                if(c1.getValor()=="12"||c1.getValor()=="11"||c1.getValor()=="10"){
                    if(c2.getValor()=="12"||c2.getValor()=="11"||c2.getValor()=="10"){
                        puntoAuxiliar=20;
                    }else {
                        puntoAuxiliar=20+ Integer.parseInt(c2.getValor());
                    }

                }else{
                    if(c2.getValor()=="12"||c2.getValor()=="11"||c2.getValor()=="10"){
                        puntoAuxiliar=20+Integer.parseInt(c1.getValor());
                    }else {
                        puntoAuxiliar = 20 +Integer.parseInt(c1.getValor()) + Integer.parseInt(c2.getValor());;
                    }
                }

            } else if (c1.getPalo() == c3.getPalo()) {
                if(c1.getValor()=="12"||c1.getValor()=="11"||c1.getValor()=="10"){
                    if(c3.getValor()=="12"||c3.getValor()=="11"||c3.getValor()=="10"){
                        puntoAuxiliar=20;
                    }else {
                        puntoAuxiliar=20+ Integer.parseInt(c2.getValor());
                    }
                }else {
                    if (c3.getValor() == "12" || c3.getValor() == "11" || c3.getValor() == "10") {
                        puntoAuxiliar = 20 + Integer.parseInt(c1.getValor());
                    } else {
                        puntoAuxiliar = 20 +Integer.parseInt(c1.getValor()) + Integer.parseInt(c3.getValor());;
                    }
                }
            } else if (c2.getPalo() == c3.getPalo()) {
                if(c2.getValor()=="12"||c2.getValor()=="11"||c2.getValor()=="10"){
                    if(c3.getValor()=="12"||c3.getValor()=="11"||c3.getValor()=="10"){
                        puntoAuxiliar=20;
                    }else {
                        puntoAuxiliar=20+ Integer.parseInt(c2.getValor());
                    }
                }else {
                    if (c3.getValor() == "12" || c3.getValor() == "11" || c3.getValor() == "10") {
                        puntoAuxiliar = 20 + Integer.parseInt(c1.getValor());
                    } else {
                        puntoAuxiliar = 20 +Integer.parseInt(c2.getValor()) + Integer.parseInt(c3.getValor());;
                    }
                }

            } else {
                if(Integer.parseInt(c1.getValor())>Integer.parseInt(c2.getValor()) && Integer.parseInt(c1.getValor())<10 &&Integer.parseInt(c2.getValor())<10){
                    if(Integer.parseInt(c1.getValor())>Integer.parseInt(c3.getValor()) && Integer.parseInt(c3.getValor())<10){
                        puntoAuxiliar=Integer.parseInt(c1.getValor());
                    }
                }
                if(Integer.parseInt(c2.getValor())>Integer.parseInt(c3.getValor()) && Integer.parseInt(c2.getValor())<10 &&Integer.parseInt(c3.getValor())<10){
                    if(Integer.parseInt(c2.getValor())>Integer.parseInt(c3.getValor()) && Integer.parseInt(c1.getValor())<10){
                        puntoAuxiliar=Integer.parseInt(c2.getValor());
                    }
                }
                if(Integer.parseInt(c3.getValor())>Integer.parseInt(c2.getValor()) && Integer.parseInt(c3.getValor())<10 &&Integer.parseInt(c2.getValor())<10){
                    if(Integer.parseInt(c3.getValor())>Integer.parseInt(c1.getValor()) && Integer.parseInt(c1.getValor())<10){
                        puntoAuxiliar=Integer.parseInt(c1.getValor());
                    }
                }
            }

         return puntoAuxiliar;

    }

    public int estadoTruc() {
        return estadoTruco;
    }


    public void jugo(int op) {
        Cambios cambiosaux= Cambios.SEGUIJUGANDO;
        ronda++;
        switch (op){
            case 0,1,2:
                System.out.println("jugo una carta");
                gestorCartas(op);
                if(ronda==0 || ronda==1){
                    estadoEnvido=0;
                }
                if(ronda>1){
                    estadoEnvido=-1;
                    estadoTruco=1;
                }
                break;
            case 3:
                System.out.println("truco");
                cator=turno;
                estadoTruco=2;
                break;
            case  4:
                System.out.println("envido");
                if (estadoEnvido ==1){estadoEnvido+=1;}else {estadoEnvido=1;}
                break;
            case 5:
                System.out.println("realevnido");
                estadoEnvido=3;
                break;
            case 6:
                System.out.println("FALTA ENVIDO");
                estadoEnvido=4;
                break;
            case 7:
                System.out.println("se fue al mazo");
                break;
            case 8:
                System.out.println("quiere el envido");
                puntajeEnjuegoEnvido=estadoEnvido;
                cambiosaux= Cambios.NOTIFICARESTADOENVIO;
                break;
            case 9:
                System.out.println("no quiere el envido");
                break;
            case 10:
                System.out.println("retruco");
                estadoTruco=3;
                break;
            case 11:
                System.out.println("quiere truco");
                puntajeEnTruco=estadoTruco;
                cambiosaux = Cambios.NOTIFICARESTADOTRUCO;
                break;
            case 12:
                System.out.println("vale cuatro");
                estadoTruco=4;
                break;
        }
        cambiarTurno();
        notificar(cambiosaux);
    }

    private void gestorCartas(int op) {
        Carta aux = jugadores.get(turno).getManoRonda().get(op);
        jugadores.get(turno).añadirCartaOrdenJugada(aux);
        jugadores.get(turno).eliminarCartaMano(op);


        if (jugadores.get(0).getOrdenDeJugada().size() == jugadores.get(1).getOrdenDeJugada().size()) {
            if (jugadores.get(0).getOrdenDeJugada().size() == 2) {
                int auxc1 = determinarCartaGanadora(jugadores.get(0).getOrdenDeJugada().get(0), jugadores.get(1).getOrdenDeJugada().get(0));
                int auxc2= determinarCartaGanadora(jugadores.get(0).getOrdenDeJugada().get(1), jugadores.get(1).getOrdenDeJugada().get(1));
                if(auxc1==auxc2 && auxc1==0){terminarRonda(0);}
                if(auxc1==auxc2 && auxc1==1){terminarRonda(1);}
            }
            if (jugadores.get(0).getOrdenDeJugada().size() == 3){
                int auxc1 = determinarCartaGanadora(jugadores.get(0).getOrdenDeJugada().get(0), jugadores.get(1).getOrdenDeJugada().get(0));
                int auxc2= determinarCartaGanadora(jugadores.get(0).getOrdenDeJugada().get(1), jugadores.get(1).getOrdenDeJugada().get(1));
                int auxc3= determinarCartaGanadora(jugadores.get(0).getOrdenDeJugada().get(2), jugadores.get(1).getOrdenDeJugada().get(2));
                int contAux= ganadorTresCartas(auxc1,auxc2,auxc3);
                terminarRonda(contAux);
            }
        }
    }

    public  int ganadorTresCartas(int num1, int num2, int num3) {
        int[] contador = new int[3];

        // Incrementa el contador correspondiente al número recibido
        contador[num1]++;
        contador[num2]++;
        contador[num3]++;

        // Comprueba cuál número se repite más veces
        int maxRepetido = 0;
        int maxVeces = contador[0];

        for (int i = 1; i < contador.length; i++) {
            if (contador[i] > maxVeces) {
                maxRepetido = i;
                maxVeces = contador[i];
            }
        }

        return maxRepetido;
    }
    int ganadorRonda=-1;
    public int getGanadorRonda(){return ganadorRonda;};

    private void terminarRonda(int ganador ){
        jugadores.get(ganadorEnvido).sumarPuntosGlobal(puntajeEnjuegoEnvido);
        jugadores.get(ganadorTruco).sumarPuntosGlobal(puntajeEnTruco);
        jugadores.get(0).limiarManos();
        jugadores.get(1).limiarManos();
        mazoPartida.reiniciarMazo();
        mazoPartida.mezclar();
        puntajeEnTruco=1;
        puntajeEnjuegoEnvido=0;
        ganadorEnvido=0;
        ganadorTruco=0;
        turno= turnoAnterior;
        cambiarTurno();
        turnoAnterior=turno;
        ganadorRonda=ganador;

        notificar(Cambios.RONDATERMINADA);
    }

    private void gestioarEnvio() {
        estadoEnvido= estadoEnvido+1;
    }

    private int determinarCartaGanadora(Carta carta, Carta carta1) {
        int valorCarta = Integer.parseInt(carta.getValor());
        int valorCarta1 = Integer.parseInt(carta1.getValor());
        String paloCarta = carta.getPalo();
        String paloCarta1 = carta1.getPalo();

        // Definir el orden de las cartas según las reglas del truco argentino
        String[] ordenCartas = {"1Espada", "2Basto", "7Espada", "7Oro", "3Espada", "3Oro", "3Basto", "3Copa",
                "2Espada", "2Oro", "2Basto", "2Copa", "1Oro", "1Copa", "12Espada", "12Oro",
                "12Basto", "12Copa", "11Espada", "11Oro", "11Basto", "11Copa", "10Espada",
                "10Oro", "10Basto", "10Copa", "7Basto", "7Copa", "6Espada", "6Oro", "6Basto",
                "6Copa", "5Espada", "5Oro", "5Basto", "5Copa", "4Espada", "4Oro", "4Basto", "4Copa"};

        // Obtener índices de las cartas a comparar en el orden definido
        int indexCarta = -1;
        int indexCarta1 = -1;
        for (int i = 0; i < ordenCartas.length; i++) {
            if (ordenCartas[i].equals(valorCarta + paloCarta)) {
                indexCarta = i;
            }
            if (ordenCartas[i].equals(valorCarta1 + paloCarta1)) {
                indexCarta1 = i;
            }
        }

        // Comparar los índices para determinar la carta ganadora o empate
        if (indexCarta == indexCarta1) {
            return 2; // Empate
        } else if (indexCarta < indexCarta1) {
            return 0; // Gana carta
        } else {
            return 1; // Gana carta1
        }
    }

    public int getGanadorEnvido() {
        return ganadorEnvido;
    }
}
