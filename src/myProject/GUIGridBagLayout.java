package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @autor Fabian Lopez - fabian.muriel@correounivalle.edu.co
 *  @autor Juan Jose - Viafara.juan@correounivalle.edu.co
 * @version v.1.0.0 date:03/02/2022
 */
public class GUIGridBagLayout extends JFrame {

    private Header headerProject;
    private JButton si, no, continuar, registrar;
    private JPanel panelInteraccion, panelDatos;
    private JTextArea datos, nivel, inicio, palabra, tiempo, aciertos, espacioVacio;
    private Escucha escucha;
    private ModelGame modelGame;
    private ImageIcon imageBienvenido;
    private JLabel imagen;
    private JTextField nombreUsuario;
    private GridBagConstraints constrains, constrainsDatos, constrainsInteraccion;
    private Timer timer, temporizador ;
    private int segundos, interfaz;

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

        headerProject = new Header("", Color.BLACK);
        headerProject.setPreferredSize(new Dimension(20, 30));
        headerProject.setVisible(false);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.HORIZONTAL;
        add(headerProject, constrains);


        imageBienvenido = new ImageIcon(getClass().getResource("/Resources/img.png"));
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

        timer = new Timer(5000, escucha);
        temporizador = new Timer(1000, escucha);

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
        private int counter;
        @Override
        public void actionPerformed(ActionEvent e) {
            String usuario = "";
            if(e.getSource()==registrar){
                usuario = nombreUsuario.getText();
                modelGame = new ModelGame(usuario);

                panelInteraccion.setPreferredSize(new Dimension(396,500));
                panelDatos.setPreferredSize(new Dimension(264,500));
                
                headerProject.setVisible(true);
                headerProject.setText("Debes memorizar las siguientes "+ modelGame.palabrasEnNivel/2 +" palabras");
                
                panelInteraccion.removeAll();
                panelDatos.removeAll();

                nivel = new JTextArea();
                nivel.setText("Nivel: "+modelGame.getNivelActual());
                nivel.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,25));
                nivel.setBackground(null);
                nivel.setEditable(false);
                constrainsDatos.gridx = 0;
                constrainsDatos.gridy = 0;
                constrainsDatos.fill =  GridBagConstraints.NONE;
                constrainsDatos.anchor = GridBagConstraints.CENTER;
                panelDatos.add(nivel, constrainsDatos);

