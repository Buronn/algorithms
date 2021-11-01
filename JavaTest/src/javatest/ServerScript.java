/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatest;

import java.io.*;
import java.util.*;

public class ServerScript {

    static void setHome(String nombre, String destino, String x, String y, String z, BufferedWriter writer2) throws IOException {
        if (!existen2(nombre, destino) && !existOnFile(nombre, destino)) {
            
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

    static boolean existen2(String nombre, String destino) throws IOException {
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
        if (destinos.size() >= 2) {
            existe = true;
        }
        return existe;
    }

    static boolean existOnFile(String nombre, String destino) throws IOException {
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
    }

    static void delHome(String nombre, String destino, BufferedWriter writer2) throws IOException {
        if (existOnFile(nombre, destino)) {
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
        if (existOnFile(nombre, destino)) {

            delHome(nombre, destino, writer2);
            setHome(nombre, destino, x, y, z, writer2);
        } else {
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"El destino no existe. \",\"color\":\"gold\"},"
                    + "{\"text\":\"<!set destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
            String[] o = new String[]{aviso};
            Ejecutar(o, writer2);
        }

    }

    static void listHome(String nombre, BufferedWriter writer2) throws FileNotFoundException, IOException {

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
                existe = true;
            }

        }
        tps.close();
        if (existe) {
            Iterator<String> it = destinos.iterator();
            String[] o = new String[destinos.size() + 1];
            o[0] = "/tellraw " + nombre + " {\"text\":\"Lista de destinos disponibles:\",\"color\":\"gold\"}";
            int i = 1;
            while (it.hasNext()) {
                o[i] = "/tellraw " + nombre + " {\"text\":\"" + it.next() + "\",\"color\":\"dark_aqua\"}";
                i++;
            }
            Ejecutar(o, writer2);
        } else {
            String[] o = new String[destinos.size() + 1];
            o[0] = "/tellraw " + nombre + " {\"text\":\"Lista de destinos vacía.\",\"color\":\"gold\"}";
            Ejecutar(o, writer2);
        }
    }

    static void tpHome(String nombre, String destino, BufferedWriter writer) throws IOException {
        if(existOnFile(nombre, destino)){
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

            if (tpline.contains(nombre + " " + destino)) {
                String[] aux = tpline.split(destino + " ");
                coords = aux[1];
                System.out.println(coords);
                encontrado = true;
                break;
            }
            tpline = tps.readLine();
        }
        if (encontrado) {
            String[] o = new String[]{"/tp " + nombre + " " + coords, aviso};
            Ejecutar(o, writer);

        }
        }
        else{
            
            String aviso = "/tellraw " + nombre + " [\"\",{\"text\":\"No existe el destino. \",\"color\":\"gold\"},"
                    + "{\"text\":\"<!set destino x y z>\",\"italic\":true,\"color\":\"gray\"}]";
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
                "cmd.exe", "/c", "cd /d \"C:\\Users\\Fernando\\Desktop\\Server Yeyoldo\" && START2.bat");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        InputStream stdout = p.getInputStream();
        BufferedReader r = new BufferedReader(new InputStreamReader(stdout));
        OutputStream stdin = p.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

        String showMuertes = "/scoreboard objectives setdisplay sidebar MUERTES";
        String showTemporizador = "/scoreboard objectives setdisplay sidebar Temporizador";
        String Name = "";
        boolean apagar = false;
        boolean TempEncendido = false;

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
//------------------------------------------------------------------------------ COMANDOS ------------------------------------------------------------------------------//
            System.out.println(line);
            if (line.contains("!help")) {

                String output = "/tellraw " + Name + " {\"text\":\"Comando: \\u0020Uso:\\n"
                        + "!t =n \\u0020 \\u0020 \\u0020 n equivale a los segundos para apagar el server\\n"
                        + "!cancel \\u0020 \\u0020 cancela el temporizador\\n"
                        + "!set nombre x y z \\u0020 \\u0020 creas un setpoint con las coordenadas\\n"
                        + "!mod nombre x y z \\u0020 \\u0020 modificas nombre a x y z\\n"
                        + "!homes  \\u0020 \\u0020 lista de destinos disponible\\n"
                        + "!tpto nombre \\u0020 \\u0020 te teletransportas hacia tu destino\\n"
                        + "!del nombre \\u0020 \\u0020 borra el setpoint de la casa\\n"
                        + "!lobby \\u0020 \\u0020 \\u0020vuelves al lobby\\n"
                        + "!save  \\u0020 \\u0020 \\u0020guardar el mundo\\n"
                        + "!on \\u0020 \\u0020 \\u0020 \\u0020 el PC se apaga al cerrar el server\\n"
                        + "!off \\u0020 \\u0020 \\u0020 \\u0020el PC no se apaga al cerrar el server\\n"
                        + "!funar X \\u0020 X equivale al jugador funado\\n \"}";
                String[] o = new String[]{output};
                Ejecutar(o, writer);
            }
            if (line.contains("!del ")) {
                String[] sp = line.split("!del ");
                sp = sp[1].split(" ");
                String destino = sp[0];
                System.out.println(destino);
                delHome(Name, destino, writer);
            }

            if (line.contains("!set ")) {
                String[] sp = line.split("!set ");
                sp = sp[1].split(" ");
                String destino = sp[0];
                String x = sp[1];
                String y = sp[2];
                String z = sp[3];
                setHome(Name, destino, x, y, z, writer);
            }
            if (line.contains("!mod ")) {
                String[] sp = line.split("!mod ");
                sp = sp[1].split(" ");
                String destino = sp[0];
                String x = sp[1];
                String y = sp[2];
                String z = sp[3];
                modHome(Name, destino, x, y, z, writer);
            }
            if (line.contains("!homes")) {
                listHome(Name, writer);
            }

            if (line.contains("!tpto")) {
                String[] sp = line.split("!tpto ");
                sp = sp[1].split(" ");
                String destino = sp[0];
                tpHome(Name, destino, writer);
            }

            if (line.contains("!funar")) {
                String[] sp = line.split("!funar ");
                String funado = sp[1];
                String output = "/effect give " + funado + " minecraft:nausea 10 1 true";
                String output2 = "/effect give " + funado + " minecraft:slowness 4 10 true";
                String output3 = "/effect give " + funado + " minecraft:blindness 6 5 true";
                String output4 = "/title @a title [\"\",{\"text\":\"" + Name + " ha funado a " + funado + "\",\"color\":\"gold\"}]";
                String output5 = "/execute as @a run summon minecraft:lightning_bolt ~ ~15 ~";
                String[] o = new String[]{output, output2, output3, output4, output5};
                Ejecutar(o, writer);
            }
            if (line.contains("!on")) {
                String output = "/tellraw @a [\"\",{\"text\":\"El PC del Buwuron \",\"color\":\"yellow\"},{\"text\":\"SI\",\"color\":\"green\"},{\"text\":\" se va a apagar al cerrar el server.\",\"color\":\"yellow\"}]";
                String[] o = new String[]{output};
                Ejecutar(o, writer);
                apagar = true;
            }
            if (line.contains("!off")) {
                String output = "/tellraw @a [\"\",{\"text\":\"El PC del Buwuron \",\"color\":\"yellow\"},{\"text\":\"NO\",\"color\":\"dark_red\"},{\"text\":\" se va a apagar al cerrar el server.\",\"color\":\"yellow\"}]";
                String[] o = new String[]{output};
                Ejecutar(o, writer);
                apagar = false;
            }
            if (line.contains("!cancel")) {
                if (TempEncendido) {
                    String output = "/setblock -1170 10 638 air";
                    String output2 = "/scoreboard players set segundos Temporizador 0";
                    String aviso = "/tellraw @a [\"\",{\"text\":\"Temporizador \",\"color\":\"yellow\"},{\"text\":\"cancelado\",\"color\":\"green\"},{\"text\":\"...\",\"color\":\"yellow\"}]";
                    String[] o = new String[]{output, output2, aviso, showMuertes};
                    Ejecutar(o, writer);
                    TempEncendido = false;
                } else {
                    String aviso = "/tellraw @a [\"\",{\"text\":\"No hay temporizador activado.\",\"color\":\"yellow\"},{\"text\":\"\",\"color\":\"green\"},{\"text\":\"...\",\"color\":\"yellow\"}]";
                    String[] o = new String[]{aviso};
                    Ejecutar(o, writer);
                }

            }
            if (line.contains("zzzz")) {
                String title = "/title @a title {\"text\":\"DUERMAN CONCHETUMARE\",\"color\":\"dark_red\"}";
                String[] o = new String[]{title};
                Ejecutar(o, writer);

            }
            if (line.contains("!lobby")) {
                String coord = "/execute in minecraft:overworld as " + Name + " run tp @s -1212 68 588 90 0";
                String mensaje = "/tellraw @a [\"\",{\"selector\":\"" + Name + "\"},{\"text\":\" se fue pal lobby.\"}]";
                String[] o = new String[]{coord, mensaje};
                Ejecutar(o, writer);
            }

            if (line.contains("!t =")) { //---------------INICIA TEMPORIZADOR---------------
                String[] a = line.split("=");
                System.out.println(a[0] + "+" + a[1]);
                String output = "/scoreboard players set segundos Temporizador " + a[1];
                String output2 = "/scoreboard players set segundos milisegundos 0";
                String output3 = "/setblock -1170 10 638 minecraft:redstone_block";
                String aviso = "/tellraw @a [\"\",{\"text\":\"El server se cerrará en\",\"color\":\"yellow\"},{\"text\":\" " + a[1] + "\",\"color\":\"green\"},{\"text\":\" segundos...\",\"color\":\"yellow\"}]";
                String[] o = new String[]{output, output2, output3, aviso, showTemporizador};
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
                String output = "/setblock -1170 10 638 air";
                String output2 = "/scoreboard players set segundos Temporizador 0";
                String[] o = new String[]{output, output2, showMuertes, "stop"};
                Ejecutar(o, writer);
            }
        }

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
