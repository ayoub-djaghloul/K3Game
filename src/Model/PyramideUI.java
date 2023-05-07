package Model;

import javax.swing.*;
import java.awt.*;

public class PyramideUI extends JPanel {
    private int nbRows;
    private int nbCols;
    private int cellSize;
    private int cellPadding;
    private int pyramideX;
    private int pyramideY;

    public PyramideUI(int nbRows, int nbCols, int cellSize, int cellPadding, int pyramideX, int pyramideY) {
        this.nbRows = nbRows;
        this.nbCols = nbCols;
        this.cellSize = cellSize;
        this.cellPadding = cellPadding;
        this.pyramideX = pyramideX;
        this.pyramideY = pyramideY;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // dessiner les pions vides
        ImageIcon emptyIcon = new ImageIcon("empty.png");
        for (int row = 0; row < nbRows; row++) {
            for (int col = 0; col <= row; col++) {
                int x = pyramideX + (nbCols/2 - row + col)*(cellSize + cellPadding);
                int y = pyramideY + row*(cellSize + cellPadding);
                g.drawImage(emptyIcon.getImage(), x, y, cellSize, cellSize, this);
            }
        }

        // dessiner les bords de la pyramide
        g.setColor(Color.BLACK);
        for (int row = 0; row < nbRows; row++) {
            for (int col = 0; col <= row; col++) {
                int x = pyramideX + (nbCols/2 - row + col)*(cellSize + cellPadding);
                int y = pyramideY + row*(cellSize + cellPadding);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        PyramideUI pyramideUI = new PyramideUI(4, 7, 40, 5, 100, 50);
        frame.add(pyramideUI);

        frame.setVisible(true);
    }
}
