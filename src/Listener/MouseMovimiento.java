package Listener;

import Principal.VideoJuego;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class MouseMovimiento implements MouseMotionListener {
	
	VideoJuego juego;
	
	public MouseMovimiento(VideoJuego passjuego){
		juego=passjuego;
	}
	
	
	public void mouseDragged(MouseEvent evento) {

	}

	/*Se llama cuando se mueve el mouse*/
	public void mouseMoved(MouseEvent evento) {	
		
	}

}

