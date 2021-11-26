import javax.swing.*;

public class MainFrame extends JFrame {
    MainFrame(){
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(250,250,300,325);
        add(new GameField());
        setVisible(true);
    }
    public static void main(String [] args){
        new MainFrame();
    }
}
