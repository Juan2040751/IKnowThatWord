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

    public ArrayList<String> getPalabrasMemorizar(int cantidadPalabras) {
        for (int i = 0; i < cantidadPalabras; i++) {
            Random aleatory = new Random();
            int indexAletorio = aleatory.nextInt(diccionario.size());
            palabrasMemorizar.add(diccionario.get(indexAletorio));
            diccionario.remove(indexAletorio);
        }
        return palabrasMemorizar;
    }

    public ArrayList<String> getPalabrasDistraccion(int cantidadPalabras) {
        for (int i = 0; i < cantidadPalabras; i++) {
            Random aleatory = new Random();
            int indexAletorio = aleatory.nextInt(diccionario.size());
            palabrasDistraccion.add(diccionario.get(indexAletorio));
            diccionario.remove(indexAletorio);
        }
        return palabrasDistraccion;
    }

    public boolean isUser(String usuario) {
        boolean estaRegistrado = false;
        if (buscarUsuario(usuario)!=-1){
            estaRegistrado=true;
        }
        return estaRegistrado;
    }

    public void newUser(String userName, int nivelesSuperados) {
        fileManager.escribirTexto(userName + ": " + nivelesSuperados);
    }
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
    public int getNivelesUser(String userName){
        String usuario =usuariosRegistrados.get(buscarUsuario(userName));
        String nivelesEnString=usuario.substring(usuario.lastIndexOf(":")+2);
        return Integer.valueOf (nivelesEnString);
    }
}

