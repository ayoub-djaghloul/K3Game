

import javax.swing.*;
import java.awt.*;
import Model.*;
import Controller.*;
import GameBuilder.*;
import Exceptions.*;
import AI.*;

import View.MainFrame;

public class Main {
    public static void main(String[] args) {
     /* int bag[] = {9, 9, 9, 9, 9};
        Table2D table2D = new Table2D(2, 11, bag);
        Pyramide pyramide = new Pyramide(5, 5);
        pyramide.initPyramide(5,5   );
        DisplayPyramidC displayPyramidC = new DisplayPyramidC(table2D, pyramide);
        displayPyramidC.displayTableAndPyramid();*/

        int bag[] = {99, 99, 99, 99, 99};
        Table2D baseK3 = new Table2D(1, 9, bag);
        Table2D table2DP1 = new Table2D(2, 11, bag);
        Table2D table2DP2 = new Table2D(2, 11, bag);

        Pyramide p1Pyramide = new Pyramide(6, 6);
        p1Pyramide.initPyramide();
        Pyramide p2Pyramide = new Pyramide(6, 6);
        p2Pyramide.initPyramide();
        //new RandomAI(p2Pyramide, table2DP2);
        Pyramide K3 = new Pyramide(9, 9).initPyramide();
        K3.K3Base(baseK3);
        MainFrame mainFrame = new MainFrame(table2DP1, table2DP2, baseK3, p1Pyramide, p2Pyramide, K3);
    }
}