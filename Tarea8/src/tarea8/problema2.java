/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea8;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

public class problema2 {

    static int[][] ERDIOS(String[][] linea, int[][] valores, LinkedList<Integer> l, Hashtable<String, Integer> nombres) {
        boolean existeErdios = false;
        int n = linea.length;
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < valores[i].length; k++) {
                if ("Erdos, P.".equals(linea[i][k])) {
                    existeErdios = true;
                }
                if (valores[i][k] == 0) {
                    for (int r = 0; r < valores[i].length; r++) {

                        if (valores[i][r] != 0) {
                            valores[i][r] = 1;
                            if (!nombres.containsKey(linea[i][r])) {
                                nombres.put(linea[i][r], valores[i][r]);
                                //System.out.println("Se añade a nombres "+linea[i][r]);
                            }
                        }
                    }
                }
            }
        }

        Enumeration<String> nsize = nombres.keys();
        LinkedList<String> sol = new LinkedList<>();
        if (!existeErdios) {
            for (int i = 0; i < n; i++) {
                for (String item : linea[i]) {
                    if (!sol.contains(item)) {

                        sol.add(item);
                    }
                }
            }
            return valores;
        }
        modif(linea, valores, nsize.nextElement(), l, nombres, nsize);
        return valores;
    }

    static void modif(String[][] linea, int[][] valores, String mod, LinkedList<Integer> l, Hashtable<String, Integer> nombres, Enumeration<String> nsize) {
        //----------------------------------------------------------------
        boolean[] contieneCambio = new boolean[linea.length];
        for (int i = 0; i < linea.length; i++) {
            contieneCambio[i] = false;
        }
        int n = linea.length;
        for (int i = 0; i < n; i++) {
            for (String item : linea[i]) {
                if (mod.equals(item)) {
                    //System.out.println("contiene cambio por "+item);
                    contieneCambio[i] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < linea[i].length; k++) {

                if (!l.contains(i) && contieneCambio[i]) { //SI NO ES VECINO DIRECTO DE ERDOS
                    if (nombres.containsKey(linea[i][k])) {
                        valores[i][k] = nombres.get(linea[i][k]);
                        int aux = valores[i][k];
                        for (int ñ = 0; ñ < linea[i].length; ñ++) {
                            if (!nombres.containsKey(linea[i][ñ])) {
                                if (aux > valores[i][ñ]) {
                                    if (nombres.get(linea[i][k]) + 1 < aux) {
                                        nombres.put(linea[i][ñ], nombres.get(linea[i][k]));
                                    } else {
                                        nombres.put(linea[i][ñ], nombres.get(linea[i][k]) + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        if (nsize.hasMoreElements()) {
            modif(linea, valores, nsize.nextElement(), l, nombres, nsize);
        }
    }

    public static void main(String[] args) {

        int n, m;
        Scanner a = new Scanner(System.in);
        n = a.nextInt();
        m = a.nextInt();
        String[] splited; //spliteado
        String[][] linea = new String[n][]; //linea n
        String y = a.nextLine(); //linea en blanco que se come
        int[][] valores = new int[n][];
        for (int i = 0; i < n; i++) {

            splited = a.nextLine().split(":");
            String[] separador = splited[0].split(", ");
            int tamaño = separador.length / 2;
            String[] aux = new String[tamaño];
            int[] aux2 = new int[tamaño];

            for (int j = 0; j < separador.length; j++) {
                if (separador[j].contains(".")) {
                    separador[j - 1] = separador[j - 1] + ", " + separador[j];
                    aux[(j - 1) / 2] = separador[j - 1];
                }
            }
            valores[i] = aux2;
            linea[i] = aux;

        }
        String[] busqueda = new String[m];
        for (int i = 0; i < m; i++) {
            busqueda[i] = a.nextLine();
        }
        LinkedList<Integer> l = new LinkedList();
        Hashtable<String, Integer> nombres = new Hashtable();

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < linea[i].length; j++) {
                if (!"Erdos, P.".equals(linea[i][j])) {
                    valores[i][j] = -1;
                } else {
                    valores[i][j] = 0;
                    l.add(i);
                }
            }
        }
        ERDIOS(linea, valores, l, nombres);
        String respuesta = "";
        for (int i = 0; i < busqueda.length; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < linea[j].length; k++) {
                    if (busqueda[i].equals(linea[j][k])) {
                        busqueda[i] = "xd";
                        if (valores[j][k] != -1) {
                            respuesta = respuesta + linea[j][k] + " " + valores[j][k] + "\n";
                        } else {
                            respuesta = respuesta + linea[j][k] + " " + "infinity" + "\n";
                        }
                    }
                }
            }
        }
        System.out.println(respuesta);
    }
}
