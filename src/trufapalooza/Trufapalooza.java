
package trufapalooza;

//Importes Realizados
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import javax.swing.*;
import java.util.Random;

//Los sig. import son para manejar música
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

class Ventana extends JFrame{
    //Atributos a usar
    //Imagenes
    public JLayeredPane panel; //Al ser LayeredPane se pueden manejar capas para colocar los diversos elementos
    public JLabel  fondo,fotoContador,contadorHongos,fotoCronometro,cronometro,Aviso; //Las etiquetas contienen las fotos/fondo/gif
    public JButton hongo1, hongo2, hongo3,hongoEspecial,start; //Son los interactivos que usará el jugador
    public ImageIcon imagenFondo, imagenHongo, imagenHongoEspecial, imagenContador, imagenCronometro,imagenStart; //Se carga la imagen en estos para que las etiquetas las usen
    private Temporizador2 tiempo2; //Atributo de la clase del juego
    public Clip clip; //Permite manejar la música
    

    
    //Constructor de la clase, se cargan los elementos a utilizar
    public Ventana() throws UnsupportedAudioFileException, LineUnavailableException, IOException { //Si no se colocan las excepciones no se puede manejar el archivo de la música
        try {
            ImageIcon icon; // Creo variable para poner mi icono de ventana
            setSize(1000, 547); // Defino las dimensiones de mi ventana, un poco mas grande en dimensión y para que salga correctamente el label de fondo
            setTitle("Trufapalooza"); // Deifinición de título

            setDefaultCloseOperation(EXIT_ON_CLOSE); // Cerrar programa al cerrar ventana
            setLocationRelativeTo(null); // Se coloca en el centro de la pantalla
            setResizable(false); // Para evitar que me redimensionen la ventana

            // Load the icon from the resources directory
            InputStream inputStream = getClass().getResourceAsStream("/trufapalooza/resources/rareTruffle.png");
            BufferedImage image = ImageIO.read(inputStream);
            icon = new ImageIcon(image);
            setIconImage(icon.getImage());

            agregarElementos(); // Método para agregar todos los elementos
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    
    public void agregarElementos() throws UnsupportedAudioFileException, IOException, LineUnavailableException { //Si no se colocan las excepciones no se puede manejar el archivo de la múscia
        colocarPanel();
        agregarFondo();
        agregarContadores();
        agregarHongo1();
        agregarHongo2();
        agregarHongo3();
        agregarHongoEspecial();
        agregarInicio();
        musicPlayer();
    }
    
    //Método colocar panel
    public void colocarPanel(){
        panel= new JLayeredPane(); //En vez de ser un panel normal, es uno con capas
        panel.setLayout(null); //Desactivo el diseño predeterminado del Panel y tengo mas libertad
        getContentPane().add(panel);//Agrega el panel

    }
    
    //Metodo para asignar un fondo
    //Fondo animado fiesta
    public void agregarFondo() {
        try {
            imagenFondo = new ImageIcon(getClass().getResource("/trufapalooza/resources/danceFloor.gif"));// Se carga el fondo animado

            fondo = new JLabel(); // Create a JLabel with the animated GIF
            fondo.setOpaque(true);// Al definir opaque, permite editar la etiqueta según sea necesario
            fondo.setIcon(imagenFondo);// Establecer el GIF como ícono
            fondo.setBounds(0, 0, 1000, 500);// Definir dimensiones y tamaño de la etiqueta

            panel.add(fondo, JLayeredPane.DEFAULT_LAYER);// Agregar el fondo a la capa predeterminada
            getContentPane().add(panel);// Agregar el panel al panel de contenido
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    // Hongo1
    public void agregarHongo1() {
        try {
            // Load the animated GIF using ImageIcon
            imagenHongo = new ImageIcon(getClass().getResource("/trufapalooza/resources/whiteMushroom.gif"));

            hongo1 = new JButton();
            hongo1.setBackground(new Color(0, 0, 0, 0)); // Transparent background
            hongo1.setOpaque(false);
            hongo1.setIcon(imagenHongo);
            hongo1.setBounds(113, 205, 246, 296);
            hongo1.setBorderPainted(false);
            panel.add(hongo1, Integer.valueOf(3));

            // Action listener to stop the process when clicked
            ActionListener oyenteDeAccion = (ActionEvent e) -> {
                tiempo2.parar(1); // Stop the process to avoid losing
            };

            hongo1.addActionListener(oyenteDeAccion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    
    // Hongo2
    public void agregarHongo2() {
        try {
            // Load the animated GIF using ImageIcon
            imagenHongo = new ImageIcon(getClass().getResource("/trufapalooza/resources/whiteMushroom.gif"));

            hongo2 = new JButton();
            hongo2.setBackground(new Color(0, 0, 0, 0)); // Transparent background
            hongo2.setOpaque(false);
            hongo2.setIcon(imagenHongo);
            hongo2.setBounds(380, 205, 246, 296);
            hongo2.setBorderPainted(false);
            panel.add(hongo2, Integer.valueOf(3));

            // Action listener to stop the process when clicked
            ActionListener oyenteDeAccion = (ActionEvent e) -> {
                tiempo2.parar(2); // Stop the process to avoid losing
            };

            hongo2.addActionListener(oyenteDeAccion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    
    // Hongo3
    public void agregarHongo3() {
        try {
            // Load the animated GIF using ImageIcon
            imagenHongo = new ImageIcon(getClass().getResource("/trufapalooza/resources/whiteMushroom.gif"));

            hongo3 = new JButton();
            hongo3.setBackground(new Color(0, 0, 0, 0)); // Transparent background
            hongo3.setOpaque(false);
            hongo3.setIcon(imagenHongo);
            hongo3.setBounds(647, 205, 246, 296);
            hongo3.setBorderPainted(false);
            panel.add(hongo3, Integer.valueOf(3));

            // Action listener to stop the process when clicked
            ActionListener oyenteDeAccion = (ActionEvent e) -> {
                tiempo2.parar(3); // Stop the process to avoid losing
            };

            hongo3.addActionListener(oyenteDeAccion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    
    // HongoEspecial
public void agregarHongoEspecial() {
        try {
            // Load the animated GIF using ImageIcon
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/trufapalooza/resources/pinkAgaricus.gif"));

            hongoEspecial = new JButton();
            hongoEspecial.setBackground(new Color(0, 0, 0, 0)); // Transparent background
            hongoEspecial.setOpaque(false);
            hongoEspecial.setIcon(imageIcon);
            hongoEspecial.setBounds(305, 35, 360, 360);
            hongoEspecial.setBorderPainted(false);
            panel.add(hongoEspecial, Integer.valueOf(2));

            // Action listener to stop the process when clicked
            ActionListener oyenteDeAccion = (ActionEvent e) -> {
                tiempo2.parar(4); // Stop the process to avoid losing
            };

            hongoEspecial.addActionListener(oyenteDeAccion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
// Contador de tiempo y hongos
    public void agregarContadores() {
        try {
            // Imagen contador Hongos
            InputStream inputStreamContador = getClass().getResourceAsStream("/trufapalooza/resources/whiteMushroom.png");
            BufferedImage imageContador = ImageIO.read(inputStreamContador);
            imagenContador = new ImageIcon(imageContador);

            fotoContador = new JLabel();
            fotoContador.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
            fotoContador.setOpaque(false);
            fotoContador.setIcon(imagenContador);
            fotoContador.setBounds(16, 16, 45, 75);
            fotoContador.setIcon(new ImageIcon(imagenContador.getImage().getScaledInstance(fotoContador.getWidth(), fotoContador.getHeight(), Image.SCALE_SMOOTH))); // Escalo la imagen
            panel.add(fotoContador, Integer.valueOf(2));

            // Imagen cronometro
            InputStream inputStreamCronometro = getClass().getResourceAsStream("/trufapalooza/resources/fotoCronometro.png");
            BufferedImage imageCronometro = ImageIO.read(inputStreamCronometro);
            imagenCronometro = new ImageIcon(imageCronometro);

            fotoCronometro = new JLabel();
            fotoCronometro.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
            fotoCronometro.setOpaque(false);
            fotoCronometro.setIcon(imagenCronometro);
            fotoCronometro.setBounds(16, 110, 45, 75);
            fotoCronometro.setIcon(new ImageIcon(imagenCronometro.getImage().getScaledInstance(fotoCronometro.getWidth(), fotoCronometro.getHeight(), Image.SCALE_SMOOTH))); // Escalo la imagen
            panel.add(fotoCronometro, Integer.valueOf(2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Etiquetas para poner numeros
        // Contador Hongos
        contadorHongos = new JLabel();
        contadorHongos.setText("0");
        contadorHongos.setBounds(50, 18, 75, 75);
        contadorHongos.setVerticalAlignment(SwingConstants.CENTER);
        contadorHongos.setHorizontalAlignment(SwingConstants.CENTER);
        contadorHongos.setOpaque(true);
        contadorHongos.setForeground(Color.YELLOW);
        contadorHongos.setBackground(Color.BLACK);
        contadorHongos.setFont(new Font("Arcadepix", Font.PLAIN, 30));
        panel.add(contadorHongos, Integer.valueOf(2));

        // Contador cronometro
        cronometro = new JLabel();
        cronometro.setText("0");
        cronometro.setBounds(50, 110, 75, 75);
        cronometro.setVerticalAlignment(SwingConstants.CENTER);
        cronometro.setHorizontalAlignment(SwingConstants.CENTER);
        cronometro.setOpaque(true);
        cronometro.setForeground(Color.YELLOW);
        cronometro.setBackground(Color.BLACK);
        cronometro.setFont(new Font("Arcadepix", Font.PLAIN, 30));
        panel.add(cronometro, Integer.valueOf(2));
    }

    
    
    // Inicio del Juego
    public void agregarInicio() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/trufapalooza/resources/imagenStart.png");
            imagenStart = new ImageIcon(ImageIO.read(inputStream));

            start = new JButton();
            start.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
            start.setOpaque(false);
            start.setIcon(imagenStart);
            start.setBounds(134, 25, 100, 50);
            start.setIcon(new ImageIcon(imagenStart.getImage().getScaledInstance(start.getWidth(), start.getHeight(), Image.SCALE_SMOOTH)));
            start.setBorderPainted(false);
            panel.add(start, Integer.valueOf(2));

            ActionListener oyenteDeAccion = (ActionEvent e) -> {
                tiempo2 = new Temporizador2(cronometro, contadorHongos, hongo1, hongo2, hongo3, hongoEspecial, start, clip);
                tiempo2.iniciar();
                tiempo2.start();
                start.setVisible(false);
                contadorHongos.setText("0");
            };

            start.addActionListener(oyenteDeAccion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    //Metodo para agregar música
    public void musicPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Obtener el flujo de entrada del recurso de audio desde el archivo WAV
        InputStream inputStream = getClass().getResourceAsStream("/trufapalooza/resources/flantasticSeven.wav");

        // Envolver el flujo de entrada en un BufferedInputStream para mejorar el rendimiento
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        // Crear un flujo de audio a partir del flujo de entrada bufferizado
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedInputStream);

        // Obtener un Clip del sistema de audio
        clip = AudioSystem.getClip();

        // Abrir el Clip con el flujo de audio
        clip.open(audioStream);
    }

}

class Objetivo extends Thread{ //Clase para los forajidos enemigos del modo tiroteo
    private Temporizador2 general;
    private JLabel Puntos;
    private JButton Boton;
    private int Tiempo, tipo;
    static boolean v = false;
    
    Random r = new Random();
    public Objetivo(Temporizador2 general, JLabel Puntos, JButton Boton,int tipo){
        Tiempo = 1;
        this.general = general; 
        this.Puntos = Puntos;
        this.Boton = Boton;
        this.tipo = tipo;
    }
    public void run(){
        espera(); //Se manda al método donde aparece el botón
        general.iniciar();
    }
    public synchronized void espera(){ //Método donde aparece el botón
        if(v) //Esto sirve para que el monitor verifique si ya hay otros botones activos
            try{
            wait();}catch(InterruptedException e){}
        v = true;
        Boton.setVisible(true); //El botón se vuelve verdadero para mostrarse
        while(Tiempo>0){ //Mientras no sea 0 el proceso sigue
        try{
            Thread.sleep(1500);
            Tiempo--;
        }catch(InterruptedException ex){}
        }
        Boton.setVisible(false); //El botón desaparece
        v = false; //Se desactiva v para que el siguiente botón no pare
        notifyAll(); //Se notifica al proceso siguiente
    }
    public void dejar(){ //Método utilizado desde fuera de esta clase
        this.interrupt();
        Boton.setVisible(false);
    }
}

class Temporizador2 extends Thread{

    Objetivo f, f2,f3, f4;
    JLabel Aviso,Tempo, Puntos;
    JButton En1,En2,En3,En4,start;
    Random r = new Random();
    int Tiempo = 0, puntuacion =0;
    Clip clip;
    
    public Temporizador2(JLabel Tempo,JLabel Puntos, JButton En1, JButton En2, JButton En3, JButton En4,JButton start, Clip clip){
        this.En1=En1; 
        this.En2=En2;
        this.En3=En3;
        this.En4=En4;
        this.Tempo = Tempo;
        this.Puntos = Puntos;
        this.start=start;
        this.clip=clip;
    }
    public void run(){
        System.out.println("Inicia el juego");
        En1.setVisible(false);
        En2.setVisible(false);
        En3.setVisible(false);
        En4.setVisible(false);
        //Aviso.setText("Inicia"); //Se inicia el proceso
        while(Tiempo<61){ //El proceso llega hasta 30 segundos y para
            
            try{
                activar(); //Funcion donde se activa uno de los botones de clase forajido
                
                clip.start();
                Thread.sleep(1000);
                Tempo.setText(String.valueOf(Tiempo++)); //Se actualiza el cronometro
            }catch(InterruptedException ex){}
        }
//        Aviso.setText("Tiempo"); //Si se llega a 30 segundos se gana
        terminar();
        clip.stop();
        clip.setMicrosecondPosition(0);
    }
    public void activar(){ //Sirve para para activar aleatoriamente a los bandidos
        float x = r.nextInt(100);
        if(x<30){ //Por medio de probabilidades se checa que botón se va a activar
        if(f.isAlive() == false) //Se checa si no está corriendo el objeto en cuestión.
                    f.start();}
        else{
            if(x>=30&&x<=60){
            if(f2.isAlive() == false)
                    f2.start();}
            else{
                if(x>60&&x<=90){
                if(f3.isAlive() == false)
                    f3.start();}
                else{
                if(f4.isAlive() == false)
                    f4.start();}
            }
        }
    }
    
    
    public void iniciar(){ //Inicia a todos los objetos forajido, usado en la clase forajido
        f = new Objetivo(this,Puntos,En1,1);
        f2 = new Objetivo(this,Puntos,En2,1);
        f3 = new Objetivo(this,Puntos,En3,1);
        f4 = new Objetivo(this,Puntos,En4,2);
    }
    public void terminar(){
        f.interrupt(); //Todos los procesos se acaban
        En1.setVisible(false);
        f2.interrupt();
        En2.setVisible(false);
        f3.interrupt();
        En3.setVisible(false);
        f4.interrupt();
        En4.setVisible(false);
        start.setVisible(true);
    }
    public void parar(int x){ //Para un objeto objetivo de entre los 4 posibles
        switch(x){
            case 1:
                En1.setVisible(false); //Se desaparece su botón
                f = new Objetivo(this,Puntos, En1,1); //Se crea otro objeto para poder correrlo
                puntuacion++;
                break;
            case 2:
                En2.setVisible(false);
                f2 = new Objetivo(this,Puntos, En2,1);
                puntuacion++;
                break;
            case 3:
                En3.setVisible(false);
                f3 = new Objetivo(this,Puntos, En3,1);
                puntuacion++;
                break;
            case 4:
                En4.setVisible(false);
                f4= new Objetivo(this,Puntos,En4,2);
                puntuacion = puntuacion +5;
        }
        Puntos.setText(String.valueOf(puntuacion)); //Muestra la nueva puntuación
    }
}




public class Trufapalooza {
    public static void main(String[] args) throws UnsupportedAudioFileException{
       Ventana ventana = null;
        try {
            ventana = new Ventana();
        } catch (LineUnavailableException | IOException ex) {
            Logger.getLogger(Trufapalooza.class.getName()).log(Level.SEVERE, null, ex);
        }
       ventana.setVisible(true); //Al definirlo como visible, ya no es necesario redimensionar cada vez que se inicia el juego
       
    }
    
}
