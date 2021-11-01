/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solemne;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;


/**
 *
 * @author Fernando
 */
public class Main {
    void Divisor(int n, int a[], int k){
        Queue cola=new LinkedList();
        int[] resultado=new int[n-k+1];
        int[] aux=new int[k];
        int ayuda=0;
        for(int i=0;i<n-k+1;i++){
            
            for(int j=0;j<k;j++){
                
                
                aux[j]=a[j+i];
                
                
            }
            
                cola.add(Maximo(aux));
            
        }
        
        while(!cola.isEmpty()){
            System.out.print(cola.peek()+" ");
            cola.remove();
        }
    }
    int Maximo(int[] a){
        int max=a[0];
        for(int i=0;i<a.length;i++){
            if(max<a[i]){
                max=a[i];
            }
        }
        
        
        return max;
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in;
        in = new Scanner(System.in);
        int n=in.nextInt();
        int[] a=new int[n];
        for(int j=0; j<n;++j){
            a[j]=in.nextInt();
        }
        int k=in.nextInt();
        Main hola=new Main();
        hola.Divisor(n, a, k);
        
        
        // TODO code application logic here
    }
    
}
