import java.util.Random;
import java.util.Scanner;

public class Arena {
   // Declaração de variáveis da classe
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
    public int manaMaximaPlayer1;
    private int manaMaximaPlayer2;
    protected Carta[] cemiterioPlayer1;
    private Carta[] cemiterioPlayer2;
    private Scanner scanner;
  // Construtor para inicializar o estado do jogo
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
        this.scanner = new Scanner(System.in);
    }

    public Arena(Usuario jogador, Usuario adversario, Deck modoDeJogo) {
    }
  // Método para realizar a compra de cartas de um jogador
    public void saque(Usuario player, int quantidade) {
  // Utilização de um gerador de números aleatórios para comprar cartas do deck
        Random rand = new Random();
        for (int i = 0; i < quantidade; i++) {
            int deckSize = player == Player1 ? deckPlayer1.getTamanho() : deckPlayer2.getTamanho();
  // Verifica se há cartas para comprar no deck
            if (deckSize > 0) {
  // Gera um índice aleatório para selecionar uma carta do deck
                int randomIndex = rand.nextInt(deckSize);
  // Obtém a carta selecionada
                Carta carta = player == Player1 ? deckPlayer1.getCarta(randomIndex) : deckPlayer2.getCarta(randomIndex);
  // Adiciona a carta comprada à mão do jogador
                player.adicionarCartaMao(carta);

  // Remove a carta comprada do deck
                if (player == Player1) {
                    deckPlayer1.removerCarta(randomIndex);
                } else {
                    deckPlayer2.removerCarta(randomIndex);
                }
            }
        }
    }
 // Método para executar o turno de um jogador
    public void turno(Usuario jogadorAtual, Usuario jogadorDefensor) {
        System.out.println("Turno do jogador " + jogadorAtual.getNome());

        saque(jogadorAtual, 1);
        jogadorAtual.aumentarManaMaxima(1);
        jogadorAtual.resetMana();

        compra(jogadorAtual);

        if (jogadorAtual.temMana()) {
            posicionamento(jogadorAtual);
        } else {
            Carta cartaCampo = jogadorAtual.escolherCartaMao();
            int posicaoCampo = jogadorAtual.escolherPosicaoCampo();
            jogadorAtual.posicionarCartaCampo(cartaCampo, posicaoCampo);
        }

        ataque(jogadorAtual, jogadorDefensor);
        fimDoTurno(jogadorAtual, jogadorDefensor);
    }
  // Método para iniciar o jogo 
    public void iniciarPartida() {
    Random rand = new Random();
    int randomPlayer = rand.nextInt(2); // 0 ou 1
    Usuario primeiroJogador = randomPlayer == 0 ? Player1 : Player2;
    Usuario segundoJogador = randomPlayer == 0 ? Player2 : Player1;

    saque(primeiroJogador, 7);
    saque(segundoJogador, 7);

    long tempoLimiteMillis = System.currentTimeMillis() + 60000; // 60 segundos
    boolean partidaEncontrada = false;

    while (!partidaEncontrada && System.currentTimeMillis() < tempoLimiteMillis) {
        if (false) {
            partidaEncontrada = true;
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    if (!partidaEncontrada) {
        try {
            throw new TimeOutException("Não foi possível encontrar uma partida a tempo.");
        } catch (TimeOutException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }
    }

    while (true) {
        turno(primeiroJogador, segundoJogador);
        if (verificarFimPartida()) {
            declararVencedor(segundoJogador);
            break;
        }

        turno(segundoJogador, primeiroJogador);
        if (verificarFimPartida()) {
            declararVencedor(primeiroJogador);
            break;
        }
    }
}


 // Método para comprar uma carta durante o turno de um jogador
    public void compra(Usuario jogador) {
        int deckSize = jogador == Player1 ? deckPlayer1.getTamanho() : deckPlayer2.getTamanho();

        if (deckSize > 0) {
            int randomIndex = new Random().nextInt(deckSize);
            Carta cartaComprada = jogador == Player1 ? deckPlayer1.getCarta(randomIndex) : deckPlayer2.getCarta(randomIndex);

            jogador.adicionarCartaMao(cartaComprada);

            if (jogador == Player1) {
                deckPlayer1.removerCarta(randomIndex);
            } else {
                deckPlayer2.removerCarta(randomIndex);
            }

            jogador.aumentarManaMaxima(1);

            System.out.println(jogador.getNome() + " comprou a carta: " + cartaComprada.getNome());
            System.out.println("Mana máxima de " + jogador.getNome() + " aumentou para: " + jogador.getManaMaxima());
        } else {
            System.out.println("O deck de " + jogador.getNome() + " está vazio. Não é possível comprar cartas.");
        }
    }
 // Método para posicionar cartas durante o turno de um jogador
    public void posicionamento(Usuario jogador) {
        System.out.println("Turno de " + jogador.getNome() + " - Posicionamento:");

        System.out.println("Você tem " + jogador.getMana() + " de mana disponível.");

        System.out.println("Escolha o que posicionar: ");
        System.out.println("1. Mana");
        System.out.println("2. Carta");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                jogador.diminuirMana(1);
                System.out.println(jogador.getNome() + " posicionou uma mana no campo.");
                break;
            case 2:
                Carta cartaEscolhida = jogador.escolherCartaMao();
                int posicaoCampo = jogador.escolherPosicaoCampo();

                if (posicaoCampo >= 0 && posicaoCampo < campoPlayer1.length) {
                    jogador.posicionarCartaCampo(cartaEscolhida, posicaoCampo);
                    System.out.println(jogador.getNome() + " posicionou a carta " + cartaEscolhida.getNome() + " no campo.");
                    jogador.diminuirMana(cartaEscolhida.getCustoMana());
                } else {
                    System.out.println("Posição inválida no campo. A carta não foi posicionada.");
                }
                break;
            default:
                System.out.println("Opção inválida. Nada foi posicionado.");
                break;
        }
    }
 // Método para lidar com ataques durante o turno de um jogador
    public void ataque(Usuario jogadorAtual, Usuario jogadorDefensor) {
        System.out.println("Turno de " + jogadorAtual.length + " - Ataque:");

        Carta[] vetorAtacante = jogadorAtual.getCampo();
        Carta[] vetorDefensor = jogadorDefensor.getCampo();

        if (vetorAtacante != null && vetorAtacante.length > 0) {
            System.out.println("Você tem cartas no campo. Deseja atacar?");
            System.out.println("1. Sim");
            System.out.println("2. Não");

            int escolhaAtaque = scanner.nextInt();

            switch (escolhaAtaque) {
                case 1:
                    for (int i = 0; i < vetorAtacante.length; i++) {
                        if (vetorAtacante[i] != null) {
                            int posicaoDefensor = jogadorDefensor.escolherPosicaoCampo(); 

                            if (posicaoDefensor >= 0 && posicaoDefensor < vetorDefensor.length && vetorDefensor[posicaoDefensor] != null) {
                                // Realiza o ataque
                                int danoAtacante = vetorAtacante[i].getDano();
                                int danoDefensor = vetorDefensor[posicaoDefensor].getDano();

                                vetorDefensor[posicaoDefensor].sofrerDano(danoAtacante);
                                vetorAtacante[i].sofrerDano(danoDefensor);

                                if (vetorAtacante[i].getPontosVida() < 1) {
                                    cemiterioPlayer1[getProximaPosicaoCemiterio(jogadorAtual)] = vetorAtacante[i];
                                    vetorAtacante[i] = null;
                                }

                                if (vetorDefensor[posicaoDefensor].getPontosVida() < 1) {
                                    cemiterioPlayer2[getProximaPosicaoCemiterio(jogadorDefensor)] = vetorDefensor[posicaoDefensor];
                                    vetorDefensor[posicaoDefensor] = null;
                                }
                            } else {
                                System.out.println("Posição inválida para ataque. Nenhum ataque realizado.");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Você optou por não atacar neste turno.");
                    break;
                default:
                    System.out.println("Opção inválida. Nenhum ataque realizado.");
                    break;
            }
        } else {
            System.out.println("Você não tem cartas no campo. Nenhum ataque realizado.");
        }
    }
 // Método para o fim do turno de um jogador
    public void fimDoTurno(Usuario jogadorAtual, Usuario jogadorDefensor) {
        System.out.println("Fim do turno de " + jogadorAtual.getNome());

        if (jogadorAtual.getPontosVida() < 1 || jogadorDefensor.getPontosVida() < 1) {
            declararVencedor(jogadorDefensor);
        } else {
            proximoTurno(jogadorDefensor, jogadorAtual);
        }
    }
 // Método protegido para declarar o vencedor e lidar com as recompensas
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
        System.out.println(vencedor.getNome() + " recebeu " + cardCoinsVencedor + " Card Coins.");
        System.out.println(perdedor.getNome() + " recebeu " + cardCoinsPerdedor + " Card Coins.");
    }
 // Método para lidar com o próximo turno
    private void proximoTurno(Usuario jogadorAtual, Usuario jogadorDefensor) {
        turno(jogadorDefensor, jogadorAtual);
    }
 // Método para obter a próxima posição disponível no cemitério do jogador
    private int getProximaPosicaoCemiterio(Usuario jogador) {
        Carta[] cemiterio = (jogador == Player1) ? cemiterioPlayer1 : cemiterioPlayer2;

        for (int i = 0; i < cemiterio.length; i++) {
            if (cemiterio[i] == null) {
                return i;
            }
        }

        return -1; 
    }
 // Método para verificar se o jogo chegou ao fim  
    public boolean verificarFimPartida() {
        return pontosVidaPlayer1 < 1 || pontosVidaPlayer2 < 1;
    }
 // Método para lidar com o fim do jogo   
    public void fimDaPartida() {
        if (verificarFimPartida()) {
            Usuario vencedor = (pontosVidaPlayer1 < 1) ? Player2 : Player1;
            Usuario perdedor = (pontosVidaPlayer1 < 1) ? Player1 : Player2;
    
            int cardCoinsVencedor = (vencedor == Player2) ? 100 : 10;
            int cardCoinsPerdedor = (vencedor == Player2) ? 10 : 100;
    
            vencedor.adicionarCardCoins(cardCoinsVencedor);
            perdedor.adicionarCardCoins(cardCoinsPerdedor);
    
            System.out.println("O jogador " + vencedor.getNome() + " venceu a partida!");
            System.out.println(vencedor.getNome() + " recebeu " + cardCoinsVencedor + " Card Coins.");
            System.out.println(perdedor.getNome() + " recebeu " + cardCoinsPerdedor + " Card Coins.");
    
            System.exit(0);
        }
    }
  // Método de espaço reservado para lidar com ataques (não totalmente implementado)
    public void ataque(Carta[] vetorAtacante, Carta[] vetorDefensor) {
    }
    

}