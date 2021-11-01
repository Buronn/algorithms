
package wea;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;



public class Wea {

        
        
        
        
        
        
        
        public static void main(String[] args) {
        //creamos la tabla de hash
       Hashtable<String, String> h1 = new Hashtable<>();
       Hashtable<String, String> h2 = new Hashtable<>();
       Hashtable<String, String> h3 = new Hashtable<>(); 
       Hashtable<String, ArrayList> h4 = new Hashtable<>();
        BufferedReader reader;
        BufferedReader reader2;
        BufferedReader reader3;
        BufferedReader reader4;
        AVLTREE tree_province = new AVLTREE();
        AVLTREE tree_country = new AVLTREE();
        AVLTREE tree_continent = new AVLTREE();
        //----------------------------------------------------------------------//
        try
        {
            reader = new BufferedReader(new FileReader("F:\\Descargas\\Countries-Continents.csv"));
            reader2 = new BufferedReader(new FileReader("F:\\Descargas\\COVID19_open_line_list.csv"));
            reader3 = new BufferedReader(new FileReader("F:\\Descargas\\COVID19_open_line_list.csv"));
            reader4 = new BufferedReader(new FileReader("F:\\Descargas\\COVID19_open_line_list.csv"));
           String line = reader.readLine();         
           String line2 = reader2.readLine();
           String line3 = reader3.readLine();
           String line4 = reader4.readLine();
           System.out.println("---------------Continente & PaÃ­s---------------");
            while (line != null)
            {
                String[] stack1;
                stack1 = line.split(",");
                h1.put(stack1[1], stack1[0]);                
                line=reader.readLine();    
                   for(int i = 1; i<stack1.length; i++)
                {
                    tree_continent.root = tree_continent.insert(tree_continent.root, i);                   
                }
            } 
            reader.close();
            Enumeration<String> e1=h1.elements();
            Enumeration<String> ek1 = h1.keys(); 
            int cont=0;
            while(e1.hasMoreElements())
            {
               
                System.out.println("<"+e1.nextElement()+", "+ek1.nextElement()+">");
                cont++;
            }

            System.out.println(cont);
            //----------------------------------------------------------------------//
            System.out.println("---------------PaÃ­s & Provincia---------------");
            while(line2 != null){
                String[] stack2;
                stack2 = line2.split(",",-1);
                h2.put(stack2[4],stack2[5]);
                line2=reader2.readLine();
                for(int i = 1; i<stack2.length; i++)
                {
                    tree_country.root = tree_country.insert(tree_country.root, i);                   
                }
                                  
            }
            reader2.close();
            Enumeration<String> e2 = h2.elements();
            Enumeration<String> e2K = h2.keys(); 
            int cont2 =0;
          
               while(line4!=null)   
                {              
                String[] stack4;
                stack4 = line4.split(",",-1);
                h4.put(stack4[4], a.add(a));
                
                line4=reader4.readLine();
               
               
               
               
            while(e2.hasMoreElements())
            {
                System.out.println("<"+e2.nextElement()+", "+e2K.nextElement()+">");
                cont2++;
            }*/
            //----------------------------------------------------------------------------------//
            System.out.println("Cantidad de elementos totales: "+cont2); // cantidad de elementos
            System.out.println("---------------Provincia & Ciudad---------------");
            while(line3 != null){
                String[] stack3;
                stack3 = line3.split(",",-1);
                h3.put(stack3[4],stack3[3]);                
                line3=reader3.readLine();
                 for(int i = 1; i<16; i++)
                {
                    tree_province.root = tree_province.insert(tree_province.root, i);                   
                }
            }
            reader2.close();
            Enumeration<String> e3 = h3.elements();
            Enumeration<String> e3K = h3.keys(); 
            int cont3 =0;
            while(e3.hasMoreElements())
            {
                System.out.println("<"+e3.nextElement()+", "+e3K.nextElement()+">");
                cont3++;
            }
            
            System.out.println("Cantidad de elementos totales: "+cont3); // cantidad de elementos
        }
        
    catch(IOException e) 
        {
            e.printStackTrace();
        }
        System.out.println("---------------Ãrbol Provincia Anhui---------------");
        tree_province.preOrder(tree_province.root);
        System.out.println();
        System.out.println("---------------Ãrbol PaÃ­s---------------");
        tree_country.preOrder(tree_country.root);
        System.out.println();
         System.out.println("---------------Ãrbol Continente---------------");
        tree_continent.preOrder(tree_continent.root);
        System.out.println();
        }

}
    

