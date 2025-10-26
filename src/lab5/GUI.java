package lab5;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import org.jdesktop.swingx.prompt.PromptSupport;
public abstract class GUI extends JFrame {
    final Font font = new Font("SansSerif", Font.BOLD| Font.ITALIC, 30);
    final Font font2 = new Font("SansSerif", Font.BOLD, 18);
    final Color whiteColor = new Color(0xFFFFFF);
    final Color blackColor = new Color(0x000000);
    final Color defaultColor = new Color(0x323232);
    final Color darkGrey = new Color(0x252525);
    public GUI() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(defaultColor);
        this.setLayout(null);
    }
    public JButton newButton(String name,int [] dimension, ActionListener e){
        JButton button = new JButton();
        button.setFocusable(false);
        button.setText(name);
        button.setFont(font);
        button.setForeground(whiteColor);
        button.setBackground(blackColor);
        button.setBounds(dimension[0], dimension[1], dimension[2], dimension[3]);
        button.addActionListener(e);
        return button;
    }
    public JLabel newLabel(String name, Font font, int horizontalAlign, int verticalAlign, int[] dimension ){
        JLabel label = new JLabel();
        label.setText(name);
        label.setFont(font);
        label.setHorizontalAlignment(horizontalAlign);
        label.setVerticalAlignment(verticalAlign);
        label.setForeground(whiteColor);
        label.setBounds(dimension[0], dimension[1], dimension[2], dimension[3]);
        return label;
    }
    public JTextField newTextField(int[] dimension, String placeHolder){
        JTextField textField = new JTextField();
        textField.setFont(font2);
        textField.setBounds(dimension[0], dimension[1], dimension[2], dimension[3]);
        PromptSupport.setPrompt(placeHolder, textField);
        PromptSupport.setForeground(Color.LIGHT_GRAY, textField);
        textField.setBorder(BorderFactory.createLineBorder(darkGrey, 3));
        return textField;
    }
    public JPasswordField newPasswordField(int[] dimension, String placeHolder){
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(font2);
        passwordField.setBounds(dimension[0], dimension[1], dimension[2], dimension[3]);
        PromptSupport.setPrompt(placeHolder, passwordField);
        PromptSupport.setForeground(Color.LIGHT_GRAY, passwordField);
        passwordField.setBorder(BorderFactory.createLineBorder(darkGrey, 3));
        return passwordField;
    }
    public JPanel newPanel(int [] dimension, Color color){
        JPanel panel = new JPanel();
        panel.setBounds(dimension[0], dimension[1], dimension[2], dimension[3]);
        panel.setBackground(color);
        panel.setLayout(null);
        return panel;
    }
    public JTable newTable(String[] columns) {
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setFont(font2);
        table.setRowHeight(34);
        table.setForeground(whiteColor);
        table.setBackground(defaultColor);
        table.setGridColor(whiteColor);
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setSelectionBackground(darkGrey);
        table.setSelectionForeground(whiteColor);
        table.getTableHeader().setFont(font2);
        table.getTableHeader().setBackground(blackColor);
        table.getTableHeader().setForeground(whiteColor);
        table.setFillsViewportHeight(true);
        return table;
    }
}