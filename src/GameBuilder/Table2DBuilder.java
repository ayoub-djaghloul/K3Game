package GameBuilder;

import Model.Pion;
import Model.Table2D;

public class Table2DBuilder {
    private final int hight =2;
    private final int width =8;
    private Table2D table2D;

    String[] couleurs = {"ROUGE", "BLEU", "VERT", "JAUNE", "NOIR", "BLANC", "BEIGE", "VIDE"};
    String[] types = {"NATUREL", "COLORE", "BLANC", "VIDE"};


    public Table2DBuilder() {
        this.table2D = new Table2D(this.hight, this.width);
    }
    public Table2DBuilder ajouterPion(Pion pion) {
        this.table2D.ajouterPion(pion);
        return this;
    }

    public void RandomPions() {

    }
}
