/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.*;
import java.util.*;

/**
 *
 * @author Fernando
 */

public class lector {
     
    public static double get(char c) {
       
      return Integer.parseInt("" + c);
        
    }
   
    
    public static void main(String[] args) throws IOException, InterruptedException {
        File archivo1 = new File("F:\\U\\2020\\Proba\\dataSet10.csv");
        BufferedReader br1 = new BufferedReader(new java.io.FileReader(archivo1));
        String line;

        
        LinkedList<Double> A = new LinkedList<>();
        while ((line = br1.readLine()) != null) {
            
            System.out.println(line);

        }
        System.out.println((double)A.size()/10000);
    }
}
