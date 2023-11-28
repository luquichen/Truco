    package modelo;

    // Clase Carta
    public class Carta implements  Icarta{
        private String palo;
        private String valor;

        public Carta(String palo, String valor) {
            this.palo = palo;
            this.valor = valor;
        }

        public String getPalo() {
            return palo;
        }

        public void setPalo(String palo) {
            this.palo = palo;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }

        @Override
        public String toString() {
            return valor + " de " + palo;
        }
    }
