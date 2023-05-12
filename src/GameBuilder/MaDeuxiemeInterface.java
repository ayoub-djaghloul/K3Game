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
import java.util.Stack;

public class MaDeuxiemeInterface extends JFrame {
    private JLabel labelr;
    private Pyramide pyramidePlayer;
    private PyramideIA pyramideIA;
    private Table2D table2D;
    private Stack<Pion> history2Dtable = new Stack<Pion>();
    private Stack<Pion> historyPyramid = new Stack<Pion>();


    public MaDeuxiemeInterface(Pyramide pyramidePlayer, PyramideIA pyramideIA) {
        super("Deuxième Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        final int[] pionCount = {0};
        final Pion[] pionSource = new Pion[1];



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
        JPanel pyramidPanel = new JPanel(new GridLayout(pyramidePlayer.getHight(), pyramidePlayer.getWidth()));
        // Ajouter un panel pour afficher la pyramide
        for (int i = 0; i < pyramidePlayer.getHight(); i++) {
            JPanel pyramideiPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int j = 0; j <= i; j++) {
                Pion pion = pyramidePlayer.getPion(i, j);
                JLabel pionLabel = new JLabel(pion.getImageIcon());
                int finalI = i;
                int finalJ = j;
                pionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (labelr != null) {
                            Pion pionDestination = pyramidePlayer.getPion(finalI, finalJ);
                            if (pionDestination.estAccessible()) {
                                pionLabel.setIcon(labelr.getIcon());
                                //labelr.setIcon(new ImageIcon(new ImageIcon("sources/Images/VIDE.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                                labelr.setVisible(false);
                                labelr = null;
                                pionDestination.replacePion(pionSource[0]);
                                pionDestination.setAccessible(false);
                                historyPyramid.push(pionDestination);
                                pionCount[0]++;
                                System.out.println(pionCount[0]);
                            }
                        }
                    }
                });
                pyramideiPanel.add(pionLabel);
            }
            pyramidPanel.add(pyramideiPanel);
        }
        JPanel pyramidiaPanel = new JPanel(new GridLayout(pyramideIA.getHight(), pyramideIA.getWidth()));
        // Ajouter un panel pour afficher la pyramide
        for (int i = 0; i < pyramideIA.getHight(); i++) {
            JPanel pyramideiPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int j = 0; j <= i; j++) {
                Pion pion = pyramideIA.getPion(i, j);
                JLabel pionLabel = new JLabel(pion.getImageIcon());
                int finalI = i;
                int finalJ = j;
                pionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (labelr != null) {
                            Pion pionDestination = pyramideIA.getPion(finalI, finalJ);
                            if (pionDestination.estAccessible()) {
                                pionLabel.setIcon(labelr.getIcon());
                                //labelr.setIcon(new ImageIcon(new ImageIcon("sources/Images/VIDE.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                                labelr.setVisible(false);
                                labelr = null;
                                pionDestination.replacePion(pionSource[0]);
                                pionDestination.setAccessible(false);
                                historyPyramid.push(pionDestination);
                                pionCount[0]++;
                                System.out.println(pionCount[0]);
                            }
                        }
                    }
                });
                pyramideiPanel.add(pionLabel);
            }
            pyramidiaPanel.add(pyramideiPanel);
        }

        add(pyramidPanel, BorderLayout.WEST);
        add(pyramidiaPanel, BorderLayout.EAST);

    }

    public void displayPyramids() {
    //afficher la pyramide du joueur et la pyramide de l'adversaire et un pyramide vide de base 9


        setVisible(true);
    }
}
