/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Principal;

import static Principal.VideoJuego.CurrentPlayerIsMaharaja;
import static Principal.VideoJuego.cellMatrix;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Esmeregildo
 */
public class PaintGame extends JPanel{
    

    PaintGame(Graphics grafico){
        this.setDoubleBuffered(true);
    }
    // - Start: Principales métodos de render() -
        /*Dibuja la zona de juego*/
        public void PaintTheGame(Graphics grafico){
            if(cellMatrix.gameStarted){
                PaintBoard(grafico);
                PaintPieces(grafico);
                PaintData(grafico);
            }else{
                PaintBoard(grafico);
                PaintPiecesBeforeGame(grafico);
                PaintData(grafico);
            }
        }
        
        /*Dibuja el tablero en panelBoard*/
        private void PaintBoard(Graphics grafico){
            ImageManager boardImage = new ImageManager(grafico);
            VideoJuego.panelBoard.add(boardImage);
            VideoJuego.panelBoard.repaint();
        }
        
        /*Dibuja las piezas en panelBoard*/
        private void PaintPieces(Graphics grafico){
            cellMatrix.CompleteCooMatrix();
            cellMatrix.CompletePieceMatrix();
            for (int row =0; row < 8; row++) {
                for (int column = 0; column<8; column++){
                    
                    int x           = cellMatrix.getCooCell(row, column,0);    //Coordenadas de x
                    int y           = cellMatrix.getCooCell(row, column,1);    //Coordenadas de y
                    int pieceCell   = cellMatrix.getPieceCell(row, column);     //Piezas
                    
                    if (pieceCell != 7) {
                        try {
                            PaintPieces(grafico,pieceCell, x, y);
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }   
                    }
                }
            }
            int row    = cellMatrix.getCurrentPositionCell(0);
            int column = cellMatrix.getCurrentPositionCell(1);
            PaintCurrentPosition(grafico, cellMatrix.getCooCell(row,column,0), cellMatrix.getCooCell(row,column,1));
        }
        
        /*Dibuja las piezas en panelBoard antes de iniciar juego*/
        private void PaintPiecesBeforeGame(Graphics grafico){
            cellMatrix.CompleteCooMatrix();
            cellMatrix.CompletePieceMatrix();
            int row    = cellMatrix.getCurrentPositionCell(0);
            int column = cellMatrix.getCurrentPositionCell(1);
            PaintCurrentPosition(grafico, cellMatrix.getCooCell(row,column,0), cellMatrix.getCooCell(row,column,1));
        }
        
        /* Dibuja una pieza */
        private void PaintPieces(Graphics grafico,int posPiece,int x,int y){
            ImagePiece pieceImage = new ImagePiece(grafico,posPiece,x,y);
            VideoJuego.panelBoard.add(pieceImage);
            VideoJuego.panelBoard.repaint();
        }
        
        /* Dibuja el id del usuario en el tablero */
        private void PaintCurrentPosition(Graphics grafico, int x, int y){
            ImageCurrentPosition currentPosition = new ImageCurrentPosition(grafico,x,y);
            VideoJuego.panelBoard.add(currentPosition);
            VideoJuego.panelBoard.repaint();
        }
        
        
        private void PaintData(Graphics grafico){
            grafico.setColor(Color.black);                        //Color del cuadro
            grafico.fillRect(520, 50, 360, 480);                  //Cuadro de la derecha para los datos
                    
            grafico.setColor(Color.white);
            grafico.drawString("Notificación", 600, 250);
            grafico.drawString(VideoJuego.messageForData, 630, 265);
            grafico.drawString(VideoJuego.messageForData2, 630, 280);
            grafico.drawString("Celda seleccionada:", 600, 330);
            grafico.drawString(VideoJuego.cellForData, 630, 345);
            grafico.drawString(VideoJuego.extraForData, 600, 395);
            grafico.drawString(VideoJuego.extraForData2, 600, 410);
            grafico.drawString("Controles de los cipayos:  W, A, S, D, SPACE", 600, 460);     //Ayuda
            grafico.drawString("Controles del Maharajá:    ↑, ←, ↓, →, ENTER", 600, 475);     //Ayuda
            grafico.drawString("ESCAPE: Abandona o tablas.", 600, 490);     //Ayuda
            if (true==Listener.Teclado.waitingClosing){
                grafico.setColor(Color.black);                        //Color del cuadro
                grafico.fillRect(20, 50, 480, 480);                  //Cuadro de la derecha para los datos
            }
            if (CurrentPlayerIsMaharaja==true) grafico.drawString("Jugador actual: Maharajá", 600, 200);
            else grafico.drawString("Jugador actual: Cipayos", 600, 200);
            if (VideoJuego.cellMatrix.checkExistKing()==false && VideoJuego.cellMatrix.maharajaReady==true){
                VideoJuego.cellMatrix.gameOver=true;
                grafico.setColor(Color.black);                        //Color del cuadro
                grafico.fillRect(20, 50, 480, 480);                  //Cuadro de la derecha para los datos
                grafico.setColor(Color.white);                        //Color del cuadro
                grafico.drawString("¡El Maharajá ha ganado!", 300, 300);     //Ayuda
                grafico.drawString("Por favor presione ESCAPE", 300, 315);     //Ayuda
            }else if (VideoJuego.cellMatrix.checkExistMaharaja()==false && VideoJuego.cellMatrix.maharajaReady==true){
                VideoJuego.cellMatrix.gameOver=true;
                grafico.setColor(Color.black);                        //Color del cuadro
                grafico.fillRect(20, 50, 480, 480);                  //Cuadro de la derecha para los datos
                grafico.setColor(Color.white);                        //Color del cuadro
                grafico.drawString("¡Los Cipayos ha ganado!", 300, 300);     //Ayuda
                grafico.drawString("Por favor presione ESCAPE", 300, 315);     //Ayuda
            }
            
        }
    
}
