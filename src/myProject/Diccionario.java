package myProject;

import java.util.ArrayList;
import java.util.Random;

public class Diccionario {
    FileManager fileManager;
    private ArrayList<String> diccionario = new ArrayList<String>();
    private ArrayList<String> usuariosRegistrados = new ArrayList<String>();
    private ArrayList<String> palabrasMemorizar = new ArrayList<String>();
    private ArrayList<String> palabrasDistraccion = new ArrayList<String>();
    String userName;

    public Diccionario(String userName) {
        this.userName=userName;
        fileManager = new FileManager();
        diccionario = fileManager.lecturaFile("bancoPalabras");
        usuariosRegistrados = fileManager.lecturaFile("usuarios");
    }

    /**
     * returns the words to memorize for the level
     * @param cantidadPalabras count of words to memorize
     * @return words to memorize
     */
    public ArrayList<String> getPalabrasMemorizar(int cantidadPalabras) {
        for (int i = 0; i < cantidadPalabras; i++) {
            Random aleatory = new Random();
            int indexAletorio = aleatory.nextInt(diccionario.size());
            palabrasMemorizar.add(diccionario.get(indexAletorio));
            diccionario.remove(indexAletorio);
        }
        return palabrasMemorizar;
    }

    /**
     * returns the distraction words for the level
     * @param cantidadPalabras count of distraction words
     * @return distraction words
     */
    public ArrayList<String> getPalabrasDistraccion(int cantidadPalabras) {
        for (int i = 0; i < cantidadPalabras; i++) {
            Random aleatory = new Random();
            int indexAletorio = aleatory.nextInt(diccionario.size());
            palabrasDistraccion.add(diccionario.get(indexAletorio));
            diccionario.remove(indexAletorio);
        }
        return palabrasDistraccion;
    }

    /**
     * search for a user within the registered users
     * @return whether or not
     */
    public boolean isUser() {
        boolean estaRegistrado = false;
        if (buscarUsuario()!=-1){
            estaRegistrado=true;
        }
        return estaRegistrado;
    }

    /**
     * join a user inside the registered users
     */
    public void newUser() {
        fileManager.escribirTexto(userName + ": " + 0);
    }

    /**
     * search for a user within the registered users
     * @return the posicion where the user it is
     */
    private int buscarUsuario(){
        int posicion=-1;
        for (int i = 0; i < usuariosRegistrados.size(); i++) {
            String thisUser = usuariosRegistrados.get(i).substring(0, usuariosRegistrados.get(i).lastIndexOf(":"));
            if (thisUser==userName){
                posicion=i;
                break;
            }
        }
        return posicion;
    }

    /**
     * Returns the levels exceeded by the user
     * @return levels exceeded by the user
     */
    public int getNivelesUser(){
        String usuario =usuariosRegistrados.get(buscarUsuario());
        String nivelesEnString=usuario.substring(usuario.lastIndexOf(":")+2);
        return Integer.valueOf (nivelesEnString);
    }
    public int setNivelUser(){
        fileManager.modificarNivelAprobado(getNivelesUser()+1,buscarUsuario());
        return getNivelesUser();
    }
}
