import java.util.Random;

public class Arena {
    private Usuario Player1;
    private Usuario Player2;
    private Deck deckPlayer1;
    private Deck deckPlayer2;
    private Carta[][] campoPlayer1;
    private Carta[][] campoPlayer2;
    private int pontosVidaPlayer1;
    private int pontosVidaPlayer2;
    private Carta[] maoPlayer1;
    private Carta[] maoPlayer2;
    private int manaMaximaPlayer1;
    private int manaMaximaPlayer2;
    private Carta[] cemiterioPlayer1;
    private Carta[] cemiterioPlayer2;

    public Arena(Usuario[] team1, Usuario[] team2, Deck deckJogador1, Deck deckJogador2) {
        this.Player1 = team1[0];
        this.Player2 = team2[0];
        this.deckPlayer1 = deckJogador1;
        this.deckPlayer2 = deckJogador2;
        this.campoPlayer1 = new Carta[2][5];
        this.campoPlayer2 = new Carta[2][5];
        this.pontosVidaPlayer1 = 20;
        this.pontosVidaPlayer2 = 20;
        this.maoPlayer1 = new Carta[10];
        this.maoPlayer2 = new Carta[10];
        this.manaMaximaPlayer1 = 0;
        this.manaMaximaPlayer2 = 0;
        this.cemiterioPlayer1 = new Carta[100];
        this.cemiterioPlayer2 = new Carta[100];
    }

    public Arena(Usuario[] usuarios, Usuario[] usuarios2, Deck modoDeJogo) {
    }

    public void saque(Usuario player, int quantidade) {
        Random rand = new Random();
        for (int i = 0; i < quantidade; i++) {
            int deckSize = player == Player1 ? deckPlayer1.getTamanho() : deckPlayer2.getTamanho();
            if (deckSize > 0) {
                int randomIndex = rand.nextInt(deckSize);
                Carta carta = player == Player1 ? deckPlayer1.getCarta(randomIndex) : deckPlayer2.getCarta(randomIndex);
                player.adicionarCartaMao(carta);
            }
        }
    }

    public void iniciarPartida() {
        // Sorteia aleatoriamente quem começa
        Random rand = new Random();
        int randomPlayer = rand.nextInt(2); // 0 ou 1
        Usuario primeiroJogador = randomPlayer == 0 ? Player1 : Player2;
        Usuario segundoJogador = randomPlayer == 0 ? Player2 : Player1;

        saque(primeiroJogador, 7);
        saque(segundoJogador, 7);

        while (true) {
            turno(primeiroJogador);
            if (verificarFimPartida()) {
                declararVencedor(segundoJogador);
                break;
            }

            turno(segundoJogador);
            if (verificarFimPartida()) {
                declararVencedor(primeiroJogador);
                break;
            }
        }
    }
    
        public void turno(Usuario jogador) {
            System.out.println("Turno do jogador " + jogador.getNome());
        
            saque(jogador, 1);
            jogador.aumentarManaMaxima(1);
            jogador.resetMana();
    
            if (jogador.temMana()) {
                Carta cartaMana = jogador.jogarCartaMana(); // Suponha que você tenha um método para jogar carta de mana
                jogador.diminuirMana(cartaMana.getCustoMana());
                int posicaoCampo = jogador.escolherPosicaoCampo(); // Suponha que você tenha um método para escolher a posição no campo
                jogador.posicionarCartaCampo(cartaMana, posicaoCampo);
            } else {
                
                Carta cartaCampo = jogador.escolherCartaMao(); // Suponha que você tenha um método para escolher carta da mão
                int posicaoCampo = jogador.escolherPosicaoCampo(); // Suponha que você tenha um método para escolher a posição no campo
                jogador.posicionarCartaCampo(cartaCampo, posicaoCampo);
            }
        
            Carta[] vetorAtacante = jogador.getCampo();
            Carta[] vetorDefensor = (jogador == Player1) ? Player2.getCampo() : Player1.getCampo();
            ataque(vetorAtacante, vetorDefensor);
        }
        
    

    protected void ataque(Carta[] vetorAtacante, Carta[] vetorDefensor) {
        for (int i = 0; i < vetorAtacante.length; i++) {
            if (vetorAtacante[i] != null && vetorDefensor[i] != null) {
                int danoAtacante = vetorAtacante[i].getDano();
                int danoDefensor = vetorDefensor[i].getDano();

                vetorAtacante[i].sofrerDano(danoDefensor);
                vetorDefensor[i].sofrerDano(danoAtacante);

                if (vetorAtacante[i].getPontosVida() < 1) {
                    cemiterioPlayer1[getProximaPosicaoCemiterio(1)] = vetorAtacante[i];
                    vetorAtacante[i] = null;
                }

                if (vetorDefensor[i].getPontosVida() < 1) {
                    cemiterioPlayer2[getProximaPosicaoCemiterio(2)] = vetorDefensor[i];
                    vetorDefensor[i] = null;
                }
            }
        }
    }

    private int getProximaPosicaoCemiterio(int i) {
        return 0;
    }

    protected void declararVencedor(Usuario segundoJogador) {
        Usuario vencedor;
        Usuario perdedor;
    
        if (pontosVidaPlayer1 < 1) {
            vencedor = Player2;
            perdedor = Player1;
        } else {
            vencedor = Player1;
            perdedor = Player2;
        }
    
        int cardCoinsVencedor = (vencedor == segundoJogador) ? 100 : 10;
        int cardCoinsPerdedor = (vencedor == segundoJogador) ? 10 : 100;
    
        vencedor.adicionarCardCoins(cardCoinsVencedor);
        perdedor.adicionarCardCoins(cardCoinsPerdedor);
    
        System.out.println("O jogador " + vencedor.getNome() + " venceu a partida!");
    }
    
    protected boolean verificarFimPartida() {
        return pontosVidaPlayer1 < 1 || pontosVidaPlayer2 < 1;
   
    }
}

