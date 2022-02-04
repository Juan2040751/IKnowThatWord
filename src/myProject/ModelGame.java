package myProject;

public class ModelGame {
    Diccionario diccionario;
    String nombreUsuario;
    int nivelesAprobados, nivelActual;

    ModelGame(String nombreUsuario){
        this.nombreUsuario=nombreUsuario;
        diccionario= new Diccionario(nombreUsuario);
        if (diccionario.isUser()){
            nivelesAprobados= diccionario.getNivelesUser();
        }else{
            diccionario.newUser();
            nivelesAprobados=0;
        }
        nivelActual=nivelesAprobados+1;
    }
    private void actualizarNivel(){
        nivelesAprobados= diccionario.setNivelUser();
        nivelActual=nivelesAprobados+1;
    }
}
