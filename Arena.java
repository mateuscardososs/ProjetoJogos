public class Arena {
    private Usuario Player1;
    private Usuario Player2;
    private Deck deckPlayer1;
    private Deck deckPlayer2;
    private Carta[][] campoPlayer1;
    private Carta[][] campoPlayer2;
    private int pontosVidaPlayer1;
    private int pontosVidaPlayer2;

    public Arena(Usuario[] team1, Usuario[] team2, Deck deckJogador1, Deck deckJogador2) {
        this.Player1 = team1;
        this.Player2 = team2;
        this.deckPlayer1 = deckJogador1;
        this.deckPlayer2 = deckJogador2;
        this.campoPlayer1 = new Carta[2][5];
        this.campoPlayer2 = new Carta[2][5];
        this.pontosVidaPlayer1 = 20;
        this.pontosVidaPlayer2 = 20;
    }

    public Arena(Usuario[] team1, Usuario[] team2, Deck modoDeJogo) {
    }

    public Arena(Usuario jogador, Usuario adversario, Deck modoDeJogo) {
    }

    public Usuario getPlayer1(){
        return Player1;
    }

    public void setPlayer1(Usuario Player1){
        this.Player1 = Player1;
    }

    public Usuario getPlayer2(){
        return Player2;
    }

    public void setPlayer2(Usuario Player2){
        this.Player2 = Player2;
    }

    public Deck getDeckPlayer1(){
        return deckPlayer1;
    }

    public void setDeckPlayer1(Deck deckPlayer1){
        this.deckPlayer1 = deckPlayer1;
    }

    public Deck getDeckPlayer2(){
        return deckPlayer2;
    }

    public void setDeckPlayer2(Deck deckPlayer2){
        this.deckPlayer2 = deckPlayer2;
    }

    public Carta[][] campoPlayer1(){
        return campoPlayer1;
    }

    public void setCampoPlayer1(Carta[][] campoPlayer1){
        this.campoPlayer1 = campoPlayer1;
    }

    public Carta[][] campoPlayer2(){
        return campoPlayer2;
    }

    public void setCampoPlayer2(Carta[][] campoPlayer2){
        this.campoPlayer2 = campoPlayer2;
    }

    public int getPontosVidaPlayer1(){
        return pontosVidaPlayer1;
    }

    public int getPontosVidaPlayer2(){
        return pontosVidaPlayer2;
    }

    public void iniciarPartida() {
    }

    public void iniciarPartidaDupla() {
    }
   }