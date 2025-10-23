package lab5;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JFrame;
public abstract class GUI extends JFrame {
    public GUI() {
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x323232));
        this.setLayout(null);
    }
}