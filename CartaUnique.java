import java.util.Random;

public class CartaUnique extends Carta {

        // Construtor
    public CartaUnique(String nome, String imagem, String tipo, String raridade, int ataque, int defesa, int custo, String habilidade, int quantidade) {
        super(nome, imagem, tipo, raridade, ataque + 1, defesa + 1, custo, habilidade, quantidade);
        adicionarHabilidadeExtra();
    }
      // Adiciona uma habilidade extra de forma aleatória
    private void adicionarHabilidadeExtra() {
        
        String[] habilidadesExtras = {"VELOCIDADE,GELO,SUPERPULO,MAGIA"};
        Random random = new Random();
        int indiceHabilidadeExtra = random.nextInt(habilidadesExtras.length);
        String habilidadeExtra = habilidadesExtras[indiceHabilidadeExtra];
        setHabilidade(getHabilidade() + ", " + habilidadeExtra);
    }
     // Define as habilidades da carta
    private void setHabilidade(String string) {
    }
    // Obtém as habilidades da carta
    private String getHabilidade() {
        return null;
    }
}
