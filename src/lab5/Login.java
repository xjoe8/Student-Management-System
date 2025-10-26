package lab5;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Login extends GUI implements ActionListener {
    public JButton loginButton, exitButton;
    JTextField textField;
    JPasswordField passwordField;
    public Login(){
        this.setSize(700,600);
        this.setTitle("Login Page");

        int [] panelDimension1 = {0, 0, 700, 100};
        JPanel welcomePanel = newPanel(panelDimension1, darkGrey);
        this.add(welcomePanel);

        JLabel label1 = newLabel("Welcome to Student Management System", font, JLabel.CENTER, JLabel.CENTER, panelDimension1);
        welcomePanel.add(label1);

        int [] labelDimension1 = {50, 150, 300, 100};
        JLabel label2 = newLabel("Username:", font2, JLabel.CENTER, JLabel.TOP, labelDimension1);
        this.add(label2);

        int [] textFieldDimension = {150, 180, 250, 40};
        textField = newTextField(textFieldDimension, " Username");
        this.add(textField);

        int [] labelDimension2 = {50, 300, 300, 100};
        JLabel label3 = newLabel("Password:", font2, JLabel.CENTER, JLabel.TOP, labelDimension2);
        this.add(label3);

        int [] passwordFieldDimension = {150, 330, 250, 40};
        passwordField = newPasswordField(passwordFieldDimension, " Password");
        this.add(passwordField);

        int [] buttonDimension1 = {100, 450, 150, 50};
        loginButton = newButton("Login", buttonDimension1, this);
        this.add(loginButton);

        int [] buttonDimension2 = {450, 450, 150, 50};
        exitButton = newButton("Exit", buttonDimension2, this);
        this.add(exitButton);

        this.repaint();
        this.revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){
            String username = textField.getText();
            String password = new String(passwordField.getPassword());
            System.out.println(username);
            System.out.println(password);
            //method that checks if username and password are found in the file or not
            //this method should return true or false
            //if true then it access the main window
            //if false then it gives an error message and the user clicks on okay then it goes to the login page again
        }
        if (e.getSource() == exitButton){
            System.exit(0);
        }
    }
}