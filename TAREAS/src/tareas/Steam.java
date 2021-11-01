
package tareas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class Steam {
    public static void main(String[] args){
        Steam entrada=new Steam();
        System.out.println(entrada.exchange(10,1,5,10,50));
        System.out.println(entrada.exchange(15,1,5,10,50));
        System.out.println(entrada.exchange(15,1,5,10,50));
        System.out.println(entrada.exchange(66,1,5,10,50));
        System.out.println(entrada.exchange(1000,999,998,997,996));
        System.out.println(entrada.exchange(20,5,5,5,5));
        System.out.println(entrada.exchange(2,1,5,10,50));
    }
    public boolean exchange(int value, int item1, int item2, int item3, int item4){
        List<Integer> L=new ArrayList();
        L.add(item1);
        L.add(item2);
        L.add(item3);
        L.add(item4);
        Collections.sort(L);
        return resolver(value,L);
    }
    public boolean resolver(int value, List<Integer> list){
        List<Integer> listaux=new ArrayList();
        listaux=list;
        if(listaux.size()>0){
            int i=listaux.size()-1;
            if(listaux.get(i)>value){
                list.remove(i);
                return resolver(value,list);
            }
            else if(listaux.get(i)==value){
                return true;
            }
            else if(listaux.get(i)<value&&listaux.size()>1){
                int aux=listaux.get(i)+listaux.get(i-1);
                if(aux>value){
                   listaux.remove(i-1);
                   return resolver(value,listaux);
                }
                else if(aux<value){
                    listaux.set(i-1,aux);
                    listaux.remove(i);
                    return resolver(value,listaux);
                }
                else{
                return true;
                }
            }
            if(listaux.size()==1){
                if(listaux.get(i)<value){
                    list.remove(list.size()-1);
                    return resolver(value,list);
                }
            }
        }
        else if(list.size()<=0){
            return false;
        }
        return true;
    }
}