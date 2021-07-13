import java.util.EmptyStackException;

public class PilhaEncadeada {
    //implemeneta a classe node para a lista encadeada
    public class Node{
        public Character element;
        public Node next;
        public Node(Character e) {
            element = e;
        }
    }
    // atributos
    private int count;
    private Node topo;

    // construtor
    public PilhaEncadeada(){
        count = 0;
        topo = null;
    }

    // metodos da pilha

    // insere o elemento na lista
    public void push(Character element){
        Node aux = new Node(element);
        if (count > 0) {
            aux.next = topo;
        }
        topo = aux;
        count++;
    }

    // remove e retorna o elemento do topo da pilha
    public Character pop(){
        if (count == 0) {
            throw new EmptyStackException();
        }
        Character aux = topo.element;
        topo = topo.next;
        count--;
        return aux;
    }

    public Character top(){
        if (count == 0){
            throw new EmptyStackException();
        }
        return topo.element;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void clear(){
        count = 0;
        topo = null;
    }

}
