package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for to graph the game
 *  @autor Fabian Lopez - fabian.muriel@correounivalle.edu.co
 *  @autor Juan Jose viafara - Viafara.juan@correounivalle.edu.co
 * @version v.1.0.0 date:03/02/2022
 */
public class GUIGridBagLayout extends JFrame {

    private Header headerProject;
    private JButton si, no, continuar, registrar;
    private JPanel panelInteraccion, panelDatos;
    private JTextArea datos;
    private JTextArea nivel;
    private JTextArea inicio;
    private JTextArea palabra;
    private JTextArea tiempo;
    private JTextArea aciertos;
    private JTextArea espacioVacio;
    private Escucha escucha;
    private ModelGame modelGame;
    private JLabel imagen;
    private JTextField nombreUsuario;
    private GridBagConstraints constrains, constrainsDatos, constrainsInteraccion;
    private Timer timer, temporizador ;
    private int segundos, interfaz, flag, aciertosNivel, aciertosParaNivel;

    /**
     * Constructor of GUI class
     */
    public GUIGridBagLayout(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("I know that word!!");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        constrains = new GridBagConstraints();
        constrainsInteraccion = new GridBagConstraints();
        constrainsDatos = new GridBagConstraints();
        //Create Listener Object and Control Object
        escucha = new Escucha();
        //Set up JComponents
        datos = new JTextArea(1, 2);
        inicio = new JTextArea(1, 2);
        tiempo = new JTextArea(1, 2);
        aciertos = new JTextArea(1, 2);
        espacioVacio=new JTextArea(1,2);

        interfaz=1;
        flag=0;

        headerProject = new Header("", Color.BLACK);
        headerProject.setPreferredSize(new Dimension(20, 30));
        headerProject.setVisible(false);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.HORIZONTAL;
        add(headerProject, constrains);


        ImageIcon imageBienvenido = new ImageIcon(getClass().getResource("/Resources/img.png"));
        imagen = new JLabel(imageBienvenido);

        panelInteraccion = new JPanel(new GridBagLayout());
        panelInteraccion.setPreferredSize(new Dimension(330, 530));
        panelInteraccion.setBackground(new Color(112, 219, 12));
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.VERTICAL;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelInteraccion, constrains);

