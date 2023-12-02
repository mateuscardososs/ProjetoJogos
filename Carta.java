public class Carta {
    // Propriedades da classe Carta
    private String nome;
    private String imagem;
    private String tipo;
    private String raridade;
    private int ataque;
    private int defesa;
    private int custo;
    private String habilidade;
    private int quantidade;

    // Construtor para inicializar as propriedades
    public Carta(String nome, String imagem, String tipo, String raridade, int ataque, int defesa, int custo, String habilidade, int quantidade) {
        this.nome = nome;
        this.imagem = imagem;
        this.tipo = tipo;
        this.raridade = raridade;
        this.ataque = ataque;
        this.defesa = defesa;
        this.custo = custo;
        this.habilidade = habilidade;
        
        // Tratando a quantidade com base no tipo de carta
        if (tipo.equalsIgnoreCase("Mana")) {
            this.quantidade = quantidade;
        } else {
            // Restringindo a quantidade para 3 para cartas que não são de Mana
            if (quantidade <= 3) {
                this.quantidade = quantidade;
            } else {
                this.quantidade = 3;
            }
        }
    }

    // Método para jogar uma carta de Mana da mão
    public Carta jogarCartaMana() {
        // Supondo que haja um array 'mao' representando a mão de cartas
        for (int i = 0; i < mao.length; i++) {
            // Verificando se a carta não é nula e é uma carta de Mana
            if (mao[i] != null && mao[i].getTipo().equalsIgnoreCase("Mana")) {
                Carta cartaMana = mao[i];
                mao[i] = null; // Removendo a carta de Mana jogada da mão
                return cartaMana; // Retornando a carta de Mana jogada
            }
        }
        return null; // Retornando nulo se nenhuma carta de Mana for encontrada na mão
    }
    
    // Métodos de obtenção (get) para as propriedades
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

    // Métodos de configuração (set) para as propriedades
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

    // Métodos de espaço reservado com valores de retorno e sem lógica real
    public Object getCustoMana() {
        return null;
    }

    public int getDano() {
        return 0;
    }

    public void sofrerDano(int danoDefensor) {
       
        return null;
    }

    public int getPontosVida() {
        return 0;
    }

    public void sofrerDano(int danoAtacante) {
       
        return null;
    }
}
