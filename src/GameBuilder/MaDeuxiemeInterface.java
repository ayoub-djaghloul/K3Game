package GameBuilder;

import Controller.Table2DDisplay;
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
                dispose(); // ferme cette fenêtre
                Table2DBuilder builder = new Table2DBuilder();
                builder.RandomPions();
                Table2D table2D = builder.getTable2D();
                Table2DDisplay maPremiereInterface = new Table2DDisplay(table2D);
                maPremiereInterface.display();
            }
        });
        add(retourButton, BorderLayout.SOUTH);
    }

    public void display() {
        setVisible(true);
    }
}
