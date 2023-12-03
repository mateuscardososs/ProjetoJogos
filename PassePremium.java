public class PassePremium extends Progresso {
    // Atributos
    private boolean passeFinalizado;
    // Construtor
    public PassePremium() {
        super();
        this.passeFinalizado = false;
    }
   // Método para registrar uma vitória no passe premium
    @Override
    public void registrarVitoria() {
        if (!passeFinalizado) {
            premiacaoAtual += 2;
        }
    }
   // Método para entregar a premiação do passe premium
    @Override
    public void entregarPremiacao() {
        if (!passeFinalizado) {
            System.out.println("Booster comum aberto para níveis não múltiplos de 5.");

            if (premiacaoAtual % 5 == 0) {
                System.out.println("Booster especial aberto para níveis múltiplos de 5.");
            }
        }
    }
    // Método para resetar o passe premium
    public void resetarPasse() {
        this.premiacaoAtual = 0;
        this.passeFinalizado = false;
    }
}
