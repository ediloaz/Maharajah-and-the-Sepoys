
package Principal;

//import Images;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class ImageManager extends javax.swing.JPanel {

    public ImageManager(Graphics grafico) {
        this.setSize(480, 480); //se selecciona el tamaño del panel
        paintBoard(grafico);
    }

    //Se crea un método cuyo parámetro debe ser un objeto Graphics

    public void paintBoard(Graphics grafico) {
        Dimension height = getSize();
        
        //Dependiendo del diseño selccionado por el usuario
        ImageIcon Img; 
        switch(VideoJuego.colorBoard){
            case 1:
                Img = new ImageIcon(getClass().getResource("/Images/board1.png")); 
                break;
            case 2:
                Img = new ImageIcon(getClass().getResource("/Images/board2.png")); 
                break;
            case 3:
                Img = new ImageIcon(getClass().getResource("/Images/board3.png"));
                break;
            case 4:
                Img = new ImageIcon(getClass().getResource("/Images/board4.png")); 
                break;
            case 5:
                Img = new ImageIcon(getClass().getResource("/Images/board5.png")); 
                break;
            default:      //Nos ahorramos el case 6
                Img = new ImageIcon(getClass().getResource("/Images/board6.png")); 
                break;
        }
            grafico.drawImage(Img.getImage(), 20, 50, height.width, height.height, null);
            setOpaque(false);
            super.paintComponent(grafico);
            
        /*
            grafico.drawImage((new ImageIcon(getClass().getResource("/Images/mBoard1.png"))).getImage(), 530, 318, 96, 96, null);
            setOpaque(false);
            super.paintComponent(grafico);
            grafico.drawImage((new ImageIcon(getClass().getResource("/Images/mBoard2.png"))).getImage(), 636, 318, 96, 96, null);
            setOpaque(false);
            super.paintComponent(grafico);
            grafico.drawImage((new ImageIcon(getClass().getResource("/Images/mBoard3.png"))).getImage(), 742, 318, 96, 96, null);
            setOpaque(false);
            super.paintComponent(grafico);
            grafico.drawImage((new ImageIcon(getClass().getResource("/Images/mBoard4.png"))).getImage(), 530, 424, 96, 96, null);
            setOpaque(false);
            super.paintComponent(grafico);
            grafico.drawImage((new ImageIcon(getClass().getResource("/Images/mBoard5.png"))).getImage(), 636, 424, 96, 96, null);
            setOpaque(false);
            super.paintComponent(grafico);
            grafico.drawImage((new ImageIcon(getClass().getResource("/Images/mBoard6.png"))).getImage(), 742, 424, 96, 96, null);
            setOpaque(false);
            super.paintComponent(grafico);
            */
            
        //se dibuja la imagen que tenemos en el paquete Images //dentro de un panel

        
    }
    

   /* public class MiniBoardManager extends ImageManager {
          
        public MiniBoardManager(Graphics grafico){
            //ImageIcon Img; 
            grafico.drawImage(new ImageIcon(getClass().getResource("/Images/mBoard1.png")).getImage(), 530, 318, 96, 96, null);
            grafico.drawImage(new ImageIcon(getClass().getResource("/Images/mBoard2.png")).getImage(), 636, 318, 96, 96, null);
            grafico.drawImage(new ImageIcon(getClass().getResource("/Images/mBoard3.png")).getImage(), 742, 318, 96, 96, null);
            grafico.drawImage(new ImageIcon(getClass().getResource("/Images/mBoard4.png")).getImage(), 530, 424, 96, 96, null);
            grafico.drawImage(new ImageIcon(getClass().getResource("/Images/mBoard5.png")).getImage(), 636, 424, 96, 96, null);
            grafico.drawImage(new ImageIcon(getClass().getResource("/Images/mBoard6.png")).getImage(), 742, 424, 96, 96, null);
            setOpaque(false);
            super.paintComponent(grafico);

        }
    }*/
}