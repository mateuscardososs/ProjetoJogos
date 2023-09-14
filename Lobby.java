public class Lobby {
    
    private Usuario[] Players;
    private int numPlayers;
    
   
    public Usuario[] getPlayers() {
        return Players;
    }

    public void setPlayers(Usuario[] Players) {
        this.Players = Players;
    }
    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
   }
    public Lobby(int maxJogadores) {
        Players = new Usuario[maxJogadores];
        numPlayers = 0;
    }   
}