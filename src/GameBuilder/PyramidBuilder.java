package GameBuilder;
import Model.*;

import javax.swing.*;

public class PyramidBuilder {

    private Pyramide pyramidePlayer;
    private Pion pion;
    ImageIcon VIDE = new ImageIcon("sources/Images/VIDE.png");
    public PyramidBuilder(int hight, int width) {
        this.pyramidePlayer = new Pyramide(hight, width);
    }
    public Pyramide getPyramidePlayer() {
        return this.pyramidePlayer;
    }

    public void generatePyramid() {
        for (int i = 0; i < this.pyramidePlayer.getHight(); i++) {
            for (int j = 0; j <= i; j++) {
                pion = new Pion(null, Pion.TypePion.VIDE, VIDE, i, j);
                this.pyramidePlayer.ajouterPion(pion);
            }
        }
    }
}
