package ed.lab;

public class E01KthSmallest {
    int global=0;
    public int kthSmallest(TreeNode<Integer> root, int k) {
        if(root==null){
            return -1;
        }
        int x=kthSmallest(root.left,k);
        if(x!=-1){
            return x;
        }
        global +=1;
        if (global==k){
            return root.value;
        }
        return kthSmallest(root.right,k);
    }
    //1. La mejor complejidad es Log(n), esto en el caso de que la funcion recorra el camino
    //mas corto para llegar k-esimo elemento.
    //El caso promedio, nuevamente seria log(n). Esto se debe a que en promedio los arboles se
    //encuentran balanceados, por lo que mantiene la complejidad.
    //En el peor de los casos la complejidad es O(n), ya que tendria que recorrer un arbol
    //binario con forma de lista enlazada.

    //2.La complejidad de espacio en el mejor caso y el caso promedio esta dada por la altura
    //del arbol, donde si esta balanceado el arbol su complejidad de espacio seria log(n)
    //Mientras que si el arbol esta desbalanceado o posee una estructura de lista enlazada, la
    //complejidad sera de o(n).

    //3. Se tendria que trabajar usando rotaciones como un arbol AVL, ya que esto permitiria
    // que el arbol se mantuviese balanceado y se mantuviese la complejidad de tiempo y
    //espacio de log(n).
}