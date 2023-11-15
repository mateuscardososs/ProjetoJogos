import java.util.Arrays;

public class Usuario {
    private String nick;
    private String cpf;
    private String senha;
    private int idade;
    private String sexo;
    private String email;
    private int nivel = 1;
    private String[] decks = new String[5];
    private double pontos = 0;
}
    public Usuario(String nick, String cpf, String senha, int idade, String sexo, String email) {
        this.nick = nick;
        this.cpf = cpf;
        this.senha = senha;
        this.idade = idade;
        this.sexo = sexo;
        this.email = email;
    }

    public Usuario(String nick, String cpf, String senha, int idade, String sexo, String email, int nivel, String[] decks, double cardcoins) {
        this(nick, cpf, senha, idade, sexo, email); // Chama o construtor principal
        this.nivel = nivel;
        this.decks = decks;
        this.pontos = cardcoins;
    }

    // Métodos getter e setter

    public String getNick() {
        return nick;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public int getNivel() {
        return nivel;
    }

    public String[] getDecks() {
        return decks;
    }

    public double getPontos() {
        return pontos;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setDecks(String[] decks) {
        this.decks = decks;
    }

    public void setPontos(double pontos) {
        this.pontos = pontos;
    }
    public String toString() {
        return "Usuario{" +
                "nick='" + nick + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                ", idade=" + idade +
                ", sexo='" + sexo + '\'' +
                ", email='" + email + '\'' +
                ", nivel=" + nivel +
                ", decks=" + Arrays.toString(decks) +
                ", pontos=" + pontos +
                '}';
    
    } 

    public void adicionarCartaMao(int carta) {
    }

    public void aumentarManaMaxima(int i) {
    }

    public void resetMana() {
    }

    public boolean temMana() {
        return false;
    }

    public Carta jogarCartaMana() {
        return null;
    }

    public int escolherPosicaoCampo() {
        return 0;
    }

    public void diminuirMana(Object custoMana) {
    }

    public void posicionarCartaCampo(Carta cartaMana, int posicaoCampo) {
    }

    public Carta escolherCartaMao() {
        return null;
    }

    public Carta[] getCampo() {
        return null;
    }

    public void adicionarCardCoins(int cardCoinsVencedor) {
    }

    public String getNome() {
        return null;
    }

    public String getMana() {
        return null;
    }

    public int getPontosVida() {
        return 0;
    }

    public String getManaMaxima() {
        return null;
    }

    public int getIndex() {
        return 0;
    }