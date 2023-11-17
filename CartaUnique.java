import java.util.Random;

public class CartaUnique extends Carta {

    public CartaUnique(String nome, String imagem, String tipo, String raridade, int ataque, int defesa, int custo, String habilidade, int quantidade) {
        super(nome, imagem, tipo, raridade, ataque + 1, defesa + 1, custo, habilidade, quantidade);
        adicionarHabilidadeExtra();
    }

    private void adicionarHabilidadeExtra() {
        
        String[] habilidadesExtras = {"VELOCIDADE,GELO,SUPERPULO,MAGIA"};
        Random random = new Random();
        int indiceHabilidadeExtra = random.nextInt(habilidadesExtras.length);
        String habilidadeExtra = habilidadesExtras[indiceHabilidadeExtra];
        setHabilidade(getHabilidade() + ", " + habilidadeExtra);
    }
}
