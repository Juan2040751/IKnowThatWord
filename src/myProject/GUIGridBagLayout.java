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
    private JTextArea aciertosRequeridos, nivel, inicio, palabraMemorizar;
    private Escucha escucha;
    private ModelGame modelGame;
    private ImageIcon imageBienvenido;
    private JLabel imagen;
    private JTextField nombreUsuario;
    private GridBagConstraints constrains, constrainsDatos, constrainsInteraccion;

    /**
     * Constructor of GUI class
     */
    public GUIGridBagLayout(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("I know that word!!");
        this.setSize(200,100);
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
        aciertosRequeridos = new JTextArea(2,2);
        inicio = new JTextArea(1,2);



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
        add(panelInteraccion,constrains);

        inicio.setText("REGISTRA TU USUARIO \nO CREA UNO");
        inicio.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,18));
        inicio.setBackground(null);
        inicio.setEditable(false);
        constrainsInteraccion.gridx = 0;
        constrainsInteraccion.gridy = 0;
        constrainsInteraccion.anchor = GridBagConstraints.LINE_START;
        panelInteraccion.add(inicio, constrainsInteraccion);

        panelDatos = new JPanel(new GridBagLayout());
        panelDatos.setBackground(new Color(196,196,196));
        panelDatos.setPreferredSize(new Dimension(330, 530));
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.fill = GridBagConstraints.VERTICAL;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelDatos,constrains);

        nombreUsuario = new JTextField();
        nombreUsuario.setPreferredSize(new Dimension(250,20));
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
            String usuario = "";
            if(e.getSource()==registrar){
                usuario = nombreUsuario.getText();
                modelGame = new ModelGame(usuario);

                headerProject = new Header("Debes memorizar las siguientes "+modelGame.palabrasEnNivel+" palabras", Color.BLACK);
                headerProject.setPreferredSize(new Dimension(20, 30));
                constrains.gridx = 0;
                constrains.gridy = 0;
                constrains.gridwidth = 2;
                constrains.fill = GridBagConstraints.HORIZONTAL;
                add(headerProject,constrains);
                panelInteraccion.remove(inicio);
                panelInteraccion.remove(registrar);
                panelInteraccion.remove(nombreUsuario);
                panelDatos.remove(imagen);

                nivel = new JTextArea();
                nivel.setText("Nivel: "+modelGame.getNivelActual());
                nivel.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,25));
                nivel.setBackground(null);
                nivel.setEditable(false);
                constrainsDatos.gridx = 0;
                constrainsDatos.gridy = 0;
                constrainsDatos.fill =  GridBagConstraints.BOTH;
                constrainsDatos.anchor = GridBagConstraints.CENTER;
                panelDatos.add(nivel, constrainsDatos);

                palabraMemorizar = new JTextArea();
                palabraMemorizar.setText(modelGame.getPalabrasMemorizar());
                palabraMemorizar.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,50));
                palabraMemorizar.setBackground(null);
                palabraMemorizar.setEditable(false);
                constrainsInteraccion.gridx = 0;
                constrainsInteraccion.gridy = 1;
                constrainsInteraccion.fill =  GridBagConstraints.NONE;
                constrainsInteraccion.anchor = GridBagConstraints.CENTER;
                panelInteraccion.add(palabraMemorizar, constrainsInteraccion);

                modelGame.

                panelInteraccion.setPreferredSize(new Dimension(396,530));
                panelDatos.setPreferredSize(new Dimension(264,530));


                repaint();
                revalidate();
            }
        }

    }
}
