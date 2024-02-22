package tree;

import java.util.ArrayList;
import java.util.List;

import estrut.Tree;

public class BinarySearchTree implements Tree {

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElemento(root, valor);
    }

    private boolean buscaElemento(Node node, int valor) {
        if (node == null) {
            return false;
        }
        if (node.valor == valor) {
            return true;
        } else if (valor < node.valor) {
            return buscaElemento(node.esquerda, valor);
        } else {
            return buscaElemento(node.direita, valor);
        }
    }

    @Override
    public int minimo() {
        if (root == null) {
            throw new IllegalStateException("Árvore vazia");
        }
        Node node = root;
        while (node.esquerda != null) {
            node = node.esquerda;
        }
        return node.valor;
    }

    @Override
    public int maximo() {
        if (root == null) {
            throw new IllegalStateException("Árvore vazia");
        }
        Node node = root;
        while (node.direita != null) {
            node = node.direita;
        }
        return node.valor;
    }

    @Override
    public void insereElemento(int valor) {
        root = insereElemento(root, valor);
    }

    private Node insereElemento(Node node, int valor) {
        if (node == null) {
            return new Node(valor);
        }
        if (valor < node.valor) {
            node.esquerda = insereElemento(node.esquerda, valor);
        } else if (valor > node.valor) {
            node.direita = insereElemento(node.direita, valor);
        }
        return node;
    }

    @Override
    public void remove(int valor) {
        root = remove(root, valor);
    }

    private Node remove(Node node, int valor) {
        if (node == null) {
            return null;
        }
        if (valor < node.valor) {
            node.esquerda = remove(node.esquerda, valor);
        } else if (valor > node.valor) {
            node.direita = remove(node.direita, valor);
        } else {
            if (node.esquerda == null && node.direita == null) {
                return null;
            } else if (node.esquerda == null) {
                return node.direita;
            } else if (node.direita == null) {
                return node.esquerda;
            } else {
                node.valor = minimo(node.direita);
                node.direita = remove(node.direita, node.valor);
            }
        }
        return node;
    }

    private int minimo(Node node) {
        int min = node.valor;
        while (node.esquerda != null) {
            min = node.esquerda.valor;
            node = node.esquerda;
        }
        return min;
    }

    @Override
    public int[] preOrdem() {
    List<Integer> elementos = new ArrayList<>();
    preOrdem(root, elementos);
    return elementos.stream().mapToInt(Integer::intValue).toArray();
}


    private void preOrdem(Node node, List<Integer> elementos) {
        if (node != null) {
            elementos.add(node.valor);
            preOrdem(node.esquerda, elementos);
            preOrdem(node.direita, elementos);
        }
    }

    @Override
    public int[] emOrdem() {
        List<Integer> elementos = new ArrayList<>();
        emOrdem(root, elementos);
        return elementos.stream().mapToInt(Integer::intValue).toArray();
    }

    private void emOrdem(Node node, List<Integer> elementos) {
        if (node != null) {
            emOrdem(node.esquerda, elementos);
            elementos.add(node.valor);
            emOrdem(node.direita, elementos);
        }
    }

    @Override
    public int[] posOrdem() {
        List<Integer> elementos = new ArrayList<>();
        posOrdem(root, elementos);
        return elementos.stream().mapToInt(Integer::intValue).toArray();
    }

    private void posOrdem(Node node, List<Integer> elementos) {
        if (node != null) {
            posOrdem(node.esquerda, elementos);
            posOrdem(node.direita, elementos);
            elementos.add(node.valor);
        }
    }

    private static class Node {
        private int valor;
        private Node esquerda;
        private Node direita;

        public Node(int valor) {
            this.valor = valor;
            this.esquerda = null;
            this.direita = null;
        }
    }
}
