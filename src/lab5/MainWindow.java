package lab5;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainWindow extends GUI implements ActionListener {
    JButton Home, Add, View, Search, Update, Delete, Exit;
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

        this.add(Menu);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==Exit){
            System.exit(0);
        }
    }
}
