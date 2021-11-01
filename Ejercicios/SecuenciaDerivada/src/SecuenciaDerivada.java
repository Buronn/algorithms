
import java.util.*;

public class SecuenciaDerivada {

    public int[] seqDer(int[] a, int n) {
        int[] res;

        if (n == 0) {
            res = a;
        } else {
            int[] a2 = new int[a.length - 1];
            for (int pos = 0; pos < a.length - 1; pos++) {
                a2[pos] = a[pos + 1] - a[pos];
            }
            res = seqDer(a2, n - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Random rnd = new Random();

        List<int[]> entradas1 = new ArrayList<>();
        List<Integer> entradas2 = new ArrayList<>();
        List<int[]> salidas = new ArrayList<>();

        entradas1.add(new int[]{5, 6, 3, 9, -1});
        entradas2.add(1);
        salidas.add(new int[]{1, -3, 6, -10});

        entradas1.add(new int[]{5, 6, 3, 9, -1});
        entradas2.add(2);
        salidas.add(new int[]{-4, 9, -16});

        entradas1.add(new int[]{5, 6, 3, 9, -1});
        entradas2.add(4);
        salidas.add(new int[]{-38});

        entradas1.add(new int[]{4, 4, 4, 4, 4, 4, 4, 4});
        entradas2.add(3);
        salidas.add(new int[]{0, 0, 0, 0, 0});

        entradas1.add(new int[]{-100, 100});
        entradas2.add(0);
        salidas.add(new int[]{-100, 100});

        SecuenciaDerivada tester = new SecuenciaDerivada();

        for (int testNo = 0; testNo < entradas1.size(); testNo++) {

            System.out.println("Test no " + Integer.toString(testNo));
            int[] res = tester.seqDer(entradas1.get(testNo),
                    entradas2.get(testNo));
            System.out.println("Obtenido: " + Arrays.toString(res));
            System.out.println("Esperado: " + Arrays.toString(salidas.get(testNo)));
            if (Arrays.equals(res, salidas.get(testNo))) {
                System.out.println("Aprobado");
            } else {
                System.out.println("Reprobado");
            }
        }

        long startTime;
        long endTime;
        double duration;

        int[] entradaGrande1 = rnd.ints(20, -100, 101).toArray();
        int entradaGrande2 = 19;
        startTime = System.nanoTime();
        tester.seqDer(entradaGrande1, entradaGrande2);
        endTime = System.nanoTime();

        duration = (endTime - startTime) / 1000000.0;
        System.out.println("La entrada grande tomó " + duration + " milisegundos.");
    }
}
