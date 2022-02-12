package myProject;

import java.util.ArrayList;
import java.util.Random;

public class ModelGame {
    Diccionario diccionario;
    String nombreUsuario,palabraAMostrar;
    int nivelesAprobados, nivelActual, palabrasEnNivel, aciertos, flagMemorizar, flagNivel;
    double porcentajeAciertos;
    ArrayList<String> palabrasNivel,palabrasMemorizar, palabrasDistraccion;
    boolean nuevoUsuario;

    /**
     *assigns initial values that are essential for running the game
     * check if the user entered is a new user and if it is not, order its creation
     * @param nombreUsuario name of the current game user
     */
    ModelGame(String nombreUsuario){
        nuevoUsuario=false;
        this.nombreUsuario=nombreUsuario;
        diccionario= new Diccionario(nombreUsuario);
        if (diccionario.isUser()){
            nivelesAprobados= diccionario.getNivelesUser();
        }else{
            diccionario.newUser();
            nuevoUsuario=true;
            nivelesAprobados=0;
        }
        aciertos=0;
        flagMemorizar=0;
        flagNivel=0;
        setNivelActual();
        if (nivelesAprobados<8) {
            nivelActual = nivelesAprobados + 1;
        }else {
            nivelActual = nivelesAprobados;
        }

    }

    /**
     * advance the level of the game
     */
    private void setNivelActual(){
        aciertos=0;
        nivelActual=nivelesAprobados+1;
        setPalabrasEnNivel();
        palabraAMostrar="";
        palabrasDistraccion=diccionario.getPalabrasDistraccion(palabrasEnNivel/2);
        palabrasMemorizar=diccionario.getPalabrasMemorizar(palabrasEnNivel/2);
        palabrasNivel= new ArrayList<>();
        setPalabrasNivel();
    }

    /**
     * check if it is sufficient to pass the level
     */
    private void setNivelesAprobados(){
        /**
         * when the user won
         */
        if(aciertos>=palabrasEnNivel*porcentajeAciertos){
            nivelesAprobados= diccionario.setNivelUser();
            setNivelActual();
            flagNivel=0;
            flagMemorizar=0;
        }

        /**
         * when the user lost
         */

        else{
            flagNivel=0;
            flagMemorizar=0;
            aciertos=0;
            setPalabrasEnNivel();
            aciertos=0;
            palabraAMostrar="";
            palabrasDistraccion=diccionario.getPalabrasDistraccion(palabrasEnNivel/2);
            palabrasMemorizar=diccionario.getPalabrasMemorizar(palabrasEnNivel/2);
            palabrasNivel= new ArrayList<>();
            setPalabrasNivel();
        }

    }

    /**
     * assigns the percent of hits needed to change of level
     */
    private void setPorcentajeAciertos(){
        switch (nivelActual){
            case 1, 2 -> porcentajeAciertos=0.7;
            case 3-> porcentajeAciertos=0.75;
            case 4, 5 -> porcentajeAciertos=0.8;
            case 6-> porcentajeAciertos=0.85;
            case 7, 8 -> porcentajeAciertos=0.9;
            case 9-> porcentajeAciertos=0.95;
            case 10->porcentajeAciertos=1;
        }
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
     * creates a random pattern to show distraction words and the words to memorize in the game
     */
    private void setPalabrasNivel(){

        ArrayList<String> palabrasAMemorizar=new ArrayList<>(palabrasMemorizar);
        ArrayList<String> palabrasADistraer=new ArrayList<>(palabrasDistraccion);
        while (palabrasNivel.size()<palabrasEnNivel) {
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
    public String getPalabrasNivel(){
        if (flagNivel<palabrasNivel.size()){
            palabraAMostrar=palabrasNivel.get(flagNivel);
            flagNivel++;
        }else{
            setNivelesAprobados();
            palabraAMostrar="";
        }
        return palabraAMostrar;
    }

    /**
     * check if it's a word to memorize
     * @param palabra word to check
     * @return whether
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
     * @param respuestaUsuario user's answer
     */
    public void setAciertos( boolean respuestaUsuario){
        boolean respuestaCorrecta= esPalabraAMemorizar(palabraAMostrar);
        if (respuestaUsuario == respuestaCorrecta){
            aciertos++;
        }
    }


    /**
     * @return the number of success
     */
    public int  getAciertos(){
        return aciertos;
    }

    /**
     * say if the actual user is whether a new user
     * @return whether is a new user
     */
    public boolean isNuevoUsuario() {
        return nuevoUsuario;
    }

    /**
     * @return the actual level of the game
     */
    public int getNivelActual() {
        return nivelActual;
    }

    /**
     * @return words to be memorized
     */
    public String getPalabrasMemorizar() {
        String palabraMemorizar="";
        if (flagMemorizar<palabrasMemorizar.size()){
            palabraMemorizar=palabrasMemorizar.get(flagMemorizar);
            flagMemorizar++;
        }
        return palabraMemorizar;
    }
    public int getAciertosNivel(){
        setPorcentajeAciertos();
        return (int) Math.ceil(palabrasEnNivel * porcentajeAciertos);
    }
}