public class Usuario {
    private String nick;
    private String cpf;
    private String senha;
    private int idade;
    private String sexo;
    private String email;
    private int nivel = 1;
    private String [] decks = new String[5];
    private double pontos = 0;

    public Usuario(String nick, String cpf, String senha, int idade, String sexo, String email, int nivel, String[] decks, double cardcoins) {
        this.nick = nick;
        this.cpf = cpf;
        this.senha = senha;
        this.idade = idade;
        this.sexo = sexo;
        this.email = email;
        this.nivel = nivel;
        this.decks = decks;
        this.pontos = pontos;
    }
}