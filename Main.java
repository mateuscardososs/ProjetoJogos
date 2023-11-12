public interface Main {
    void ativarHabilidade();
}

class Carta implements Ativacoes {
    private String habilidade;

    public Carta(String habilidade) {
        this.habilidade = habilidade;
    }

    @Override
    public void ativarHabilidade() {
        System.out.println("Ativando habilidade da carta: " + (habilidade != null ? habilidade : "Esta carta não possui habilidade."));
    }
}

class CartaUnique implements Ativacoes {
    private String habilidade1;
    private String habilidade2;

    public CartaUnique(String habilidade1, String habilidade2) {
        this.habilidade1 = habilidade1;
        this.habilidade2 = habilidade2;
    }

    @Override
    public void ativarHabilidade() {
        System.out.println("Ativando primeira habilidade da carta unique: " + habilidade1);
        System.out.println("Ativando segunda habilidade da carta unique: " + habilidade2);
    }
}

// Exemplo
public class Main {
    public static void main(String[] args) {
        Carta cartaNormal = new Carta("Infligir Dano");
        cartaNormal.ativarHabilidade();

        Carta cartaSemHabilidade = new Carta(null);
        cartaSemHabilidade.ativarHabilidade();

        CartaUnique cartaEspecial = new CartaUnique("Curar Aliados", "Aumentar Ataque");
        cartaEspecial.ativarHabilidade();
    }
}
