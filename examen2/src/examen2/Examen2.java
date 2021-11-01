/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2;

import java.util.*;

public class Examen2 {

    public static int r(Hashtable<Integer, Integer> preg, int num) {
        int res = 0;
        int[] aux = new int[preg.size()];

        int l = 0;
        Enumeration<Integer> key = preg.keys();
        while (key.hasMoreElements()) {
            Integer nextElement = key.nextElement();
            aux[l] = nextElement;
            l++;
        }
        Arrays.sort(aux);
        

        for (int i = aux.length-1; i>(aux.length-1)-num; i--) {
            res = res + preg.get(aux[i]);
        }

        return res;
    }

    public static void main(String[] args) {

        Scanner a = new Scanner(System.in);
        int n = a.nextInt();
        int m = a.nextInt();
        Hashtable<Integer, Integer> preg = new Hashtable<>();
        int[] t = new int[n];
        int[] p = new int[n];
        for (int j = 0; j < n; j++) {
            t[j] = a.nextInt();
        }
        for (int j = 0; j < n; j++) {
            p[j] = a.nextInt();
        }
        for (int i = 0; i < n; i++) {
            preg.put(p[i], t[i]);
        }
        for (int i = 0; i < m; i++) {
            System.out.println(r(preg, a.nextInt()));
        }

    }

}
