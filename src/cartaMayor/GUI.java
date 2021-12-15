package cartaMayor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private static final String MENSAJE_INICIO="BIENVENIDO AL JUEGO DE CARTA MAYOR \n"
            +"Oprime el boton lanzar para iniciar el juego"+
            "\n si tu cartaMayor.carta es mayor a la del sistema, ganarás"
            +"\nSi tu cartaMayor.carta es menor a la del sistema, perderas"
            +"\nEn caso de empate el juego se decidirá por el palo: "
            +"\nOros es mayor que Copas, Copas es mayor que Espadas y Espadas es mayor que Bastos."
           ;
    private cartaMayor.Header headerProject;
    private JLabel cartaJugador,cartaMaquina;
    private JButton sacar,ayuda, salir, Oponente;
    private JPanel panelCartas,panelCartasOponente;
    private ImageIcon imageCard;
    private JTextArea mensajeSalida;
    private Escucha escucha;
    private modelCarta modelCarta;


    public GUI() {
         intGUI();
        //configurar ventana
        this.setTitle("Carta Mayor");
        this.pack();
        this.setResizable(true);
        //this.setSize(600, 540);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void intGUI() {
//create listener object or control object
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints=new GridBagConstraints();
        escucha= new Escucha();
        modelCarta =new modelCarta();
        //set up Jcomponents
        headerProject= new Header("Mesa Carta Mayor", Color.black);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);
        ayuda= new JButton(" ? ");
        ayuda.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        salir= new JButton("Salir");
        salir.addActionListener(escucha);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir,constraints);




        Oponente=  new JButton(" Carta Oponente");
        Oponente.addActionListener(escucha);
        Oponente.setEnabled(false);
        constraints.gridx=1;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(Oponente,constraints);

        imageCard= new ImageIcon(getClass().getResource("/resources/dado.png"));

        cartaJugador= new JLabel(imageCard);
        cartaMaquina= new JLabel(imageCard);


        panelCartas = new JPanel();
        panelCartas.setPreferredSize(new Dimension(300,180));
        panelCartas.setBorder(BorderFactory.createTitledBorder("Tus Cartas "));
        panelCartas.add(cartaJugador);


        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelCartas,constraints);

        panelCartasOponente=new JPanel();
        panelCartasOponente.setPreferredSize(new Dimension(300,180));
        panelCartasOponente.setBorder(BorderFactory.createTitledBorder("Cartas oponente "));
        panelCartasOponente.add(cartaMaquina);

        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelCartasOponente,constraints);


        sacar =new JButton("Sacar Carta");
        sacar.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.FIRST_LINE_START;
        add(sacar,constraints);

        mensajeSalida= new JTextArea(7,31);
        mensajeSalida.setText("Usa el boton (?) para ver las reglas del juego");
        mensajeSalida.setBorder(null);
        mensajeSalida.setBackground(null);
        mensajeSalida.setEditable(false);
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(mensajeSalida,constraints);
    }

 
    public static void main(String[] args) {
        // Ejecución del programa

        EventQueue.invokeLater(()-> {


            GUI presentacion = new GUI();


        });

    }
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==sacar){
                modelCarta.calcularTiroJugador();
                int[] caras= modelCarta.getCaras();
                int palo= modelCarta.getPaloJugador();

                imageCard= new ImageIcon(getClass().getResource("/resources/"+caras[0]+"-"+palo+".png"));
                cartaJugador.setIcon(imageCard);
                Oponente.setEnabled(true);
            }else{
                if(e.getSource()==Oponente)
                {
                    modelCarta.calcularTiroMaquina();
                    int[] caras= modelCarta.getCaras();
                    int palomaquina= modelCarta.getPaloMaquina();
                    imageCard= new ImageIcon(getClass().getResource("/resources/"+caras[1]+"-"+palomaquina+".png"));
                    cartaMaquina.setIcon(imageCard);
                    modelCarta.determinarJuego();
                    mensajeSalida.setRows(4);
                    Oponente.setEnabled(false);
                    mensajeSalida.setText(modelCarta.getEstadoToString()[1]);
                }else{



                if(e.getSource()==ayuda){
                    JOptionPane.showMessageDialog(null,MENSAJE_INICIO);
                }else{
                    System.exit(0);
                }
                }
            }


        }
}
}
