package tarea9;

import java.util.Scanner;
import java.util.*;

public class problema1 {

    static void God(Hashtable<Integer, LinkedList> graph, int nivel, int actualKey, LinkedList<Integer> res) {
        if (graph.containsKey(actualKey)) {
            if (graph.get(actualKey).contains(nivel)) {
                res.add(1);
            }
            Iterator<Integer> valor = graph.get(actualKey).iterator();
            while (valor.hasNext()) {
                God(graph, nivel, valor.next(), res);
            }
        }
    }

    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        int n = a.nextInt();
        int m = a.nextInt();
        Hashtable<Integer, LinkedList> p = new Hashtable<>();
        for (int i = 0; i < m; i++) {
            int nodo = a.nextInt();
            int destino = a.nextInt();
            LinkedList<Integer> arista = new LinkedList<>();
            if (!p.containsKey(nodo)) {
                arista.add(destino);
                p.put(nodo, arista);
            } else {
                arista = p.get(nodo);
                arista.add(destino);
                p.replace(nodo, arista);
            }
        }
        LinkedList<Integer> vacio = new LinkedList();
        God(p, n, 1, vacio);
        System.out.println(vacio.size());
    }
}

/*static void Print(Hashtable<Integer,LinkedList> graph){
        Enumeration<Integer> gKeys=graph.keys();
        while(gKeys.hasMoreElements()){
            int key=gKeys.nextElement();
            LinkedList<Integer> aux=graph.get(key);
            Iterator<Integer> list=aux.iterator();
            System.out.println("Key: "+key);
            while(list.hasNext()){
                
                System.out.print(list.next()+" ");
            }
            System.out.println("");
        }
    }*/
