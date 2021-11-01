/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea4;

/**
 *
 * @author Fernando
 */

public class Search2 {
    int[] encontrar(int[] arr, int num){
        int top=0;
        int bot=0;
        int[] out={0,0};
        for(int i=0;i<arr.length;i++){
            
            if(arr[i]<num){
                if(i+1==arr.length){
                    out[0]=arr[i];
                    out[1]=-1;
                    return out;
                }
                else if(arr[i+1]>num){
                    bot=i;
                    top=bot+1;
                    out[0]=arr[bot];
                    out[1]=arr[top];
                    return out;
                }
                bot=i;
                
                
                
            }
            else if(arr[i]==num){
                out[0]=arr[i];
                out[1]=arr[i];
                return out;
            }
            else if(arr[i]>num){
                top=i;
                if(top==0){
                    out[0]=-1;
                    out[1]=arr[top];
                    return out;     
                }
            }
        }
        if(bot==arr.length){
            out[0]=arr[bot];
            out[1]=-1;
        }
        return out;
        
    }
    void print(int arr[]){
        System.out.print("{");
        for(int i=0;i<arr.length;i++){
            
            if(i==arr.length-1){
                System.out.print(arr[i]);
            }
            else{
            System.out.print(arr[i]+",");
            }
        }
        System.out.print("}");
        System.out.println();
    }
    public static void main(String[] args) {
        Search2 a=new Search2();
        System.out.println("O(n)");
        int[] Entrada1={1, 2, 8, 10, 10, 12, 19};
        int[] e1=a.encontrar(Entrada1, 0);
        int[] Entrada2={1, 2, 8, 10, 10, 12, 19};
        int[] e2=a.encontrar(Entrada2, 5);
        int[] Entrada3={3, 6, 8, 11, 15, 18};
        int[] e3=a.encontrar(Entrada3, 22);
        a.print(e1);
        a.print(e2);
        a.print(e3);
    }
}
