package vista;

import controlador.Controlador;

public interface Ivista {
    void setControlador(Controlador controlador);
    void iniciarVisa();

    void menuPrincipal();

    void menuListarJugadores();

    void iniciarVistaPartida();

    void menuAuxiliar(int turno, int estadotruco, int estadoenvido);

    void notificarEnvido(int i);

    void notificarTruco(int i);

    void notificarRondaTerminada(int ganadorRonda, int ganadorEnvido);

    //void menuPartida();

   // void menuConsultarEnvido(int turno, int i);

   // void menuTerminarEnvido();

   // void menuPartida(int turno, int i);

   // void menuRondaTerminada();

   // void menuConsultaTruco(int turno, int estadoEnvido, int estadoTruco);

   // void MenuEnvidoTraasTruco(int turno);

   // void menuDesarolloSinOpcciones(int turno);

   // void consularEnvio(int turno);
}
