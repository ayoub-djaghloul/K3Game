package launcher;
import javax.swing.*;
import Model.*;


import View.MainFrame;

public class GameLauncher extends JFrame {


    //constructor
    public GameLauncher() {
        this.launcher();
    }



    public void launcher(){
        int bag[] = {10, 10, 10, 10, 10};
        Table2D baseK3 = new Table2D(1, 9, bag);
        Table2D table2DP1 = new Table2D(2, 11, bag);
        Table2D table2DP2 = new Table2D(2, 11, bag);
        Pyramide p1Pyramide = new Pyramide(6, 6);
        p1Pyramide.initPyramide(1);
        Pyramide p2Pyramide = new Pyramide(6, 6);
        p2Pyramide.initPyramide(2);
        Pyramide K3 = new Pyramide(9, 9).initPyramide(0);
        K3.K3Base(baseK3);
        MainFrame mainFrame = new MainFrame(table2DP1, table2DP2, baseK3, p1Pyramide, p2Pyramide, K3);
    }
}