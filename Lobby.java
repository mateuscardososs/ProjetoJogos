import java.util.Random;

public class Lobby {
    private Usuario[] players;
    private int numPlayers;
    private int maxPlayers;

    public Lobby(int maxJogadores) {
        maxPlayers = maxJogadores;
        players = new Usuario[maxPlayers];
        numPlayers = 0;
    }

    public Usuario[] getPlayers() {
        return players;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void adicionarjogador(Usuario jogador) {
        if (numPlayers < maxPlayers) {
            players[numPlayers] = jogador;
            numPlayers++;
        } else {
            System.out.println("Lobby está cheio.");
        }
    }

    public void removerJogador(Usuario jogador) {
        for (int i = 0; i < numPlayers; i++) {
            if (players[i].equals(jogador)) {
                for (int j = i; j < numPlayers - 1; j++) {
                    players[j] = players[j + 1];
                }
                numPlayers--;
                break;
            }
        }
    }

    public void encontrarPartida(Usuario jogador) {
        if (numPlayers >= 2) {
            Random rand = new Random();
            int indiceAdversario;
            
            do {
                indiceAdversario = rand.nextInt(numPlayers);
            } while (players[indiceAdversario].equals(jogador) || players[indiceAdversario].getNivel() != jogador.getNivel());

            Usuario adversario = players[indiceAdversario];
            
            Arena arena = new Arena(jogador, adversario, modoDeJogo);
            
            // Iniciar a partida
            arena.iniciarPartida();
            
            // Remover os jogadores do lobby
            removerJogador(jogador);
            removerJogador(adversario);
        } else {
            System.out.println("Não há jogadores suficientes para iniciar uma partida.");
        }
    }
}