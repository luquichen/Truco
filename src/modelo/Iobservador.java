package modelo;

public interface Iobservador {
    void notificar(Cambios cambiosModelo);
    void setModelo(GestorTruco modelo);
}
