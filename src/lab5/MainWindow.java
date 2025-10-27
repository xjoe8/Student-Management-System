import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class MainWindow extends JFrame {
    private final FileHandler fileHandler = new FileHandler("students.txt");
    private final IdGenerator idGen = new IdGenerator("id-seq.txt", 1000);
    private java.util.List<Student> students;

    private JTabbedPane tabs;
    private JTable tableAll, tableSearch, tableDelete;
    private DefaultTableModel modelAll, modelSearch, modelDelete;
    private JTextField nameField, ageField, deptField, gpaField, searchField, updIdField;
    private JComboBox<String> genderBox;
    private JButton addBtn, clearBtn, updateBtn, saveUpdBtn, deleteBtn;

    public MainWindow(String username) {
        setTitle("Student Management System - Welcome " + username);
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load data
        students = fileHandler.loadStudents();

        tabs = new JTabbedPane();
        buildAddPanel();
        buildViewPanel();
        buildSearchPanel();
        buildDeletePanel();
        refreshTables();

        add(tabs);
    }

    // ---------- ADD TAB ----------
    private void buildAddPanel() {
        JPanel addPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(20);
        ageField = new JTextField(20);
        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        deptField = new JTextField(20);
        gpaField = new JTextField(20);
        addBtn = new JButton("Add Student");
        clearBtn = new JButton("Clear");

        int r = 0;
        gbc.gridx = 0; gbc.gridy = r; addPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; addPanel.add(nameField, gbc); r++;
        gbc.gridx = 0; gbc.gridy = r; addPanel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1; addPanel.add(ageField, gbc); r++;
        gbc.gridx = 0; gbc.gridy = r; addPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1; addPanel.add(genderBox, gbc); r++;
        gbc.gridx = 0; gbc.gridy = r; addPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1; addPanel.add(deptField, gbc); r++;
        gbc.gridx = 0; gbc.gridy = r; addPanel.add(new JLabel("GPA:"), gbc);
        gbc.gridx = 1; addPanel.add(gpaField, gbc); r++;

        JPanel btns = new JPanel();
        btns.add(addBtn); btns.add(clearBtn);
        gbc.gridx = 0; gbc.gridy = r; gbc.gridwidth = 2;
        addPanel.add(btns, gbc);

        addBtn.addActionListener(e -> addStudent());
        clearBtn.addActionListener(e -> {
            nameField.setText(""); ageField.setText("");
            deptField.setText(""); gpaField.setText("");
            genderBox.setSelectedIndex(0);
        });

        tabs.addTab("Add", addPanel);
    }

    private void addStudent() {
        try {
            String name = nameField.getText().trim();
            String ageS = ageField.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            String dept = deptField.getText().trim();
            String gpaS = gpaField.getText().trim();

            if (name.isEmpty() || ageS.isEmpty() || dept.isEmpty() || gpaS.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Validation", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int age = Integer.parseInt(ageS);
            double gpa = Double.parseDouble(gpaS);
            
            if(gpa < 0.0 || gpa > 4.0) {
                JOptionPane.showMessageDialog(this, "GPA must be gretaer than 0.0 and less than 4.0 .", "Validation", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = idGen.nextId();
            Student s = new Student(id, name, age, gender, dept, gpa);
            students.add(s);
            fileHandler.addStudent(s);
            refreshTables();

            JOptionPane.showMessageDialog(this, "Student Added Successfully! (ID: " + id + ")");
            nameField.setText(""); ageField.setText(""); deptField.setText(""); gpaField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be integer and GPA must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------- VIEW TAB ----------
    private void buildViewPanel() {
        JPanel viewPanel = new JPanel(new BorderLayout());
        modelAll = new DefaultTableModel(new Object[]{"ID", "Name", "Age", "Gender", "Department", "GPA"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableAll = new JTable(modelAll);
        viewPanel.add(new JScrollPane(tableAll), BorderLayout.CENTER);
        tabs.addTab("View", viewPanel);
    }

    // ---------- SEARCH & UPDATE TAB ----------
    private void buildSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        JPanel top = new JPanel();
        top.add(new JLabel("Search by ID or Name:"));
        searchField = new JTextField(18);
        JButton searchBtn = new JButton("Search");
        top.add(searchField); top.add(searchBtn);
        searchPanel.add(top, BorderLayout.NORTH);

        modelSearch = new DefaultTableModel(new Object[]{"ID", "Name", "Age", "Gender", "Department", "GPA"}, 0);
        tableSearch = new JTable(modelSearch);
        tableSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchPanel.add(new JScrollPane(tableSearch), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updIdField = new JTextField(10); updIdField.setEditable(false);
        JTextField updName = new JTextField(15);
        JTextField updAge = new JTextField(5);
        JComboBox<String> updGender = new JComboBox<>(new String[]{"Male", "Female"});
        JTextField updDept = new JTextField(10);
        JTextField updGpa = new JTextField(5);
        saveUpdBtn = new JButton("Save Update");

        int r=0;
        gbc.gridx=0; gbc.gridy=r; bottom.add(new JLabel("ID:"), gbc); gbc.gridx=1; bottom.add(updIdField, gbc); r++;
        gbc.gridx=0; gbc.gridy=r; bottom.add(new JLabel("Name:"), gbc); gbc.gridx=1; bottom.add(updName, gbc); r++;
        gbc.gridx=0; gbc.gridy=r; bottom.add(new JLabel("Age:"), gbc); gbc.gridx=1; bottom.add(updAge, gbc); r++;
        gbc.gridx=0; gbc.gridy=r; bottom.add(new JLabel("Gender:"), gbc); gbc.gridx=1; bottom.add(updGender, gbc); r++;
        gbc.gridx=0; gbc.gridy=r; bottom.add(new JLabel("Dept:"), gbc); gbc.gridx=1; bottom.add(updDept, gbc); r++;
        gbc.gridx=0; gbc.gridy=r; bottom.add(new JLabel("GPA:"), gbc); gbc.gridx=1; bottom.add(updGpa, gbc); r++;
        gbc.gridx=0; gbc.gridy=r; gbc.gridwidth=2; bottom.add(saveUpdBtn, gbc);
        searchPanel.add(bottom, BorderLayout.SOUTH);

        searchBtn.addActionListener(e -> {
            String q = searchField.getText().trim().toLowerCase();
            modelSearch.setRowCount(0);
            for (Student s : students) {
                if (String.valueOf(s.getId()).equals(q) || s.getFullName().toLowerCase().contains(q))
                    modelSearch.addRow(new Object[]{s.getId(), s.getFullName(), s.getAge(), s.getGender(), s.getDepartment(), s.getGpa()});
            }
        });

        tableSearch.getSelectionModel().addListSelectionListener(e -> {
            int row = tableSearch.getSelectedRow();
            if (row >= 0) {
                updIdField.setText(modelSearch.getValueAt(row, 0).toString());
                updName.setText(modelSearch.getValueAt(row, 1).toString());
                updAge.setText(modelSearch.getValueAt(row, 2).toString());
                updGender.setSelectedItem(modelSearch.getValueAt(row, 3).toString());
                updDept.setText(modelSearch.getValueAt(row, 4).toString());
                updGpa.setText(modelSearch.getValueAt(row, 5).toString());
            }
        });

        saveUpdBtn.addActionListener(e -> {
            try {
                if (updIdField.getText().isEmpty()) return;
                int id = Integer.parseInt(updIdField.getText());
                String name = updName.getText().trim();
                int age = Integer.parseInt(updAge.getText().trim());
                String gender = (String) updGender.getSelectedItem();
                String dept = updDept.getText().trim();
                double gpa = Double.parseDouble(updGpa.getText().trim());

                Student updated = new Student(id, name, age, gender, dept, gpa);
                if (fileHandler.updateStudent(updated)) {
                    students = fileHandler.loadStudents();
                    refreshTables();
                    JOptionPane.showMessageDialog(this, "Student updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        tabs.addTab("Search/Update", searchPanel);
    }

    // ---------- DELETE TAB ----------
    private void buildDeletePanel() {
        JPanel delPanel = new JPanel(new BorderLayout());
        modelDelete = new DefaultTableModel(new Object[]{"ID", "Name", "Age", "Gender", "Department", "GPA"}, 0);
        tableDelete = new JTable(modelDelete);
        delPanel.add(new JScrollPane(tableDelete), BorderLayout.CENTER);

        deleteBtn = new JButton("Delete Selected");
        deleteBtn.addActionListener(e -> {
            int row = tableDelete.getSelectedRow();
            if (row < 0) return;
            int id = Integer.parseInt(modelDelete.getValueAt(row, 0).toString());
            if (JOptionPane.showConfirmDialog(this, "Delete student ID " + id + "?") == JOptionPane.YES_OPTION) {
                if (fileHandler.deleteStudentById(id)) {
                    students = fileHandler.loadStudents();
                    refreshTables();
                    JOptionPane.showMessageDialog(this, "Student deleted.");
                }
            }
        });
        delPanel.add(deleteBtn, BorderLayout.SOUTH);
        tabs.addTab("Delete", delPanel);
    }

    private void refreshTables() {
        students = fileHandler.loadStudents();
        DefaultTableModel[] models = {modelAll, modelSearch, modelDelete};
        for (DefaultTableModel m : models) {
            if (m == null) continue;
            m.setRowCount(0);
            for (Student s : students)
                m.addRow(new Object[]{s.getId(), s.getFullName(), s.getAge(), s.getGender(), s.getDepartment(), s.getGpa()});
        }
    }
}
