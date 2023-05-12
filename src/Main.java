

import javax.swing.*;
import java.awt.*;
import Model.*;
import Controller.*;
import GameBuilder.*;
import Exceptions.*;
import AI.*;


public class Main {
    public static void main(String[] args) {
        int bag[] = {9, 9, 9, 9, 9};
        Table2D table2D = new Table2D(2, 11, bag);
        Pyramide pyramide = new Pyramide(5, 5);
        pyramide.initPyramide(5,5   );
        DisplayPyramidC displayPyramidC = new DisplayPyramidC(table2D, pyramide);
        displayPyramidC.displayTableAndPyramid();
    }
}