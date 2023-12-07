public class Inventario {
    // Atributos da classe Inventario
    private Carta[] cartas;
    private int nivelAtual;
    private int cardcoins;
    // Construtor
    public Inventario() {
        this.cartas = new Carta[200];
        this.nivelAtual = 1;
        this.cardcoins = 0;
    }
    // Métodos getters e setters

    public Carta[] getCartas() {
        return cartas;
    }

    public void setCartas(Carta[] cartas) {
        this.cartas = cartas;
    }

    public int getNivelAtual() {
        return nivelAtual;
    }

    public void setNivelAtual(int nivelAtual) {
        this.nivelAtual = nivelAtual;
    }

    public int getCardcoins() {
        return cardcoins;
    }

    public void setCardcoins(int cardcoins) {
        this.cardcoins = cardcoins;
    }
    // Adiciona uma carta ao inventário
    public void adicionarCarta(Carta carta) {
        // Verifica se o inventário já está cheio
        if (cartas.length >= 200) {
            System.out.println("O inventário está cheio. Não é possível adicionar mais cartas.");
            return;
        }
        for (int i = 0; i < cartas.length; i++) {
            // Encontra a primeira posição nula e adiciona a carta
            if (cartas[i] == null) {
                cartas[i] = carta;
                System.out.println("Carta adicionada ao inventário: " + carta.getNome());
                return;
            }
        }

        System.out.println("O inventário está cheio.");
    }
    // Método para remover carta
    public void removerCarta(Carta carta) {
        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i] != null && cartas[i].equals(carta)) {
                cartas[i] = null;
                System.out.println("Carta removida do inventário: " + carta.getNome());
                return;
            }
        }

        System.out.println("Carta não encontrada no inventário.");
    }

    // Método para contar quantas cartas de um tipo específico existem no inventário
    public int contagemCarta(Carta cartaAleatoria) {
        int contagem = 0;
        for (Carta carta : cartas) {
            if (carta != null && carta.equals(cartaAleatoria)) {
                contagem++;
            }
        }
        return contagem;
    }

}