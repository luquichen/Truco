package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private List<Carta> cartas;
    private List<Carta> cartasUsadas;

    public Mazo() {
        cartas = new ArrayList<>();
        cartasUsadas = new ArrayList<>();

        // Crear el mazo de cartas españolas
        String[] palos = {"Copa", "Espada", "Oro", "Basto"};
        String[] valores = {"1", "2", "3", "4", "5", "6", "7", "10", "11", "12"};

        for (String palo : palos) {
            for (String valor : valores) {
                Carta carta = new Carta(palo, valor);
                cartas.add(carta);
            }
        }
    }

    public void mezclar() {
        Collections.shuffle(cartas);
    }

    public Carta obtenerPrimeraCarta() {
        if (cartas.isEmpty()) {
            System.out.println("El mazo está vacío");
            return null;
        }

        Carta primeraCarta = cartas.remove(0);
        cartasUsadas.add(primeraCarta);
        return primeraCarta;
    }

    public void reiniciarMazo() {
        cartas.addAll(cartasUsadas);
        cartasUsadas.clear();
        mezclar();
    }
}
