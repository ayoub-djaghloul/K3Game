package Controller;

import Model.CouleurPion;
import Model.Pion;
import Model.Pyramide;

public class GameController {

    public boolean testDeplacementPion(Pion source, Pion destination, Pyramide K3) {
        CouleurPion CSource = source.getCouleur();
        int finalI = destination.getX();
        int finalJ = destination.getY();
        if(!destination.estAccessible()){
            System.out.println("Destination non accessible(pas vide)");
            return false;
        }
        else{
        Pion pionfils1 = K3.getPion(finalI + 1, finalJ);
        Pion pionfils2 = K3.getPion(finalI + 1, finalJ + 1);
        if(pionfils1.getCouleur()==null || pionfils2.getCouleur()==null){
            System.out.println("un des fils est null");
            return false;
        }else if (CSource == CouleurPion.BEIGE) {
            destination.replacePion(source);
            System.out.println("Deplacement effectué parceque la source est beige");
            destination.setAccessible(false);
            return true;
        }else if (CSource==pionfils1.getCouleur()&&CSource==pionfils2.getCouleur()) {
            destination.replacePion(source);
            System.out.println("Deplacement effectué mais avec pénalité");
            destination.setAccessible(false);
            return true;
        } else if (pionfils1.getCouleur() == CSource || pionfils2.getCouleur() == CSource) {
            System.out.println("y a un fils qui a la meme couleur que la source");
            destination.replacePion(source);
            destination.setAccessible(false);
            return true;
        }  else {
            System.out.println("Deplacement impossible");
            return false;
        }
    }}

}
