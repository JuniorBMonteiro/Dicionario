import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author José Junior Borges Monteiro
 * Dicionário utilizando Árvore Genérica
 */
public class App {
    public static void main(String[] args) {
        String linhas[] = new String[1000];
        String aux[];
        WordTree tree = new WordTree();
        InterfaceUsuario opcoes = new InterfaceUsuario(tree);
        Path path1 = Paths.get("src/dicionario.csv");

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                aux = line.split(";");
                Palavra p = new Palavra(aux[0].toLowerCase(), aux[1].toLowerCase());
                tree.addWord(p);
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }
        opcoes.menu();
    }
}