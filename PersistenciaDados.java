import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PersistenciaDados {

    private static final String NOME_ARQUIVO = "dadosUsuarios.txt";

    public static void salvarDadosUsuario(String dados) {
        try {
            Files.writeString(Paths.get(NOME_ARQUIVO), dados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String carregarDadosUsuario() {
        StringBuilder dados = new StringBuilder();

        try {
            List<String> linhas = Files.readAllLines(Paths.get(NOME_ARQUIVO));
            linhas.forEach(linha -> dados.append(linha).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dados.toString();
    }
}
