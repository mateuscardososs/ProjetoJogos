public class PasseComum extends Progresso {
    // Atributos
    private boolean passeFinalizado;
    // Construtor
    public PasseComum() {
        super();
        this.passeFinalizado = false;
    }
    // Método para registrar uma vitória no passe comum
    @Override
    public void registrarVitoria() {
        if (!passeFinalizado) {
            premiacaoAtual++;
        }
    }
    // Método para entregar a premiação do passe comum
    @Override
    public void entregarPremiacao() {
        if (!passeFinalizado) {
            System.out.println("Booster comum aberto.");
        }
    }
    // Método para finalizar o passe comum
    public void finalizarPasse() {
        this.passeFinalizado = true;
    }
}
