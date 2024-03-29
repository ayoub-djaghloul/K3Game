package Model;
import javax.swing.*;

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



    public Pyramide initPyramide(int player) {
        ImageIcon VIDE = new ImageIcon(getClass().getResource("/Images/VIDE.png"));

        for (int i = 0; i < this.hight; i++) {
            for (int j = 0; j <= i; j++) {
                Pion pion = new Pion(null, Pion.TypePion.VIDE, VIDE, i, j, player);
                this.ajouterPion(pion);
            }
        }
        return this;
    }


    public void K3Base( Table2D table2D){
        //go to the base of the pyramid
        int base = this.getHight()-1;
        for (int i = 0; i <= base; i++) {
            this.getPion(base, i).replacePion(table2D.getPion(0, i));
        }
    }
}