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
public class Search {
    int[] encontrar(int[] arr, int num){
        return resolver(arr,num,arr.length-1,(arr.length-1)/2,0);
        
    }
    int[] resolver(int[] arr,int num,int top,int center,int bot){
        int[] out={0,0};
        if(arr[center]==num){
            top=center;
            bot=center;
            out[0]=arr[bot];
            out[1]=arr[top];
            return out;
        }
        
        else{
            if(arr[center]<num){ // SI EL CENTRO ES MENOR AL NUMERO
                bot=center;
                center=(top+bot)/2;
                if(arr[center]>num){
                    top=center;
                    bot=center-1;
                    out[0]=arr[bot];
                    out[1]=arr[top];
                    if(center==arr.length-1){
                        
                        out[1]=-1;
                        return out;
                    }
                    
                    return out;
                }
                else if(bot==arr.length-2 && bot==center){
                    
                    if(num<arr[top]){
                        out[0]=arr[bot];
                        out[1]=arr[top];
                    return out;
                    }
                    else{
                    out[0]=arr[top];
                    out[1]=-1;
                    return out;
                    }
                }
                else if(bot==center){
                    out[0]=arr[bot];
                    out[1]=arr[top];
                    if(center==arr.length-1){
                        out[1]=-1;
                        return out;
                    }
                    return out;   
                }
                else{
                return resolver(arr,num,top,center,bot);
                }
            }
            
            else if(arr[center]>num){ // SI EL CENTRO ES MAYOR AL NUMERO
                top=center;
                center=(top+bot)/2;
                /*System.out.println("centro nuevo: "+arr[center]);
                System.out.println("bot: "+arr[bot]);
                System.out.println("top: "+arr[top]);*/
                if(arr[center]<num){
                    bot=center;
                    top=center+1;
                    out[0]=arr[bot];
                    out[1]=arr[top];
                    if(center==0 && num<arr[0]){
                       
                        out[0]=-1;
                        
                        return out;
                    }

                    return out;
                }
                else if(top==center){
                    /*System.out.println("top = centro");*/
                    out[0]=arr[bot];
                    out[1]=arr[top];
                    if(center==0){
                        //System.out.println("pasa por aqui");
                        out[0]=-1;
                        
                        return out;
                    }

                    return out;   
                }
                else{
                return resolver(arr,num,top,center,bot);
                }
            }
        }
        out[0]=arr[bot];
        out[1]=arr[top];
        
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
        Search a=new Search();
        System.out.println("O(log n)");
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

