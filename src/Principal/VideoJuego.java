/*
iDEAS:
PINTAR LOS TEXTOS SIN GRAPHICS.!!!Quitar rects
par aesto hadcer otra ventana y probar ahi las posiciones
Arrgelar: mahgraaja en las 3 filas

cuando el awaiting es false, se puede terminar la partidao declarar tabalr (menmsajesa, te rindes, o los 2 juegadores declaran tablas?
*/




package Principal;                          //Paquete de donde está esta clase.


//  Start: Import de las librerías necesarias.
import Listener.*;                          //Paquete listener. Para dar uso del teclado y mouse.
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;          //Para los botones.
import java.awt.event.ActionListener;       //Para los botones.
//  End: Import de las librerías necesarias.


public class VideoJuego extends JFrame implements Runnable {
        // Start: Definimos unos atributos de la clase.
	static  final int ANCHO=900,LARGO=560;
	public  static int colorBoard=1;
        public  static String messageForData="¡Hola jugador!",messageForData2="Selecciona donde colocar al Maharajá", cellForData="", extraForData="",extraForData2="";
        public  static boolean CurrentPlayerIsMaharaja=true;
        static JPanel  panelBoard, panelData;
        JLabel  labelData;
        JButton startButton;
        ImageIcon img;
	static boolean condicion=true, gameStarted=false, maharajaPlaced=true;
	Thread  threadprincipal;
	static  ArrayList currentPlayer = new ArrayList();
        public static Graphics grafico;                                   //Creamos a Graphics
        public static CellMatrix cellMatrix = new CellMatrix();
        public PaintGame paintGame = new PaintGame(grafico);
        // End: Definimos unos atributos de la clase.
        
    
        // Start: Constructor de la clase
	public VideoJuego(){
            this.setTitle("El Maharajá y los Cipayos (Joel Barrantes - Edisson López)"); //Titulo de la ventana.
            this.setBounds(100, 100, ANCHO, LARGO);                 //Definimos donde inicia y el tamaño que tiene la ventana.
            this.setVisible(true);                                  //La hacemos visible.
            this.setResizable(false);                               //Tamaño reajustable.
            this.setIgnoreRepaint(true);                            
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

            panelBoard=  new JPanel();                              //JPanel del tablero.
            this.add( panelBoard, BorderLayout.SOUTH );             //Agregamos el JPanel del tablero.
            
            panelData= new JPanel();                                //Panel para colocar datos importantes.
            panelData.setBounds(600,50,300,480);
            this.add(panelData,BorderLayout.SOUTH );                //Agregamos el panel al VideoJuego.
            
            this.addWindowListener(new Ventana(this));              //Agregamo el Listener de ventana.
            this.addKeyListener(new Teclado(this));                 //Agregamo el Listener de teclado (más usado con el mouse).
            this.addMouseMotionListener(new MouseMovimiento(this)); //Agregamo el Listener de movimiento de mouse.
            this.addMouseListener(new Mouse(this));                 //Agregamo el Listener de clics de mouse.

            threadprincipal = new Thread(this);                     //Creamos un hilo.
            threadprincipal.start();                                //Ponemos a trabajar al hilo.	
	}
        // End: Constructor de la clase
	
        
	// Start: Gameloop
	public void run() {
		System.out.println("Juego Iniciado..");
		condicion=true;
		while(condicion){
			update();
			render();
			// Start: dormir()
			try {Thread.sleep(50);} //luego vamos a regular
			catch (InterruptedException e) {}
                        // End: dormir()
		}
		System.exit(0);                                         //Condicion=false entonces se sale del juego.
	}
        // End: Gameloop
        
        
        // - Start: Principales métodos del GameLoop -
        // Cuando se cierra la ventana, el Listener usa este método, para que se cierre el programa
	public static void EndTheGame(){
            condicion=false;
	}

	//Funcion encargada de actualizar el estado del juego
	private void update(){
            //Nothing
	}
	
	//Funcion encargada de dibujar en pantalla el estado del juego
	private void render(){
            
            grafico= this.getGraphics();                        //Agarramos los graficos de la ventana

            if (grafico!=null){

                    
                    paintGame.PaintTheGame(grafico);                         //Dibuja en el JPanel de panelBoard (En el tablero y sus elementos)
                    
                    //Para una mejor vista y borra basura
                    Toolkit.getDefaultToolkit().sync();             //syncronizar con la pantalla	   
                    grafico.dispose();
            }
	}
        // - End: Principales métodos del GameLoop -
        
        
        
        
        /*Cuando una pieza mueve correctamente, llama a TurnoTerminado*/
        private void NextPlayer(){
            if (CurrentPlayerIsMaharaja==true) CurrentPlayerIsMaharaja=false;
            else CurrentPlayerIsMaharaja=true;
        }
        
        private static void StartGame(){
            currentPlayer.add("Maharaja");
            currentPlayer.add("Cipayos");            
            VideoJuego ventana = new VideoJuego();
            
        }
        
        
        // End: Métodos para ayudar al programador
        
        public static void main(String[] args) {	
            StartGame();
            
	}
        
        
}