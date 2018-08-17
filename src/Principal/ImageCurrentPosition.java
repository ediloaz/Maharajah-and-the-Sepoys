
package Principal;

//import Images;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class ImageCurrentPosition extends javax.swing.JPanel {

    public ImageCurrentPosition(Graphics grafico, int x, int y) {
        this.setSize(55, 55); //se selecciona el tamaño del panel
        paintCurrentPosition(grafico,x,y);
    }

    //Se crea un método cuyo parámetro debe ser un objeto Graphics

    public void paintCurrentPosition(Graphics grafico, int x, int y) {
        Dimension height = getSize();
        
        //Dependiendo del diseño seleccionado por el usuario
        ImageIcon Img = new ImageIcon(getClass().getResource("/Images/pos.png"));
        grafico.drawImage(Img.getImage(), x, y, height.width, height.height, null);
        setOpaque(false);
        super.paintComponent(grafico);

    }
}