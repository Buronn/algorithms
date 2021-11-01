
class AVLTREE {

    Node root;

    int altura(Node N) {
        if (N == null) {
            return 0;
        }
        return N.altura;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rotacionDER(Node y) {
        Node x = y.izq;
        Node T2 = x.der;

        x.der = y;
        y.izq = T2;

        y.altura = max(altura(y.izq), altura(y.der)) + 1;
        x.altura = max(altura(x.izq), altura(x.der)) + 1;

        return x;
    }

    Node rotacionIZQ(Node x) {
        Node y = x.der;
        Node T2 = y.izq;

        y.izq = x;
        x.der = T2;

        // Update heights  
        x.altura = max(altura(x.izq), altura(x.der)) + 1;
        y.altura = max(altura(y.izq), altura(y.der)) + 1;

        return y;
    }

    int Balanceado(Node N) {
        if (N == null) {
            return 0;
        }
        return altura(N.izq) - altura(N.der);
    }

    Node insert(Node node, int key, String nombre) {
        if (key == -1) {
            return node;
        }
        if (node == null) {
            return (new Node(key, nombre));
        }

        if (key < node.key) {
            node.izq = insert(node.izq, key, nombre);
        } else if (key >= node.key) {
            node.der = insert(node.der, key, nombre);
        } else // Equal keys not allowed  
        {
            return node;
        }

        node.altura = 1 + max(altura(node.izq),
                altura(node.der));

        int balance = Balanceado(node);

        if (balance > 1 && key < node.izq.key) {
            return rotacionDER(node);
        }

        if (balance < -1 && key > node.der.key) {
            return rotacionIZQ(node);
        }

        if (balance > 1 && key > node.izq.key) {
            node.izq = rotacionIZQ(node.izq);
            return rotacionDER(node);
        }

        if (balance < -1 && key < node.der.key) {
            node.der = rotacionDER(node.der);
            return rotacionIZQ(node);
        }

        return node;
    }

    Node minValueNode(Node node) {
        Node current = node;

        while (current.izq != null) {
            current = current.izq;
        }

        return current;
    }

    Node borrarNodo(Node raíz, int key) {

        if (raíz == null) {
            return raíz;
        }

        if (key < raíz.key) {
            raíz.izq = borrarNodo(raíz.izq, key);
        } else if (key > raíz.key) {
            raíz.der = borrarNodo(raíz.der, key);
        } else {

            if ((raíz.izq == null) || (raíz.der == null)) {
                Node temp = null;
                if (temp == raíz.izq) {
                    temp = raíz.der;
                } else {
                    temp = raíz.izq;
                }

                if (temp == null) {
                    temp = raíz;
                    raíz = null;
                } else {
                    raíz = temp;
                }
            } else {

                Node temp = minValueNode(raíz.der);

                raíz.key = temp.key;

                raíz.der = borrarNodo(raíz.der, temp.key);
            }
        }

        if (raíz == null) {
            return raíz;
        }

        raíz.altura = max(altura(raíz.izq), altura(raíz.der)) + 1;

        int Balance = Balanceado(raíz);

        if (Balance > 1 && Balanceado(raíz.izq) >= 0) {
            return rotacionDER(raíz);
        }

        if (Balance > 1 && Balanceado(raíz.izq) < 0) {
            raíz.izq = rotacionIZQ(raíz.izq);
            return rotacionDER(raíz);
        }

        if (Balance < -1 && Balanceado(raíz.der) <= 0) {
            return rotacionIZQ(raíz);
        }

        if (Balance < -1 && Balanceado(raíz.der) > 0) {
            raíz.der = rotacionDER(raíz.der);
            return rotacionIZQ(raíz);
        }

        return raíz;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.nombre + ": " + node.key);
            preOrder(node.izq);
            preOrder(node.der);

        }

    }

}
