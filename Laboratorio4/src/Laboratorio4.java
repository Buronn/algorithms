
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Laboratorio4 {

    static void Print(Hashtable<String, Hashtable<String, Integer>> g, LinkedList<String> nombres) {
        Enumeration<String> gKeys = g.keys();
        while (gKeys.hasMoreElements()) {
            String actualKey = gKeys.nextElement();
            if (!nombres.contains(actualKey)) {
                nombres.add(actualKey);
            }
            Enumeration<String> ggKeys = g.get(actualKey).keys();
            while (ggKeys.hasMoreElements()) {
                String aKey = ggKeys.nextElement();
                if (aKey.length() != 0) {
                    System.out.println(actualKey + " ←" + g.get(actualKey).get(aKey) + "→ " + aKey);
                }
                if (!nombres.contains(aKey)) {
                    nombres.add(aKey);
                }
            }
            System.out.println(" ");
        }
    }

    static void matrizgenerator(Node Arbol, Hashtable<String, Hashtable<String, Integer>> g) {
        Node actual = Arbol;
        Hashtable<String, Integer> aux = new Hashtable<>();
        if (actual.der != null) {
            if (actual.der.key > actual.key / 2) {
                aux.put(actual.der.nombre, peso(actual.key, actual.der.key));
                g.put(actual.nombre, aux);                                          //camino entre el nodo actual y el hijo der
                matrizgenerator(actual.der, g);
            } else {
                matrizgenerator(actual.der, g);
            }
        }
        if (actual.izq != null) {
            if (actual.izq.key > actual.key / 2) {
                aux.put(actual.izq.nombre, peso(actual.key, actual.izq.key));
                g.put(actual.nombre, aux);
                matrizgenerator(actual.izq, g);
            } else {
                matrizgenerator(actual.izq, g);
            }
        }
    }

    static int peso(int raiz, int hijo) {
        
        int p = 20000 - Math.abs(raiz - hijo);
        return p;
    }

    public static void main(String[] args) {
        //creamos la tabla de hash
        Hashtable<String, String> h1 = new Hashtable<>();
        Hashtable<String, String> h2 = new Hashtable<>();

        BufferedReader reader;
        BufferedReader reader2;
        BufferedReader reader3;
        BufferedReader reader4;
        BufferedReader readerCont;
        BufferedReader readerRepeat;
        AVLTREE tree_province = new AVLTREE();
        AVLTREE tree_continent = new AVLTREE();
        AVLTREE tree_country = new AVLTREE();
        //----------------------------------------------------------------------//
        try {
            reader = new BufferedReader(new FileReader("F:\\Descargas\\Countries-Continents.csv"));
            reader2 = new BufferedReader(new FileReader("F:\\Descargas\\COVID19_open_line_list.csv"));
            reader3 = new BufferedReader(new FileReader("F:\\Descargas\\COVID19_open_line_list.csv"));
            reader4 = new BufferedReader(new FileReader("F:\\Descargas\\COVID19_open_line_list.csv"));
            readerRepeat = new BufferedReader(new FileReader("F:\\Descargas\\COVID19_open_line_list.csv"));
            readerCont = new BufferedReader(new FileReader("F:\\Descargas\\Countries-Continents.csv"));
            String line = reader.readLine();
            String line2 = reader2.readLine();
            String line3 = reader3.readLine();
            String line4 = reader4.readLine();
            String line5 = readerRepeat.readLine();
            String line6 = readerCont.readLine();
            //----------------------------------------------------------------------//

            System.out.println("---------------Continente & País---------------");
            while (line != null) {
                String[] stack1;
                stack1 = line.split(",");
                h1.put(stack1[1], stack1[0]);
                line = reader.readLine();

            }
            reader.close();
            Enumeration<String> e1 = h1.elements();
            Enumeration<String> ek1 = h1.keys();
            int cont = 0;
            while (e1.hasMoreElements()) {

                System.out.println("<" + e1.nextElement() + ", " + ek1.nextElement() + ">");
                cont++;
            }
            System.out.println(cont);
            //----------------------------------------------------------------------//
            System.out.println("---------------País & Provincia---------------");

            while (line2 != null) {
                String[] stack2;
                stack2 = line2.split(",", -1);
                h2.put(stack2[4], stack2[5]);
                line2 = reader2.readLine();

            }
            reader2.close();
            Enumeration<String> e2 = h2.elements();
            Enumeration<String> e2K = h2.keys();

            while (e2.hasMoreElements()) {
                System.out.println("<" + e2.nextElement() + "," + e2K.nextElement() + ">");

            }
            //-----------------------------ARBOL PAIS (CHINA)-----------------------------------------//

            Hashtable<String, Integer> hc = new Hashtable<>();
            while (line4 != null) {
                String[] Stack4;
                Stack4 = line4.split(",", -1);
                if ("China".equals(Stack4[5])) {           //CAMBIAR PARA LAB 5
                    if (hc.containsKey(Stack4[4])) {
                        int aux = hc.get(Stack4[4]);
                        aux++;
                        hc.replace(Stack4[4], aux);
                    } else {
                        hc.put(Stack4[4], 1);
                    }
                }
                line4 = reader4.readLine();
            }
            Enumeration<String> hcKeys = hc.keys();

            while (hcKeys.hasMoreElements()) {
                String actualKey = hcKeys.nextElement();
                tree_country.root = tree_country.insert(tree_country.root, hc.get(actualKey), actualKey);
                //System.out.println(actualKey+" "+hc.get(actualKey));
            }

            reader4.close();

            //----------------------------------------------------------------------------------//
            Hashtable<String, Integer> ciudades = new Hashtable<>();

            while (line3 != null) {
                String[] stack3;
                stack3 = line3.split(",", -1);
                if ("Anhui".equals(stack3[4])) {           //CAMBIAR PARA LAB 5
                    if (ciudades.containsKey(stack3[3])) {
                        int aux = ciudades.get(stack3[3]);
                        aux++;
                        ciudades.replace(stack3[3], aux);
                    } else {
                        ciudades.put(stack3[3], 1);
                    }
                }
                line3 = reader3.readLine();
            }
//--------------------------ARBOL PROVINCIA (ANHUI)----------------------------//
            reader2.close();
            Enumeration<String> cKeys = ciudades.keys();

            while (cKeys.hasMoreElements()) {
                String actualKey = cKeys.nextElement();
                tree_province.root = tree_province.insert(tree_province.root, ciudades.get(actualKey), actualKey);
                //System.out.println(actualKey+" "+ciudades.get(actualKey));
            }

            //---------------------------Árbol Continente-------------------------------------------//
            Hashtable<String, Integer> hcont = new Hashtable<>();
            while (line6 != null) {
                String[] stack;
                stack = line6.split(",");
                if ("Asia".equals(stack[0])) {           //CAMBIAR PARA LAB 5
                    hcont.put(stack[1], -1);
                }
                line6 = readerCont.readLine();
            }
            readerCont.close();

            while (line5 != null) {

                String[] stack;
                stack = line5.split(",", -1);

                if (hcont.containsKey(stack[5])) {
                    int a = hcont.remove(stack[5]);

                    if (a == -1) {
                        a = 0;
                    }
                    a++;
                    hcont.put(stack[5], a);
                }
                line5 = readerRepeat.readLine();
            }

            readerRepeat.close();
            Enumeration<String> eC = hcont.keys();

            while (eC.hasMoreElements()) {
                String k = eC.nextElement();

                tree_continent.root = tree_continent.insert(tree_continent.root, hcont.get(k), k);
            }

        } catch (IOException e) {
        }
        System.out.println("---------------Árbol Provincia Anhui---------------");
        tree_province.preOrder(tree_province.root);
        System.out.println();
        System.out.println("---------------Árbol País China---------------");
        tree_country.preOrder(tree_country.root);
        System.out.println();
        System.out.println("---------------Árbol Continente Asiático---------------");
        tree_continent.preOrder(tree_continent.root);
        System.out.println();

        Node raiz = tree_province.root;  //ir cambiando a country/province/continent

        Hashtable<String, Hashtable<String, Integer>> caminos = new Hashtable<>();
        LinkedList<String> nombres = new LinkedList<>();
        matrizgenerator(raiz, caminos);
        Print(caminos, nombres);
        System.out.println(nombres);
        int[][] ma = new int[nombres.size()][nombres.size()];
        Enumeration<String> gKeys = caminos.keys();
        while (gKeys.hasMoreElements()) {
            String actualKey = gKeys.nextElement();
            Enumeration<String> ggKeys = caminos.get(actualKey).keys();
            int i = nombres.indexOf(actualKey);
            while (ggKeys.hasMoreElements()) {
                String aKey = ggKeys.nextElement();
                int j = nombres.indexOf(aKey);
                ma[i][j] = (int) caminos.get(actualKey).get(aKey);
                ma[j][i] = (int) caminos.get(actualKey).get(aKey);
            }

        }

        for (int[] ma1 : ma) {
            for (int j = 0; j < ma1.length; j++) {
                System.out.print(ma1[j] + " ");
            }
            System.out.println(" ");
        }

        //implementar weas a la matriz "ma"
    }

}
