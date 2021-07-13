import java.util.List;
import java.util.Scanner;

public class InterfaceUsuario {
    private WordTree dicionario;

    public InterfaceUsuario(WordTree dicionario) {
        this.dicionario = dicionario;
    }

    public WordTree getDicionario() {
        return dicionario;
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n ---  Dicionário  ---");
            System.out.println("Digite o número correspondente a sua escolha:");
            System.out.println("1 - Pesquisar palavra");
            System.out.println("2 - Sair\n");
            opcao = sc.nextInt();
            if (opcao == 1) {
                pesquisaDicionario();
            }
        } while (opcao != 2);
    }


    public void pesquisaDicionario() {
        Scanner sc = new Scanner(System.in);
        List<Palavra> palavras;

        System.out.println("\nInsira as letras iniciais da palavra que você deseja pesquisar:\n");
        palavras = getDicionario().searchAll(sc.next().toLowerCase());

        if (palavras.size() == 0) {
            System.out.println("Não foi possivel encontrar uma palavra no dicionario com esse prefixo!");
        } else {
            System.out.println("Insira o número da palavra que você deseja saber o significado:");
            for (int i = 0; i < palavras.size(); i++) {
                System.out.println(i+1 + " - " + palavras.get(i).getPalavra());
            }
            int escolha = sc.nextInt()-1;
            String palavra = palavras.get(escolha).getPalavra();
            String significado = palavras.get(escolha).getSignificado();
            System.out.println("\n"+palavra + " - " + significado);
        }
    }
}
