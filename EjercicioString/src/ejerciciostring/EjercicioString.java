/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciostring;

import java.text.Collator;
import java.util.*;

/**
 *
 * @author Fernando
 */
public class EjercicioString {

    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        int t = a.nextInt();
        Hashtable<String, String> c = new Hashtable<>();

        List<String> numeros = new ArrayList<>();
        List<String> letras = new ArrayList<>();
        a.nextLine();
        for (int i = 0; i < t; i++) {
            String[] sp = a.nextLine().split(" ");
            boolean num = Character.isDigit(sp[1].charAt(0));
            String aux = "";
            for (int j = 0; j < sp.length - 1; j++) {
                aux = aux + sp[j + 1];
                if (j != sp.length - 2) {
                    aux = aux + " ";
                }
            }

            if (num) {
                c.put(aux, sp[0]);
                numeros.add(aux);
            } else {
                c.put(aux, sp[0]);
                letras.add(aux);
            }

        }
        Collections.sort(letras, Collator.getInstance());
        Iterator<String> i1 = letras.iterator();
        while (i1.hasNext()) {
            String actualKey = i1.next();
            System.out.println(c.get(actualKey) + " " + actualKey);
        }

        Iterator<String> i2 = numeros.iterator();
        while (i2.hasNext()) {
            String actualKey = i2.next();
            System.out.println(c.get(actualKey) + " " + actualKey);
        }

    }

}
