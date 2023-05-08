

import javax.swing.*;
import java.awt.*;
import Model.*;
import Controller.*;
import GameBuilder.*;
import Exceptions.*;
import AI.*;


public class Main {
    public static void main(String[] args) {
        Table2DBuilder table2DBuilder = new Table2DBuilder();
        PyramidBuilder pyramidBuilder = new PyramidBuilder(5, 5);
        table2DBuilder.RandomPions();
        pyramidBuilder.generatePyramid();
        Table2D table2D = table2DBuilder.getTable2D();
        Pyramide pyramide = pyramidBuilder.getPyramidePlayer();
        DisplayPyramidC displayPyramidC = new DisplayPyramidC(table2D, pyramide);
    }
}