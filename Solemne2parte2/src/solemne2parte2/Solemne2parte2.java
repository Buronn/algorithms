/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solemne2parte2;

import java.text.Collator;
import java.util.*;

/**
 *
 * @author Fernando
 */
public class Solemne2parte2 {

    static void Dios(Hashtable<Character, Character> l, String line, Hashtable<String, String> dios, Hashtable<String,Integer> p) {

        String tr = "";
        for (int i = 0; i < line.length(); i++) {                 //a
            char actual = l.get(line.charAt(i));
            tr = tr + actual;

        }
        if(dios.containsKey(tr)){
            if(p.contains(tr)){
               
                p.replace(tr, p.get(tr)+1);
            }
            else{
            p.put(tr, 2);
            }
            
        }
        dios.put(tr, line);
        
        
        
    }

    static void Ordenar(Hashtable<String, String> l,Hashtable<String,Integer> p) {
        Enumeration<String> k = l.keys();
        LinkedList<String> a = new LinkedList<>();
        while (k.hasMoreElements()) {
            String actual = k.nextElement();
            a.add(actual);
        }
        Collections.sort(a, Collator.getInstance());
        for (int i = 0; i < a.size(); i++) {
            
            if(p.containsKey(a.get(i))){
                
                for(int j=0; j<p.get(a.get(i)) ; j++){
                    System.out.println(l.get(a.get(i)));
                }
            }
            else{
            System.out.println(l.get(a.get(i)));
            }
        }
    }

    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        int T = a.nextInt();

      
        char[] abc = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
       
        for (int i = 0; i < T; i++) {
            Hashtable<String,String> output=new Hashtable<>();
            Hashtable<String,Integer> p=new Hashtable<>();
            String L = a.next();
            char[] line = L.toCharArray();
            Hashtable<Character, Character> l = new Hashtable<>();
            for (int j = 0; j < abc.length; j++) {

                l.put(line[j], abc[j]);
            }

            int E = a.nextInt();
            a.nextLine();

            for (int j = 0; j < E; j++) {
                String actual = a.next();

                Dios(l, actual, output,p);
            }
            Ordenar(output,p);
        }
        

    }

}
