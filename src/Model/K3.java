package Model;

import java.util.ArrayList;
import java.util.List;

public class K3 {
    private Pion[][] cases;

    public K3() {
        this.cases = new Pion[3][3];
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

    public List<Pion> getPionsAdjacent(Pion pion) {
        List<Pion> pionsAdjacent = new ArrayList<>();
        int x = pion.getX();
        int y = pion.getY();
        for (int i = x - 1; i <= x + 1; i++) {
            if (i >= 0 && i < 3) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (j >= 0 && j < 3) {
                        if (i != x || j != y) {
                            Pion pionAdjacent = this.getPion(i, j);
                            if (pionAdjacent != null) {
                                pionsAdjacent.add(pionAdjacent);
                            }
                        }
                    }
                }
            }
        }

        return pionsAdjacent;
    }
}