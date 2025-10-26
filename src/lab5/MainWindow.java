package lab5;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainWindow extends GUI implements ActionListener {
    JButton Home, Add, View, Search, Update, Delete, Exit;
    JPanel cards;
    public String CARD_HOME   = "HOME";
    public String CARD_ADD    = "ADD";
    public String CARD_VIEW   = "VIEW";
    public String CARD_SEARCH = "SEARCH";
    public String CARD_UPDATE = "UPDATE";
    public String CARD_DELETE = "DELETE";
    DefaultTableModel viewModel;
    DefaultTableModel updateModel;
    JTable updateTable;
    JTextField uID, uName, uAge, uGpa;
    JComboBox<String> uDept;
    JRadioButton uMale, uFemale;
    public MainWindow(){
        this.setSize(1000, 700);
        this.setTitle("Main Window");
        int [] panelDimension1 = {0, 0, 350, 700};
        JPanel Menu = newPanel(panelDimension1, darkGrey);

        int [] dimension = {100, 50, 150, 50};
        Home = newButton("Home", dimension, this);
        Menu.add(Home);

        dimension[1] += 75;
        Add = newButton("Add", dimension, this);
        Menu.add(Add);

        dimension[1] += 75;
        View = newButton("View", dimension, this);
        Menu.add(View);

        dimension[1] += 75;
        Search = newButton("Search", dimension, this);
        Menu.add(Search);

        dimension[1] += 75;
        Update = newButton("Update", dimension, this);
        Menu.add(Update);

        dimension[1] += 75;
        Delete = newButton("Delete", dimension, this);
        Menu.add(Delete);

        dimension[1] += 75;
        Exit = newButton("Exit", dimension, this);
        Menu.add(Exit);

        int [] panelDimension2 = {350, 0, 650, 700};
        JPanel homePanel = newPanel(panelDimension2, defaultColor);
        int [] homeLabelDimension = {10, 10, 630, 680};
        JLabel homeLabel = newLabel("Welcome to Student Management System", font, JLabel.CENTER, JLabel.CENTER, homeLabelDimension);
        homePanel.add(homeLabel);

        JPanel addPanel = newPanel(panelDimension2, defaultColor);
        int xLabel = 30;
        int xField = 170;
        int y = 30;
        int labelW = 120;
        int fieldW = 350;
        int h = 40;
        int gap = 70;
        JLabel lblID = newLabel("Student ID:", font2, JLabel.LEFT, JLabel.CENTER, new int[]{xLabel, y, labelW, h});
        addPanel.add(lblID);
        JTextField txtID = newTextField(new int[]{xField, y, fieldW, h}, " ID");
        addPanel.add(txtID);
        y += gap;
        JLabel lblName = newLabel("Full Name:", font2, JLabel.LEFT, JLabel.CENTER, new int[]{xLabel, y, labelW, h});
        addPanel.add(lblName);
        JTextField txtName = newTextField(new int[]{xField, y, fieldW, h}, " Full Name");
        addPanel.add(txtName);
        y += gap;
        JLabel lblAge = newLabel("Age:", font2, JLabel.LEFT, JLabel.CENTER, new int[]{xLabel, y, labelW, h});
        addPanel.add(lblAge);
        JTextField txtAge = newTextField(new int[]{xField, y, 80, h}, " Age");
        addPanel.add(txtAge);
        JLabel lblGender = newLabel("Gender:", font2, JLabel.LEFT, JLabel.CENTER, new int[]{xField + 100, y, 80, h});
        addPanel.add(lblGender);
        JRadioButton rbMale = new JRadioButton("Male");
        rbMale.setFont(font2);
        rbMale.setBounds(xField + 180, y, 90, h);
        rbMale.setOpaque(false);
        rbMale.setForeground(whiteColor);
        rbMale.setFocusable(false);
        addPanel.add(rbMale);
        JRadioButton rbFemale = new JRadioButton("Female");
        rbFemale.setFont(font2);
        rbFemale.setBounds(xField + 270, y, 110, h);
        rbFemale.setOpaque(false);
        rbFemale.setForeground(whiteColor);
        rbFemale.setFocusable(false);
        addPanel.add(rbFemale);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        y += gap;
        JLabel lblDept = newLabel("Department:", font2, JLabel.LEFT, JLabel.CENTER, new int[]{xLabel, y, labelW, h});
        addPanel.add(lblDept);
        String[] departments = {"IGCSE", "American", "National"};
        JComboBox<String> cbDept = new JComboBox<>(departments);
        cbDept.setFont(font2);
        cbDept.setBounds(xField, y, fieldW, h);
        addPanel.add(cbDept);
        y += gap;
        JLabel lblGPA = newLabel("GPA: ", font2, JLabel.LEFT, JLabel.CENTER, new int[]{xLabel, y, labelW, h});
        addPanel.add(lblGPA);
        JTextField txtGPA = newTextField(new int[]{xField, y, 120, h}, " GPA");
        addPanel.add(txtGPA);
        y += gap + 10;
        int[] saveBtnDim = new int[]{xField, y, 120, 45};
        JButton btnSave = newButton("Save", saveBtnDim, this);
        addPanel.add(btnSave);

        JPanel viewPanel = newPanel(panelDimension2, defaultColor);
        String[] cols = {"Student ID", "Full Name", "Age", "Gender", "Department", "GPA"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
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
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 10, 630, 680);
        scroll.getViewport().setBackground(defaultColor);
        viewPanel.add(scroll);

        JPanel searchPanel = newPanel(panelDimension2, defaultColor);
        JTable viewTable = newTable(cols);
        JScrollPane viewScroll = new JScrollPane(viewTable);
        viewScroll.setBounds(10, 10, 630, 680);
        viewScroll.getViewport().setBackground(defaultColor);
        viewPanel.add(viewScroll);
        JLabel lblSearch = newLabel("Search (ID or Name):", font2, JLabel.LEFT, JLabel.CENTER, new int[]{30, 20, 250, 40});
        searchPanel.add(lblSearch);
        JTextField txtSearch = newTextField(new int[]{280, 20, 250, 40}, " Enter ID or Name");
        searchPanel.add(txtSearch);
        JButton btnSearch = newButton("Search", new int[]{540, 20, 100, 40}, this);
        btnSearch.setFont(new Font("SansSerif", Font.BOLD, 15));
        searchPanel.add(btnSearch);
        JTable searchTable = newTable(cols);
        JScrollPane searchScroll = new JScrollPane(searchTable);
        searchScroll.setBounds(10, 80, 630, 600);
        searchScroll.getViewport().setBackground(defaultColor);
        searchPanel.add(searchScroll);

        JPanel updatePanel = newPanel(panelDimension2, defaultColor);
        updateModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        updateTable = newTable(cols);
        updateTable.setModel(updateModel);
        JScrollPane uscroll = new JScrollPane(updateTable);
        uscroll.setBounds(10, 10, 630, 250);
        uscroll.getViewport().setBackground(defaultColor);
        updatePanel.add(uscroll);
        int ux = 30, uy = 280, w = 250, uh = 40;
        uID = newTextField(new int[]{ux, uy, w, uh}, " Student ID");
        updatePanel.add(uID);
        uName = newTextField(new int[]{ux + 270, uy, w, uh}, " Full Name");
        updatePanel.add(uName);
        uy += 60;
        uAge = newTextField(new int[]{ux, uy, w, uh}, " Age");
        updatePanel.add(uAge);
        JLabel ulblGender = newLabel("Gender:", font2, JLabel.LEFT, JLabel.CENTER, new int[]{ux + 270, uy, 100, uh});
        updatePanel.add(ulblGender);
        uMale = new JRadioButton("Male");
        uMale.setFont(font2);
        uMale.setBounds(ux + 350, uy, 90, uh);
        uMale.setOpaque(false);
        uMale.setForeground(whiteColor);
        updatePanel.add(uMale);
        uFemale = new JRadioButton("Female");
        uFemale.setFont(font2);
        uFemale.setBounds(ux + 440, uy, 120, uh);
        uFemale.setOpaque(false);
        uFemale.setForeground(whiteColor);
        updatePanel.add(uFemale);
        ButtonGroup group = new ButtonGroup();
        group.add(uMale);
        group.add(uFemale);
        uy += 60;
        JLabel ulblDept = newLabel("Department:", font2, JLabel.LEFT, JLabel.CENTER, new int[]{ux, uy, 150, h});
        updatePanel.add(ulblDept);
        uDept = new JComboBox<>(departments);
        uDept.setFont(font2);
        uDept.setBounds(ux + 160, uy, w, h);
        updatePanel.add(uDept);
        uGpa = newTextField(new int[]{ux + 270, uy, w, h}, "GPA");
        updatePanel.add(uGpa);
        uy += 70;
        JButton btnLoad = newButton("Load", new int[]{ux, uy, 150, 50}, this);
        btnLoad.setFont(new Font("SansSerif", Font.BOLD, 20));
        updatePanel.add(btnLoad);
        JButton ubtnSave = newButton("Save Changes", new int[]{ux + 200, uy, 250, 50}, this);
        ubtnSave.setFont(new Font("SansSerif", Font.BOLD, 20));
        updatePanel.add(ubtnSave);

        JPanel deletePanel = newPanel(panelDimension2, defaultColor);
        JTable deleteTable = newTable(cols);
        JScrollPane deleteScroll = new JScrollPane(deleteTable);
        deleteScroll.setBounds(10, 10, 630, 600);
        deleteScroll.getViewport().setBackground(defaultColor);
        deletePanel.add(deleteScroll);
        JButton btnDelete = newButton("Delete", new int[]{30, 620, 200, 45}, this);
        deletePanel.add(btnDelete);

        cards = new JPanel(new CardLayout());
        cards.setBounds(panelDimension2[0], panelDimension2[1], panelDimension2[2], panelDimension2[3]);
        cards.add(homePanel, CARD_HOME);
        cards.add(addPanel, CARD_ADD);
        cards.add(viewPanel, CARD_VIEW);
        cards.add(searchPanel, CARD_SEARCH);
        cards.add(updatePanel, CARD_UPDATE);
        cards.add(deletePanel, CARD_DELETE);

        this.add(Menu);
        this.add(cards);
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, CARD_HOME);
        this.revalidate();
        this.repaint();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        if (e.getSource() == Home){
            cl.show(cards, CARD_HOME);
        } else if (e.getSource() == Add){
            cl.show(cards, CARD_ADD);
        } else if (e.getSource() == View){
            cl.show(cards, CARD_VIEW);
        } else if (e.getSource() == Search){
            cl.show(cards, CARD_SEARCH);
        } else if (e.getSource() == Update){
            cl.show(cards, CARD_UPDATE);
        } else if (e.getSource() == Delete){
            cl.show(cards, CARD_DELETE);
        } else if (e.getSource() == Exit){
            System.exit(0);
        }
    }
}
