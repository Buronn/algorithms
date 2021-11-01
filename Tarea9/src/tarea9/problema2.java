package tarea9;
import java.util.*;
public class problema2 {
    static void god(Hashtable<Integer,LinkedList<Integer>> caminos,
                    Hashtable<Integer,Integer> times,int actualKey,
                    int[] ToTime,LinkedList<Integer> recorridos,
                    LinkedList<Integer> ciclo){
        
        // CAMINOS SON MIS DESTINOS CON REQUISITOS
        // TIMES SON LOS TIEMPOS RESPECTIVOS A CADA FILA(key)
        // ACTUAL KEY ES EN LA FILA EN LA QUE ME ENCUENTRO
        // TOTIME ES UN ARREGLO QUE CONTIENE EL TIEMPO DE CADA KEY, QUE SE VA MODIFICANDO SEGUN SUS REQUISITOS
        // RECORRIDOS ES UNA LISTA QUE CONTIENE CADA ACTUAL KEY, LA UTILIZO PARA VER SI SE FORMA UN CICLO EN ALGÚN MOMENTO
        
        if(!caminos.isEmpty()){
            if(actualKey==-99){  
                Enumeration<Integer> anyKey=caminos.keys();    
                actualKey=anyKey.nextElement(); 
            }
            if(recorridos.contains(actualKey)){
                ciclo.add(actualKey); // AÑADE LA KEY DONDE HAY CICLO
            }
            
            if(ciclo.size()<times.size()){ //si recorre las mismas keys más de n veces, entonces es ciclo sí o sí
                recorridos.add(actualKey);
                Iterator<Integer> actualFrom=caminos.get(actualKey).iterator();
                LinkedList<Integer> tmp=new LinkedList<>();
                while(actualFrom.hasNext() ){
                    int from=actualFrom.next();
                    if(caminos.containsKey(from) ){
                        
                        god(caminos,times,from,ToTime,recorridos,ciclo);
                        tmp.add(ToTime[from]);
                    }
                    else tmp.add(times.get(from));
                }
                Iterator<Integer> r=tmp.iterator();
                int maxT=0;
                while(r.hasNext()){
                    int l=r.next();
                    if(l>maxT){
                        maxT=l;
                    }
                }
                ToTime[actualKey]=times.get(actualKey)+maxT; 
            }
        }
        else{          
            Enumeration<Integer> x=times.keys();            
            while(x.hasMoreElements()){                
                int key=x.nextElement();                
                ToTime[key]=times.get(key);            
            }
        }
    }
    public static void main(String[] args) {
        Scanner a=new Scanner(System.in);
        int n=a.nextInt(); 
        Hashtable<Integer,Integer> tiempos=new Hashtable<>();
        Hashtable<Integer,LinkedList<Integer>> caminos=new Hashtable<>();
        for(int i=0;i<n;i++)tiempos.put(i,a.nextInt());
        a.nextLine(); //SE COME LA LINEA EN BLANCO
        for(int i=0;i<n;i++){
            String line=a.nextLine();
            
            for(int j=0;j<line.length();j++){
                
                LinkedList<Integer> aux=new LinkedList<>();
                if(line.charAt(j)=='Y'){
                    if(caminos.containsKey(j)){
                    aux=caminos.get(j);
                    aux.add(i);
                    
                    caminos.replace(j, aux);
                    }
                    else{
                    aux.add(i); 
                    caminos.put(j,aux);
                    }
                    
                }
            }
        }
        int[] r=new int[n];
        LinkedList<Integer> recorridos=new LinkedList<>();
        LinkedList<Integer> ciclo=new LinkedList<>();
        if(caminos.size()<n){ //SI TODOS REQUIEREN DE UN ANTERIOR, ENTONCES ES CICLO SI O SI
            god(caminos,tiempos,-99,r,recorridos,ciclo); 
            int fin=0;
            if(ciclo.size()<n){
            for(int i=0;i<r.length;i++){
                if(r[i]>fin){
                    fin=r[i];
                }
            }
            System.out.println(fin);
            }
            else System.out.println(-1);
        }
        else System.out.println(-1);
        
    }
}
