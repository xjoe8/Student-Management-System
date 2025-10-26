package lab5;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends GUI implements ActionListener {
    public JButton loginButton, exitButton;
    JTextField textField;
    JPasswordField passwordField;
    
    public Login() {
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
            String username = textField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            
            if ( checkCrendits(username, password) ) {
                this.dispose(); // close login window
                FileHandler handler = new FileHandler("students.txt");
                new MainWindow(handler);
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == exitButton){
            System.exit(0);
        }
    }

    private boolean checkCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading users file: " + ex.getMessage());
        }
        return false;
    }
}
