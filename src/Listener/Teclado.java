/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Listener;

import Principal.VideoJuego;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Teclado implements KeyListener {
        boolean teclaPresionada=false, tablas=false,maharajaAccepted=false,tablasDeclarated=false,endGame=false;
        public static boolean waitingClosing=false;
	VideoJuego juego;
	
	public Teclado(VideoJuego passjuego){
		juego=passjuego;
	}
	
	public void keyPressed(KeyEvent evento) {
            teclaPresionada=true;
            if (waitingClosing==false){
		if(evento.getKeyCode()==KeyEvent.VK_LEFT && VideoJuego.CurrentPlayerIsMaharaja==true){
			//juego.mover_izquierda();
                    VideoJuego.cellMatrix.currentPositionLeft();
		}
		if(evento.getKeyCode()==KeyEvent.VK_RIGHT && VideoJuego.CurrentPlayerIsMaharaja==true){
			//juego.mover_derecha();
                    VideoJuego.cellMatrix.currentPositionRight();
		}
		if(evento.getKeyCode()==KeyEvent.VK_UP && VideoJuego.CurrentPlayerIsMaharaja==true){
			//juego.mover_arriba();
                        VideoJuego.cellMatrix.currentPositionUp();
		}
		if(evento.getKeyCode()==KeyEvent.VK_DOWN && VideoJuego.CurrentPlayerIsMaharaja==true){
			//juego.mover_abajo();
                        VideoJuego.cellMatrix.currentPositionDown();
		}
                if(evento.getKeyCode()==KeyEvent.VK_A && VideoJuego.CurrentPlayerIsMaharaja==false){
			//juego.mover_izquierda();
                    VideoJuego.cellMatrix.currentPositionLeft();
		}
		if(evento.getKeyCode()==KeyEvent.VK_D && VideoJuego.CurrentPlayerIsMaharaja==false){
			//juego.mover_derecha();
                    VideoJuego.cellMatrix.currentPositionRight();
		}
		if(evento.getKeyCode()==KeyEvent.VK_W && VideoJuego.CurrentPlayerIsMaharaja==false){
			//juego.mover_arriba();
                        VideoJuego.cellMatrix.currentPositionUp();
		}
		if(evento.getKeyCode()==KeyEvent.VK_S && VideoJuego.CurrentPlayerIsMaharaja==false){
			//juego.mover_abajo();
                        VideoJuego.cellMatrix.currentPositionDown();
		}
                if(evento.getKeyCode()==KeyEvent.VK_1){
			VideoJuego.colorBoard=1;
		}
                if(evento.getKeyCode()==KeyEvent.VK_2){
			VideoJuego.colorBoard=2;
		}
                if(evento.getKeyCode()==KeyEvent.VK_3){
			VideoJuego.colorBoard=3;
		}
                if(evento.getKeyCode()==KeyEvent.VK_4){
			VideoJuego.colorBoard=4;
		}
                if(evento.getKeyCode()==KeyEvent.VK_5){
			VideoJuego.colorBoard=5;
		}
                if(evento.getKeyCode()==KeyEvent.VK_6){
			VideoJuego.colorBoard=6;
		}
                if(evento.getKeyCode()==KeyEvent.VK_ENTER && VideoJuego.CurrentPlayerIsMaharaja==true){
                    if (VideoJuego.cellMatrix.maharajaReady==false){
                VideoJuego.cellMatrix.awaitingMovement=false;
                VideoJuego.cellMatrix.MaharajaReady();
            }else{
                if (VideoJuego.cellMatrix.awaitingMovement==false){
                    VideoJuego.cellMatrix.awaitingMovement=true;
                    VideoJuego.cellMatrix.PosCurrentPieceBefore[0]=VideoJuego.cellMatrix.getCurrentPositionCell(0);
                    VideoJuego.cellMatrix.PosCurrentPieceBefore[1]=VideoJuego.cellMatrix.getCurrentPositionCell(1);
                    VideoJuego.cellForData =Integer.toString((( VideoJuego.cellMatrix.posToMove[0])-8)*-1+1) +""+ ChangeNumber(VideoJuego.cellMatrix.PosCurrentPieceBefore[1]);
                    //VideoJuego.cellMatrix.ManagerMovePiece();
                }else{
                    VideoJuego.cellMatrix.posToMove[0]=VideoJuego.cellMatrix.getCurrentPositionCell(0);
                    VideoJuego.cellMatrix.posToMove[1]=VideoJuego.cellMatrix.getCurrentPositionCell(1);
                    VideoJuego.cellMatrix.awaitingMovement=false;
                    VideoJuego.cellForData =Integer.toString((( VideoJuego.cellMatrix.posToMove[0])-8)*-1+1) +""+ ChangeNumber(VideoJuego.cellMatrix.PosCurrentPieceBefore[1]);
                    //VideoJuego.cellMatrix.ManagerMovePiece();
                }

                VideoJuego.cellMatrix.ManagerMovePiece();
            }
		}
                if(evento.getKeyCode()==KeyEvent.VK_SPACE && VideoJuego.CurrentPlayerIsMaharaja==false){
                    if (VideoJuego.cellMatrix.maharajaReady==false){
                VideoJuego.cellMatrix.awaitingMovement=false;
                VideoJuego.cellMatrix.MaharajaReady();
            }else{
                if (VideoJuego.cellMatrix.awaitingMovement==false){
                    VideoJuego.cellMatrix.awaitingMovement=true;
                    VideoJuego.cellMatrix.PosCurrentPieceBefore[0]=VideoJuego.cellMatrix.getCurrentPositionCell(0);
                    VideoJuego.cellMatrix.PosCurrentPieceBefore[1]=VideoJuego.cellMatrix.getCurrentPositionCell(1);
                    VideoJuego.cellForData =Integer.toString((( VideoJuego.cellMatrix.posToMove[0])-8)*-1+1) +""+ ChangeNumber(VideoJuego.cellMatrix.PosCurrentPieceBefore[1]);
                    //VideoJuego.cellMatrix.ManagerMovePiece();
                }else{
                    VideoJuego.cellMatrix.posToMove[0]=VideoJuego.cellMatrix.getCurrentPositionCell(0);
                    VideoJuego.cellMatrix.posToMove[1]=VideoJuego.cellMatrix.getCurrentPositionCell(1);
                    VideoJuego.cellMatrix.awaitingMovement=false;
                    VideoJuego.cellForData =Integer.toString((( VideoJuego.cellMatrix.posToMove[0])-8)*-1+1) +""+ ChangeNumber(VideoJuego.cellMatrix.PosCurrentPieceBefore[1]);
                    //VideoJuego.cellMatrix.ManagerMovePiece();
                }

                VideoJuego.cellMatrix.ManagerMovePiece();
            }
		}
                
                if(evento.getKeyCode()==KeyEvent.VK_ESCAPE){
                    if (VideoJuego.cellMatrix.gameOver==true){
                        VideoJuego.EndTheGame();
                    }else{
                        //Tablas o abandono
                        VideoJuego.extraForData ="¿Abandonar(tecla: i) o Tablas(tecla: o)? " ;
                        waitingClosing=true;                        //Esperamos la tecla
                    }
		}
            }
            else{
                VideoJuego.grafico.setColor(Color.black);
                VideoJuego.grafico.drawRect(10, 10, 300, 300);
                if(evento.getKeyCode()==KeyEvent.VK_R && waitingClosing==true){
			VideoJuego.cellMatrix.RestartGame();
		}
                if(evento.getKeyCode()==KeyEvent.VK_I && waitingClosing==true){
			//Hacer que declare ganador al otro jugador
                        if (VideoJuego.cellMatrix.awaitingMovement==true){
                            VideoJuego.extraForData="Debió de hacerlo antes de iniciar su turno";
                        }else{
                            if (VideoJuego.CurrentPlayerIsMaharaja==true) {
                                VideoJuego.extraForData="¡Los cipayos han ganado!";
                                VideoJuego.extraForData2=" Presione Escape para salir";
                            }
                            else {
                                VideoJuego.extraForData="¡El Maharajá ha ganado!";
                                VideoJuego.extraForData2=" Presione Escape para salir";
                            }
                            endGame=true;
                        }
                }
                if(evento.getKeyCode()==KeyEvent.VK_N && waitingClosing==true){
                    waitingClosing=false;
                    VideoJuego.extraForData="";
                    VideoJuego.extraForData2="";
                }
                if(evento.getKeyCode()==KeyEvent.VK_O && waitingClosing==true){
			//Hacer que pregunte a los 
                        tablas=true;
                        VideoJuego.extraForData="Mahrajá, presione Enter para confirmar";
                        VideoJuego.extraForData2=" Sino presione n";
                }
                if (evento.getKeyCode()==KeyEvent.VK_ENTER && waitingClosing==true && tablas==true){
                        VideoJuego.extraForData="Cipayos, presione Space para confirmar";
                        VideoJuego.extraForData2=" Sino presione n";
                        maharajaAccepted=true;
                }
                if (evento.getKeyCode()==KeyEvent.VK_SPACE && waitingClosing==true && tablas==true && maharajaAccepted==true){
                        VideoJuego.extraForData="Cipayos y Maharajá han empatado";
                        VideoJuego.extraForData2=" Presione Escape para salir";
                        endGame=true;
                }
                if (evento.getKeyCode()==KeyEvent.VK_ESCAPE && endGame==true){
                        VideoJuego.EndTheGame();
                }
                
            }
                
		
	}

	@Override
	public void keyReleased(KeyEvent evento) {
		

	}

	@Override
	public void keyTyped(KeyEvent evento) {

	}
        
        private void SelectTyped(){
            if (VideoJuego.cellMatrix.maharajaReady==false){
                VideoJuego.cellMatrix.awaitingMovement=false;
                VideoJuego.cellMatrix.MaharajaReady();
            }else{
                if (VideoJuego.cellMatrix.awaitingMovement==false){
                    VideoJuego.cellMatrix.awaitingMovement=true;
                    VideoJuego.cellMatrix.PosCurrentPieceBefore[0]=VideoJuego.cellMatrix.getCurrentPositionCell(0);
                    VideoJuego.cellMatrix.PosCurrentPieceBefore[1]=VideoJuego.cellMatrix.getCurrentPositionCell(1);
                    System.out.print(" *");System.out.print(((VideoJuego.cellMatrix.posToMove[0])-8)*-1+1);System.out.println(ChangeNumber(VideoJuego.cellMatrix.PosCurrentPieceBefore[1]));
                    //VideoJuego.cellMatrix.ManagerMovePiece();
                }else{
                    VideoJuego.cellMatrix.posToMove[0]=VideoJuego.cellMatrix.getCurrentPositionCell(0);
                    VideoJuego.cellMatrix.posToMove[1]=VideoJuego.cellMatrix.getCurrentPositionCell(1);
                    VideoJuego.cellMatrix.awaitingMovement=false;
                    System.out.print(" *");System.out.print(((VideoJuego.cellMatrix.posToMove[0])-8)*-1+1);System.out.println(ChangeNumber(VideoJuego.cellMatrix.posToMove[1]));
                    //VideoJuego.cellMatrix.ManagerMovePiece();
                }

                VideoJuego.cellMatrix.ManagerMovePiece();
            }
        }
        
        private String ChangeNumber(int number){
            switch(number){
                case 0:
                    return "A";
                    
                case 1:
                    return "B";
                    
                case 2:
                    return "C";
                    
                case 3:
                    return "D";
                   
                case 4:
                    return "E";
                   
                case 5:
                    return "F";
                   
                case 6:
                    return "G";
                    
                default:
                    return "H";  
            }
        }

}
