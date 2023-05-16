package Model;

import javax.swing.*;
import java.awt.*;

public class Pion {
    CouleurPion couleur;
    private TypePion type;
    private int x;
    private int y;
    ImageIcon imageIcon;
    private boolean Accessible;

    private int player;

    ImageIcon BEIGE = new ImageIcon("sources/Images/BEIGE.png");
    ImageIcon BLANC = new ImageIcon("sources/Images/BLANC.png");
    ImageIcon BLEU = new ImageIcon("sources/Images/BLEU.png");
    ImageIcon JAUNE = new ImageIcon("sources/Images/JAUNE.png");
    ImageIcon NOIR = new ImageIcon("sources/Images/NOIR.png");
    ImageIcon ROUGE = new ImageIcon("sources/Images/ROUGE.png");
    ImageIcon VERT = new ImageIcon("sources/Images/VERT.png");

    public Pion(CouleurPion couleur, TypePion type, ImageIcon imageIcon, int x, int y, int player) {
        this.couleur = couleur;
        this.type = type;
        this.x = x;
        this.y = y;
        this.imageIcon = imageIcon;
        this.Accessible = true;
        this.player= player;
    }

    public boolean ifestnull() {
        if(this.couleur == null)
            return true;
    else
        return false;
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

    public int getPlayer() {
        return this.player;
    }

    public void replacePion(Pion pionSource) {
        this.couleur = pionSource.couleur;
        this.type = pionSource.type;
        this.imageIcon = pionSource.imageIcon;
        this.setAccessible(false);
    }

    public void resetPion() {
        this.couleur = null;
        this.type = TypePion.VIDE;
        this.imageIcon = new ImageIcon("sources/Images/VIDE.png");
        this.setAccessible(true);
    }

    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public void setAccessible(boolean Accessible) {
        this.Accessible = Accessible;
    }

    public boolean estAccessible() {
        return this.Accessible;
    }
    public enum TypePion {
        NATUREL,
        COLORED,
        BLANC,
        VIDE
    }
}
