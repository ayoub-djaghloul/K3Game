package Model;

import javax.swing.*;
import java.util.ArrayList;

public class Table2D {
    private Pion[][] cases;
    // bag containes the number of colors of each pion

    // colors of pions
    ImageIcon BEIGE = new ImageIcon("sources/Images/BEIGE.png");
    ImageIcon BLANC = new ImageIcon("sources/Images/BLANC.png");
    ImageIcon BLEU = new ImageIcon("sources/Images/BLEU.png");
    ImageIcon JAUNE = new ImageIcon("sources/Images/JAUNE.png");
    ImageIcon NOIR = new ImageIcon("sources/Images/NOIR.png");
    ImageIcon ROUGE = new ImageIcon("sources/Images/ROUGE.png");
    ImageIcon VERT = new ImageIcon("sources/Images/VERT.png");
    ImageIcon VIDE = new ImageIcon("sources/Images/VIDE.png");

    public Table2D(int hight, int width, int[] bag) {
        if (hight >1){

            this.cases = new Pion[hight][width];
            this.initTable2D(bag);
        }else {
            this.cases = new Pion [hight][width];
            this.initTable1D(width,bag);
        }
    }
    public Table2D(int hight, int width) {
        this.cases = new Pion[hight][width];
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < width; j++) {
                this.cases[i][j] = new Pion(null, Pion.TypePion.VIDE, VIDE, 0,i, 0);
            }
        }
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
    }
    public void retirerPion(Pion pion) {
        int x = pion.getX();
        int y = pion.getY();
        this.cases[x][y] = null;
    }

    public int getHeight() {
        return this.cases.length;
    }

    public int getWidth() {
        return this.cases[0].length;
    }

    public Pion[][] getCases() {
        return this.cases;
    }

    public Pion getCases(int x, int y) {
        return this.cases[x][y];
    }
    public void setCases(Pion pion, int x, int y) {
        this.cases[x][y] = pion;
    }


    public CouleurPion RandomPions(int bag[]) { // Randomly choose a color of pion
        ArrayList<Integer> listdesPions = new ArrayList<Integer>();
        for (int k = 0; k < bag.length; k++) {
            if(bag[k] != 0)
                listdesPions.add(k);
        }

         int random = (int) (Math.random() * listdesPions.size());
        bag[listdesPions.get(random)]--;
        switch (listdesPions.get(random)) {
            case 0:
                return CouleurPion.ROUGE;
            case 1:
                return CouleurPion.BLEU;
            case 2:
                return CouleurPion.VERT;
            case 3:
                return CouleurPion.JAUNE;
            case 4:
                return CouleurPion.NOIR;
        }
        return null;
    }

    public Table2D initTable2D(int bag[]) {
        CouleurPion couleurPion;
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (i == 0 && j == 0) {
                    this.setCases(new Pion(CouleurPion.BLANC, Pion.TypePion.COLORED, BLANC, i, j, 0), i, j);
                } else if (i == 0 && j == 1) {
                    this.setCases(new Pion(CouleurPion.BEIGE, Pion.TypePion.COLORED, BEIGE, i, j,0), i, j);
                } else if (i == 0 && j == 2) {
                    this.setCases(new Pion(CouleurPion.BEIGE, Pion.TypePion.COLORED, BEIGE, i, j,0), i, j);
                } else {
                    //randomly generate the pions
                    if (i > 1 && j == this.getWidth()) {
                        return this;
                    } else {
                        couleurPion = RandomPions(bag);
                        switch (couleurPion) {
                            case ROUGE:
                                this.setCases(new Pion(couleurPion, Pion.TypePion.COLORED, ROUGE, i, j,0), i, j);
                                break;
                            case BLEU:
                                this.setCases(new Pion(couleurPion, Pion.TypePion.COLORED, BLEU, i, j,0), i, j);
                                break;
                            case VERT:
                                this.setCases(new Pion(couleurPion, Pion.TypePion.COLORED, VERT, i, j,0), i, j);
                                break;
                            case JAUNE:
                                this.setCases(new Pion(couleurPion, Pion.TypePion.COLORED, JAUNE, i, j,0), i, j);
                                break;
                            case NOIR:
                                this.setCases(new Pion(couleurPion, Pion.TypePion.COLORED, NOIR, i, j,0), i, j);
                                break;
                        }
                    }
                }
            }
        }
        return this;
    }


    public Table2D initTable1D(int width,int bag[]) {//K3 base
        CouleurPion couleurPion;
        for (int i = 0; i < width; i++) {
            couleurPion = RandomPions(bag);
            switch (couleurPion) {
                case ROUGE:
                    this.cases[0][i] = new Pion(couleurPion, Pion.TypePion.COLORED, ROUGE, 0, i,0);
                    break;
                case BLEU:
                    this.cases[0][i] = new Pion(couleurPion, Pion.TypePion.COLORED, BLEU, 0, i,0);
                    break;
                case VERT:
                    this.cases[0][i] = new Pion(couleurPion, Pion.TypePion.COLORED, VERT, 0, i,0);
                    break;
                case JAUNE:
                    this.cases[0][i] = new Pion(couleurPion, Pion.TypePion.COLORED, JAUNE, 0, i,0);
                    break;
                case NOIR:
                    this.cases[0][i] = new Pion(couleurPion, Pion.TypePion.COLORED, NOIR, 0, i,0);
                    break;
            }
        }
        return this;
    }






}