public class Carta {
    private String nome;
    private String imagem;
    private String tipo;
    private String raridade;
    private int ataque;
    private int defesa;
    private int custo;
    private String habilidade;
    private int quantidade;

    public Carta(String nome, String imagem, String tipo, String raridade, int ataque, int defesa, int custo, String habilidade, int quantidade) {
        this.nome = nome;
        this.imagem = imagem;
        this.tipo = tipo;
        this.raridade = raridade;
        this.ataque = ataque;
        this.defesa = defesa;
        this.custo = custo;
        this.habilidade = habilidade;
        
        if (tipo.equalsIgnoreCase("Mana")) {
            this.quantidade = quantidade;
        } else {
            if (quantidade <= 3) {
                this.quantidade = quantidade;
            } else {
                this.quantidade = 3;
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public String getTipo() {
        return tipo;
    }

    public String getRaridade() {
        return raridade;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getCusto() {
        return custo;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    ////////////////////
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}