        inicio.setText("REGISTRA TU USUARIO \nO CREA UNO");
        inicio.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 18));
        inicio.setBackground(null);
        inicio.setEditable(false);
        constrainsInteraccion.gridx = 0;
        constrainsInteraccion.gridy = 0;
        constrainsInteraccion.anchor = GridBagConstraints.LINE_START;
        panelInteraccion.add(inicio, constrainsInteraccion);

        panelDatos = new JPanel(new GridBagLayout());
        panelDatos.setBackground(new Color(196, 196, 196));
        panelDatos.setPreferredSize(new Dimension(330, 530));
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.fill = GridBagConstraints.VERTICAL;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelDatos, constrains);

        nombreUsuario = new JTextField();
        nombreUsuario.setPreferredSize(new Dimension(250, 20));
        constrainsInteraccion.gridx = 0;
        constrainsInteraccion.gridy = 1;
        constrainsInteraccion.anchor = GridBagConstraints.WEST;
        panelInteraccion.add(nombreUsuario, constrainsInteraccion);

        constrainsInteraccion.gridx = 0;
        constrainsInteraccion.gridy = 0;
        constrainsInteraccion.anchor = GridBagConstraints.CENTER;
        panelDatos.add(imagen, constrainsInteraccion);

        registrar = new JButton("Iniciar");
        registrar.addActionListener(escucha);
        constrainsInteraccion.gridx = 0;
        constrainsInteraccion.gridy = 2;
        constrainsInteraccion.anchor = GridBagConstraints.SOUTH;
        panelInteraccion.add(registrar, constrainsInteraccion);



        continuar = new JButton("Continuar");
        continuar.addActionListener(escucha);

        si = new JButton("Si");
        si.addActionListener(escucha);

        no = new JButton("No");
        no.addActionListener(escucha);


        temporizador = new Timer(1000, escucha);
        timer = new Timer(5000, escucha);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /**
             * 'registrar' button, responsible for calling modelGame to save the user and move to the next phase of the game.
             */
            if(e.getSource()==registrar){

                if (!nombreUsuario.getText().equals("")) {
                    if (flag == 0) {

                        modelGame = new ModelGame(nombreUsuario.getText());
                        panelInteraccion.remove(nombreUsuario);
                        headerProject.setVisible(true);
                        panelInteraccion.remove(inicio);
                        flag=1;
                        panelDatos.removeAll();

                        panelInteraccion.setPreferredSize(new Dimension(396, 500));
                        panelDatos.setPreferredSize(new Dimension(264, 500));

                        nivel = new JTextArea();
                        nivel.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 25));
                        nivel.setBackground(null);
                        nivel.setEditable(false);
                        constrainsDatos.gridx = 0;
                        constrainsDatos.gridy = 0;
                        constrainsDatos.fill = GridBagConstraints.NONE;
                        constrainsDatos.anchor = GridBagConstraints.CENTER;
                        panelDatos.add(nivel, constrainsDatos);

                        aciertos = new JTextArea();
                        aciertos.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 30));
                        aciertos.setSize(200, 30);
                        aciertos.setBackground(null);
                        aciertos.setEditable(false);
                        constrainsInteraccion.gridx = 0;
                        constrainsInteraccion.gridy = 0;
                        constrainsInteraccion.gridwidth = 1;
                        constrainsInteraccion.weightx = 180;
                        constrainsInteraccion.weighty = 50;
                        constrainsInteraccion.anchor = GridBagConstraints.CENTER;
                        panelInteraccion.add(aciertos, constrainsInteraccion);

                        tiempo = new JTextArea();
                        tiempo.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 30));
                        tiempo.setSize(200, 30);
                        tiempo.setBackground(new Color(34, 85, 85));
                        tiempo.setEditable(false);
                        constrainsInteraccion.gridx = 1;
                        constrainsInteraccion.gridy = 0;
                        constrainsInteraccion.gridwidth = 1;
                        constrainsInteraccion.weightx = 180;
                        constrainsInteraccion.weighty = 50;
                        constrainsInteraccion.anchor = GridBagConstraints.CENTER;
                        panelInteraccion.add(tiempo, constrainsInteraccion);

                        palabra = new JTextArea();
                        palabra.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 50));
                        palabra.setBackground(null);
                        palabra.setEditable(false);
                        constrainsInteraccion.gridx = 0;
                        constrainsInteraccion.gridy = 1;
                        constrainsInteraccion.gridwidth = 2;
                        constrainsInteraccion.weighty = 200;
                        constrainsInteraccion.fill = GridBagConstraints.NONE;
                        constrainsInteraccion.anchor = GridBagConstraints.SOUTH;
                        panelInteraccion.add(palabra, constrainsInteraccion);

                        espacioVacio = new JTextArea();
                        espacioVacio.setText("");
                        espacioVacio.setFont(new Font(Font.DIALOG, Font.BOLD + Font.ITALIC, 30));
                        espacioVacio.setSize(200, 30);
                        espacioVacio.setBackground(null);
                        espacioVacio.setEditable(false);
                        constrainsInteraccion.gridx = 0;
                        constrainsInteraccion.gridy = 2;
                        constrainsInteraccion.gridwidth = 2;
                        constrainsInteraccion.weighty = 250;
                        constrainsInteraccion.anchor = GridBagConstraints.NORTH;
                        panelInteraccion.add(espacioVacio, constrainsInteraccion);
                    }
                    else {
                        timer.addActionListener(escucha);
                        tiempo.setVisible(true);
                        datos.setVisible(false);
                        timer.setDelay(5000);
                        timer.setInitialDelay(5000);
                    }

                    headerProject.setText("Debes memorizar las siguientes " + modelGame.palabrasEnNivel / 2 + " palabras");

                    registrar.setVisible(false);

                    nivel.setText("Nivel: " + modelGame.getNivelActual());
                    aciertos.setText("");

                    segundos = 5;
                    tiempo.setText("    " + segundos + "    ");

                    palabra.setText(modelGame.getPalabrasMemorizar());

                    espacioVacio.setVisible(true);

                    interfaz = 2;

                    timer.start();
                    temporizador.start();

                }else{
                    inicio.setText("Debes ingresar un \nnombre de usuario");
                }
                /**
                 * 'timer' is responsible for keeping time between each word
                 */
            }
            else if(e.getSource()==timer){
                if (interfaz==2){
                    palabra.setText(modelGame.getPalabrasMemorizar());

                    //cuando ya no hay mas palabras para memorizar
                    if (palabra.getText().equals("")){
                        timer.stop();
                        temporizador.stop();
                        interfaz=3;

                        espacioVacio.setVisible(false);

                        continuar.setVisible(true);
                        constrainsInteraccion.gridx = 0;
                        constrainsInteraccion.gridy = 2;
                        constrainsInteraccion.gridwidth=2;
                        constrainsInteraccion.weighty=250;
                        constrainsInteraccion.anchor = GridBagConstraints.NORTH;
                        panelInteraccion.add(continuar, constrainsInteraccion);


                        segundos=0;
                        tiempo.setText("    " + segundos + "    ");

                        palabra.setText("¿Deseas continuar a \nla siguiente fase?");
                        palabra.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,40));
                    }
                }
                else if (interfaz==3){
                    getPalabraNivel();

                }

            }
            else if (e.getSource()==temporizador){
                segundos--;
                if (segundos==0&&interfaz==2){
                    segundos=5;
                }
                else if(segundos==0&&interfaz==3){
                    segundos=7;
                }
                tiempo.setText("    " + segundos + "    ");

            }
            /**
             * 'continuar' button, responsible for moving to the next stage of the game when you run out of words to display and when you pass or repeat a level.
             */
            else if(e.getSource()==continuar){

                segundos=7;
                tiempo.setText("    " + segundos + "    ");

                interfaz=3;
                continuar.setVisible(false);
                aciertos.setText("Aciertos: "+modelGame.getAciertos());
                getPalabraNivel();
                headerProject.setText("¿Debias memorizar esta palabra?");

                datos = new JTextArea();
                datos.setText("Debes acertar "+modelGame.getAciertosNivel()+"\nPara ganar el nivel");
                datos.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,25));
                datos.setBackground(null);
                datos.setVisible(true);
                datos.setEditable(false);
                constrainsDatos.gridx = 0;
                constrainsDatos.gridy = 1;
                constrainsDatos.weighty = 400;
                constrainsDatos.gridwidth = 1;
                constrainsDatos.fill =  GridBagConstraints.NONE;
                constrainsDatos.anchor = GridBagConstraints.CENTER;
                panelDatos.add(datos,constrainsDatos);
                aciertosParaNivel=modelGame.getAciertosNivel();

                si.setVisible(true);
                constrainsInteraccion.gridx = 0;
                constrainsInteraccion.gridy = 2;
                constrainsInteraccion.gridwidth=1;
                constrainsInteraccion.weighty=250;
                constrainsInteraccion.anchor = GridBagConstraints.NORTH;
                panelInteraccion.add(si, constrainsInteraccion);

                no.setVisible(true);
                constrainsInteraccion.gridx = 1;
                constrainsInteraccion.gridy = 2;
                constrainsInteraccion.gridwidth=1;
                constrainsInteraccion.weighty=250;
                constrainsInteraccion.anchor = GridBagConstraints.NORTH;
                panelInteraccion.add(no, constrainsInteraccion);

                timer.setDelay(7000);
                timer.setInitialDelay(7000);
                timer.restart();
                temporizador.start();


            }
            /**
             * 'si' button, responsible for getting it right
             */
            else if(e.getSource()==si){
                modelGame.setAciertos(true);
                aciertos.setText("Aciertos: "+modelGame.getAciertos());
                getPalabraNivel();
                segundos=7;
                tiempo.setText("    " + segundos + "    ");
                timer.restart();
            }
            /**
             * 'no' button, responsible for getting it right
             */
            else if(e.getSource()==no){
                modelGame.setAciertos(false);
                aciertos.setText("Aciertos: "+modelGame.getAciertos());
                getPalabraNivel();
                segundos=7;
                tiempo.setText("    " + segundos + "    ");
                timer.restart();
            }

            repaint();
            revalidate();
        }

        /**
         * responsible for displaying the level you are at and the win/lose messages at the end of each phase.
         */
        private void getPalabraNivel(){
            aciertosNivel=modelGame.getAciertos();

            palabra.setText(modelGame.getPalabrasNivel());

            if (palabra.getText().equals("")){


                temporizador.stop();
                aciertos.setText("");
                tiempo.setVisible(false);
                registrar.setVisible(true);
                constrainsInteraccion.gridx = 0;
                constrainsInteraccion.gridy = 2;
                constrainsInteraccion.gridwidth=2;
                constrainsInteraccion.weighty=250;
                constrainsInteraccion.anchor = GridBagConstraints.NORTH;
                panelInteraccion.add(registrar, constrainsInteraccion);

                registrar.setText("nivel: "+modelGame.getNivelActual());
                si.setVisible(false);
                no.setVisible(false);


                if (aciertosNivel>=aciertosParaNivel){
                    datos.setText("Felicitaciones ganaste \nahora puedes avanzar de nivel");
                }else{
                    datos.setText("Perdiste, puedes \nvolver a intentarlo");
                }

                palabra.setText("¿Deseas continuar a \nal siguiente nivel?");
                palabra.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,40));

                segundos=0;
                tiempo.setText("    " + segundos + "    ");

                timer.stop();
                timer.removeActionListener(escucha);

                repaint();
                revalidate();
            }
        }

    }
}