

import javax.swing.*;
import java.awt.*;
import Model.*;
import Controller.*;
import GameBuilder.*;
import Exceptions.*;
import AI.*;


public class Main {
    public static void main(String[] args) {
        Table2DBuilder builder = new Table2DBuilder();
        builder.RandomPions();
        Table2D table2D = builder.getTable2D();

        Table2DDisplay display = new Table2DDisplay(table2D);
        display.display();
    }
}