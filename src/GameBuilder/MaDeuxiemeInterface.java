package GameBuilder;

import Controller.DisplayPyramidC;
import Model.Pion;
import Model.Pyramide;
import Model.PyramideIA;
import Model.Table2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaDeuxiemeInterface extends JFrame {

    public MaDeuxiemeInterface(Pyramide pyramidePlayer, PyramideIA pyramideIA) {
        super("Deuxième Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

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

        // Ajouter un bouton "Afficher" qui affiche la pyramide du joueur
        JButton afficherButton = new JButton("pyramid 1 ");
        afficherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < pyramidePlayer.getHight(); i++) {
                    for (int j = 0; j <= i; j++) {
                        Pion pion = pyramidePlayer.getPion(i, j);
                        System.out.print(pion.getCouleur().toString() + " ");
                    }
                    System.out.println();
                }
            }
        });
        add(afficherButton, BorderLayout.NORTH);

        // Ajouter un panel pour afficher la pyramide
        JPanel pyramidePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = 50; // position horizontale du premier pion
                int y = 50; // position verticale du premier pion
                int size = 50; // taille d'un pion
                for (int i = 0; i < pyramidePlayer.getHight(); i++) {
                    for (int j = 0; j <= i; j++) {
                        Pion pion = pyramidePlayer.getPion(i, j);
                        g.setColor(pion.getCouleur());
                        g.fillOval(x + j * size, y + i * size, size, size);
                    }
                }
            }
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600, 600);
            }
        };
        JPanel pyramideIAPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = 50; // position horizontale du premier pion
                int y = 50; // position verticale du premier pion
                int size = 50; // taille d'un pion
                for (int i = 0; i < pyramideIA.getHight(); i++) {
                    for (int j = 0; j <= i; j++) {
                        Pion pion = pyramideIA.getPion(i, j);
                        g.setColor(pion.getCouleur());
                        g.fillOval(x + j * size, y + i * size, size, size);
                    }
                }
            }
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600, 600);
            }
        };
        add(pyramidePanel, BorderLayout.WEST);
        add(pyramideIAPanel, BorderLayout.EAST);

    }

    public void displayPyramids() {
    //afficher la pyramide du joueur et la pyramide de l'adversaire et un pyramide vide de base 9


        setVisible(true);
    }
}
