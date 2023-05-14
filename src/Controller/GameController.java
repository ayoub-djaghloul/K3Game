package Controller;

import Model.CouleurPion;
import Model.Pion;
import Model.Pyramide;

public class GameController {

    public void testDeplacementPion(Pion source, Pion distination, Pyramide K3) {
        CouleurPion CSource = source.getCouleur();
        int finalI = distination.getX();
        int finalJ = distination.getY();
        Pion pionfils1 = K3.getPion(finalI + 1, finalJ);
        Pion pionfils2 = K3.getPion(finalI + 1, finalJ + 1);

        if (CSource == CouleurPion.BEIGE) {
            distination.replacePion(source);
        } else if (pionfils1.getCouleur() == CSource || pionfils2.getCouleur() == CSource) {
            System.out.println("Deplacement possible");
            distination.replacePion(source);
        } else {
            System.out.println("Deplacement impossible");
        }
    }

}
