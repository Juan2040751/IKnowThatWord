package myProject;


import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public static final String bancoPalabras="src/myProject/files/bancoPalabras.txt";
    public static final String usuarios="src/myProject/files/usuarios.txt";
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;


    public ArrayList<String> lecturaFile(String archivo) {
        String archivoALeer="";
        if (archivo=="bancoPalabras"){
            archivoALeer=bancoPalabras;
        }else if (archivo=="usuarios"){
            archivoALeer=usuarios;
        }
        ArrayList<String> frases = new ArrayList<String>();
        try {
            fileReader = new FileReader(archivoALeer);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line!=null){
                frases.add(line);
                line=input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return frases;
    }

    public void escribirTexto(String linea){
        try {
            fileWriter = new FileWriter(usuarios,true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
