public abstract class Progresso {
    // Atributos 
    protected Premiacao[] premiacoes;
    protected int premiacaoAtual;
    // Construtor
    public Progresso() {
        this.premiacoes = new Premiacao[60];
        this.premiacaoAtual = 0;
    }
    // Métodos abstratos para registrar vitória e entregar premiação
    public abstract void registrarVitoria();
    public abstract void entregarPremiacao();
    // Enumeração de tipos de premiação
    protected enum Premiacao {
        BOOSTER_COMUM,
        BOOSTER_ESPECIAL
    }
}
