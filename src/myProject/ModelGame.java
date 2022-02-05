package myProject;

import java.util.ArrayList;
import java.util.Random;

public class ModelGame {
    Diccionario diccionario;
    String nombreUsuario;
    int nivelesAprobados, nivelActual, palabrasEnNivel, aciertos;
    ArrayList<String> palabrasNivel,palabrasMemorizar, palabrasDistraccion;

    ModelGame(String nombreUsuario){
        this.nombreUsuario=nombreUsuario;
        diccionario= new Diccionario(nombreUsuario);
        if (diccionario.isUser()){
            nivelesAprobados= diccionario.getNivelesUser();
        }else{
            diccionario.newUser();
            nivelesAprobados=0;
        }
        aciertos=0;
        nivelActual=nivelesAprobados+1;
        setPalabrasEnNivel();
        palabrasDistraccion=diccionario.getPalabrasDistraccion(palabrasEnNivel/2);
        palabrasMemorizar=diccionario.getPalabrasMemorizar(palabrasEnNivel/2);
        palabrasNivel= new ArrayList<>();
    }

    /**
     * advance the level of the game  and save the new info of the player in the file text
     */
    private void setNivelActual(){
        nivelesAprobados= diccionario.setNivelUser();
        nivelActual=nivelesAprobados+1;
    }

    /**
     * assigns the number of words to be displayed in the game
     */
    private void setPalabrasEnNivel(){
        switch (nivelActual){
            case 1-> palabrasEnNivel=20;
            case 2-> palabrasEnNivel=40;
            case 3-> palabrasEnNivel=50;
            case 4-> palabrasEnNivel=60;
            case 5-> palabrasEnNivel=70;
            case 6-> palabrasEnNivel=80;
            case 7-> palabrasEnNivel=100;
            case 8-> palabrasEnNivel=120;
            case 9-> palabrasEnNivel=140;
            case 10->palabrasEnNivel=200;
        }
    }

    /**
     * creates a random pattern to show distraction words and words to memorize in the game
     */
    private void setPalabrasNivel(){

        ArrayList<String> palabrasAMemorizar=palabrasMemorizar;
        ArrayList<String> palabrasADistraer=palabrasDistraccion;

        for (int i=0;palabrasNivel.size()<palabrasEnNivel;i++){
            Random aleatory = new Random();

            int aletorio = aleatory.nextInt(0,3);
            for (int otherI=0;otherI<=aletorio&&palabrasAMemorizar.size()>0;otherI++){
                palabrasNivel.add(palabrasAMemorizar.get(0));
                palabrasAMemorizar.remove(0);
            }

            aletorio = aleatory.nextInt(0,3);
            for (int otherI=0;otherI<=aletorio && palabrasADistraer.size()>0;otherI++){
                palabrasNivel.add(palabrasADistraer.get(0));
                palabrasADistraer.remove(0);
            }
        }

    }

    /**
     * return the all the words to show in the game
     * @return arrayList with the words
     */
    public ArrayList<String> getPalabrasNivel(){
        setPalabrasNivel();
        return palabrasNivel;
    }

    /**
     * check if it's a word to memorize
     * @param palabra word to check
     * @return whether or not
     */
    private boolean esPalabraAMemorizar(String palabra){
        boolean esPalabraA= false;
        for (int i = 0; i < palabrasMemorizar.size() ; i++) {
            if (palabrasMemorizar.get(i).equals(palabra)){
                esPalabraA=true;
                break;
            }
        }
        return esPalabraA;
    }

    /**
     * check if the user's answer is correct
     * @param palabraActual word to review
     * @param respuestaUsuario user's answer
     */
    public void setAciertos(String palabraActual, boolean respuestaUsuario){
        boolean respuestaCorrecta= esPalabraAMemorizar(palabraActual);
        if (respuestaUsuario== respuestaCorrecta){
               aciertos++;
        }
    }

    /**
     * @return the number of success
     */
    public int  getAciertos(){
        return aciertos;
    }
}
