public class PassePremium extends Progresso {

    private boolean passeFinalizado;

    public PassePremium() {
        super();
        this.passeFinalizado = false;
    }

    @Override
    public void registrarVitoria() {
        if (!passeFinalizado) {
            premiacaoAtual += 2;
        }
    }

    @Override
    public void entregarPremiacao() {
        if (!passeFinalizado) {
            System.out.println("Booster comum aberto para níveis não múltiplos de 5.");

            if (premiacaoAtual % 5 == 0) {
                System.out.println("Booster especial aberto para níveis múltiplos de 5.");
            }
        }
    }

    public void resetarPasse() {
        this.premiacaoAtual = 0;
        this.passeFinalizado = false;
    }
}
