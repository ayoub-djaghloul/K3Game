package Controller;

import javax.swing.*;
import java.awt.*;
import Model.*;


public class DisplayPyramidC {
    /*this phase is for the displaying the construction of the pyramid of
     the player and displaying the 2D table*/

    private Table2D table2D;
    private Pyramide pyramidePlayer;

    public DisplayPyramidC(Table2D table2D, Pyramide pyramidePlayer) {
        this.table2D = table2D;
        this.pyramidePlayer = pyramidePlayer;
    }

    public void displayTableAndPyramid(){
        JFrame frame = new JFrame("Table2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(5, 5));

        JPanel tablePanel = new JPanel(new GridLayout(table2D.getHeight(), table2D.getWidth()));
        int k = 0;
        for (int i = 0; i < table2D.getHeight(); i++) {
            for (int j = 0; j < table2D.getWidth(); j++) {
                k++;
                Pion pion = table2D.getCases()[i][j];
                JLabel table2DLabel = new JLabel(pion.getImageIcon());
                if (k==16){
                    table2DLabel.setVisible(false);
                }else {
                    tablePanel.add(table2DLabel);
                }
            }
        }
        frame.add(tablePanel); // add the panel to the frame
        JPanel pyramidPanel = new JPanel(new GridLayout(pyramidePlayer.getHight(), pyramidePlayer.getWidth()));
        for (int i = 0; i < pyramidePlayer.getHight(); i++) {
            for (int j = 0; j < pyramidePlayer.getWidth(); j++) {
               Pion pion = pyramidePlayer.getPion(i,j);
                JLabel pyramideLabel = new JLabel(pion.getImageIcon());
                pyramidPanel.add(pyramideLabel);
        }
        }
        frame.add(pyramidPanel); // add the panel to the frame
        frame.setVisible(true);
    }

}
