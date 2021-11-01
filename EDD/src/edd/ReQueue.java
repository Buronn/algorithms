
package edd;

/**
 *
 * @author Fernando
 */
/*      cola.dequeue() quita el primero de la cola
        cola.enqueue(item) agrega un item al final
        cola.peek() retorna el primero de la cola
        cola.size() tamaño de la cola
        cola.isEmpty() ve si está vacía la cola     */


public class ReQueue {
    Queue invert(Queue Q){
        if (Q.isEmpty()){
            return Q;
        }
        int valor=Q.peek();
        Q.dequeue();
        Q=invert(Q);        
        Q.enqueue(valor);    
        return Q;
    }
    int println(Queue Q){
        while( Q.isEmpty()==false){
            System.out.println(Q.peek()); 
            Q.dequeue(); 
        }
        return 0;
    }
    public static void main(String[] args) {
        Queue L=new Queue();
        L.enqueue(10);
        L.enqueue(9);
        L.enqueue(8);
        L.enqueue(7);
        L.enqueue(6);
        ReQueue output=new ReQueue();
        output.invert(L);
        output.println(L);
    }
    
}
