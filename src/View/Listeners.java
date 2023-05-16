package View;

import Controller.GameController;
import Model.Pion;
import Model.Pyramide;
import Model.Table2D;

import javax.swing.*;
import java.util.Stack;

public class Listeners {
    private JLabel labelr;
    private Stack<Pion> history2Dtable;
    private Pion[] pionSource;
    private Stack<Pion> historyPyramid;

    public Listeners(JLabel labelr, Stack<Pion> history2Dtable, Stack<Pion> historyPyramid,  Pion[] pionSource) {
        this.labelr = labelr;
        this.history2Dtable = history2Dtable;
        this.historyPyramid = historyPyramid;
        this.pionSource = pionSource;
    }

    public void tableListener(Table2D table2D, int finalI, int finalJ, JLabel table2DLabel) {
        pionSource[0] = table2D.getPion(finalI, finalJ);
        labelr = table2DLabel;
        history2Dtable.push(pionSource[0]);
    }



    public void pyramidListener(Pyramide pyramide, int finalI, int finalJ, JLabel pyramideLabel, int option,/* int[] pionCount,*/  int s ) {
        switch (s) {
            case 1: {
                if (labelr != null) {
                    Pion pionDestination = pyramide.getPion(finalI, finalJ);
                    if (option == 1) {
                        if (new GameController().testDeplacementPion(pionSource[0], pionDestination, pyramide) == true) {
                            pyramideLabel.setIcon(labelr.getIcon());
                            labelr.setVisible(false);
                            labelr = null;
                            //pionDestination.setAccessible(false);
                           // pionCount[0]++;
                        }
                    } else {
                        pionDestination.replacePion(pionSource[0]);
                        pyramideLabel.setIcon(labelr.getIcon());
                       // pionCount[0]++;
                        labelr.setVisible(false);
                        labelr = null;
                        pionDestination.setAccessible(false);
                    }
                    historyPyramid.push(pionDestination);

                }
                break;
            }
            case 2: {
                pionSource[0] = pyramide.getPion(finalI, finalJ);
                //joueur =!joueur;
                if (new GameController().testAvantDeplacement(pionSource[0], pyramide, 1) == false) {
                    System.out.println("pion non accessible");
                } else {
                    labelr = pyramideLabel;
                }
                break;
            }
        }
        /*
         case 3 :{
                                pionSource[0]=pyramide.getPion(finalI, finalJ);
                                joueur =!joueur;
                                if (new GameController().testAvantDeplacement(pionSource[0], pyramide*//*, count, joueur*//* )==false){
                                    System.out.println("pion non accessible");
                                }else{
                                    labelr = pyramideLabel;
                                }
                            }*/
    }







}
