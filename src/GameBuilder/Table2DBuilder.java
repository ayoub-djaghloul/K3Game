package GameBuilder;
import java.awt.*;
import java.util.Random;
import Model.Pion;
import Model.CouleurPion;
import Model.Table2D;

import javax.swing.*;

public class Table2DBuilder {
    private final int hight =2;
    private final int width =8;
    private Table2D table2D;


    ImageIcon BEIGE = new ImageIcon("source\\Images\\beige.png");
    ImageIcon BLANC = new ImageIcon("source\\Images\\blanc.png");
    ImageIcon BLEU = new ImageIcon("source\\Images\\bleu.png");
    ImageIcon JAUNE = new ImageIcon("source\\Images\\jaune.png");
    ImageIcon NOIR = new ImageIcon("source\\Images\\noir.png");
    ImageIcon ROUGE = new ImageIcon("source\\Images\\rouge.png");
    ImageIcon VERT = new ImageIcon("source\\Images\\vert.png");

    public Table2DBuilder() {
        this.table2D = new Table2D(this.hight, this.width);
    }

    public void RandomPions() {
        Random random = new Random();
        //random color from enum
        CouleurPion[] couleurs = CouleurPion.values();
        for (int i = 0; i < this.hight; i++) {
            for (int j = 0; j < this.width; j++) {
                int randomIndex = random.nextInt(couleurs.length);
                CouleurPion couleur = couleurs[randomIndex];
                //switch case to get the right image
                switch (couleur) {
                    case BEIGE:
                        this.table2D.setCases(new Pion(couleur, Pion.TypePion.COLORE, BEIGE, i, j), i, j);
                        break;
                    case BLANC:
                        this.table2D.setCases(new Pion(couleur, Pion.TypePion.COLORE, BLANC, i, j), i, j);
                        break;
                    case BLEU:
                        this.table2D.setCases(new Pion(couleur, Pion.TypePion.COLORE, BLEU, i, j), i, j);
                        break;
                    case JAUNE:
                        this.table2D.setCases(new Pion(couleur, Pion.TypePion.COLORE, JAUNE, i, j), i, j);
                        break;
                    case NOIR:
                        this.table2D.setCases(new Pion(couleur, Pion.TypePion.COLORE, NOIR, i, j), i, j);
                        break;
                    case ROUGE:
                        this.table2D.setCases(new Pion(couleur, Pion.TypePion.COLORE, ROUGE, i, j), i, j);
                        break;
                    case VERT:
                        this.table2D.setCases(new Pion(couleur, Pion.TypePion.COLORE, VERT, i, j), i, j);
                        break;
                }
            }
        }
    }
}
