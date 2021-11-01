package tarea8;
import java.util.Scanner;
import java.util.*;
public class problema1 {
    static int[] recorrido(Hashtable<Integer,int[]> a, int[] b){
        
        for(int i=0;i<a.size();i++){
            boolean visitado=false;
            int j=i;
            int contador=0;
            
            while(!visitado){
            if(a.get(j)[1]==0){
                a.get(j)[1]=1;
                j=a.get(j)[0];
                contador++;
            }
            else{           
                visitado=true;
            }
            }
            for(int k=0;k<a.size();k++){ //los vuelvo a marcar como no visitados para el siguiente planeta
            a.get(k)[1]=0;
            }
            b[i]=contador;
        }
        return b;
    }
    public static void main(String[] args) {
        
        Hashtable<Integer,int[]> planetas=new Hashtable<>();
        Scanner a = new Scanner(System.in);
        int n=a.nextInt();
        int portal;
        int[] camino=new int[n];
        for(int i=0;i<n;i++){
            int[] l=new int[2];
            portal=a.nextInt()-1;
            planetas.put(i,l);
            planetas.get(i)[0]=portal; 
            planetas.get(i)[1]=0;  //marca de "no visitado"
        }
        int[] r=recorrido(planetas,camino);
        for(int i=0;i<r.length;i++){
            if(i!=r.length-1){
                System.out.print(r[i]+" ");
            }
            else{
                System.out.print(r[i]);
            }
        }
    }
}
