package myProject;

import java.util.ArrayList;
import java.util.Random;

public class Diccionario {
    FileManager fileManager;
    private ArrayList<String> diccionario = new ArrayList<String>();
    private ArrayList<String> usuariosRegistrados = new ArrayList<String>();
    private ArrayList<String> palabrasMemorizar = new ArrayList<String>();
    private ArrayList<String> palabrasDistraccion = new ArrayList<String>();

    public Diccionario() {
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
     * @param usuario user to know if is within the registered users
     * @return whether or not
     */
    public boolean isUser(String usuario) {
        boolean estaRegistrado = false;
        if (buscarUsuario(usuario)!=-1){
            estaRegistrado=true;
        }
        return estaRegistrado;
    }

    /**
     * join a user inside the registered users
     * @param userName user to registered
     * @param nivelesSuperados levels exceeded by the user
     */
    public void newUser(String userName, int nivelesSuperados) {
        fileManager.escribirTexto(userName + ": " + nivelesSuperados);
    }

    /**
     * search for a user within the registered users
     * @param nombreUsuario user to know if is within the registered users
     * @return the posicion where the user it is
     */
    private int buscarUsuario(String nombreUsuario){
        int posicion=-1;
        for (int i = 0; i < usuariosRegistrados.size(); i++) {
            String thisUser = usuariosRegistrados.get(i).substring(0, usuariosRegistrados.get(i).lastIndexOf(":"));
            if (thisUser==nombreUsuario){
                posicion=i;
                break;
            }
        }
        return posicion;
    }

    /**
     * Returns the levels exceeded by the user
     * @param userName user to know the levels
     * @return levels exceeded by the user
     */
    public int getNivelesUser(String userName){
        String usuario =usuariosRegistrados.get(buscarUsuario(userName));
        String nivelesEnString=usuario.substring(usuario.lastIndexOf(":")+2);
        return Integer.valueOf (nivelesEnString);
    }
}

