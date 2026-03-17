package ed.lab;

public class Main {

    public static void main(String[] args) {
        E02AVLTree<Integer> avlTree = new E02AVLTree<>(Integer::compare);

        System.out.println("Buscar 5 (árbol vacío): " + avlTree.search(5));

        avlTree.insert(5);
        System.out.println("Insertar 5 en el árbol AVL");

        avlTree.insert(3);
        System.out.println("Insertar 3 en el árbol AVL");

        avlTree.insert(1);
        System.out.println("Insertar 1 en el árbol AVL y rebalancear");

        System.out.println("Buscar 5: " + avlTree.search(5));

        System.out.println("Buscar 1: " + avlTree.search(1));

        System.out.println("Tamaño del árbol: " + avlTree.size());

        System.out.println("Altura del árbol: " + avlTree.height());

        avlTree.delete(3);
        System.out.println("Eliminar 3 del árbol AVL");

        System.out.println("Buscar 3 después de eliminarlo: " + avlTree.search(3));

        avlTree.insert(4);
        System.out.println("Insertar 4 en el árbol AVL y rebalancear");
    }
}