/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios;

import java.util.*;

public class ejercicio1 {

    public static void god(Hashtable<Integer, Integer> portales, int key, LinkedList<Integer> saltos) {

        int[] v = new int[6];
        int[] riesgo = new int[6];
        boolean entra = false;
        for (int i = 0; i < v.length; i++) {
            v[i] = (i + 1) + key;

            if (v[i] <= 100) {
                if (v[i] == 100) {
                    saltos.add(v[i]);
                    entra = true;

                }
                if (portales.containsKey(v[i])) {
                    if (v[i] < portales.get(v[i])) {
                        entra = true;
                        saltos.add(v[i]);
                        god(portales, portales.get(v[i]), saltos);

                    } else {
                        riesgo[i] = i + 1;
                    }
                }
            }
        }
        if (!entra) {
            for (int i = 5; i >= 0; i--) {
                if (riesgo[i] != v[i]) {
                    saltos.add(v[i]);
                    god(portales, v[i], saltos);
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        int t, n1, n2;

        Scanner a = new Scanner(System.in);
        t = a.nextInt();

        for (int i = 0; i < t; i++) {
            n1 = a.nextInt();
            LinkedList<Integer> tiros = new LinkedList<>();
            Hashtable<Integer, Integer> portales = new Hashtable<>();
            for (int j = 0; j < n1; j++) {
                portales.put(a.nextInt(), a.nextInt());

            }
            n2 = a.nextInt();
            for (int j = 0; j < n2; j++) {
                portales.put(a.nextInt(), a.nextInt());

            }
            god(portales, 1, tiros);
            LinkedList<Integer> l = new LinkedList<>();
            int tamaño = Integer.MAX_VALUE;
            for (int j = 0; j < tiros.size(); j++) {
                int aux = tiros.get(j);

                l.add(aux);
                if (aux == 100) {
                    if (tamaño > l.size()) {
                        tamaño = l.size();
                    }
                    l.clear();
                }
            }
            
            System.out.println(tamaño);
        }

    }
}
