

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

        int bag[] = {9, 9, 9, 9, 9};
        Table2D table2D = new Table2D(2, 11, bag);
        Pyramide playerPyramide = new Pyramide(6, 6);
        Table2D baseK3 = new Table2D(1, 9, bag);
        playerPyramide.initPyramide(6, 6);
        MainFrame mainFrame = new MainFrame(table2D, playerPyramide , baseK3);

    }
}