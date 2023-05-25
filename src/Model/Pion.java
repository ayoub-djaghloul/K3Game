package Model;

import javax.swing.*;

public class Pion {
    CouleurPion couleur;
    private TypePion type;
    private int x;
    private int y;
    ImageIcon imageIcon;
    private boolean VideCase;

    private int player;

    ImageIcon BEIGE = new ImageIcon(getClass().getResource("/Images/BEIGE.png"));
    ImageIcon BLANC = new ImageIcon(getClass().getResource("/Images/BLANC.png"));
    ImageIcon BLEU = new ImageIcon(getClass().getResource("/Images/BLEU.png"));
    ImageIcon JAUNE = new ImageIcon(getClass().getResource("/Images/JAUNE.png"));
    ImageIcon NOIR = new ImageIcon(getClass().getResource("/Images/NOIR.png"));
    ImageIcon ROUGE = new ImageIcon(getClass().getResource("/Images/ROUGE.png"));
    ImageIcon VERT = new ImageIcon(getClass().getResource("/Images/VERT.png"));

    public Pion(CouleurPion couleur, TypePion type, ImageIcon imageIcon, int x, int y, int player) {
        this.couleur = couleur;
        this.type = type;
        this.x = x;
        this.y = y;
        this.imageIcon = imageIcon;
        this.VideCase = true;
        this.player= player;
    }
    public Pion(CouleurPion couleur, TypePion type, ImageIcon imageIcon, int x, int y) {
        this.couleur = couleur;
        this.type = type;
        this.x = x;
        this.y = y;
        this.imageIcon = imageIcon;
        this.VideCase = true;
        this.player=this.player;
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
        this.setVideCase(false);
    }

    public void resetPion() {
        this.couleur = null;
        this.type = TypePion.VIDE;
        this.imageIcon = new ImageIcon(getClass().getResource("/Images/VIDE.png"));
        this.setVideCase(true);
    }

    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public void setVideCase(boolean Accessible) {
        this.VideCase = Accessible;
    }

    public void setCouleur(CouleurPion couleur) {
        this.couleur = couleur;
    }

    public boolean estVide() {
        return this.VideCase;
    }

    public void setY(int i) {
        this.y = i;
    }

    public enum TypePion {
        NATUREL,
        COLORED,
        BLANC,
        VIDE
    }
}
