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
            
            Deck modoDeJogo;
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
    
    public void encontrarPartidaDupla() {
        if (numPlayers < 4) {
            System.out.println("Não há jogadores suficientes para iniciar uma partida em dupla.");
            return;
        }
        
        Random rand = new Random();
        Usuario[] team1 = selecionarJogadoresAleatorios(2);
        Usuario[] team2 = selecionarJogadoresAleatorios(2);
        
        Deck modoDeJogoDupla;
        Arena arenaDupla = criarArenaDupla(team1, team2, modoDeJogoDupla);
        
        arenaDupla.iniciarPartidaDupla();
        
        removerJogadores(team1);
        removerJogadores(team2);
    }
    
    private Usuario[] selecionarJogadoresAleatorios(int quantidade) {
        Random rand = new Random();
        Usuario[] selectedPlayers = new Usuario[quantidade];
        for (int i = 0; i < quantidade; i++) {
            int selectedIndex;
            do {
                selectedIndex = rand.nextInt(numPlayers);
            } while (contains(selectedPlayers, selectedIndex));
            selectedPlayers[i] = players[selectedIndex];
        }
        return selectedPlayers;
    }
    
    private Arena criarArenaDupla(Usuario[] team1, Usuario[] team2, Deck modoDeJogo) {
        return new Arena(team1, team2, modoDeJogo, modoDeJogo);
    }
    
    private void removerJogadores(Usuario[] jogadores) {
        for (Usuario jogador : jogadores) {
            removerJogador(jogador);
        }
    }
    
    private boolean contains(Usuario[] array, int value) {
        for (Usuario item : array) {
            if (item == players[value]) {
                return true;
            }
        }
        return false;
    }
}    