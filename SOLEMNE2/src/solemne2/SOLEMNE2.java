/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solemne2;

import java.util.*;

/**
 *
 * @author Fernando
 */
public class SOLEMNE2 {

    static void god(Hashtable<Integer, Hashtable<Integer, Integer>> caminos,
            int res, int actual, LinkedList<Integer> top,
            int nodo) {
        if (actual == nodo) {
            top.add(res);

        } else if (caminos.containsKey(actual)) {
            Hashtable<Integer, Integer> aux = caminos.get(actual);
            Enumeration<Integer> key = aux.keys();
            while (key.hasMoreElements()) {

                int auxKey = key.nextElement();

                int peso = aux.get(auxKey) - res;
                if (peso < 0) {
                    peso = 0;
                } else {
                    res = res + peso;
                }
            
                god(caminos, res, auxKey, top, nodo);
                res = 0;
            }
        }
    }

    public static void main(String[] args) {

        Scanner a = new Scanner(System.in);
        int nodos = a.nextInt();
        Hashtable<Integer, Hashtable<Integer, Integer>> caminos = new Hashtable<>();

        int aristas = a.nextInt();
        for (int i = 0; i < aristas; i++) {
            int desde = a.nextInt();
            int hacia = a.nextInt();
            int peso = a.nextInt();
            Hashtable<Integer, Integer> aux = new Hashtable<>();
            if (caminos.containsKey(desde)) {
                aux = caminos.get(desde);
                aux.put(hacia, peso);

                caminos.replace(peso, aux);

            } else {
                aux.put(hacia, peso);
                caminos.put(desde, aux);
            }
            

        }
        int res = 0;
        LinkedList<Integer> resultados = new LinkedList<>();

        god(caminos, res, 1, resultados, nodos);
        if (resultados.isEmpty()) {
            System.out.println("NO PATH EXISTS");
        } else {
            Iterator<Integer> b = resultados.iterator();
            int menor = resultados.get(0);
            while (b.hasNext()) {
                int k = b.next();
                System.out.print(k + " ");
                if (menor > k) {
                    menor = k;
                }

            }
            System.out.println(" ");
            System.out.println(menor);
        }
        // TODO code application logic here
    }

}
