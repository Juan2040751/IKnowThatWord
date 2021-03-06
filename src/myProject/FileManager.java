package myProject;


import java.io.*;
import java.util.ArrayList;

/**
 * Reads, Writes and modifies text files
 */
public class FileManager {
    public static final String bancoPalabras="src/myProject/files/bancoPalabras.txt";
    public static final String usuarios="src/myProject/files/usuarios.txt";
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;

    /**
     * scrolls through a text file, to enter the strings into an arraylist
     * @param archivo name of the file to scroll
     * @return arrayList with the strings
     */
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
                if (line.startsWith("--")){
                    line=input.readLine();
                }else{
                    frases.add(line);
                    line=input.readLine();
                }

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

    /**
     * writes a certain string inside the usuarios file
     * @param linea the string to write
     */
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

    /**
     * modifies the level approved by the user in the text file
     * @param nuevoNivel currently approved user levels
     * @param posicion position where the user is in the text file
     */
    public void modificarNivelAprobado( int nuevoNivel, int posicion){
        try {
            ArrayList<String> usuariosActulizados=lecturaFile("usuarios");
            usuariosActulizados.add(0,"--NombreDeUsuario: nivelAprobado--");
            posicion++;
            String usuarioAntiguo=usuariosActulizados.get(posicion);
            String usuarioActualizado = usuarioAntiguo.substring(0, usuarioAntiguo.lastIndexOf(":")+2) +  nuevoNivel;
            usuariosActulizados.remove(posicion);
            usuariosActulizados.add(posicion,usuarioActualizado);
            fileWriter = new FileWriter(usuarios,false);
            output = new BufferedWriter(fileWriter);
            for (int i=0;i<usuariosActulizados.size();i++){
                output.write(usuariosActulizados.get(i));
                output.newLine();

            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
