
package Listener;

import Principal.VideoJuego;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Ventana implements WindowListener{
	
	VideoJuego juego;
	
	public Ventana(VideoJuego passjuego){
		this.juego=passjuego;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
            System.out.println("Se Cerró la Ventana");
            System.out.println("¡A la próxima termina el juego o rindete!");
            juego.EndTheGame();
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
