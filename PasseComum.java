public class PasseComum extends Progresso {
 
    public PasseComum() {
        for (int i = 0; i < 60; i++) {
            this.premiacoes[i] = new Premiacao("Booster Comum " + (i + 1));
        }
    }

    @Override
    public void entregarPremiacao() {
        if (premiacaoAtual < premiacoes.length) {
            System.out.println("Você ganhou um booster comum: " + premiacoes[premiacaoAtual].getNome());
        } else {
            System.out.println("O passe chegou ao fim. Não há mais premiações disponíveis.");
        }
    }

    @Override
    public void progresso() {
        System.out.println("Ganhou uma vitória! Avançando no Passe Comum...");
        premiacaoAtual++;
        if (premiacaoAtual <= premiacoes.length) {
            entregarPremiacao();
        } else {
            System.out.println("O passe chegou ao fim. Não há mais premiações disponíveis.");
        }
    }

    // Método adicional específico do Passe Comum (se necessário)
    public void metodoAdicional() {
        System.out.println("Método adicional do Passe Comum.");
    }
}