                aciertos= new JTextArea();
                aciertos.setText("");
                aciertos.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,30));
                aciertos.setSize(200,30);
                aciertos.setBackground(null);
                aciertos.setEditable(false);
                constrainsInteraccion.gridx = 0;
                constrainsInteraccion.gridy = 0;
                constrainsInteraccion.gridwidth=1;
                constrainsInteraccion.weightx=198;
                constrainsInteraccion.weighty=50;
                constrainsInteraccion.anchor = GridBagConstraints.CENTER;
                panelInteraccion.add(aciertos, constrainsInteraccion);

                segundos = 5;
                tiempo.setText(String.valueOf("    "+segundos+"    "));
                tiempo.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,30));
                tiempo.setSize(200,30);
                tiempo.setBackground(new Color(34, 85, 85));
                tiempo.setEditable(false);
                constrainsInteraccion.gridx = 1;
                constrainsInteraccion.gridy = 0;
                constrainsInteraccion.gridwidth=1;
                constrainsInteraccion.weightx=180;
                constrainsInteraccion.weighty=50;
                constrainsInteraccion.anchor = GridBagConstraints.CENTER;
                panelInteraccion.add(tiempo, constrainsInteraccion);

                palabra = new JTextArea();
                palabra.setText(modelGame.getPalabrasMemorizar());
                palabra.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,50));
                palabra.setBackground(null);
                palabra.setEditable(false);
                constrainsInteraccion.gridx = 0;
                constrainsInteraccion.gridy = 1;
                constrainsInteraccion.gridwidth=2;
                constrainsInteraccion.weighty=200;
                constrainsInteraccion.fill =  GridBagConstraints.NONE;
                constrainsInteraccion.anchor = GridBagConstraints.SOUTH;
                panelInteraccion.add(palabra, constrainsInteraccion);


                espacioVacio= new JTextArea();
                espacioVacio.setText("");
                espacioVacio.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,30));
                espacioVacio.setSize(200,30);
                espacioVacio.setBackground(null);
                espacioVacio.setEditable(false);
                constrainsInteraccion.gridx = 0;
                constrainsInteraccion.gridy = 2;
                constrainsInteraccion.gridwidth=2;
                constrainsInteraccion.weighty=250;
                constrainsInteraccion.anchor = GridBagConstraints.NORTH;
                panelInteraccion.add(espacioVacio, constrainsInteraccion);

                interfaz=2;
                timer.start();
                temporizador.start();

                repaint();
                revalidate();
            }
            else if(e.getSource()==timer){
                palabra.setText(modelGame.getPalabrasMemorizar());

                //cuando ya no hay mas palabras para memorizar
                if (modelGame.getPalabrasMemorizar()==""&&interfaz==2){
                    timer.stop();
                    temporizador.stop();
                    interfaz=3;

                    panelInteraccion.remove(espacioVacio);

                    continuar.setVisible(true);
                    constrainsInteraccion.gridx = 0;
                    constrainsInteraccion.gridy = 2;
                    constrainsInteraccion.gridwidth=2;
                    constrainsInteraccion.weighty=250;
                    constrainsInteraccion.anchor = GridBagConstraints.NORTH;
                    panelInteraccion.add(continuar, constrainsInteraccion);
                    

                    segundos=0;
                    tiempo.setText(String.valueOf("    "+segundos+"    "));

                    palabra.setText("¿Deseas continuar a \nla siguiente fase?");
                    palabra.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,40));
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
                tiempo.setText(String.valueOf("    "+segundos+"    "));

            }
            //crear el boton continuar, desde ahi pasas a la tercera interfaz (interfaz=3),
            else if(e.getSource()==continuar){

                segundos=7;
                tiempo.setText(String.valueOf("    "+segundos+"    "));
                interfaz=3;
                panelInteraccion.remove(continuar);
                aciertos.setText("Aciertos: "+modelGame.getAciertos());
                palabra.setText(modelGame.getPalabrasNivel());
                headerProject.setText("¿Debias memorizar esta palabra?");

                datos = new JTextArea();
                datos.setText("Debes acertar "+modelGame.getAciertosNivel()+"\nPara ganar el nivel");
                datos.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,25));
                datos.setBackground(null);
                datos.setEditable(false);
                constrainsDatos.gridx = 0;
                constrainsDatos.gridy = 1;
                constrainsDatos.weighty = 400;
                constrainsDatos.gridwidth = 1;
                constrainsDatos.fill =  GridBagConstraints.NONE;
                constrainsDatos.anchor = GridBagConstraints.CENTER;
                panelDatos.add(datos,constrainsDatos);

                si.setVisible(true);
                constrainsInteraccion.gridx = 0;
                constrainsInteraccion.gridy = 2;
                constrainsInteraccion.gridwidth=1;
                constrainsInteraccion.weighty=250;
                constrainsInteraccion.anchor = GridBagConstraints.CENTER;
                panelInteraccion.add(si, constrainsInteraccion);

                no.setVisible(true);
                constrainsInteraccion.gridx = 1;
                constrainsInteraccion.gridy = 2;
                constrainsInteraccion.gridwidth=1;
                constrainsInteraccion.weighty=250;
                constrainsInteraccion.anchor = GridBagConstraints.CENTER;
                panelInteraccion.add(no, constrainsInteraccion);

                timer.setDelay(7000);
                timer.start();
                temporizador.start();


            }
            // alli haces algo similar a lo que hiciste para pasar a la segunda interfaz en el boton 'registrarse'
            //dejas lo que nesecitas quitas lo que no.
        }

    }
}
