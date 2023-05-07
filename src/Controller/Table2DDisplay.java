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
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        int y = 0;

        JPanel panel = new JPanel(new GridLayout(table2D.getHeight(), table2D.getWidth()));
        for (int i = 0; i < table2D.getHeight(); i++) {
            for (int j = 0; j < table2D.getWidth(); j++) {
                y++;
                Pion pion = table2D.getCases()[i][j];
                JLabel label = new JLabel(pion.getImageIcon());

                //print the image in the console
                System.out.print(pion.getImageIcon().toString());

                panel.add(label);
            }
        }
        int x = 0;
        JPanel pyramidPanel = new JPanel(new GridLayout(5, 5,0,0));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4 - i; j++) {
                JLabel label = new JLabel(new ImageIcon(new ImageIcon("sources/Images/VIDE.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        System.out.println("clicked vide");
                    }
                });
                pyramidPanel.add(label);
            }
            for (int j = 0; j < i+1; j++) {
                x++;
                JLabel label = new JLabel(new ImageIcon(new ImageIcon("sources/Images/ROUGE.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                //onclick label listener
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        System.out.println("clicked rouge");
                    }
                });
                System.out.println(x);
                pyramidPanel.add(label);
            }
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.add(pyramidPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

}
