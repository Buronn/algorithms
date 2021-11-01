package tarea10;

import java.util.*;

/**
 *
 * @author Fernando
 */
public class ejercicio1 {

    static int[] FindPath(int[][] W, int u, int v) {

        Queue<Integer> queue = new LinkedList();
        int a;
        Way[] arr = new Way[W.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Way();
            if (i == u) {
                arr[i].setPuntero(0);
                arr[i].setPeso(0);
                arr[u].setVisited(true);
            }
        }
        queue.add(u);
        while (!queue.isEmpty()) {
            a = queue.poll();

            for (int i = 0; i < W.length; i++) {
                
                if (W[a][i] < 9999 && W[a][i] != 0) {
                    if (!arr[i].isVisited()) {
                        arr[i].setPeso(arr[i].getPeso()+ W[a][i]);
                        arr[i].setPuntero(a);
                        arr[i].setJumps(arr[a].getJumps() + 1);
                        arr[a].setVisited(true);
                        queue.add(i);

                    } else {
                        if (arr[i].getPeso() > arr[a].getPeso() + W[a][i]) {
                            arr[i].setPeso(arr[a].getPeso() + W[a][i]);
                            arr[i].setPuntero(a);
                            arr[i].setJumps(arr[a].getJumps() + 1);
                            queue.add(i);
                        } else if (arr[i].getPeso() == arr[a].getPeso() + W[a][i]) {
                            if (arr[i].getJumps() > arr[a].getJumps() + 1) {
                                arr[i].setJumps(arr[a].getJumps() + 1);
                                arr[i].setPuntero(a);
                            }
                        }
                    }
                }
            }

        }
        Stack<Integer> stack = new Stack();
        while (v != u) {

            stack.add(v);
            v = arr[v].getPuntero();
            
            stack.add(v);

        }
        while (!stack.empty()) {
            System.out.println(stack.pop() + " " + stack.pop());
        }
        int[] out = new int[1];
        return out;
    }

    static class Way {

        int peso, jumps, Puntero;

        boolean visited;

        public Way() {
            visited = false;
            peso = 0;
            jumps = 0;

        }

        public int getPuntero() {
            return Puntero;
        }

        public void setPuntero(int edge) {
            this.Puntero = edge;
        }

        public int getPeso() {
            return peso;
        }

        public void setPeso(int peso) {
            this.peso = peso;
        }

        public int getJumps() {
            return jumps;
        }

        public void setJumps(int jumps) {
            this.jumps = jumps;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

    }

    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        int G = a.nextInt();
        int[][] m = new int[G][G];
        for (int i = 0; i < G; i++) {

            for (int j = 0; j < G; j++) {

                String l = a.next();

                int actual;

                if (l.contains("Inf")) {
                    actual = 9999;
                } else {
                    actual = Integer.parseInt(l);
                }
                m[i][j] = actual;

                //System.out.print(m[i][j] + "   ");
            }
            //System.out.println(" \n");

        }

        int s = a.nextInt();
        int d = a.nextInt();
        FindPath(m, s, d);

    }

}
