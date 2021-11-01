/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando
 */
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
public class problema1 {
    
//-------------------------------------------------------
    static void insert( int[] a){    
        Queue camino=new LinkedList();
        for(int i=0;i<a.length;i++){
            if(a[0]==a[i] && (2*i+1)>a.length){
                callSymmetry(a,i,camino);
                isSymmetric(camino);
                camino.removeAll(camino);
                System.out.print("\n");
            }
        }
    }
    static void callSymmetry(int[] a,int index,Queue datos){ //2*i+1=x, (x-1)/2       
        int raiz=(index-1)/2;
        datos.add(a[index]);
        if(index!=0) callSymmetry(a,raiz,datos);
    }
    static void isSymmetric(Queue datos){   
        boolean xd=true;
        int[] a=new int[datos.size()];
        for(int i=0;i<a.length;i++){
            a[i]=(int)datos.peek();
            datos.remove();  
        }
        for(int i=0,j=a.length-1;i<a.length/2;i++,j--){   
            if(a[i]!=a[j] && i!=j ){
                xd=false;
            }
        }
        if(xd){
            for(int i=0;i<a.length;i++){
                System.out.print(a[i]+" ");
            }
        } 
    }
    public static void main(String[] args)  
    { 
        Scanner a = new Scanner(System.in);
        int largo=a.nextInt();
        int[] array=new int[largo];
        for(int i=0;i<largo;i++){
            array[i]=a.nextInt();
        }
        insert(array);
        
    }
}
