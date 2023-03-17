
package trufapalooza3;

//Importes Realizados
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

//Los sig. import son para manejar música
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public Ventana() throws UnsupportedAudioFileException, LineUnavailableException, IOException{ //Si no se colocan las excepciones no se puede manejar el archivo de la múscia
        ImageIcon icon; //Creo variable para poner mi icono de ventana
        setSize(1000,547); //Defino las dimensiones de mi ventana, un poco mas grande en dimensión y para que salga correctamente el label de fondo
        setTitle("Trufapalooza"); //Deifinión de título
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Cerrar programa al cerrar ventana
        setLocationRelativeTo(null); //Se coloca en el centro de la pantalla
        setResizable(false); //Para evitar que me redimensionen la ventana
        icon=new ImageIcon("rareTruffle.png"); //Asigno el archivo
        setIconImage(icon.getImage()); //Se establece el archivo
        
        agregarElementos(); //Método para agregar todos los elementos 
        
    }
    
    public void agregarElementos() throws UnsupportedAudioFileException, IOException, LineUnavailableException{ //Si no se colocan las excepciones no se puede manejar el archivo de la múscia
        colocarPanel();
        agregarFondo();
        agregarContadores();
        agregarHongo1();
        agregarHongo2();
        agregarHongo3();
        agregarHongoEspecial();
        agregarInicio();
        musicPlayer() ;
        
        
    }
    
    //Método colocar panel
    public void colocarPanel(){
        panel= new JLayeredPane(); //En vez de ser un panel normal, es uno con capas
        panel.setLayout(null); //Desactivo el diseño predeterminado del Panel y tengo mas libertad
        getContentPane().add(panel);//Agrega el panel

    }
    
    //Metodo para asignar un fondo
    //Fondo animado fiesta
    public void agregarFondo(){
        imagenFondo = new ImageIcon("danceFloor.gif"); //Se carga el fondo animado 
        fondo = new JLabel(); 
        fondo.setOpaque(true); //Al definir setOpaque, me quita las restricciones que hay en las etiquetas y me permite editarla a mi gusto
        fondo.setIcon(imagenFondo); //Le asigno mi gif
        fondo.setBounds(0,0,1000,500); //Deifino mis dimensiones y tamaño de mi etiqueta
        panel.add(fondo,new Integer (1)); //Defino en que capa quiero mi fondo
        getContentPane().add(panel); //Agrego al panel mi fondo
    }
    
    
    //Hongo1
    public void agregarHongo1(){
        imagenHongo= new ImageIcon("whiteMushroom.gif"); //Cargo el Gif
        hongo1= new JButton();
        hongo1.setBackground(new Color(0,0,0,0)); //Al ser 0,0,0,0 , es color transparente
        hongo1.setOpaque(false); //Ayuda a establecer como transparente el boton
        hongo1.setIcon(imagenHongo); //Cargo mi imagen de fondo
        hongo1.setBounds(113,205,246,296); //Defino posiciones y dimensiones
        hongo1.setBorderPainted(false); //Le quito el borde al boton
        panel.add(hongo1,new Integer(3));    //Agrego y defino la capa 
        
        
        //Oyente de accion, que hace cuando se le da clic
        ActionListener oyenteDeAccion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempo2.parar(1); //Se para el proceso forajido antes de que te haga perder
   
            }
        };
        
        hongo1.addActionListener(oyenteDeAccion); //Con este método al presionar el boton pasa algo
        
    }
    
    
    //Hongo2
    public void agregarHongo2(){
        imagenHongo= new ImageIcon("whiteMushroom.gif");
        hongo2= new JButton();
        hongo2.setBackground(new Color(0,0,0,0));
        hongo2.setOpaque(false);
        hongo2.setIcon(imagenHongo);
        hongo2.setBounds(380,205,246,296);
        hongo2.setBorderPainted(false);
        panel.add(hongo2,new Integer(3));   
        
        ActionListener oyenteDeAccion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 tiempo2.parar(2); //Se para el proceso forajido antes de que te haga perder
            }
        };
        
        hongo2.addActionListener(oyenteDeAccion);
    }
    
    
    //Hongo3
    public void agregarHongo3(){
        imagenHongo= new ImageIcon("whiteMushroom.gif");
        hongo3= new JButton();
        hongo3.setBackground(new Color(0,0,0,0));
        hongo3.setOpaque(false);
        hongo3.setIcon(imagenHongo);
        hongo3.setBounds(647,205,246,296);
        hongo3.setBorderPainted(false);
        panel.add(hongo3,new Integer(3));   
        
        ActionListener oyenteDeAccion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 tiempo2.parar(3); //Se para el proceso forajido antes de que te haga perder
            }
        };
        
        hongo3.addActionListener(oyenteDeAccion);
    }
    
    
    //HongoEspecial
    public void agregarHongoEspecial(){
        imagenHongoEspecial= new ImageIcon("pinkAgaricus.gif");
        hongoEspecial= new JButton();
        hongoEspecial.setBackground(new Color(0,0,0,0));
        hongoEspecial.setOpaque(false);
        hongoEspecial.setIcon(imagenHongoEspecial);
        hongoEspecial.setBounds(305,35,360,360);
        hongoEspecial.setBorderPainted(false);
        panel.add(hongoEspecial,new Integer(2));   
        
        ActionListener oyenteDeAccion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 tiempo2.parar(4); //Se para el proceso forajido antes de que te haga perder
            }
        };
        
        hongoEspecial.addActionListener(oyenteDeAccion);
        
    }
    
    //Contador de tiempo y hongos
    public void agregarContadores(){
        //Imagenes
        //Imagen contador Hongos
        imagenContador=new ImageIcon("whiteMushroom.png"); //Defino mi imagen a agregar
        fotoContador= new JLabel(); //Creo mi etiqueta
        fotoContador.setBackground(new Color(0,0,0,0)); //Background transparente
        fotoContador.setOpaque(false); //Ayuda a que este transparetnte
        fotoContador.setIcon(imagenContador); //Asigno el icono
        fotoContador.setBounds(16,16,45,75); //Defino posición y dimensiones
        fotoContador.setIcon(new ImageIcon(imagenContador.getImage().getScaledInstance(fotoContador.getWidth(),fotoContador.getHeight(),Image.SCALE_SMOOTH))); //Solo funciona con jpgs normales, no con gifs, asigno imagen y escalo a dimensiones
        panel.add(fotoContador, new Integer(2)); //Agrego y asigno a capa correspondiente
        
        //Imagen cronometro
        imagenCronometro=new ImageIcon("fotoCronometro.png");
        fotoCronometro= new JLabel();
        fotoCronometro.setBackground(new Color(0,0,0,0)); //Background transparente
        fotoCronometro.setOpaque(false);
        fotoCronometro.setIcon(imagenCronometro);
        fotoCronometro.setBounds(16,110,45,75);
        fotoCronometro.setIcon(new ImageIcon(imagenCronometro.getImage().getScaledInstance(fotoCronometro.getWidth(),fotoCronometro.getHeight(),Image.SCALE_SMOOTH))); //Solo funciona con jpgs normales, no con gifs
        panel.add(fotoCronometro, new Integer(2));
        
        
        //Etiquetas para poner numeros
        
        
        //Contador Hongos
        contadorHongos= new JLabel();
        contadorHongos.setText("0");
        contadorHongos.setBounds(50,18,75,75); //Defino posicion x,y, y dimensiones de mi etiqueta
        contadorHongos.setVerticalAlignment(SwingConstants.CENTER); //Con esto y la siguiente linea defino posicion de mi texto
        contadorHongos.setHorizontalAlignment(SwingConstants.CENTER);
        contadorHongos.setOpaque(true);//Mayor libertad, de fondo y diseño en c de ser necesario
        contadorHongos.setForeground(Color.YELLOW);
        contadorHongos.setBackground(Color.BLACK);
        contadorHongos.setFont(new Font("Arcadepix",0,30)); //En teoria cambia la fuente empleada, se asigna el estilo y el tamaño
        panel.add(contadorHongos, new Integer(2)); //Agrego mi etiqueta
        
        
        //Contador cronometro
        cronometro= new JLabel();
        cronometro.setText("0");
        cronometro.setBounds(50,110,75,75); //Defino posicion x,y, y dimensiones de mi etiqueta
        cronometro.setVerticalAlignment(SwingConstants.CENTER); //Con esto y la siguiente linea defino posicion de mi texto
        cronometro.setHorizontalAlignment(SwingConstants.CENTER);
        cronometro.setOpaque(true);//Mayor libertad, de fondo y diseño en c de ser necesario
        cronometro.setForeground(Color.YELLOW);
        cronometro.setBackground(Color.BLACK);
        cronometro.setFont(new Font("Arcadepix",0,30)); //En teoria cambia la fuente empleada, se asigna el estilo y el tamaño
        panel.add(cronometro, new Integer(2)); //Agrego mi etiqueta 
        
    }
    
    
    //Inicio del Juego
    public void agregarInicio(){
        imagenStart= new ImageIcon("imagenStart.png"); //Asigno mi imagen
        start= new JButton(); //Creo mi etiqueta
        start.setBackground(new Color(0,0,0,0)); //Defino mi fondo transparente
        start.setOpaque(false); //Ayuda a definir mi fondo
        start.setIcon(imagenStart);        //Asigno mi imagen
        start.setBounds(134,25,100,50); //Defino tam´ño y dimensiones
        start.setIcon(new ImageIcon(imagenStart.getImage().getScaledInstance(start.getWidth(),start.getHeight(),Image.SCALE_SMOOTH))); //Coloco mi imagen en la etiqueta, la redimensiono y que su escalado sea suave
        start.setBorderPainted(false); //Borde transparente
        panel.add(start,new Integer(2));   //Agrego y asigno la capa
        
        ActionListener oyenteDeAccion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 tiempo2 = new Temporizador2(cronometro,contadorHongos,hongo1,hongo2,hongo3,hongoEspecial,start,clip); //Se declara bien el objeto temporizador 2
                 tiempo2.iniciar(); //Se declaran dentro del objeto los objetos forajido, me dio errores si no lo hacía así, no sé por qué
                 tiempo2.start();
                 start.setVisible(false); //Para que ya no se pueda volver a interactuar con el boton
                 contadorHongos.setText("0"); //Para que cada vez que se inicie siempre este en 0
            }
        };
        
        start.addActionListener(oyenteDeAccion);
        
    }
    
    
    //Metodo para agregar música
    public void musicPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException { //Si no se agregan estas excepciones, no se puede manejar la musica
        File file = new File("flantasticSeven.wav"); //Instancio File y asigno el archivo .wav
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file); //Asigno mi musica
        clip = AudioSystem.getClip(); //Creo mi variable clip y que obtenga el clip
        clip.open(audioStream); //Permite que el audio sea operacional
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




public class Trufapalooza3 {
    public static void main(String[] args) throws UnsupportedAudioFileException{
       Ventana ventana = null;
        try {
            ventana = new Ventana();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Trufapalooza3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Trufapalooza3.class.getName()).log(Level.SEVERE, null, ex);
        }
       ventana.setVisible(true); //Al definirlo como visible, ya no es necesario redimensionar cada vez que se inicia el juego
       
    }
    
}
