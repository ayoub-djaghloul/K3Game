package Controller;

import javax.swing.*;
import java.awt.*;
import Model.*;

public class Table2DDisplay {
    private Table2D table2D;

    public Table2DDisplay(Table2D table2D) {
        this.table2D = table2D;
    }

    public void display() {
        JFrame frame = new JFrame("Table2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(5, 5));

        JPanel panel = new JPanel(new GridLayout(table2D.getHeight(), table2D.getWidth()));
        int k = 0;
        for (int i = 0; i < table2D.getHeight(); i++) {
            for (int j = 0; j < table2D.getWidth(); j++) {
                k++;
                Pion pion = table2D.getCases()[i][j];
                JLabel label = new JLabel(pion.getImageIcon());
                if (k==16){
                    label.setVisible(false);
                }else {
                    panel.add(label);
                }
            }
        }
        frame.add(panel); // add the panel to the frame

        frame.setVisible(true);
    }
}
