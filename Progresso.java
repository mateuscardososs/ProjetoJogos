// Progresso.java
public abstract class Progresso {
    protected Premiacao[] premiacoes;
    protected int premiacaoAtual;

    public Progresso() {
        this.premiacoes = new Premiacao[60];
        this.premiacaoAtual = 0;
    }

    public abstract void registrarVitoria();
    public abstract void entregarPremiacao();

    protected enum Premiacao {
        BOOSTER_COMUM,
        BOOSTER_ESPECIAL
    }
}
