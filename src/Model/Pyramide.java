package Model;
import javax.swing.*;
import java.awt.*;

public class Pyramide {

    private int nbPions;
    private int nbPionsMax;
    private int hight;
    private int width;

    private Pion[][] cases; // tableau de pions

    public Pyramide(int hight, int width) {
        this.nbPions = 0;
        this.nbPionsMax = (hight * (hight + 1)) / 2;
        this.hight = hight;
        this.width = width;
        this.cases = new Pion[hight][width];
    }

    public int getNbPions() {
        return this.nbPions;
    }

    public int getNbPionsMax() {
        return this.nbPionsMax;
    }

    public int getHight() {
        return this.hight;
    }

    public int getWidth() {
        return this.width;
    }

    public Pion getPion(int x, int y) {
        return this.cases[x][y];
    }

    public boolean estVide(int x, int y) {
        return this.getPion(x, y) == null;
    }

    public void ajouterPion(Pion pion) {
        int x = pion.getX();
        int y = pion.getY();
        this.cases[x][y] = pion;
        this.nbPions++;
    }

    public void retirerPion(Pion pion) {
        int x = pion.getX();
        int y = pion.getY();
        this.cases[x][y] = null;
        this.nbPions--;
    }

    public void deplacerPion(Pion pion, int x, int y) {
        this.retirerPion(pion);
        pion.deplacer(x, y);
        this.ajouterPion(pion);
    }



    public Pyramide initPyramide() {
        ImageIcon VIDE = new ImageIcon("sources/Images/VIDE.png");

        for (int i = 0; i < this.hight; i++) {
            for (int j = 0; j <= i; j++) {
                Pion pion = new Pion(null, Pion.TypePion.VIDE, VIDE, i, j);
                this.ajouterPion(pion);
            }
        }
        return this;
    }


    public void setPion(int i, int j, Pion pion) {
        this.cases[i][j] = pion;
    }
}