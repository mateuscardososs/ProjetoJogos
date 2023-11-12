public class PassePremium extends Progresso {

    public PassePremium() {
        for (int i = 0; i < 60; i++) {
            this.premiacoes[i] = new Premiacao("Booster Premium " + (i + 1));
        }
    }

    @Override
    public void entregarPremiacao() {
        if (premiacaoAtual < premiacoes.length) {
    
            if ((premiacaoAtual + 1) % 5 == 0) {
                System.out.println("Você ganhou um booster especial: " + premiacoes[premiacaoAtual].getNome());
            } else {
                System.out.println("Você ganhou um booster comum: " + premiacoes[premiacaoAtual].getNome());
            }
        } else {
            System.out.println("O passe chegou ao fim. Você pode reiniciar para pegar as premiações novamente.");
            premiacaoAtual = 0;
        }
    }

    @Override
    public void progresso() {
        System.out.println("Ganhou uma vitória! Avançando no Passe Premium...");
        premiacaoAtual += 2; // Uma vitória são dois níveis
        if (premiacaoAtual < premiacoes.length) {
            entregarPremiacao();
        } else {
            System.out.println("O passe chegou ao fim. Você pode reiniciar para pegar as premiações novamente.");
            premiacaoAtual = 0; // Zera o passe quando chega ao fim
        }
    }


    public void metodoAdicionalPremium() {
        System.out.println("Método adicional do Passe Premium.");
    }
}
