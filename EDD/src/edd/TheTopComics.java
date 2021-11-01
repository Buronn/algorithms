
package edd;
import java.util.Stack;


/**
 *
 * @author Fernando
 */
// MÉTODOS:  isEmpty / push(int) / peek(el primero) / pop return el primero y
//            / size tamaño                           lo borra
public class TheTopComics{
    public void order(int[] ediciones){
        Stack revistas=new Stack();
        Stack aux=new Stack();
        for(int i=0;i<ediciones.length;i++){
            aux.push(ediciones[i]);
        }
        while(!aux.isEmpty()){
            int pivote=(int)aux.peek();
            aux.pop();
            while(!revistas.isEmpty() && (int)revistas.peek()<pivote){
                aux.push(revistas.peek());
                revistas.pop();
            }
            if(!revistas.isEmpty() && (int)revistas.peek()==pivote){
                revistas.pop();
            }
            revistas.push(pivote);
        }
        while(!revistas.isEmpty()){
            int a=(int)revistas.peek();
            System.out.println(revistas.peek());
            revistas.pop();
            
        }
        

    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        int[] i={1,1,3,6,1,8,1,1,2};
        TheTopComics a=new TheTopComics();
        a.order(i);
        
        
    }
}

    
