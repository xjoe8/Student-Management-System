import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Login() {
        setTitle("Student Management System - Login");
        setSize(380, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel uLabel = new JLabel("Username:");
        JLabel pLabel = new JLabel("Password:");
        usernameField = new JTextField(18);
        passwordField = new JPasswordField(18);
        loginButton = new JButton("Login");

        gbc.gridx=0; gbc.gridy=0; panel.add(uLabel, gbc);
        gbc.gridx=1; gbc.gridy=0; panel.add(usernameField, gbc);
        gbc.gridx=0; gbc.gridy=1; panel.add(pLabel, gbc);
        gbc.gridx=1; gbc.gridy=1; panel.add(passwordField, gbc);
        gbc.gridwidth = 2;
        gbc.gridx=0; gbc.gridy=2; panel.add(loginButton, gbc);

        add(panel);

        getRootPane().setDefaultButton(loginButton);
        loginButton.addActionListener(e -> attemptLogin());
    }

    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (authenticate(username, password)) {
                SwingUtilities.invokeLater(() -> {
                    MainWindow mw = new MainWindow(username);
                    mw.setVisible(true);
                });
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "users.txt not found or unreadable.\nPlace it next to the .jar/.class files.\nFormat: username:password (one per line)",
                                          "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean authenticate(String username, String password) throws IOException {
        File f = new File("users.txt");
        if (!f.exists()) throw new FileNotFoundException("users.txt not found");
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split(":", 2);
                if (parts.length != 2) continue;
                String u = parts[0].trim();
                String p = parts[1].trim();
                if (u.equals(username) && p.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
