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
public class ejercicio2 {
    public static int f(int[] v,int[] p,int n){
        int a=0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(v[i] > v[j] && p[i] < p[j]){
                    a++;
                }
            }
        }
        return a;
    }
    public static void main(String[] args) {
        int n;
        
        Scanner a = new Scanner(System.in);
        n = a.nextInt();
        int[] v = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < v.length; i++) {
            v[i] = a.nextInt();
        }
        for (int i = 0; i < p.length; i++) {
            p[i] = a.nextInt();
        }
        System.out.println(f(v,p,n));
        
    }
    
}
