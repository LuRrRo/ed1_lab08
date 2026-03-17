package ed.lab;

import java.util.Comparator;

public class E02AVLTree<T> {

    private final Comparator<T> comparator;

    public E02AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private TreeNode<T> root;
    private int size;

    public void insert(T value) {
        root = insert(root, value);
    }
    //inserte posee una complejidad de tiempo de O(log n), ya que debe recorrer cierto camino
    //para insertar un nodo y su complejidad de memoria es de O(1), ya que solo se crea un
    //nodo.

    private TreeNode<T>insert(TreeNode<T> node, T value){
        if (node == null) { size++; return new TreeNode<>(value); }
        int cmp = comparator.compare(value, node.value);
        if      (cmp < 0) node.left  = insert(node.left,  value);
        else if (cmp > 0) node.right = insert(node.right, value);
        return rebalancear(node);
    }

    public void delete(T value) {
        root = delete(root, value);
    }
    //En delete, la busqueda y los demas procesos generan una complejidad de tiemmpo de
    //O(log n) y el stack recursivo genera una complijdad de espacio de O(log n)
    private TreeNode<T> delete(TreeNode<T> node, T value) {
        if (node == null) return null;
        int cmp = comparator.compare(value, node.value);
        if(cmp < 0) node.left = delete(node.left, value);
        else if (cmp > 0) node.right = delete(node.right, value);
        else{
            if(node.left == null) {
                size--; return node.right;
            }
            if(node.right == null) {
                size--; return node.left;
            }

            TreeNode<T> succesor = nodoMinimo(node.right);
            node.value = succesor.value;
            node.right = delete(node.right, succesor.value);

        }
        return rebalancear(node);
    }

    private TreeNode<T> nodoMinimo(TreeNode<T> n) {
        while (n.left != null) n = n.left; return n;
    }
    //La complejidad de tiempo de nodo minimo es de log(n), ya que se mueve por todos los
    //nodos izquierdos hasta llegar al minimo. La complejidad de memoria es O(1), ya que solo
    //accede a datos.

    public T search(T value) {
        TreeNode<T> curr = root;
        while (curr != null) {
            int cmp = comparator.compare(value, curr.value);
            if(cmp < 0){curr = curr.left;}
            else if (cmp > 0) {curr = curr.right;}
            else {return curr.value;}
        }
        return null;
    }
    //El metodo search compara el valor de cada nodo con el valor buscado y usando un bucle
    //se desplaza por el arbol AVL, debido a las rotaciones en el arbol este se matendra
    //balanceado, por lo que las complejidades se mantendra en O(log n)

    public int height() {
        return height(root);
    }
    //La complejidad de height es de O(1), ya que accede al valor del nodo raiz.
    private int height(TreeNode<T> node){
        return node == null ? -1 : node.height;
    }
    //Toda complejidad es O(1), ya que accede al dato al instante.

    public int size() {
        return size;
    }
    //Toda complejidad es O(1), ya que accede al dato al instante.

    private TreeNode<T> rebalancear(TreeNode<T> node) {//llama insert y dele, actualiza la altura, mide el balance y si esta fuera de rango mira cual rotar
        actuHeight(node);
        int bal = balancear(node);
        if (bal == 2) {
            if (balancear(node.left) < 0) node.left = rotarIzquierda(node.left);
            return rotarDerecha(node);
        }
        if (bal == -2) {
            if (balancear(node.right) > 0) node.right = rotarDerecha(node.right);
            return rotarIzquierda(node);
        }
        return node;
    }
    //rebalancear balancea el arbol dependiendo de si el arbol esta balanceado o no,
    //haciendo rotaciones hacia izquierda o derecha segun se requiera. Ya que todos los
    //demas metodos que usan tienen complejidad de O(1), este tambien posee esta complejidad.
    private void actuHeight(TreeNode<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }
    //actuHeight: Cambia la altura de cada nodo cuando el arbol se rota o cambia. Su
    //complejidad de tiempo es de O(n) en todo caso, ya que deber recorrer por completo el arbol
    // y su complejidad de espacio es O(1), ya que utiliza la memoria del arbol.

    private int balancear(TreeNode<T> node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }
    //Balancear compara la altura del subarbol derecho e izquierdo, su complejidad de espacio
    //y tiempo es de O(1), ya que no crea nada en la memoria y solo accede a valores.

    private TreeNode<T> rotarDerecha(TreeNode<T> y){//El hijo izquierdo x sube para reemplazar a y. El subárbol derecho de x pasa a ser el hijo izquierdo de y para no perder nodos
        TreeNode<T> x = y.left, t2 = x.right;
        x.right = y; y.left = t2;
        actuHeight(y); actuHeight(x);
        return x;
    }
    //rotarDerecha intercambia el hijo izquierdo "x", para reemplazar a "y". La complejidad
    // de tiempo y espacio es de O(1), ya que solo deben cambiarse hacia donde apunta los
    // punteros de los nodos.
    private TreeNode<T> rotarIzquierda(TreeNode<T> x) {//El hijo derecho y sube para reemplazar a x. Se aplica cuando el lado derecho está sobrecargado.
        TreeNode<T> y = x.right, t2 = y.left;
        y.left = x; x.right = t2;
        actuHeight(x); actuHeight(y);
        return y;
    }
    //rotarIzquierda intercambia el hijo derecho "y", para reemplazar a "x". La complejidad
    //  de tiempo y espacio es de O(1), ya que solo deben cambiarse hacia donde apunta los
    //  punteros de los nodos.

}
//2. Las complejidades se mantienen estables en comparación con un arbol binario de busqueda
// ya que el arbol se mantiene balanceado, las complejidades solo suelen variar entre O(1)
// y O(log n).

