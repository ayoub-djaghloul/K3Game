package GameBuilder;

import Controller.DisplayPyramidC;
import Model.Pion;
import Model.Pyramide;
import Model.PyramideIA;
import Model.Table2D;
import View.MainFrame;

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

    private int pionCount;


    ImageIcon VIDE = new ImageIcon("sources/Images/VIDE.png");

    public MaDeuxiemeInterface(Pyramide pyramidePlayer, PyramideIA pyramideIA, Table2D baseK3) {
        super("Deuxième Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        final Pion[] pionSource = new Pion[1];
        pionCount = 0;
        JLabel feedbackLabelcenter = new JLabel(" ");
        JLabel feedbackLabelleft = new JLabel(" ");
        JLabel feedbackLabelright = new JLabel(" ");




        // Ajouter un bouton "Retour" qui ferme cette fenêtre et affiche la fenêtre précédente
        JButton retourButton = new JButton("Rejouer");
        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int bag[] = {9, 9, 9, 9, 9};
                Table2D table2D = new Table2D(2, 11, bag);
                Pyramide playerPyramide = new Pyramide(6, 6);
                Table2D baseK3 = new Table2D(1, 9, bag);
                playerPyramide.initPyramide(1);
              //  MainFrame mainFrame = new MainFrame(table2D, playerPyramide , baseK3);
                dispose();

            }
        });
        add(retourButton, BorderLayout.SOUTH);

        // Ajouter un bouton "Afficher" qui affiche la pyramide du joueur
        JButton afficherButton = new JButton("pyramid player 1 in terminal ");
        afficherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < pyramidePlayer.getHight(); i++) {
                    for (int j = 0; j <= i; j++) {
                        Pion pion = pyramidePlayer.getPion(i, j);
                        System.out.print(pion.getType()+ " ");
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
                        pionSource[0]=pyramidePlayer.getPion(finalI, finalJ);
                        labelr = pionLabel;
                        history2Dtable.push(pionSource[0]);
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
                        pionSource[0]=pyramideIA.getPion(finalI, finalJ);
                        labelr = pionLabel;
                        history2Dtable.push(pionSource[0]);
                        }
                });
                pyramideiPanel.add(pionLabel);
            }
            pyramidiaPanel.add(pyramideiPanel);
        }
        //pyramide vide au centre de base 9 pions
        Pyramide pyramidevidePlayer = new Pyramide(9, 9);
        pyramidevidePlayer.initPyramide(1);
        JPanel pyramidvidePanel = new JPanel(new GridLayout(pyramidevidePlayer.getHight(), pyramidevidePlayer.getWidth()));
        // Ajouter un panel pour afficher la pyramide
        for (int i = 0; i < 9; i++) {
    JPanel pyramideiPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    for (int j = 0; j <= i; j++) {
                Pion pion ;
                JLabel pionLabel;
                if(i==8){
                    pyramidevidePlayer.setPion(8, j, baseK3.getPion(0, j));
                    pion = pyramidevidePlayer.getPion(i, j);
                    pionLabel = new JLabel(pion.getImageIcon());
                }else{
                    pion = pyramidevidePlayer.getPion(i, j);
                    pionLabel = new JLabel(pion.getImageIcon());
                int finalI = i;
                int finalJ = j;
                pionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (labelr != null) {
                            Pion pionDestination = pyramidevidePlayer.getPion(finalI, finalJ);
                            Pion pionfils1 = pyramidevidePlayer.getPion(finalI + 1, finalJ);
                            Pion pionfils2 = pyramidevidePlayer.getPion(finalI + 1, finalJ + 1);
                            //ecrire les informations des fils dans le feedback avec linformation de la source
                            feedbackLabelleft.setText("fils 1 : " + pionfils1.getCouleur() + " fils 2 : " + pionfils2.getCouleur());
                            feedbackLabelleft.setVisible(true);
                            feedbackLabelright.setText("source : " + pionSource[0].getCouleur());
                            feedbackLabelright.setVisible(true);
                            //si la destination est accessible(vide)
                            if (pionDestination.estAccessible()){
                                if(pionfils1.ifestnull()||pionfils2.ifestnull()){
                                    feedbackLabelcenter.setText("les fils ou un des fils est vide");
                                    feedbackLabelcenter.setForeground(Color.RED);
                                    feedbackLabelcenter.setVisible(true);
                                    feedbackLabelleft.setVisible(false);
                                    feedbackLabelright.setVisible(false);
                                }
                            else if(pionSource[0].getCouleur()==pionfils1.getCouleur()||pionSource[0].getCouleur()==pionfils2.getCouleur()) {
                                pionDestination.setAccessible(false);
                                pionLabel.setIcon(labelr.getIcon());
                                labelr.setVisible(false);
                                labelr = null;
                                feedbackLabelcenter.setText("Pion déplacé");
                                feedbackLabelcenter.setForeground(Color.GREEN);
                                feedbackLabelcenter.setVisible(true);
                                feedbackLabelleft.setVisible(false);
                                feedbackLabelright.setVisible(false);
                                pionDestination.replacePion(pionSource[0]);
                                pionDestination.setAccessible(false);
                                historyPyramid.push(pionDestination);
                                pionCount++;
                                System.out.println(pionCount);
                            }
                            }
                            else {
                                feedbackLabelcenter.setText("le pion Destination est pas vide");
                                feedbackLabelcenter.setForeground(Color.RED);
                            }
                        }
                    }
                });}
                pyramideiPanel.add(pionLabel);
            }
            pyramidvidePanel.add(pyramideiPanel);
        }


        // créer le conteneur pour feedbackLabel et pyramidvidePanel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(feedbackLabelcenter, BorderLayout.NORTH);
        centerPanel.add(pyramidvidePanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());
        leftPanel.add(feedbackLabelleft, BorderLayout.NORTH);
        leftPanel.add(pyramidPanel, BorderLayout.SOUTH);
        rightPanel.add(feedbackLabelright, BorderLayout.NORTH);
        rightPanel.add(pyramidiaPanel, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

    public void displayPyramids() {
    //afficher la pyramide du joueur et la pyramide de l'adversaire et un pyramide vide de base 9
        setVisible(true);
    }
}
