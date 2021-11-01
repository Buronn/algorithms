/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios;

import java.util.*;

/**
 *
 * @author Fernando
 */
public class ejercicio3 {

    public static void god(LinkedList<Integer> a, int actual, int next, LinkedList views) {
   
        
            if (actual < a.size()) {
                boolean l = false;
                if (actual + 1 == next) {
                    views.add(a.get(next));
                    l = true;
                }
                if (a.get(actual) + 1 >= a.get(next)) {

                    if (!l) {
                        views.add(a.get(actual));
                    }
                    if (a.get(actual) + 1 == a.get(next) && !l) {
                      
                        god(a, actual + 1, actual + 2, views);
                    } else {
                       
                        if(next+1<a.size())
                        god(a, actual, next + 1, views);

                    }
                } else {
                    if (l) {
                        if (next + 1 < a.size()) {
                            god(a, actual + 1, next + 1, views);
                        }
                    }
                    if (next + 1 == a.size()) {
                        god(a, actual + 1, next, views);
                    }
                }
            }
        
    }

    public static void main(String[] args) {
        int n;
        Scanner a = new Scanner(System.in);
        n = a.nextInt();
        LinkedList<Integer> l = new LinkedList<>();
        LinkedList<Integer> views = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            l.add(a.nextInt());
        }
        god(l, 0, 1, views);
        System.out.println(views.size());
    }

}
