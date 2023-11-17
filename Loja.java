import java.util.Random;

public class Loja extends Inventario {
    private String numeroCartao;
    private String codigoVerificador;
    private boolean promocao;

    public Loja(String numeroCartao, String codigoVerificador, boolean promocao, int cardcoins, List<Carta> cartas) {
        super(cardcoins, cartas);
        this.numeroCartao = numeroCartao;
        this.codigoVerificador = codigoVerificador;
        this.promocao = promocao;
    }

    public void compraBooster() {
        if (promocao) {
            compraBoosterEspecial();
        } else {
            compraBooster();
        }
    }

    private void compraBooster() {
        int custoBooster = 120;

        if (getCardcoins() >= custoBooster) {
            setCardcoins(getCardcoins() - custoBooster);
            adicionarCartasAleatoriasAoInventario();
        } else {
            System.out.println("Saldo insuficiente de cardcoins para o Booster.");
        }
    }

    private void compraBoosterEspecial() {
        int custoBoosterEspecial = 150;

        if (getCardcoins() >= custoBoosterEspecial) {
            setCardcoins(getCardcoins() - custoBoosterEspecial);

            Random random = new Random();
            int quantidadeCartasNoBooster = 12;

            for (int i = 0; i < quantidadeCartasNoBooster; i++) {
                if (random.nextInt(100) < 1) {
                    Carta cartaUnica = gerarCartaAleatoria();
                    adicionarCarta(cartaUnica);
                } else {
                    adicionarCartasAleatoriasAoInventario();
                }
            }
        } else {
            System.out.println("Saldo insuficiente de cardcoins para o Booster especial.");
        }
    }

    private void adicionarCartasAleatoriasAoInventario() {
        Random random = new Random();
        int quantidadeCartasNoBooster = 12;

        for (int i = 0; i < quantidadeCartasNoBooster; i++) {
            Carta cartaAleatoria = gerarCartaAleatoria();

            if (contagemCarta(cartaAleatoria) < 3) {
                adicionarCarta(cartaAleatoria);
            } else {
                int valorCardcoins = 10;
                setCardcoins(getCardcoins() + valorCardcoins);
            }
        }
    }

    private Carta gerarCartaAleatoria() {
        String[] nomesCartas = {"Carta1", "Carta2", "Carta3", "Carta4", "Carta5"};
        Random random = new Random();
        String nomeCartaAleatoria = nomesCartas[random.nextInt(nomesCartas.length)];
        return new Carta(nomeCartaAleatoria, nomeCartaAleatoria, nomeCartaAleatoria, nomeCartaAleatoria, 0, 0, 0, nomeCartaAleatoria, 0);
    }
}
