public abstract class Progresso {
    protected Premiacao[] premiacoes; 
    protected int premiacaoAtual; 

    public Progresso() {
        this.premiacoes = new Premiacao[60]; 
        this.premiacaoAtual = 0; // Inicializando a premiação atual
    }

    public abstract void entregarPremiacao();

    public abstract void progresso();
}

class Premiacao {
    private String nome; 

    public Premiacao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

//fazer o passe de batalha em outro arquivo de classe

class PasseDeBatalha extends Progresso {
    public PasseDeBatalha() {
        for (int i = 0; i < 60; i++) {
            this.premiacoes[i] = new Premiacao("Booster " + (i + 1));
        }
    }

    @Override
    public void entregarPremiacao() {
        System.out.println("Você recebeu a premiação: " + premiacoes[premiacaoAtual].getNome());
    }

    // Implementação do método abstrato progresso
    @Override
    public void progresso() {
        System.out.println("Avançando no Passe de Batalha...");
        premiacaoAtual++;
        if (premiacaoAtual >= premiacoes.length) {
            System.out.println("Parabéns! Você concluiu o Passe de Batalha.");
        } else {
            entregarPremiacao();
        }
    }


    public void bonusPasse() {
        System.out.println("Bônus exclusivo do Passe de Batalha!");
    }
}


