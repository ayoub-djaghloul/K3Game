package Controller;

import javax.swing.*;
import java.awt.*;
import Model.*;

import static javax.swing.SwingConstants.SOUTH;


public class DisplayPyramidC {
    /*this phase is for the displaying the construction of the pyramid of
     the player and displaying the 2D table*/

    private Table2D table2D;
    private Pyramide pyramidePlayer;

    private JLabel labelr;

    public DisplayPyramidC(Table2D table2D, Pyramide pyramidePlayer) {
        this.table2D = table2D;
        this.pyramidePlayer = pyramidePlayer;
    }



    public void displayTableAndPyramid() {
        final Pion[] pionSource = new Pion[1];
        JFrame frame = new JFrame("Table2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel tablePanel = new JPanel(new GridLayout(table2D.getHeight(), table2D.getWidth()));
        int k = 0;
        for (int i = 0; i < table2D.getHeight(); i++) {
            for (int j = 0; j < table2D.getWidth(); j++) {
                k++;
                Pion pion = table2D.getCases()[i][j];
                JLabel table2DLabel = new JLabel(pion.getImageIcon());
                if (k == 16) {
                    table2DLabel.setVisible(false);
                } else {
                    tablePanel.add(table2DLabel);
                }
                int finalI = i;
                int finalJ = j;

                table2DLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pionSource[0] = table2D.getPion(finalI, finalJ);
                        if (table2DLabel.getIcon() == new ImageIcon(new ImageIcon("sources/Images/VIDE.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))) {
                            System.out.println("videed" + pion.getImageIcon().toString());
                            System.out.println("videed" + table2DLabel.getIcon().toString());
                        } else {
                            System.out.println("not videed" + pion.getImageIcon().toString());
                            System.out.println("not videed" + table2DLabel.getIcon().toString());
                        }

                        labelr = table2DLabel;
                        System.out.println("clicked pion piochable");
                    }
                });
            }
        }

        JPanel pyramidPanel = new JPanel(new GridLayout(pyramidePlayer.getHight(), pyramidePlayer.getWidth()));
        JButton printPyramid = null;
        for (int i = 0; i < pyramidePlayer.getHight(); i++) {
            JPanel pionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int j = 0; j <= i; j++) {
                Pion pion = pyramidePlayer.getPion(i, j);
                JLabel pyramideLabel = new JLabel(pion.getImageIcon());
                int finalI = i;
                int finalJ = j;
                pyramideLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (labelr != null) {
                            Pion pionDestination = pyramidePlayer.getPion(finalI, finalJ);
                            if (pionDestination.estAccessible()){
                                pyramideLabel.setIcon(labelr.getIcon());
                                System.out.println("déplacement possible vers vide");
                                labelr.setIcon(new ImageIcon(new ImageIcon("sources/Images/VIDE.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                                labelr = null;
                                pionDestination.replacePion(pionSource[0]);
                                pionDestination.setAccessible(false);
                            }
                        }
                    }
                });
                pionPanel.add(pyramideLabel);
            }

            pyramidPanel.add(pionPanel);


            //button that print the pyramid on the console
            printPyramid = new JButton("print pyramid");
            printPyramid.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    //print the pyramid on the console
                    for (int i = 0; i < pyramidePlayer.getHight(); i++) {
                        for (int j = 0; j <= i; j++) {
                            Pion pion = pyramidePlayer.getPion(i, j);
                            System.out.print(pion.getCouleur().toString() + " ");
                        }
                        System.out.println();
                    }
                }
            });


        }
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.add(printPyramid);
        frame.add(tablePanel, BorderLayout.NORTH); // add the panel to the frame
        frame.add(pyramidPanel, BorderLayout.CENTER); // add the panel to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH); // add the panel to the frame
        frame.setVisible(true);
    }









}
