package Model;

import javax.swing.*;

public class Pion {
    private CouleurPion couleur;
    private TypePion type;

    private int x;
    private int y;

    ImageIcon imageIcon;

    private boolean Accessible;

    public Pion(CouleurPion couleur, TypePion type, ImageIcon imageIcon, int x, int y) {
        this.couleur = couleur;
        this.type = type;
        this.x = x;
        this.y = y;
        this.imageIcon = imageIcon;
        this.Accessible = true;
    }

    public CouleurPion getCouleur() {
        return this.couleur;
    }

    public TypePion getType() {
        return this.type;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void deplacer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setAccessible(boolean Accessible) {
        this.Accessible = Accessible;
    }

    public boolean estAccessible() {
        return this.Accessible;
    }
    public enum TypePion {
        NATUREL,
        COLORE,
        BLANC,
        VIDE
    }
}
