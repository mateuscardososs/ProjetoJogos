public class ArenaDupla extends Arena {
    private Usuario aliado;

    public ArenaDupla(Usuario jogador1, Usuario jogador2, Usuario jogador3, Usuario jogador4, Deck modoDeJogo, Usuario aliado) {
        super(jogador1, jogador2, jogador3, jogador4, modoDeJogo);
        this.aliado = aliado;
    }

    @Override
    public void iniciarPartida() {
    }

    @Override
    public void saque() {
    }

    public void turno(VectorAtaque vetorAliado) {
        
    }
}

    
}
