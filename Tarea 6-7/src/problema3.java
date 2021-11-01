/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fernando
 */
import java.util.*; 
  

class SegmentTree
{ 

    static int getMid(int x, int y) 
    { 
        return x + (y - x) / 2; 
    } 

    static int[] DosMayores(int[][] st, int firstIndex, int lastIndex, int left, int right, int node)  
    { 
       /* System.out.println("-------------------------------------------------------");
        System.out.println(firstIndex+" "+ lastIndex+" "+ left+" "+  right+" "+ node);
        */
        int[] b=new int[2];
        b[0]=-9999;
        b[1]=-9999;
        if (left <= firstIndex && right >= lastIndex) {
            
            
            return st[node]; 
        }

        if (lastIndex < left || firstIndex > right) {
           // System.out.println("retorna -9999, NO ESTA EN EL RANGO DE BUSQUEDA");
            return b; 
        }

        int mid = getMid(firstIndex, lastIndex); 
        //System.out.println("MID "+mid);
        
        return Biggers(DosMayores(st, firstIndex, mid, left, right, 2 * node + 1),
                    DosMayores(st, mid + 1, lastIndex, left, right, 2 * node + 2)); 
    }
    static int[] Biggers(int[] a,int[] b){
    int[] output=new int[2];
    int[] aux=new int[4];
    //System.out.println("a[]");
    for(int i=0;i<a.length;i++){
        //System.out.print(a[i]+" ");
        if(aux[0]<aux[1]){
            if(a[i]>aux[0]){
                aux[0]=a[i];
            }
        }
        else{
            if(a[i]>aux[1]){
                aux[1]=a[i];
            }
        }
    }
   // System.out.println(" ");
   // System.out.println("b[]");
    for(int i=0;i<b.length;i++){
       // System.out.print(b[i]+" ");
        if(aux[2]<aux[3]){
            if(b[i]>aux[2]){
                aux[2]=b[i];
            }
        }
        else{
            if(b[i]>aux[3]){
                aux[3]=b[i];
            }
        }
    }
   // System.out.println(" ");
    for(int i=0;i<aux.length;i++){
        if(output[0]<output[1]){
            if(aux[i]>output[0]){
                output[0]=aux[i];
            }
        }
        else{
            if(aux[i]>output[1]){
                output[1]=aux[i];
            }
        }
    }
    /*System.out.println("[0]"+aux[0]+"  [1]"+aux[1]+
                "  [2]"+aux[2]+"  [3]"+aux[3]);
    System.out.println("output[0] "+output[0]+" output[1] "+output[1]);*/
    return output;
    }

    static int getMax(int[][] st, int n, int l, int r) 
    { 
        int[] b=DosMayores(st, 0, n - 1, l, r, 0);
       // System.out.println("b[0]="+b[0]+" b[1]="+b[1]);
        return b[0]+b[1]; 
    }

    static int[] STInsert(int arr[], int ss, int se, int[][] st,int si)  
    {
        /*System.out.println("---------"+ss+" "+ se +" "+ si +"----------");*/
        /*System.out.println("st["+si+"] "+st[si]);*/
        if (ss == se)  
        {
            st[si][0] = arr[ss];
            return st[si]; 
        }

        int mid = getMid(ss, se); 
        
        st[si] = Biggers(STInsert(arr, ss, mid, st, si * 2 + 1),
                STInsert(arr, mid + 1, se, st, si * 2 + 2));
        /*
        System.out.println("Trabajo pendiente de: "+ss+" "+se+ " "+si);
        System.out.println("Raiz= "+st[0]);
        System.out.println("   st["+si+"] "+st[si]);*/
        
        return st[si];
    }
   
    static int[][] constructorST(int arr[], int n) 
    {

        int x = (int)(Math.log(n) / Math.log(2))+1; //ENTREGA EL VALOR X PARA ELEVAR EL 2, PARA HACER EL TAMAÑO MÁXIMO DEL ARBOL
        int maxnodes = 2 * (int) Math.pow(2, x) - 1;  //ENTREGA LA CANTIDAD DE NODOS QUE TENDRÁ EL ÁRBOL COMO MÁXIMO, VA CAMBIANDO SEGUN X
        int[][] st = new int[maxnodes][2];
        STInsert(arr, 0, n - 1, st, 0);
        return st;
    }
  
    public static void main(String[] args)  
    { 
        Scanner a = new Scanner(System.in);
        
        int n = a.nextInt();
        int[] arr = new int[n];
        int q=a.nextInt();
        for(int i=0;i<n;i++){
            arr[i]=a.nextInt();
            
        }
        int[][] st = constructorST(arr, n); 
        int[] res=new int[q];
        for(int i=0;i<q;i++){
            res[i]=getMax(st, n, a.nextInt(), a.nextInt()); 
            
        }
        for(int i=0;i<q;i++){
            System.out.print(res[i]+" ");
            
        }
    } 
} 