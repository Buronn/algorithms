/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea8;
import java.util.*; 
import java.util.LinkedList; 
  

public class problema3{
    public static void main(String args[]) 
    { 
        Scanner a=new Scanner(System.in);
        int n=a.nextInt(); //reinos
        int e=a.nextInt(); //aristas
        Grafito crack = new Grafito(n);
        for(int i=0;i<e;i++){
        crack.vertices(a.nextInt(),a.nextInt());
        }
       
        System.out.println(crack.GOD()); 
    } 
    static class Grafito{ 
        private final int tamaño;
        private final LinkedList<Integer> A[];
        
        Grafito(int p){
            tamaño = p; 
            A = new LinkedList[p]; 
            for (int desde=0; desde<p; ++desde) 
                A[desde] = new LinkedList(); 
        } 
//-------------------------------------------------------------------
        void vertices(int x,int y){ 
            A[x].add(y);
        } 
        int GOD(){ 
            boolean check[] = new boolean[tamaño]; 
            int res=0;
            Stack saved = new Stack(); 
 
            for(int i=0;i<check.length;i++) 
                check[i] = false; 

            for (int i=0;i<check.length;i++)  //visita y cambia check[i] a true
                if (!check[i]) 
                    dfs(i,check,saved); 
            //-----------------------se da vuelta la lista, para tener las direcciones al contrario(checkea si hay un camino de vuelta o no)------------
            Grafito flip = ALREVEZ(); 
            for (int i=0;i<tamaño;i++){
            check[i]=false; 
            }
            
            while (!saved.empty()){
                int v = (int)saved.pop(); 
                if(check[v] == false){
                    flip.cycles(v, check); 
                    res++;
                    }
            }
            
            
            return res;
        }
        //---------------------------------------------
        Grafito ALREVEZ(){ 
            Grafito g = new Grafito(tamaño); 
            for (int i=0;i<tamaño;i++) 
            { 
                Iterator<Integer> it =A[i].listIterator(); 
                while(it.hasNext()) 
                    //System.out.println("Pasa por aqui3 "+it.next()+" "+i);
                    g.A[it.next()].add(i); 
            } 
            return g; 
        } 
        //----------------------------------------------
        void cycles(int pos,boolean checked[]) 
        { 
            checked[pos]=true; 
            int r; 
            Iterator<Integer> i= A[pos].iterator(); 
            while(i.hasNext()){
                r = i.next(); 
                if (!checked[r]){
                //System.out.println("Pasa por aqui4");
                    cycles(r,checked);
                }
            } 
        } 
    //------------------------------------------------------
        void dfs(int r, boolean checked[], Stack s) 
        { 
            checked[r] = true; 
            Iterator<Integer> i = A[r].iterator(); 
            while (i.hasNext()) 
            {
                int n = i.next(); 
                if(!checked[n]) 
                    dfs(n,checked,s); 
            } 
            s.push(r); 
        } 
    }
}
