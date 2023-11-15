import java.util.Random;

public class Loja {
    private String numeroCartao;
    private String codigoVerificador;
    private boolean promocao;

    public Loja(String numeroCartao, String codigoVerificador, boolean promocao) {
        this.numeroCartao = numeroCartao;
        this.codigoVerificador = codigoVerificador;
        this.promocao = promocao;
    }

    public void compraBooster(Inventario inventario) {
        if (promocao) {
            compraBoosterEspecial(inventario);
        } else {
            compraBooster(inventario);
        }
    }

    private void compraBooster(Inventario inventario) {
        int custoBooster = 120;

        if (inventario.getCardcoins() >= custoBooster) {
            inventario.setCardcoins(inventario.getCardcoins() - custoBooster);
            adicionarCartasAleatoriasAoInventario(inventario);
        } else {
            System.out.println("Saldo insuficiente de cardcoins para o Booster.");
        }
    }

    private void compraBoosterEspecial(Inventario inventario) {
        int custoBoosterEspecial = 150;

        if (inventario.getCardcoins() >= custoBoosterEspecial) {
            inventario.setCardcoins(inventario.getCardcoins() - custoBoosterEspecial);
            
            Random random = new Random();
            int quantidadeCartasNoBooster = 12;

            for (int i = 0; i < quantidadeCartasNoBooster; i++) {
                if (random.nextInt(100) < 1) {
                    Carta cartaUnica = gerarCartaAleatoria();
                    inventario.adicionarCarta(cartaUnica);
                } else {
                    adicionarCartasAleatoriasAoInventario(inventario);
                }
            }
        } else {
            System.out.println("Saldo insuficiente de cardcoins para o Booster especial.");
        }
    }

    private void adicionarCartasAleatoriasAoInventario(Inventario inventario) {
        Random random = new Random();
        int quantidadeCartasNoBooster = 12;

        for (int i = 0; i < quantidadeCartasNoBooster; i++) {

            Carta cartaAleatoria = gerarCartaAleatoria();

            if (inventario.contagemCarta(cartaAleatoria) < 3) {
                inventario.adicionarCarta(cartaAleatoria);
            } else {
                int valorCardcoins = 10; 
                inventario.setCardcoins(inventario.getCardcoins() + valorCardcoins);
            }
        }
    }

    private Carta gerarCartaAleatoria() {
        String[] nomesCartas = {"Carta1", "Carta2", "Carta3", "Carta4", "Carta5"};/
        Random random = new Random();
        String nomeCartaAleatoria = nomesCartas[random.nextInt(nomesCartas.length)];
        return new Carta(nomeCartaAleatoria, nomeCartaAleatoria, nomeCartaAleatoria, nomeCartaAleatoria, 0, 0, 0, nomeCartaAleatoria, 0);
    }
    }
}