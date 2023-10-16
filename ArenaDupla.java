import java.util.Random;

public class ArenaDupla extends Arena {
    private Usuario aliado;

    public ArenaDupla(Usuario jogador1, Usuario jogador2, Usuario jogador3, Usuario jogador4, Deck modoDeJogo, Usuario aliado) {
        super(new Usuario[] { jogador1, jogador2 }, new Usuario[] { jogador3, jogador4 }, modoDeJogo);
        this.aliado = aliado;
    }

    @Override
    public void iniciarPartida() {
    System.out.println("Iniciando partida em dupla");
    Random rand = new Random();
    int randomPlayer = rand.nextInt(4); 
    Usuario jogadorAtual = (randomPlayer < 2) ? getTeam1()[randomPlayer] : getTeam2()[randomPlayer - 2];

    while (true) {
        turno(jogadorAtual);

        if (verificarFimPartida()) {
            declararVencedor(jogadorAtual);
            break;
        }

        // Mude para o próximo jogador
        randomPlayer = (randomPlayer + 1) % 4;
        jogadorAtual = (randomPlayer < 2) ? getTeam1()[randomPlayer] : getTeam2()[randomPlayer - 2];
    }
}

    @Override
    public void saque() {
    System.out.println("Realizando o saque em dupla...");

    // Suponha que você deseja sortear 7 cartas aleatórias para cada jogador, incluindo o aliado.
    saque(getTeam1(), 7);
    saque(getTeam2(), 7);
    saque(aliado, 7);
}

private void saque(Usuario[] jogadores, int quantidade) {
    Random rand = new Random();

    for (Usuario jogador : jogadores) {
        for (int i = 0; i < quantidade; i++) {
            int deckSize = jogador.getDeck().getTamanho();
            if (deckSize > 0) {
                int randomIndex = rand.nextInt(deckSize);
                Carta carta = jogador.getDeck().getCarta(randomIndex);
                jogador.adicionarCartaMao(carta);
                jogador.getDeck().removerCarta(randomIndex);
            }
        }
    }
}


    public void turno(Usuario jogador, Carta[] vetorAliado) {
    System.out.println("Turno do jogador " + jogador.getNome());

    saque(jogador, 1);
    jogador.aumentarManaMaxima(1);
    jogador.resetMana();

    if (jogador.temMana()) {
        Carta cartaMana = jogador.jogarCartaMana();
        jogador.diminuirMana(cartaMana.getCustoMana());
        int posicaoCampo = jogador.escolherPosicaoCampo();
        jogador.posicionarCartaCampo(cartaMana, posicaoCampo);
    } else {
        Carta cartaCampo = jogador.escolherCartaMao();
        int posicaoCampo = jogador.escolherPosicaoCampo();
        jogador.posicionarCartaCampo(cartaCampo, posicaoCampo);
    }

    Carta[] vetorAtacante = jogador.getCampo();
    Carta[] vetorDefensor = (jogador == getTeam1()[0] || jogador == getTeam1()[1]) ? getTeam2()[0].getCampo() : getTeam1()[0].getCampo();

    ataque(vetorAtacante, vetorDefensor);

    Carta[] vetorAtacanteAliado = vetorAliado;
    Carta[] vetorDefensorInimigo = (jogador == getTeam1()[0] || jogador == getTeam1()[1]) ? getTeam2()[1].getCampo() : getTeam1()[1].getCampo();

    ataque(vetorAtacanteAliado, vetorDefensorInimigo);
}



private Usuario[] getTeam2() {
    return null;
}

private Usuario[] getTeam1() {
    return null;
}

}
