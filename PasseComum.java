public class PasseComum extends Progresso {

    private boolean passeFinalizado;

    public PasseComum() {
        super();
        this.passeFinalizado = false;
    }

    @Override
    public void registrarVitoria() {
        if (!passeFinalizado) {
            premiacaoAtual++;
        }
    }

    @Override
    public void entregarPremiacao() {
        if (!passeFinalizado) {
            System.out.println("Booster comum aberto.");
        }
    }

    public void finalizarPasse() {
        this.passeFinalizado = true;
    }
}
