//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame {
    public Snake() {
        this.initUI();
    }

    private void initUI() {
        this.add(new Board());
        this.setResizable(false);
        this.pack();
        this.setTitle("Snake");
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(true);
        });
    }
}
