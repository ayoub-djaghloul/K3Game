package Model;

import Model.CouleurPion;
import Model.Pion;
import Model.Pyramide;

import javax.swing.*;
import java.util.Random;

public class PyramideIA {
    private Pyramide pyramidePlayer;
    private Pion pion;
    private int hight;
    private int width;
    ImageIcon BEIGE = new ImageIcon("../sources/Images/BEIGE.png");
    ImageIcon BLANC = new ImageIcon("../sources/Images/BLANC.png");
    ImageIcon BLEU = new ImageIcon("../sources/Images/BLEU.png");
    ImageIcon JAUNE = new ImageIcon("../sources/Images/JAUNE.png");
    ImageIcon NOIR = new ImageIcon("../sources/Images/NOIR.png");
    ImageIcon ROUGE = new ImageIcon("../sources/Images/ROUGE.png");
    ImageIcon VERT = new ImageIcon("../sources/Images/VERT.png");
    ImageIcon VIDE = new ImageIcon("../sources/Images/VIDE.png");

    //liste des images
    ImageIcon[] images = {BEIGE, BLANC, BLEU, JAUNE, NOIR, ROUGE, VERT, VIDE};
    private Pion[][] cases; // tableau de pions

    private int nbPions;
    private int nbPionsMax;

    public PyramideIA(int hight, int width) {
        this.hight = hight;
        this.width = width;
        this.nbPions = 0;
        this.nbPionsMax = (hight*width/2);
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


    public Pyramide getPyramidePlayer() {
        return this.pyramidePlayer;
    }

    public Pion[][] getCases(int i,int j) {
        return this.cases;
    }

    public void setCases(Pion[][] cases) {
        this.cases = cases;
    }

    public void setPyramidePlayer(Pyramide pyramidePlayer) {
        this.pyramidePlayer = pyramidePlayer;
    }




    public void setPion(Pion pion) {
        this.pion = pion;
    }



    public void setHight(int hight) {
        this.hight = hight;
    }



    public void setWidth(int width) {
        this.width = width;
    }

    public void generatePyramidIA() {
        Random random = new Random();
        //random color from enum
        CouleurPion[] couleurs = CouleurPion.values();
        for (int i = 0; i < this.getHight(); i++) {
            for (int j = 0; j <= i; j++) {
                int randomIndex = random.nextInt(couleurs.length);
                CouleurPion couleur = couleurs[randomIndex];
                pion = new Pion(couleur, Pion.TypePion.COLORED,images[randomIndex] , i, j,1);
                this.ajouterPion(pion);
            }
        }
    }
}
