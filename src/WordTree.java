import java.util.ArrayList;
import java.util.List;

class WordTree {
    // Atributos
    private CharNode root;
    private int totalNodes = 0; // cada node é uma letra
    private int totalWords = 0; // total de palavras, quando is final == true

    private class CharNode {
        private char character;
        private String significado;
        private boolean isFinal;
        private CharNode father;
        public List<CharNode> children;

        public CharNode(char character) {
            father = null;
            this.character = character;
            children = new ArrayList<>();
        }

        public CharNode(char character, boolean isFinal, String significado) {
            father = null;
            this.character = character;
            children = new ArrayList<>();
            this.isFinal = isFinal;
            this.significado = significado;
        }

        /**
         * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
         * // * @param character - caracter a ser adicionado
         * //* @param isfinal   - se é final da palavra ou não
         */
        public CharNode addChild(char character, boolean isFinal, String significado) {
            CharNode aux = this.findChildChar(character); // verifica se o pai ja possui um filho com o mesmo character
            CharNode node;
            if (isFinal) { // verifica se é a ultima letra e inicializa a instancia charNode node
                node = new CharNode(character, true, significado); // se for final atribui o significado da palavra
            } else {
                node = new CharNode(character);
            }
            if (aux == null) { // se não possui nenhum filho com o character ele é adicionado
                children.add(node);
                node.father = this;
                return node;
            } else { // se ja existir ele retorna o filho que ja existia, com as modificações do atributo se for final.
                aux.isFinal = isFinal;
                aux.significado = significado;
                return aux;
            }
        }

        // numero de filhos
        public int getNumberOfChildren() {
            return children.size();
        }
        // buscar filho
        public CharNode getChild(int index) {
            return children.get(index);
        }

        /**
         * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
         *
         * @return a palavra
         */
        private String getWord() {
            CharNode aux = this; // inicia pelo nodo final
            PilhaEncadeada pilha = new PilhaEncadeada();
            String word = "";
            while (!aux.equals(root)) {    // enquanto não chegar na raiz
                pilha.push(aux.character); // adiciona na pilha
                aux = aux.father;          // muda para o pai
            }
            int quantPalavras = pilha.size();
            for (int i = 0; i < quantPalavras; i++) {
                word = word.concat(pilha.pop() + ""); // retira da pilha e concatena formando a palavra
            }
            return word;
        }

        /**
         * Encontra e retorna o nodo que tem determinado caracter.
         *
         * @param /*character - caracter a ser encontrado.
         */
        public CharNode findChildChar(char character) {
            for (CharNode aux : children) {           // Percorre a lista de filhos
                if (aux.character == character) {     // se encontrar um filho com o mesmo character retorna o filho
                    return aux;                       // caso contrario retorna null
                }
            }
            return null;
        }
    }

    // Construtor
    public WordTree() {
        root = new CharNode(' ');
        totalNodes = 0;
        totalWords = 0;
    }

    /**
     * Adiciona palavra na estrutura em árvore
     */
    public void addWord(Palavra p) {
        String palavra = p.getPalavra();
        String significado = p.getSignificado();
        CharNode aux = root;                         // guarda a referencia para o root para adicionar a palavra seguindo da raiz
        for (int i = 0; i < palavra.length(); i++) {
            if (i == palavra.length() - 1) {         // se for a ultima letra da palavra é atribuido o significado da palavra
                aux = aux.addChild(palavra.charAt(i), true,significado);
                totalNodes++;
                totalWords++;
            } else {
                aux = aux.addChild(palavra.charAt(i), false,null); // adiciona o nodo como filho do aux
                totalNodes++;
            }
        }
    }

    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * @param word
     * @return o nodo final encontrado
     */
    public CharNode findCharNodeForWord(String word) {
        char letra;
        CharNode aux = root;
        for (int i = 0; i < word.length(); i++) {
            letra = word.charAt(i);
            aux = aux.findChildChar(letra);
            if (aux == null) {
                return null;
            }
        }
        return aux;
    }


    /**
     * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
     * Tipicamente, um método recursivo.
     *
     * @param prefix
     */
    public List<Palavra> searchAll(String prefix) {
        CharNode aux = findCharNodeForWord(prefix); // encontra o ultimo nodo do prefixo
        List<Palavra> lista = new ArrayList<>();
        searchAllAux(aux, lista);                // a partir do nodo encontrado lista todas as palavras que iniciem
        return lista;                            // com o prefixo
    }

    private void searchAllAux(CharNode letra, List<Palavra> lista) {
        if (letra != null) {
            if (letra.isFinal) {
                lista.add(new Palavra(letra.getWord(), letra.significado)); // instancia uma nova palavra atribuindo os
            } else {                                                        // valores obtidos a partir do ultimo nodo
                for (int i = 0; i < letra.getNumberOfChildren(); i++) {
                    searchAllAux(letra.getChild(i), lista);
                }
            }
        }
    }
}