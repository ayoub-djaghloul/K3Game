package GameBuilder;

import Controller.DisplayPyramidC;
import Controller.Table2DDisplay;
import Model.Pyramide;
import Model.Table2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaDeuxiemeInterface extends JFrame {

    public MaDeuxiemeInterface() {
        super("Deuxième Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Ajouter un bouton "Retour" qui ferme cette fenêtre et affiche la fenêtre précédente
        JButton retourButton = new JButton("Retour");
        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Table2DBuilder table2DBuilder = new Table2DBuilder();
                PyramidBuilder pyramidBuilder = new PyramidBuilder(5, 5);
                table2DBuilder.RandomPions();
                pyramidBuilder.generatePyramid();
                Table2D table2D = table2DBuilder.getTable2D();
                Pyramide pyramide = pyramidBuilder.getPyramidePlayer();
                DisplayPyramidC displayPyramidC = new DisplayPyramidC(table2D, pyramide);
                displayPyramidC.displayTableAndPyramid();
                dispose();
            }
        });
        add(retourButton, BorderLayout.SOUTH);
    }

    public void display() {

        setVisible(true);
    }
}
