//package Controller;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.Stack;
//
//import GameBuilder.MaDeuxiemeInterface;
//import Model.PyramideIA;
//import Model.*;
//
//
//public class DisplayPyramidC {
//    /*this phase is for the displaying the construction of the pyramid of
//     the player and displaying the 2D table*/
//
//    private Table2D table2D;
//    private Pyramide pyramidePlayer;
//    private JLabel labelr;
//
//    //stack to store the replaced pions
//    private Stack<Pion> history2Dtable = new Stack<Pion>();
//    private Stack<Pion> historyPyramid = new Stack<Pion>();
//
//    public DisplayPyramidC(Table2D table2D, Pyramide pyramidePlayer) {
//        this.table2D = table2D;
//        this.pyramidePlayer = pyramidePlayer;
//    }
//    public void displayTableAndPyramid() {
//        final Pion[] pionSource = new Pion[1];
//        JFrame frame = new JFrame("Table2D");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1200, 800);
//        frame.setLocationRelativeTo(null);
//        frame.setLayout(new BorderLayout());
//        JPanel tablePanel = new JPanel(new GridLayout(table2D.getHeight(), table2D.getWidth()));
//        int k = 0;
//        final int[] pionCount = {0};
//
//        for (int i = 0; i < table2D.getHeight(); i++) {
//            for (int j = 0; j < table2D.getWidth(); j++) {
//                k++;
//                Pion pion = table2D.getCases()[i][j];
//                JLabel table2DLabel = new JLabel(pion.getImageIcon());
//                if (k == 22) {
//                    table2DLabel.setVisible(false);
//                } else {
//                    tablePanel.add(table2DLabel);
//                }
//                int finalI = i;
//                int finalJ = j;
//
//                table2DLabel.addMouseListener(new java.awt.event.MouseAdapter() {
//                    public void mouseClicked(java.awt.event.MouseEvent evt) {
//                        pionSource[0] = table2D.getPion(finalI, finalJ);
//                        labelr = table2DLabel;
//                        history2Dtable.push(pionSource[0]);
//                    }
//                });
//            }
//        }
//
//        JPanel pyramidPanel = new JPanel(new GridLayout(pyramidePlayer.getHight(), pyramidePlayer.getWidth()));
//        JButton printPyramid = new JButton("print pyramid");
//
//        printPyramid.setEnabled(false);
//        JButton undo = new JButton("undo");
//        undo.setEnabled(false);
//        for (int i = 0; i < pyramidePlayer.getHight(); i++) {
//            JPanel pionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
//            for (int j = 0; j <= i; j++) {
//                Pion pion = pyramidePlayer.getPion(i, j);
//                JLabel pyramideLabel = new JLabel(pion.getImageIcon());
//                int finalI = i;
//                int finalJ = j;
//                pyramideLabel.addMouseListener(new java.awt.event.MouseAdapter() {
//                    public void mouseClicked(java.awt.event.MouseEvent evt) {
//                        if (labelr != null) {
//                            Pion pionDestination = pyramidePlayer.getPion(finalI, finalJ);
//                            if (pionDestination.estAccessible()) {
//                                pyramideLabel.setIcon(labelr.getIcon());
//                                //labelr.setIcon(new ImageIcon(new ImageIcon("sources/Images/VIDE.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
//                                labelr.setVisible(false);
//                                labelr = null;
//                                pionDestination.replacePion(pionSource[0]);
//                                pionDestination.setAccessible(false);
//                                historyPyramid.push(pionDestination);
//                                pionCount[0]++;
//                                if(pionCount[0]>=1){
//                                    undo.setEnabled(true);
//                                }
//                                else {
//                                    undo.setEnabled(false);
//                                }
//                                System.out.println(pionCount[0]);
//                                if (pionCount[0] == 15) {
//                                    printPyramid.setEnabled(true);
//                                }  else {
//                                    printPyramid.setEnabled(false);
//                                }
//                            }
//                        }
//                    }
//                });
//                pionPanel.add(pyramideLabel);
//            }
//
//            pyramidPanel.add(pionPanel);
//        }
//
//
//        //undo button
//        undo.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                int tableX = history2Dtable.peek().getX();
//                int tableY = history2Dtable.peek().getY();
//                int pyramidX = historyPyramid.peek().getX();
//                int pyramidY = historyPyramid.peek().getY();
//                if (!history2Dtable.empty() && !historyPyramid.empty()) {
//                    Pion pion2D = history2Dtable.pop();
//                    Pion pionPyramid = historyPyramid.pop();
//                    pionPyramid.resetPion();
//                    table2D.getCases()[tableX][tableY].replacePion(pion2D);
//                    pion2D.setAccessible(true);
//                    // update the table's label with the pion's image icon
//                    JLabel tableLabel = (JLabel) tablePanel.getComponent(tableX * table2D.getWidth() + tableY);
//                    tableLabel.setIcon(pion2D.getImageIcon());
//                    tableLabel.setVisible(true);
//                    // update the pyramid's label with the pion's image icon
//                    JPanel pionPanel = (JPanel) pyramidPanel.getComponent(pyramidX);
//                    JLabel pyramidLabel = (JLabel) pionPanel.getComponent(pyramidY);
//                    pyramidLabel.setIcon(pionPyramid.getImageIcon());
//                    pionCount[0]--;
//                    if(pionCount[0]>=1){
//                        undo.setEnabled(true);
//                    }
//                    else {
//                        undo.setEnabled(false);
//                    }
//                }
//            }
//        });
//
//        //button that print the pyramid on the console
///*        printPyramid.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                //print the pyramid on the console
//                PyramideIA pyramideia = new PyramideIA(6,6);
//                pyramideia.generatePyramidIA();
//
//                MaDeuxiemeInterface maDeuxiemeInterface = new MaDeuxiemeInterface(pyramidePlayer,pyramideia);
//                maDeuxiemeInterface.displayPyramids();
//                frame.dispose();
//                for (int i = 0; i < pyramidePlayer.getHight(); i++) {
//                    for (int j = 0; j <= i; j++) {
//                        Pion pion = pyramidePlayer.getPion(i, j);
//                        System.out.print(pion.getCouleur().toString() + " ");
//                    }
//                    System.out.println();
//                }
//            }
//        });*/
//
//
//        JPanel undoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
//        undoPanel.add(undo);
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
//        buttonPanel.add(printPyramid);
//        frame.add(tablePanel, BorderLayout.NORTH); // add the panel to the frame
//        frame.add(pyramidPanel, BorderLayout.CENTER); // add the panel to the frame
//        frame.add(buttonPanel, BorderLayout.SOUTH); // add the panel to the frame
//        frame.add(undoPanel, BorderLayout.EAST); // add the panel to the frame
//        frame.setVisible(true);
//    }
//}