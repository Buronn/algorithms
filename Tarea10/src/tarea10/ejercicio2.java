/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea10;

import java.util.*;

/**
 *
 * @author Fernando
 */
public class ejercicio2 {

    public static int get(char c) {

        if (Character.isLowerCase(c)) {
            return c - 'a' + 26;
        } else {

            return c - 'A' + 0;
        }
    }

    public static void print(int mat[][]) {
        // Loop through all rows 
        for (int i = 0; i < mat.length; i++) // Loop through all elements of current row 
        {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
    }

    static int MinimumEffort(String[] reino, String[] ayudar, String[] combatir) {

        Hashtable<Integer, LinkedList<Integer>> h = new Hashtable<>();
        int p = 0;
        for (int i = 0; i < reino.length; i++) {

            for (int j = i; j < combatir.length; j++) {

                if (j != i) {
                    LinkedList<Integer> auxList = new LinkedList<>();
                    auxList.add(i);
                    auxList.add(j);

                    if ((reino[i].charAt(j)) + 0 == '1') {

                        auxList.add(0);
                        auxList.add(get(combatir[i].charAt(j)));
                        h.put(p, auxList);

                    } else {
                        auxList.add(get(ayudar[i].charAt(j)));
                        auxList.add(0);
                        h.put(p, auxList);
                    }
                    p++;
                }

            }
        }
        int lastpos = 0;
        LinkedList<Integer> aux = new LinkedList<>();
  
        Hashtable<Integer, LinkedList<Integer>> a = new Hashtable<>();
        for (int i = 0; i < h.size(); i++) {
            System.out.println(h.get(i));
            int x = h.get(i).get(0);
            int y = h.get(i).get(1);
            int ayuda = h.get(i).get(2);
            int combate = h.get(i).get(3);
            boolean paso = a.containsKey(lastpos);

            if (ayuda == 0) {
              
                if (paso) {
                    if (a.get(lastpos).contains(x)) {
                        System.out.println(x + " " + y + " " + lastpos);
                        aux = a.get(lastpos);
                        aux.add(y);
                        a.replace(lastpos, aux);
                        aux = new LinkedList<>();
                    }
                } else {
                    if (a.containsKey(x)) {
                        aux = a.get(x);
                        aux.add(y);
                        a.replace(x, aux);
                        aux = new LinkedList<>();
                    } else {
                        aux.add(y);
                        a.put(x, aux);
                        aux = new LinkedList<>();
                    }

                }
            }

            lastpos = x;
        }
        
        System.out.println(a.entrySet());
        return 0;
    }

    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        String linea = a.nextLine();
        String[] reino = new String[linea.length()];
        String[] ayudar = new String[linea.length()];
        String[] combatir = new String[linea.length()];
        reino[0] = linea;
        for (int i = 0; i < combatir.length; i++) {
            System.out.print(linea.charAt(i) + " ");

        }
        System.out.println("");
        for (int i = 1; i < reino.length; i++) {
            reino[i] = a.nextLine();
            for (int j = 0; j < combatir.length; j++) {
                System.out.print(reino[i].charAt(j) + " ");

            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < reino.length; i++) {
            ayudar[i] = a.nextLine();
            for (int j = 0; j < reino[i].length(); j++) {
                System.out.print(get(ayudar[i].charAt(j)) + " ");
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < reino.length; i++) {
            combatir[i] = a.nextLine();
            for (int j = 0; j < combatir[i].length(); j++) {
                System.out.print(get(combatir[i].charAt(j)) + " ");
            }
            System.out.println("");

        }

        MinimumEffort(reino, ayudar, combatir);

    }
}
