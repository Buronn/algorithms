/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solemne;

/**
 *
 * @author Fernando
 */
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;
public class b1 {

    static String puntoEquilibrio(List<Integer> a) {
        
        int sumaTotal = 0;
        int aux=0;
        int actual=a.get(aux);
            for (Integer a1 : a) {
                sumaTotal += actual;
                aux=aux+1;
                actual = a.get(aux);
            }
        int sumaActual = 0, sumaRestante = 0;
        aux=0;
        actual = aux;

        for (Integer a1 : a) {
            sumaRestante = sumaTotal - (sumaActual + actual);

            if(sumaActual == sumaRestante)
                return "yes";
            aux=aux+1;
            actual = a.get(aux);
        }
        return "no";
        
    
    
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(bufferedReader.readLine().trim());
        for(int i = 0; i < T; i++) {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());
         
                String result = puntoEquilibrio(arr);
         
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
