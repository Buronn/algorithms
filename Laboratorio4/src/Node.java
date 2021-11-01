
public class Node {

    int key, altura;
    Node izq, der;
    String nombre;

    Node(int d, String nombre) {
        key = d;
        altura = 1;
        this.nombre = nombre;
    }
}
