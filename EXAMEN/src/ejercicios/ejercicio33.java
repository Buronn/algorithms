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
public class ejercicio33 {
      public static void god(LinkedList<Integer> a, int actual, int next, LinkedList views) {
   
        
            if(next < a.size() && actual<a.size()){
                
                if(a.get(actual)+1>=a.get(next)){
                    views.add(actual);
                    if(a.get(actual)+1 == a.get(next)){
                        god(a,actual,next,views);
                        
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
