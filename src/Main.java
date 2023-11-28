import controlador.Controlador;
import modelo.GestorTruco;
import vista.Ivista;
import vista.VistaDos;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        GestorTruco modelo = new GestorTruco();
        Ivista vista = new VistaDos();
        Controlador controlador = new Controlador(vista);
        controlador.setModelo(modelo);
        vista.iniciarVisa();

    }
}