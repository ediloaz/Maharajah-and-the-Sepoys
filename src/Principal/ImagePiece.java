
package Principal;

//import Images;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class ImagePiece extends javax.swing.JPanel {

    public ImagePiece(Graphics grafico, int posPiece,int x, int y) {
        this.setSize(55, 55); //se selecciona el tamaño del panel
        paintPiece(grafico, posPiece,x,y);
    }

    //Se crea un método cuyo parámetro debe ser un objeto Graphics

    public void paintPiece(Graphics grafico, int posPiece,int x, int y) {
        Dimension height = getSize();
        
        //Dependiendo del diseño selccionado por el usuario
        ImageIcon Img; 
        posPiece++;
        switch(posPiece){
            case 1:
                Img = new ImageIcon(getClass().getResource("/Images/_pawn.png")); 
                break;
            case 2:
                Img = new ImageIcon(getClass().getResource("/Images/_rook.png")); 
                break;
            case 3:
                Img = new ImageIcon(getClass().getResource("/Images/_knight.png")); 
                break;
            case 4:
                Img = new ImageIcon(getClass().getResource("/Images/_bishop.png"));
                break;
            case 5:
                Img = new ImageIcon(getClass().getResource("/Images/_queen.png")); 
                break;
            case 6:
                Img = new ImageIcon(getClass().getResource("/Images/_king.png")); 
                break;
            default:      //Nos ahorramos el case 6
                Img = new ImageIcon(getClass().getResource("/Images/_maharaja.png")); 
                break;
        }
            
            grafico.drawImage(Img.getImage(), x, y, height.width, height.height, null);
            setOpaque(false);
            super.paintComponent(grafico);
           
    }
}