
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

    Node borrarNodo(Node ra??z, int key) {

        if (ra??z == null) {
            return ra??z;
        }

        if (key < ra??z.key) {
            ra??z.izq = borrarNodo(ra??z.izq, key);
        } else if (key > ra??z.key) {
            ra??z.der = borrarNodo(ra??z.der, key);
        } else {

            if ((ra??z.izq == null) || (ra??z.der == null)) {
                Node temp = null;
                if (temp == ra??z.izq) {
                    temp = ra??z.der;
                } else {
                    temp = ra??z.izq;
                }

                if (temp == null) {
                    temp = ra??z;
                    ra??z = null;
                } else {
                    ra??z = temp;
                }
            } else {

                Node temp = minValueNode(ra??z.der);

                ra??z.key = temp.key;

                ra??z.der = borrarNodo(ra??z.der, temp.key);
            }
        }

        if (ra??z == null) {
            return ra??z;
        }

        ra??z.altura = max(altura(ra??z.izq), altura(ra??z.der)) + 1;

        int Balance = Balanceado(ra??z);

        if (Balance > 1 && Balanceado(ra??z.izq) >= 0) {
            return rotacionDER(ra??z);
        }

        if (Balance > 1 && Balanceado(ra??z.izq) < 0) {
            ra??z.izq = rotacionIZQ(ra??z.izq);
            return rotacionDER(ra??z);
        }

        if (Balance < -1 && Balanceado(ra??z.der) <= 0) {
            return rotacionIZQ(ra??z);
        }

        if (Balance < -1 && Balanceado(ra??z.der) > 0) {
            ra??z.der = rotacionDER(ra??z.der);
            return rotacionIZQ(ra??z);
        }

        return ra??z;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.nombre + ": " + node.key);
            preOrder(node.izq);
            preOrder(node.der);

        }

    }

}
