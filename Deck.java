import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
  // Atributos da classe Deck
class Deck {
    private String nome;
    private Stack<Carta> cartas;  
    private boolean disponibilidade;
    // Construtor da classe Deck
    public Deck(String nome) {
        this.nome = nome;
        this.cartas = new Stack<>();
        this.disponibilidade = false;
    }
    // Adiciona uma carta ao deck, removendo-a do inventário
    public void adicionarCarta(Carta carta, Inventario inventario) {
        if (cartas.contains(carta)) {
            System.out.println("A carta '" + carta.getNome() + "' já está no deck.");
            return;
        }

        if (cartas.size() >= 60) {
            System.out.println("O deck já possui o número máximo de cartas (60).");
            return;
        }

        if (contarCartasRepetidas(carta) >= 3 && !carta.getNome().equals("Mana")) {
            System.out.println("Não é permitido ter mais de 3 cópias da carta '" + carta.getNome() + "' no deck.");
            return;
        }
        // Adiciona a carta ao deck e atualiza disponibilidade
        cartas.push(carta);  
        inventario.removerCarta(carta);
        atualizarDisponibilidade();
    }
    // Remove uma carta do deck, adicionando-a de volta ao inventário
    public void removerCarta(Carta carta, Inventario inventario) {
        if (cartas.remove(carta)) {
            inventario.adicionarCarta(carta);
            atualizarDisponibilidade();
        } else {
            System.out.println("A carta '" + carta.getNome() + "' não está no deck.");
        }
    }
   // Conta o número de cópias de uma carta no deck
    private int contarCartasRepetidas(Carta carta) {
        int count = 0;
        for (Carta c : cartas) {
            if (c.getNome().equals(carta.getNome())) {
                count++;
            }
        }
        return count;
    }
   // Atualiza a disponibilidade do deck
    private void atualizarDisponibilidade() {
        boolean todasCondicoesSatisfeitas = cartas.size() >= 60 && cartas.stream()
                .allMatch(carta -> contarCartasRepetidas(carta) <= 3 || carta.getNome().equals("Mana"));
        disponibilidade = todasCondicoesSatisfeitas;
    }
    // Retorna a disponibilidade do deck
    public boolean isDisponivel() {
        return disponibilidade;
}
    public int getTamanho() {
        return 0;
    }

    public Carta getCarta(int randomIndex) {
        return null;
    }

    public void removerCarta(int randomIndex) {
    }
}
