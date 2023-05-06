package Model;

import java.util.ArrayList;
import java.util.List;

public class Pyramide {
    private List<Pion> pions;
    private int nbPions;
    private CouleurPion couleurJoueur;

    public Pyramide(int nbPionsParJoueur, CouleurPion couleurJoueur) {
        this.pions = new ArrayList<>();
        this.nbPions = nbPionsParJoueur;
        this.couleurJoueur = couleurJoueur;
    }

    public List<Pion> getPions() {
        return this.pions;
    }

    public int getNbPions() {
        return this.nbPions;
    }

    public boolean estVide() {
        return this.nbPions == 0;
    }

    public void ajouterPion(Pion pion) {
        if (this.nbPions > 0) {
            this.pions.add(pion);
            this.nbPions--;
        }
    }

    public void retirerPion(Pion pion) {
        if (this.pions.remove(pion)) {
            this.nbPions++;
        }
    }

    public void retirerTousPions() {
        this.pions.clear();
        this.nbPions = 0;
    }
}