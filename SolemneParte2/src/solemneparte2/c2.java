/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solemneparte2;


import java.util.Stack;

import java.io.*;
import java.util.*;

public class c2 {
    static int PrimoIesimo(int i){

        int aux=0;
        int valor=2;
        while(aux!=i){
            if(esPrimo(valor)){
                
                aux++;
                
            }
            else{
                valor++;
            }
        }
        return valor;
    }
    public static boolean esPrimo(int numero){
        int contador = 2;
        boolean primo=true;
        while ((primo) && (contador!=numero)){
                if (numero % contador == 0)
                primo = false;
                contador++;
        }
    return primo;
    }
    static int[] ordenarPlatos(int[] numbers, int q) {
        Stack<Integer> A = new Stack<>();
        Stack<Integer> A1 = new Stack<>();
        Queue B = new Queue();
        
        for(int i=numbers.length-1;i>=0;i--){
        A.add(numbers[i]);
        System.out.println(numbers[i]);
        }
        int primo=0;                            //5 1
        int aux=0;
        int[] a=new int[numbers.length];
        System.out.println("LLEGO AQUI");
        for(int i=1;i<=q;i++){                   //3 4 7 6 5
            primo=PrimoIesimo(i);
            System.out.println(primo);
            Stack<Integer> C = new Stack<>();
            for(int j=0;j<A.size();j++){
                aux=A.lastElement();
                System.out.println("aux "+aux);
                if(aux%primo==0){
                    
                    C.add(A.pop());
                    System.out.println("añade "+aux);
                    
                    j=j-1;
                }
                else{
                    A1.add(A.pop());
                    System.out.println("A1: "+ (int)A1.peek());
                    j=j-1;
                }
                 
            }
            B.enqueue(C);
            
              
           
            
            }
        B.enqueue(A1);   
        
        while(!B.isEmpty()){
            aux=0;
            while(!B.peek().empty()){
                System.out.println("modo sexo "+aux+" "+(int)B.peek().pop());
                
                a[aux]=(int)B.peek().pop();
            }
            
            
            
            
           
               
            }
            
            
            
        
        return a;
        
        
    }
    
    

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nq = scanner.nextLine().split(" ");
        int N = Integer.parseInt(nq[0].trim());
        int Q = Integer .parseInt(nq[1].trim());
        int[] numbers = new int[N];
        String[] numElems = scanner.nextLine().split(" ");
        for (int n = 0; n < N; n++) {
            int numElem = Integer.parseInt(numElems[n].trim());
            numbers[n] = numElem;
        }

        int[] result = ordenarPlatos(numbers, Q);

        for (int r = 0; r < result.length; r++) {
            bufferedWriter.write(String.valueOf(result[r]));
            if (r != result.length - 1) {
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
