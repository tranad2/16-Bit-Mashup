import javax.swing.JFrame;
import java.awt.*;
public class TheGame {

    /**
     * Creates a new instance of <code>TheGameFrame</code>.
     */
    public TheGame() {
    }
    
    public static Dimension size = new Dimension(1280,480);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JFrame j1 = new JFrame();
        j1.setSize(1280,(int)TheGame.size.getHeight()-12);
        j1.setTitle("16-Bit Mashup");
        j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j1.add(new Board());
        j1.setVisible(true);
        j1.setResizable(false);
    }
}
