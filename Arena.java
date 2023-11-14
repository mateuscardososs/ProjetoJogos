import java.util.Random;
import java.util.Scanner;

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
    private Scanner scanner;

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

    public void saque(Usuario player, int quantidade) {
        Random rand = new Random();
        for (int i = 0; i < quantidade; i++) {
            int deckSize = player == Player1 ? deckPlayer1.getTamanho() : deckPlayer2.getTamanho();
            if (deckSize > 0) {
                int randomIndex = rand.nextInt(deckSize);
                Carta carta = player == Player1 ? deckPlayer1.getCarta(randomIndex) : deckPlayer2.getCarta(randomIndex);
                player.adicionarCartaMao(carta);

                // Remover carta do deck
                if (player == Player1) {
                    deckPlayer1.removerCarta(randomIndex);
                } else {
                    deckPlayer2.removerCarta(randomIndex);
                }
            }
        }
    }

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

    public void iniciarPartida() {
        Random rand = new Random();
        int randomPlayer = rand.nextInt(2); // 0 ou 1
        Usuario primeiroJogador = randomPlayer == 0 ? Player1 : Player2;
        Usuario segundoJogador = randomPlayer == 0 ? Player2 : Player1;

        saque(primeiroJogador, 7);
        saque(segundoJogador, 7);

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

    public void ataque(Usuario jogadorAtacante, Usuario jogadorDefensor) {
        System.out.println("Turno de " + jogadorAtacante.getNome() + " - Ataque:");

        Carta[] vetorAtacante = jogadorAtacante.getCampo();
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
                                    cemiterioPlayerAtacante[getProximaPosicaoCemiterio(jogadorAtacante)] = vetorAtacante[i];
                                    vetorAtacante[i] = null;
                                }

                                if (vetorDefensor[posicaoDefensor].getPontosVida() < 1) {
                                    cemiterioPlayerDefensor[getProximaPosicaoCemiterio(jogadorDefensor)] = vetorDefensor[posicaoDefensor];
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

    public void fimDoTurno(Usuario jogadorAtual, Usuario jogadorDefensor) {
        System.out.println("Fim do turno de " + jogadorAtual.getNome());

        if (jogadorAtual.getPontosVida() < 1 || jogadorDefensor.getPontosVida() < 1) {
            declararVencedor(jogadorDefensor);
        } else {
            proximoTurno(jogadorDefensor, jogadorAtual);
        }
    }

    private void declararVencedor(Usuario segundoJogador) {
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

    private void proximoTurno(Usuario jogadorAtual, Usuario jogadorDefensor) {
        turno(jogadorDefensor, jogadorAtual);
    }

    private int getProximaPosicaoCemiterio(Usuario jogador) {
        Carta[] cemiterio = (jogador == Player1) ? cemiterioPlayer1 : cemiterioPlayer2;

        for (int i = 0; i < cemiterio.length; i++) {
            if (cemiterio[i] == null) {
                return i;
            }
        }

        return -1; 
    }
    
    public boolean verificarFimPartida() {
        return pontosVidaPlayer1 < 1 || pontosVidaPlayer2 < 1;
    }
    
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
    

}
