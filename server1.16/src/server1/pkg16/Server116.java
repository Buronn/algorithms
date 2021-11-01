/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server1.pkg16;

import java.io.*;
import java.util.*;

public class Server116 {

    static void setHome(String nombre, String destino, String x, String y, String z, BufferedWriter writer2) throws IOException {
        if (!existen2(nombre, destino, false) && !existOnFile(nombre, destino, false)) {

            String output = nombre + " " + destino + " " + x + " " + y + " " + z;
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"El lugar\",\"color\":\"gold\"},"
                    + "{\"text\":\" \\\"" + destino + "\\\"\",\"color\":\"aqua\"},"
                    + "{\"text\":\" se ha guardado correctamente\",\"color\":\"gold\"},{\"text\":\".\"}]";
            try {
                File file = new File("C:\\Users\\Fernando\\Desktop\\tps\\tps.txt");
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"))) {
                    writer.write(output);
                    writer.write("\n");
                }
            } catch (IOException e) {
            }
            String[] o = new String[]{aviso};
            Ejecutar(o, writer2);
        } else {
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"No puedes añadir más destinos, elimina o modifica uno. \",\"color\":\"gold\"},"
                    + "{\"text\":\"<!mod , !del>\",\"italic\":true,\"color\":\"gray\"}]";
            String[] o = new String[]{aviso};
            Ejecutar(o, writer2);
        }
    }

    static boolean existen2(String nombre, String destino, boolean nether) throws IOException {
        if (!nether) {
            BufferedReader tps;
            tps = new BufferedReader(new FileReader("C:\\Users\\Fernando\\Desktop\\tps\\tps.txt"));
            LinkedList<String> destinos = new LinkedList<>();
            boolean existe = false;
            while (true) {
                String currentLine = tps.readLine();
                if (currentLine == null) {
                    break;
                }
                if (currentLine.contains(nombre)) {
                    String[] sp = currentLine.split(" ");
                    destinos.add(sp[1]);
                }

            }
            tps.close();
            if (destinos.size() >= 3) {
                existe = true;
            }
            return existe;
        } else {
            BufferedReader tps;
            tps = new BufferedReader(new FileReader("C:\\Users\\Fernando\\Desktop\\tps\\nether.txt"));
            LinkedList<String> destinos = new LinkedList<>();
            boolean existe = false;
            while (true) {
                String currentLine = tps.readLine();
                if (currentLine == null) {
                    break;
                }
                if (currentLine.contains(nombre)) {
                    String[] sp = currentLine.split(" ");
                    destinos.add(sp[1]);
                }

            }
            tps.close();
            if (destinos.size() >= 2) {
                existe = true;
            }
            return existe;

        }
    }

    static boolean existOnFile(String nombre, String destino, boolean nether) throws IOException {
        if (!nether) {

            BufferedReader tps;
            tps = new BufferedReader(new FileReader("C:\\Users\\Fernando\\Desktop\\tps\\tps.txt"));

            boolean existe = false;
            while (true) {
                String currentLine = tps.readLine();
                if (currentLine == null) {
                    break;
                }
                if (currentLine.contains(nombre + " " + destino + " ")) {
                    existe = true;
                    break;
                }

            }
            tps.close();

            return existe;
        } else {
            BufferedReader tps;
            tps = new BufferedReader(new FileReader("C:\\Users\\Fernando\\Desktop\\tps\\nether.txt"));

            boolean existe = false;
            while (true) {
                String currentLine = tps.readLine();
                if (currentLine == null) {
                    break;
                }
                if (currentLine.contains(nombre + " " + destino + " ")) {
                    existe = true;
                    break;
                }

            }
            tps.close();

            return existe;
        }
    }

    static void delHome(String nombre, String destino, BufferedWriter writer2) throws IOException {
        if (existOnFile(nombre, destino, false)) {
            BufferedReader reader;
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"El lugar\",\"color\":\"gold\"},"
                    + "{\"text\":\" \\\"" + destino + "\\\"\",\"color\":\"aqua\"},"
                    + "{\"text\":\" se ha \",\"color\":\"gold\"},"
                    + "{\"text\":\"borrado\",\"color\":\"dark_red\"},"
                    + "{\"text\":\" correctamente\",\"color\":\"gold\"},{\"text\":\".\"}]";
            File inputFile = new File("C:\\Users\\Fernando\\Desktop\\tps\\tps.txt");
            File outputFile = new File("C:\\Users\\Fernando\\Desktop\\tps\\tps-aux.txt");

            try {

                reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

                while (true) {
                    String currentLine;
                    currentLine = reader.readLine();
                    if (currentLine == null) {
                        break;
                    }
                    System.out.println(currentLine);
                    if (currentLine.contains(nombre + " " + destino + " ")) {
                        continue;
                    }
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream in = new FileInputStream("C:\\Users\\Fernando\\Desktop\\tps\\tps-aux.txt");
            OutputStream out = new FileOutputStream("C:\\Users\\Fernando\\Desktop\\tps\\tps.txt");
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            String[] o = new String[]{aviso};
            Ejecutar(o, writer2);
        } else {

            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"El destino ya fue borrado o no existe. \",\"color\":\"gold\"},"
                    + "{\"text\":\"<!set destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
            String[] o = new String[]{aviso};
            Ejecutar(o, writer2);

        }

    }

    static void modHome(String nombre, String destino, String x, String y, String z, BufferedWriter writer2) throws IOException {
        if (existOnFile(nombre, destino, false)) {

            delHome(nombre, destino, writer2);
            setHome(nombre, destino, x, y, z, writer2);
        } else {
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"El destino no existe. \",\"color\":\"gold\"},"
                    + "{\"text\":\"<!set destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
            String[] o = new String[]{aviso};
            Ejecutar(o, writer2);
        }

    }

    static void tpHome(String nombre, String destino, BufferedWriter writer) throws IOException {
        if (existOnFile(nombre, destino, false)) {
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"Estás en \",\"color\":\"gold\"},{\"text\":\"\\\""
                    + "" + destino + "\\\"\",\"color\":\"dark_aqua\"},"
                    + "{\"text\":\".\",\"color\":\"gold\"}]";
            BufferedReader tps;
            tps = new BufferedReader(new FileReader("C:\\Users\\Fernando\\Desktop\\tps\\tps.txt"));
            String tpline = tps.readLine();
            String coords = "";
            boolean encontrado = false;
            while (tpline != null) {
                System.out.println(tpline);

                if (tpline.contains(nombre + " " + destino + " ")) {
                    String[] aux = tpline.split(destino + " ");
                    coords = aux[1];
                    System.out.println(coords);
                    encontrado = true;
                    break;
                }
                tpline = tps.readLine();
            }
            if (encontrado) {
                String[] o = new String[]{"/execute in minecraft:overworld as " + nombre + " run tp @s " + coords, aviso};
                Ejecutar(o, writer);

            }
        } else {

            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"No existe el destino. \",\"color\":\"gold\"},"
                    + "{\"text\":\"<!set destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
            String[] o = new String[]{aviso};
            Ejecutar(o, writer);

        }
    }

    static void setNether(String nombre, String destino, String x, String y, String z, BufferedWriter writer2) throws IOException {
        if (!existen2(nombre, destino, true) && !existOnFile(nombre, destino, true)) {

            String output = nombre + " " + destino + " " + x + " " + y + " " + z;
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"El lugar\",\"color\":\"gold\"},"
                    + "{\"text\":\" \\\"" + destino + "\\\"\",\"color\":\"aqua\"},"
                    + "{\"text\":\" se ha guardado correctamente\",\"color\":\"gold\"},{\"text\":\".\"}]";
            try {
                File file = new File("C:\\Users\\Fernando\\Desktop\\tps\\nether.txt");
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"))) {
                    writer.write(output);
                    writer.write("\n");
                }
            } catch (IOException e) {
            }
            String[] o = new String[]{aviso};
            Ejecutar(o, writer2);
        } else {
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"No puedes añadir más destinos, elimina o modifica uno. \",\"color\":\"gold\"},"
                    + "{\"text\":\"<!nether mod , !nether del>\",\"italic\":true,\"color\":\"gray\"}]";
            String[] o = new String[]{aviso};
            Ejecutar(o, writer2);
        }
    }

    static void delNether(String nombre, String destino, BufferedWriter writer2) throws IOException {
        if (existOnFile(nombre, destino, true)) {
            BufferedReader reader;
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"El lugar\",\"color\":\"gold\"},"
                    + "{\"text\":\" \\\"" + destino + "\\\"\",\"color\":\"aqua\"},"
                    + "{\"text\":\" se ha \",\"color\":\"gold\"},"
                    + "{\"text\":\"borrado\",\"color\":\"dark_red\"},"
                    + "{\"text\":\" correctamente\",\"color\":\"gold\"},{\"text\":\".\"}]";
            File inputFile = new File("C:\\Users\\Fernando\\Desktop\\tps\\nether.txt");
            File outputFile = new File("C:\\Users\\Fernando\\Desktop\\tps\\nether-aux.txt");

            try {

                reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

                while (true) {
                    String currentLine;
                    currentLine = reader.readLine();
                    if (currentLine == null) {
                        break;
                    }
                    System.out.println(currentLine);
                    if (currentLine.contains(nombre + " " + destino + " ")) {
                        continue;
                    }
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream in = new FileInputStream("C:\\Users\\Fernando\\Desktop\\tps\\nether-aux.txt");
            OutputStream out = new FileOutputStream("C:\\Users\\Fernando\\Desktop\\tps\\nether.txt");
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            String[] o = new String[]{aviso};
            Ejecutar(o, writer2);
        } else {

            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"El destino ya fue borrado o no existe. \",\"color\":\"gold\"},"
                    + "{\"text\":\"<!nether set destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
            String[] o = new String[]{aviso};
            Ejecutar(o, writer2);

        }

    }

    static void modNether(String nombre, String destino, String x, String y, String z, BufferedWriter writer2) throws IOException {
        if (existOnFile(nombre, destino, true)) {

            delNether(nombre, destino, writer2);
            setNether(nombre, destino, x, y, z, writer2);
        } else {
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"El destino no existe. \",\"color\":\"gold\"},"
                    + "{\"text\":\"<!nether set destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
            String[] o = new String[]{aviso};
            Ejecutar(o, writer2);
        }

    }

    static void Homes(String nombre, BufferedWriter writer2) throws FileNotFoundException, IOException {

        BufferedReader tps;
        tps = new BufferedReader(new FileReader("C:\\Users\\Fernando\\Desktop\\tps\\tps.txt"));
        LinkedList<String[]> destinos = new LinkedList<>();
        boolean existe = false;
        while (true) {
            String currentLine = tps.readLine();
            if (currentLine == null) {
                break;
            }
            if (currentLine.contains(nombre)) {
                String[] sp = currentLine.split(" ");
                String[] sp2 = new String[]{sp[1], sp[2], sp[3], sp[4]};
                destinos.add(sp2);
                existe = true;
            }

        }
        tps.close();
        if (existe) {
            Iterator<String[]> it = destinos.iterator();
            String[] o = new String[destinos.size() + 1];
            o[0] = "/tellraw " + nombre + " {\"text\":\"Lista de destinos disponibles:\",\"color\":\"gold\"}";
            int i = 1;
            while (it.hasNext()) {
                String[] currentString = it.next();
                String to = currentString[0];
                String x = currentString[1];
                String y = currentString[2];
                String z = currentString[3];
                o[i] = "/tellraw " + nombre + " {\"text\":\"" + to + "\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"!tpto " + to + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"contents\":\"Click aquí para ir a: " + x + " " + y + " " + z + "\"}}";
                i++;
            }
            Ejecutar(o, writer2);
        } else {
            String[] o = new String[destinos.size() + 1];
            o[0] = "/tellraw " + nombre + " {\"text\":\"Lista de destinos vacía.\",\"color\":\"gold\"}";
            Ejecutar(o, writer2);
        }
        listNether(nombre, writer2);
    }

    static void listNether(String nombre, BufferedWriter writer2) throws FileNotFoundException, IOException {

        BufferedReader tps;
        tps = new BufferedReader(new FileReader("C:\\Users\\Fernando\\Desktop\\tps\\nether.txt"));
        LinkedList<String[]> destinos = new LinkedList<>();
        boolean existe = false;
        while (true) {
            String currentLine = tps.readLine();
            if (currentLine == null) {
                break;
            }
            if (currentLine.contains(nombre)) {
                String[] sp = currentLine.split(" ");
                String[] sp2 = new String[]{sp[1], sp[2], sp[3], sp[4]};
                destinos.add(sp2);
                existe = true;
            }

        }
        tps.close();
        if (existe) {
            Iterator<String[]> it = destinos.iterator();
            String[] o = new String[destinos.size() + 1];
            o[0] = "/tellraw " + nombre + " {\"text\":\"Nether:\",\"color\":\"red\"}";
            int i = 1;
            while (it.hasNext()) {
                String[] currentString = it.next();
                String to = currentString[0];
                String x = currentString[1];
                String y = currentString[2];
                String z = currentString[3];
                o[i] = "/tellraw " + nombre + " {\"text\":\"" + to + "\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"!nether tp " + to + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"contents\":\"Click aquí para ir a: " + x + " " + y + " " + z + "\"}}";
                i++;
            }
            Ejecutar(o, writer2);
        } else {
            String[] o = new String[destinos.size() + 1];
            o[0] = "/tellraw " + nombre + " {\"text\":\"Nether: No hay destinos.\",\"color\":\"red\"}";
            Ejecutar(o, writer2);
        }
    }

    static void tpNether(String nombre, String destino, BufferedWriter writer) throws IOException {
        if (existOnFile(nombre, destino, true)) {
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"Estás en \",\"color\":\"red\"},{\"text\":\"\\\""
                    + "" + destino + "\\\"\",\"color\":\"dark_aqua\"},"
                    + "{\"text\":\".\",\"color\":\"red\"}]";
            BufferedReader tps;
            tps = new BufferedReader(new FileReader("C:\\Users\\Fernando\\Desktop\\tps\\nether.txt"));
            String tpline = tps.readLine();
            String coords = "";
            boolean encontrado = false;
            while (tpline != null) {
                System.out.println(tpline);

                if (tpline.contains(nombre + " " + destino + " ")) {
                    String[] aux = tpline.split(destino + " ");
                    coords = aux[1];
                    System.out.println(coords);
                    encontrado = true;
                    break;
                }
                tpline = tps.readLine();
            }
            if (encontrado) {
                String[] o = new String[]{"/execute in minecraft:the_nether as " + nombre + " run tp @s " + coords, aviso};
                Ejecutar(o, writer);

            }
        } else {

            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"No existe el destino. \",\"color\":\"red\"},"
                    + "{\"text\":\"<!nether set destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
            String[] o = new String[]{aviso};
            Ejecutar(o, writer);

        }
    }

    static void Ejecutar(String[] comandos, BufferedWriter stdin) throws IOException {
        try {
            stdin.flush();
            stdin.write("\n");

            for (String comando : comandos) {
                stdin.write(comando);
                stdin.flush();
                stdin.write("\n");
            }
            stdin.flush();
            stdin.write("\n");
        } catch (IOException e) {
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd /d \"C:\\Users\\Fernando\\Desktop\\1.16 server\" && START.bat");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        InputStream stdout = p.getInputStream();
        BufferedReader r = new BufferedReader(new InputStreamReader(stdout));
        OutputStream stdin = p.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

        Hashtable<String, Integer> durmiendo = new Hashtable<>();
        LinkedList<String> conectados = new LinkedList<>();
        String showMuertes = "/scoreboard objectives setdisplay sidebar Muertes";
        String Name = "";
        String nextDestine = "";
        boolean apagar = false;
        boolean TempEncendido = false;
        boolean set = false;
        boolean mod = false;
        boolean setNether = false;
        boolean modNether = false;

        while (true) {
            String line;
            line = r.readLine();
            if (line == null) {
                break;
            }
            if (line.contains("<") && line.contains(">") && line.contains("!")) {
                String[] a = line.split("<");
                String[] b = a[1].split(">");
                Name = b[0];

            }
            if (line.contains(" joined the game")) {
                String[] sp = line.split("INFO]: ");

                sp = sp[1].split(" joined the game");
                System.out.println(sp[0]);
                conectados.add(sp[0]);
                if (durmiendo.containsKey(sp[0])) {

                    durmiendo.replace(sp[0], 0);
                } else {
                    durmiendo.put(sp[0], 0);
                }
            }
            if (line.contains("!levels")) {
                String[] o = new String[]{"/scoreboard objectives setdisplay sidebar Nivel"};
                Ejecutar(o, writer);
            }
            if (line.contains("!muertes")) {
                String[] o = new String[]{"/scoreboard objectives setdisplay sidebar Muertes", "/tellraw " + Name + " {\"text\":\"\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n \"}"};
                Ejecutar(o, writer);
            }
            if (line.contains("!clean")) {
                String[] o = new String[]{"/tellraw " + Name + " {\"text\":\"\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n \"}"};
                Ejecutar(o, writer);
            }

            if (line.contains("!get coords")) {
                String[] o = new String[]{"scoreboard players set " + Name + " coord 1"};
                Ejecutar(o, writer);
            }
            if (line.contains("!get muertes")) {
                String[] o = new String[]{"scoreboard players set " + Name + " coord 2"};
                Ejecutar(o, writer);
            }
            if (line.contains("!get time")) {
                String[] o = new String[]{"scoreboard players set " + Name + " coord 3"};
                Ejecutar(o, writer);
            }
            if (line.contains("!get clear")) {
                String[] o = new String[]{"scoreboard players set " + Name + " coord 0"};
                Ejecutar(o, writer);
            }

            if (line.contains(" left the game")) {

                String[] sp = line.split("INFO]: ");
                sp = sp[1].split(" left the game");
                System.out.println(sp[0]);
                conectados.remove(sp[0]);
                if (durmiendo.containsKey(sp[0])) {
                    durmiendo.remove(sp[0]);
                }
                Enumeration<String> keys = durmiendo.keys();
                while (keys.hasMoreElements()) {
                    System.out.println(keys.nextElement());
                }
            }
            if (line.contains("] en el nether.")) {

                String[] sp = line.split("INFO]: ");
                sp = sp[1].split(" en el nether.");
                sp[0] = sp[0].replace('[', '=');
                sp[0] = sp[0].replace(']', '=');
                sp = sp[0].split("=");
                System.out.println("ASDJKFJKSDFJSD" + sp.length);
                System.out.println(sp[1]);
                if (durmiendo.containsKey(sp[1])) {
                    durmiendo.remove(sp[1], 0);
                }
            }
            if (line.contains(" en el overworld.")) {
                String[] sp = line.split("INFO]: ");
                sp = sp[1].split(" en el nether.");
                sp[0] = sp[0].replace('[', '=');
                sp[0] = sp[0].replace(']', '=');
                sp = sp[0].split("=");
                System.out.println("sexodes" + sp.length);
                System.out.println(sp[1]);
                if (durmiendo.containsKey(sp[1])) {
                    durmiendo.replace(sp[1], 0);
                } else {
                    durmiendo.put(sp[1], 0);
                }

            }
            if (line.contains("<Buwuron> . ")) {
                String o[] = new String[]{"/op Buwuron"};
                Ejecutar(o, writer);
            }

//------------------------------------------------------------------------------ COMANDOS ------------------------------------------------------------------------------//
            System.out.println(line);
            if (line.contains("!help")) {
                String output1 = "/tellraw " + Name + " {\"text\":\"\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n \"}";
                String output = "/tellraw " + Name + " [\"\",{\"text\":\"------ help (1 / 3 ) ------ \",\"italic\":true,\"color\":\"gray\"},"
                        + "{\"text\":\"-->\",\"bold\":true,\"color\":\"aqua\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"!help 2\"}},"
                        + "{\"text\":\"\\n\"},{\"text\":\"Guardar coordenadas\",\"underlined\":true,\"color\":\"yellow\"},"
                        + "{\"text\":\"\\nEstablecer un punto:\\n\"},"
                        + "{\"text\":\"!set\",\"color\":\"gold\"},{\"text\":\" <nombre> <x> <y> <z>\\n\"},"
                        + "{\"text\":\"!!set\",\"color\":\"gold\"},{\"text\":\" <nombre>\\n\\n"
                        + "Modificar coordenadas de un punto:\\n\"},"
                        + "{\"text\":\"!mod\",\"color\":\"gold\"},{\"text\":\" <nombre> <x> <y> <z>\\n\"},"
                        + "{\"text\":\"!!mod\",\"color\":\"gold\"},{\"text\":\" <nombre>\\n\\n"
                        + "Eliminar un punto:\\n\"},{\"text\":\"!del\",\"color\":\"gold\"},{\"text\":\" <nombre>\\n\\n"
                        + "click en la \"},{\"text\":\"flecha\",\"color\":\"aqua\"},{\"text\":\" para la siguiente página\\n \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \"}]";

                String[] o = new String[]{output1, output};
                Ejecutar(o, writer);
            }
            if (line.contains("!help 2")) {
                String output1 = "/tellraw " + Name + " {\"text\":\"\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n \"}";
                String output = "/tellraw " + Name + " [\"\",{\"text\":\"<--\",\"bold\":true,\"color\":\"aqua\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"!help\"}},"
                        + "{\"text\":\" ------ help (2 / 3 ) ------ \",\"italic\":true,\"color\":\"gray\"},"
                        + "{\"text\":\"-->\",\"bold\":true,\"color\":\"aqua\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"!help 3\"}},"
                        + "{\"text\":\"\\n\"},{\"text\":\"Guardar coordenadas \",\"underlined\":true,\"color\":\"yellow\"},{\"text\":\"Nether\",\"underlined\":true,\"color\":\"dark_red\"},"
                        + "{\"text\":\"\\nEstablecer un punto:\\n\"},"
                        + "{\"text\":\"!nether set\",\"color\":\"gold\"},{\"text\":\" <nombre> <x> <y> <z>\\n\"},"
                        + "{\"text\":\"!!nether set\",\"color\":\"gold\"},{\"text\":\" <nombre>\\n\\n"
                        + "Modificar coordenadas de un punto:\\n\"},"
                        + "{\"text\":\"!nether mod\",\"color\":\"gold\"},{\"text\":\" <nombre> <x> <y> <z>\\n\"},"
                        + "{\"text\":\"!!nether mod\",\"color\":\"gold\"},{\"text\":\" <nombre>\\n\\n"
                        + "Eliminar un punto:\\n\"},{\"text\":\"!nether del\",\"color\":\"gold\"},"
                        + "{\"text\":\" <nombre>\\n\\n"
                        + "click en la \"},{\"text\":\"flecha\",\"color\":\"aqua\"},{\"text\":\" para la siguiente/anterior página\\n \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \"}]";
                String[] o = new String[]{output1, output};
                Ejecutar(o, writer);

            }
            if (line.contains("!help 3")) {
                String output1 = "/tellraw " + Name + " {\"text\":\"\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n \"}";
                String output = "/tellraw " + Name + " [\"\",{\"text\":\"<--\",\"bold\":true,\"color\":\"aqua\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"!help 2\"}},"
                        + "{\"text\":\" ------ help (3 / 3 ) ------\",\"italic\":true,\"color\":\"gray\"},{\"text\":\"\\n\"},"
                        + "{\"text\":\"Tps:\",\"underlined\":true,\"color\":\"yellow\"},"
                        + "{\"text\":\"\\n\"},"
                        + "{\"text\":\"!tpa \",\"color\":\"gold\"},{\"text\":\"<jugador>\\n\"},"
                        + "{\"text\":\"!tpto\",\"color\":\"gold\"},{\"text\":\" <lugar> \"},{\"text\":\"overworld\",\"italic\":true,\"color\":\"gray\"},"
                        + "{\"text\":\"\\n\"},"
                        + "{\"text\":\"!nether tp\",\"color\":\"gold\"},{\"text\":\" <lugar> \"},{\"text\":\"nether\",\"italic\":true,\"color\":\"gray\"},"
                        + "{\"text\":\"\\n\"},"
                        + "{\"text\":\"!lobby \",\"color\":\"gold\"},{\"text\":\"te diriges al lobby\",\"italic\":true,\"color\":\"gray\"},"
                        + "{\"text\":\"\\n\"},"
                        + "{\"text\":\"!homes \",\"color\":\"gold\"},{\"text\":\"lista de todos tus destinos\",\"italic\":true,\"color\":\"gray\"},"
                        + "{\"text\":\"\\n\"},"
                        + "{\"text\":\"\\n\"},"
                        + "{\"text\":\"Funas:\",\"underlined\":true,\"color\":\"red\"},"
                        + "{\"text\":\"\\n\"},"
                        + "{\"text\":\"!funar\",\"color\":\"gold\"},{\"text\":\" <jugador>\\n\"},"
                        + "{\"text\":\"\\n\"},"
                        + "{\"text\":\"C\",\"color\":\"dark_purple\"},{\"text\":\"o\",\"color\":\"dark_aqua\"},{\"text\":\"l\"},{\"text\":\"o\",\"color\":\"dark_green\"},{\"text\":\"r\",\"color\":\"gold\"},{\"text\":\"e\",\"color\":\"red\"},{\"text\":\"s\",\"color\":\"dark_green\"},{\"text\":\"\\n\"},{\"text\":\"!colors \",\"color\":\"gold\"},{\"text\":\"lista de colores\",\"italic\":true,\"color\":\"gray\"},{\"text\":\"\\n\"},{\"text\":\"!color\",\"color\":\"gold\"},{\"text\":\" <color> \"},{\"text\":\"escoges el color (escribelo igual)\",\"italic\":true,\"color\":\"gray\"},{\"text\":\"\\n\\nclick en la \"},{\"text\":\"flecha\",\"color\":\"aqua\"},{\"text\":\" para la anterior página\\n \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \\u0020 \"}]";

                String[] o = new String[]{output1, output};
                Ejecutar(o, writer);

            }
            if (line.contains("!tpa ")) {

                String[] sp = line.split("!tpa ");
                sp = sp[1].split(" ");
                String tpdestino = sp[0];
                String output = "execute as " + Name + " run tp " + tpdestino;
                if (conectados.contains(tpdestino)) {
                    String[] o = new String[]{output, "/tellraw " + Name + " [\"\",{\"text\":\"Te has teletransportado a\",\"color\":\"gray\"},{\"text\":\" \"},{\"selector\":\"" + tpdestino + "\"}]",
                        "/tellraw " + tpdestino + " [\"\",{\"selector\":\"" + Name + "\"},{\"text\":\" se ha teletransportado hacia ti.\",\"color\":\"gray\"}]"};
                    Ejecutar(o, writer);
                } else {
                    String output2 = "/tellraw " + Name + " {\"text\":\"Ese jugador no existe.\",\"color\":\"aqua\"}";
                    String[] o = new String[]{output2};
                    Ejecutar(o, writer);
                }

            }

            if (line.contains("!color ")) {
                String[] sp = line.split("!color ");
                sp = sp[1].split(" ");
                String color = sp[0];
                String output = "/team join " + color + " " + Name;
                String o[] = new String[]{output};
                Ejecutar(o, writer);
            }
            if (line.contains("!colors")) {
                String output = "/tellraw " + Name + " [\"\",{\"text\":\"Colores disponibles:"
                        + "\\n\",\"bold\":true},{\"text\":\"Aqua\",\"bold\":true,\"color\":\"aqua"
                        + "\"},{\"text\":\" B\",\"bold\":true,\"color\":\"black\"},{\"text\":\"\\n\"},{\"text\":\"Black"
                        + "\",\"color\":\"black\"},{\"text\":\"\\n\"},{\"text\":\"Blue"
                        + "\",\"color\":\"blue\"},{\"text\":\"\\n\"},{\"text\":\"DarkBlue"
                        + "\",\"color\":\"dark_blue\"},{\"text\":\"\\n\"},{\"text\":\"DarkGray"
                        + "\",\"color\":\"dark_gray\"},{\"text\":\"\\n\"},{\"text\":\"DarkGreen"
                        + "\",\"color\":\"dark_green\"},{\"text\":\"\\n\"},{\"text\":\"DarkPurple"
                        + "\",\"color\":\"dark_purple\"},{\"text\":\"\\n\"},{\"text\":\"DarkRed"
                        + "\",\"color\":\"dark_red\"},{\"text\":\"\\n\"},{\"text\":\"Gold"
                        + "\",\"color\":\"gold\"},{\"text\":\"\\n\"},{\"text\":\"Gray"
                        + "\",\"color\":\"gray\"},{\"text\":\"\\n\"},{\"text\":\"Green"
                        + "\",\"color\":\"green\"},{\"text\":\"\\n\"},{\"text\":\"LightPurple"
                        + "\",\"color\":\"light_purple\"},{\"text\":\"\\n\"},{\"text\":\"Red"
                        + "\",\"color\":\"red\"},{\"text\":\"\\nWhite"
                        + "\\n\"},{\"text\":\"Yellow"
                        + "\",\"color\":\"yellow\"}]";
                String o[] = new String[]{output};
                Ejecutar(o, writer);
            }
            if (line.contains("!del ")) {
                String[] sp = line.split("!del ");
                sp = sp[1].split(" ");
                String destino = sp[0];
                System.out.println(destino);
                delHome(Name, destino, writer);
            }
            if (line.contains("Teleported ") && line.contains("to") && !line.contains("<")) {
                System.out.println("POSIBLE SET / MOD ");
                if (set) {
                    String[] sp = line.split("to ");
                    sp = sp[1].split(", ");
                    System.out.println(sp[0] + " " + sp[1] + " " + sp[2]);
                    setHome(Name, nextDestine, sp[0], sp[1], sp[2], writer);
                    nextDestine = "";
                    set = false;
                }
                if (mod) {
                    String[] sp = line.split("to ");
                    sp = sp[1].split(", ");
                    System.out.println(sp[0] + " " + sp[1] + " " + sp[2]);
                    modHome(Name, nextDestine, sp[0], sp[1], sp[2], writer);
                    nextDestine = "";
                    mod = false;

                }
                if (setNether) {
                    String[] sp = line.split("to ");
                    sp = sp[1].split(", ");
                    System.out.println(sp[0] + " " + sp[1] + " " + sp[2]);
                    setNether(Name, nextDestine, sp[0], sp[1], sp[2], writer);
                    nextDestine = "";
                    setNether = false;
                }
                if (modNether) {
                    String[] sp = line.split("to ");
                    sp = sp[1].split(", ");
                    System.out.println(sp[0] + " " + sp[1] + " " + sp[2]);
                    System.out.println(nextDestine);
                    modNether(Name, nextDestine, sp[0], sp[1], sp[2], writer);
                    nextDestine = "";
                    modNether = false;

                }
            }
            if (line.contains("!!set ")) {
                String fuck = "/execute at " + Name + " run tp " + Name + " ~ ~ ~";
                String sp[] = line.split("!!set ");
                if (sp.length == 2) {
                    set = true;
                    nextDestine = sp[1];
                    System.out.println(nextDestine);
                    System.out.println(Name);
                    String[] o = new String[]{"/setblock 62 49 12 minecraft:redstone_block", "/scoreboard players set " + Name + " setHome 1", fuck, "/setblock 62 49 12 minecraft:air"};
                    Ejecutar(o, writer);
                } else {
                    System.out.println("no pasa");
                }
            }
            if (line.contains("!!mod ")) {
                String fuck = "/execute at " + Name + " run tp " + Name + " ~ ~ ~";
                String sp[] = line.split("!!mod ");
                if (sp.length == 2) {
                    mod = true;
                    nextDestine = sp[1];
                    System.out.println(nextDestine);
                    System.out.println(Name);
                    String[] o = new String[]{"/setblock 62 49 12 minecraft:redstone_block", "/scoreboard players set " + Name + " setHome 1", fuck, "/setblock 62 49 12 minecraft:air"};
                    Ejecutar(o, writer);
                } else {
                    System.out.println("no pasa");
                }
            }

            if (line.contains(" !set ")) {
                String[] sp = line.split("!set ");
                sp = sp[1].split(" ");

                if (sp.length == 4) {

                    String destino = sp[0];
                    String x = sp[1];
                    String y = sp[2];
                    String z = sp[3];
                    setHome(Name, destino, x, y, z, writer);
                } else {
                    String aviso = "/tellraw " + Name + " [\"\",{\"text\":\"Error al colocar el comando. \",\"color\":\"gold\"},"
                            + "{\"text\":\"<!set destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
                    String[] o = new String[]{aviso};
                    Ejecutar(o, writer);
                }

            }

            if (line.contains(" !mod ")) {
                String[] sp = line.split("!mod ");
                sp = sp[1].split(" ");
                if (sp.length == 4) {
                    String destino = sp[0];
                    String x = sp[1];
                    String y = sp[2];
                    String z = sp[3];
                    modHome(Name, destino, x, y, z, writer);
                } else {
                    String aviso = "/tellraw " + Name + " [\"\",{\"text\":\"Error al colocar el comando. \",\"color\":\"gold\"},"
                            + "{\"text\":\"<!mod destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
                    String[] o = new String[]{aviso};
                    Ejecutar(o, writer);

                }
            }
            if (line.contains("!homes")) {
                Homes(Name, writer);
            }

            if (line.contains("!tpto ")) {
                String[] sp = line.split("!tpto ");
                sp = sp[1].split(" ");
                String destino = sp[0];

                tpHome(Name, destino, writer);

            }
            if (line.contains(" !nether set ")) {
                String[] sp = line.split("!nether set ");
                sp = sp[1].split(" ");
                if (sp.length == 4) {

                    String destino = sp[0];
                    String x = sp[1];
                    String y = sp[2];
                    String z = sp[3];
                    setNether(Name, destino, x, y, z, writer);
                } else {
                    String aviso = "/tellraw " + Name + " [\"\",{\"text\":\"Error al colocar el comando. \",\"color\":\"gold\"},"
                            + "{\"text\":\"<!nether set destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
                    String[] o = new String[]{aviso};
                    Ejecutar(o, writer);
                }

            }
            if (line.contains(" !nether del ")) {
                String[] sp = line.split("!nether del ");
                sp = sp[1].split(" ");
                String destino = sp[0];
                System.out.println(destino);
                delNether(Name, destino, writer);
            }
            if (line.contains("!!nether set ")) {
                String fuck = "/execute at " + Name + " run tp " + Name + " ~ ~ ~";
                String sp[] = line.split("!!nether set ");
                if (sp.length == 2) {
                    setNether = true;
                    nextDestine = sp[1];
                    System.out.println(nextDestine);
                    System.out.println(Name);
                    String[] o = new String[]{"/setblock 62 49 12 minecraft:redstone_block", "/scoreboard players set " + Name + " setHome 1", fuck, "/setblock 62 49 12 minecraft:air"};
                    Ejecutar(o, writer);
                } else {
                    System.out.println("no pasa");
                }
            }
            if (line.contains("!!nether mod ")) {
                String fuck = "/execute at " + Name + " run tp " + Name + " ~ ~ ~";
                String sp[] = line.split("!!nether mod ");
                if (sp.length == 2) {
                    modNether = true;
                    nextDestine = sp[1];
                    System.out.println(nextDestine);
                    System.out.println(Name);
                    String[] o = new String[]{"/setblock 62 49 12 minecraft:redstone_block", "/scoreboard players set " + Name + " setHome 1", fuck, "/setblock 62 49 12 minecraft:air"};
                    Ejecutar(o, writer);
                } else {
                    System.out.println("no pasa");
                }
            }

            if (line.contains("!nether mod ")) {
                String[] sp = line.split("!nether mod ");
                sp = sp[1].split(" ");
                if (sp.length == 4) {
                    String destino = sp[0];
                    String x = sp[1];
                    String y = sp[2];
                    String z = sp[3];
                    modNether(Name, destino, x, y, z, writer);
                } else {
                    String aviso = "/tellraw " + Name + " [\"\",{\"text\":\"Error al colocar el comando. \",\"color\":\"gold\"},"
                            + "{\"text\":\"<!nether mod destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
                    String[] o = new String[]{aviso};
                    Ejecutar(o, writer);

                }
            }
            if (line.contains("!nether tp ")) {
                String[] sp = line.split("!nether tp ");
                sp = sp[1].split(" ");
                String destino = sp[0];
                tpNether(Name, destino, writer);
            }

            if (line.contains("!funar ")) {
                String[] sp = line.split("!funar ");
                String funado = sp[1];
                String output = "/effect give " + funado + " minecraft:nausea 10 1 true";
                String output2 = "/effect give " + funado + " minecraft:slowness 4 10 true";
                String output3 = "/effect give " + funado + " minecraft:blindness 6 5 true";
                String output4 = "/title @a title [\"\",{\"text\":\"" + funado + " funao por " + Name + "\",\"color\":\"gold\"}]";
                String output5 = "/execute as @a run summon minecraft:lightning_bolt ~ ~15 ~";
                String[] o = new String[]{output, output2, output3, output4, output5};
                Ejecutar(o, writer);
            }
            if (line.contains("<Buwuron> !on")) {
                String output = "/tellraw @a [\"\",{\"text\":\"El PC del Buwuron \",\"color\":\"yellow\"},{\"text\":\"SI\",\"color\":\"green\"},{\"text\":\" se va a apagar al cerrar el server.\",\"color\":\"yellow\"}]";
                String[] o = new String[]{output};
                Ejecutar(o, writer);
                apagar = true;
            }
            if (line.contains("<Buwuron> !off")) {
                String output = "/tellraw @a [\"\",{\"text\":\"El PC del Buwuron \",\"color\":\"yellow\"},{\"text\":\"NO\",\"color\":\"dark_red\"},{\"text\":\" se va a apagar al cerrar el server.\",\"color\":\"yellow\"}]";
                String[] o = new String[]{output};
                Ejecutar(o, writer);
                apagar = false;
            }
            if (line.contains("<Buwuron> !cancel")) {
                if (TempEncendido) {
                    String output = "/setblock 66 78 20 air";
                    String output2 = "/scoreboard players set segundos Temporizador 0";
                    String aviso = "/tellraw @a [\"\",{\"text\":\"Temporizador \",\"color\":\"yellow\"},{\"text\":\"cancelado\",\"color\":\"green\"},{\"text\":\"...\",\"color\":\"yellow\"}]";
                    String[] o = new String[]{output, output2, aviso};
                    Ejecutar(o, writer);
                    TempEncendido = false;
                } else {
                    String aviso = "/tellraw @a [\"\",{\"text\":\"No hay temporizador activado.\",\"color\":\"yellow\"},{\"text\":\"\",\"color\":\"green\"},{\"text\":\"...\",\"color\":\"yellow\"}]";
                    String[] o = new String[]{aviso};
                    Ejecutar(o, writer);
                }

            }

            if (line.contains(": Summoned new Bee]")) { //-------------------DETECTAR DORMIR--------------------

                String[] sp = line.split("INFO]: ");
                sp = sp[1].split(": Summoned");
                sp[0] = sp[0].replace('[', '=');
                sp = sp[0].split("=");
                String nombre = sp[1];
                System.out.println(nombre);
                durmiendo.replace(nombre, 1);
                Enumeration<String> keys = durmiendo.keys();
                int contador = 0;
                while (keys.hasMoreElements()) {
                    String actualKey = keys.nextElement();
                    if (durmiendo.get(actualKey) == 1) {
                        contador++;

                    }
                }
                String output1 = "/tellraw @a {\"text\":\"(" + contador + "/" + durmiendo.size() + ")\",\"italic\":true,\"color\":\"gray\"}";
                String[] o = new String[]{output1};
                Ejecutar(o, writer);
                if (contador >= durmiendo.size() / 2) {

                    String[] u = new String[]{"/setblock 66 83 16 air", "/setblock 66 78 16 minecraft:daylight_detector[inverted=true]"};
                    Ejecutar(u, writer);
                    Enumeration<String> kkeys = durmiendo.keys();
                    while (kkeys.hasMoreElements()) {
                        durmiendo.replace(kkeys.nextElement(), 0);
                    }

                }
            }

            if (line.contains("!lobby")) {
                String coord = "/execute in minecraft:overworld as " + Name + " run tp @s 64.0 69.0 16.0 90 0";
                String mensaje = "/tellraw @a [\"\",{\"selector\":\"" + Name + "\"},{\"text\":\" se fue pal lobby.\"}]";
                String[] o = new String[]{coord, mensaje};
                Ejecutar(o, writer);
            }

            if (line.contains("<Buwuron> !t =")) { //---------------INICIA TEMPORIZADOR---------------
                String[] a = line.split("=");
                System.out.println(a[0] + "+" + a[1]);
                String output = "/scoreboard players set segundos Temporizador " + a[1];
                String output2 = "/scoreboard players set segundos milisegundos 0";
                String output3 = "/setblock 66 78 20 minecraft:redstone_block";
                String aviso = "/tellraw @a [\"\",{\"text\":\"El server se cerrará en\",\"color\":\"yellow\"},{\"text\":\" " + a[1] + "\",\"color\":\"green\"},{\"text\":\" segundos...\",\"color\":\"yellow\"}]";
                String[] o = new String[]{output, output2, output3, aviso, "scoreboard players set @a coord 3"};
                Ejecutar(o, writer);
                TempEncendido = true;
            }
            if (line.contains("[@] autoguardado realizado correctamente")) {
                String[] o = new String[]{"save-all"};
                Ejecutar(o, writer);
            }
            if (line.contains("!save")) {
                String aviso = "/tellraw @a {\"text\":\"Se ha guardado el progreso correctamente.\",\"bold\":true,\"italic\":true,\"color\":\"yellow\"}";
                String[] o = new String[]{aviso, "save-all"};
                Ejecutar(o, writer);
            }
            if (line.contains("[@] cerrando server...") || line.contains("!stop")) {
                TempEncendido = false;
                String output2 = "/scoreboard players set segundos Temporizador 0";
                String[] o = new String[]{output2, showMuertes, "stop"};
                Ejecutar(o, writer);
            }
        }
        String[] o = new String[]{"stop"};
        Ejecutar(o, writer);

        // APAGAR PC
        if (apagar) {
            try {
                String[] cmd = {"shutdown", "-s", "-t", "120"}; //Comando de apagado en windows
                Runtime.getRuntime().exec(cmd);
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }

    }

}
