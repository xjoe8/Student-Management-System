package lab5;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Login extends GUI implements ActionListener {
    JButton loginButton, exitButton;
    JTextField textField;
    JPasswordField passwordField;
    final Font font = new Font("SansSerif", Font.BOLD| Font.ITALIC, 30);
    final Font font2 = new Font("SansSerif", Font.BOLD, 18);
    final Color whiteColor = new Color(0xFFFFFF);
    final Color blackColor = new Color(0x000000);
    public Login(){
        this.setSize(700,600);
        this.setTitle("Login Page");
        JLabel label = new JLabel();
        label.setText("Welcome to Student Management System");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setForeground(whiteColor);
        label.setFont(font);
        label.setBounds(0, 50, 700, 200);
        JLabel label2 = new JLabel();
        label2.setText("Username:");
        label2.setVerticalAlignment(JLabel.TOP);
        label2.setHorizontalAlignment(JLabel.LEFT);
        label2.setForeground(whiteColor);
        label2.setFont(font2);
        label2.setBounds(100, 150, 700, 20);
        textField = new JTextField();
        textField.setBounds(100, 180, 250, 45);
        textField.setFont(font2);
        JLabel label3 = new JLabel();
        label3.setText("Password:");
        label3.setVerticalAlignment(JLabel.TOP);
        label3.setHorizontalAlignment(JLabel.LEFT);
        label3.setForeground(whiteColor);
        label3.setFont(font2);
        label3.setBounds(100, 290, 700, 20);
        passwordField = new JPasswordField();
        passwordField.setFont(font2);
        passwordField.setBounds(100,320, 250, 45);
        loginButton = new JButton();
        loginButton.setBounds(100, 450, 150, 50);
        loginButton.addActionListener(this);
        loginButton.setText("Login");
        loginButton.setFocusable(false);
        loginButton.setFont(font);
        loginButton.setForeground(whiteColor);
        loginButton.setBackground(blackColor);
        exitButton = new JButton();
        exitButton.setBounds(450, 450, 150, 50);
        exitButton.addActionListener(this);
        exitButton.setText("Exit");
        exitButton.setFocusable(false);
        exitButton.setFont(font);
        exitButton.setForeground(whiteColor);
        exitButton.setBackground(blackColor);
        this.add(exitButton);
        this.add(loginButton);
        this.add(label);
        this.add(label2);
        this.add(textField);
        this.add(label3);
        this.add(passwordField);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){
            String username = textField.getText();
            String password = new String(passwordField.getPassword());
            //method that checks if username and password are found in the file or not
            //this method should return true or false
            //if true then it access the main menu
            //if false then it gives an error message and the user clicks on okay then it goes to the login page again
        }
        if (e.getSource() == exitButton){
            System.exit(0);
        }
    }
}