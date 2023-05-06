package Model;

public class Table2D {
    private Pion[][] cases;

    public Table2D(int hight, int width) {
        this.cases = new Pion[hight][width];
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

    public void setCases(Pion pion, int x, int y) {
        this.cases[x][y] = pion;
    }

}
