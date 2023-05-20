package Controller;

import Model.CouleurPion;
import Model.Pion;
import Model.Pyramide;

import javax.swing.*;

public class GameController {

    ImageIcon VIDE = new ImageIcon("sources/Images/VIDE.png");


    public boolean testDeplacementPionsanschangement1(Pion source, Pion destination, Pyramide K3) {
        CouleurPion CSource = source.getCouleur();
        int finalI = destination.getX();
        int finalJ = destination.getY();
        if(!destination.estVide()){
            //System.out.println("Destination non accessible(pas vide)");
            return false;
        }
        else{
            Pion pionfils1 = K3.getPion(finalI + 1, finalJ);
            Pion pionfils2 = K3.getPion(finalI + 1, finalJ + 1);
            if(pionfils1.getCouleur()==null || pionfils2.getCouleur()==null){
                //System.out.println("un des fils est null donc Déplacement impossible");
                return false;
            }else if (CSource == CouleurPion.BEIGE) {
                System.out.println("Deplacement effectué parceque la source est beige");
                return true;
            }else if (CSource==pionfils1.getCouleur()&&CSource==pionfils2.getCouleur()) {
                System.out.println("Deplacement effectué mais avec pénalité");
                return true;
            } else if (pionfils1.getCouleur() == CSource || pionfils2.getCouleur() == CSource
                    || pionfils1.getCouleur() == CouleurPion.BEIGE || pionfils2.getCouleur() == CouleurPion.BEIGE ) {
                System.out.println("y a un fils qui a la meme couleur que la source");
                return true;
            }  else {
                //System.out.println("Deplacement impossible");
                return false;
            }
        }
    }
    public boolean testDeplacementPion(Pion source, Pion destination, Pyramide K3) {
        CouleurPion CSource = source.getCouleur();
        int finalI = destination.getX();
        int finalJ = destination.getY();
        if(!destination.estVide()){
            System.out.println("Destination non accessible(pas vide)");
            return false;
        }
        else{
            Pion pionfils1 = K3.getPion(finalI + 1, finalJ);
            Pion pionfils2 = K3.getPion(finalI + 1, finalJ + 1);
            if(pionfils1.getCouleur()==null || pionfils2.getCouleur()==null){
                System.out.println("un des fils est null donc Déplacement impossible");
                return false;
            }else if (CSource == CouleurPion.BEIGE) {
                destination.replacePion(source);
                System.out.println("Deplacement effectué parceque la source est beige");
                destination.setVideCase(false);
                source.setVideCase(true);
                return true;
            }else if (CSource==pionfils1.getCouleur()&&CSource==pionfils2.getCouleur()) {
                destination.replacePion(source);
                System.out.println("Deplacement effectué mais avec pénalité");
                this.DeplacementPenalite(source);
                destination.setVideCase(false);
                source.setVideCase(true);
                return true;
            } else if (pionfils1.getCouleur() == CSource || pionfils2.getCouleur() == CSource
            || pionfils1.getCouleur() == CouleurPion.BEIGE || pionfils2.getCouleur() == CouleurPion.BEIGE ){
                System.out.println("y a un fils qui a la meme couleur que la source");
                destination.replacePion(source);
                destination.setVideCase(false);
                source.setVideCase(true);
                return true;
            }  else {
                System.out.println("Deplacement impossible");
                return false;
            }
        }
    }

    private void DeplacementPenalite(Pion source) {
        //choisir le pion à enlever
        int x = source.getX();
        int y = source.getY();
        //rajouter dans le tableau des pions enlevés
        //enlever le pion
        source.setVideCase(true);
    }

    public boolean testAvantDeplacement(Pion Source, Pyramide pyramide) {
        int x = Source.getX();
        int y = Source.getY();
            if (x == 0) {
                return true;
            } else if (y == 0) {
                if (pyramide.getPion(x - 1, y).estVide()) {
                    //System.out.println("le pere est vide");
                    return true;
                } else{
                    //System.out.println("le pere n'est pas vide");
                    return false;}
            } else if (y == x) {
                if (pyramide.getPion(x - 1, y - 1).estVide()) {
                    //System.out.println("le parent est vide");
                    return true;
                } else{
                    //System.out.println("le pere n'est pas vide");
                    return false;}
            } else if (pyramide.getPion(x - 1, y).estVide() && pyramide.getPion(x - 1, y - 1).estVide()) {
               // System.out.println("les deux parents sont vides");
                return true;
            } else {
                //System.out.println("les deux parents ou un des deux n'est pas vide");
                return false;
            }
    }

    public boolean testTour(int tour, Pion Source, Pyramide pyramidesource, Pyramide pyramidedestination) {
        System.out.println( "Joueur" + Source.getPlayer());
        System.out.println("Tour du Joueur" + tour);
        if (tour == Source.getPlayer()) {
            LesCoutsAccessibles liste=new LesCoutsAccessibles();
            liste.afficherCoutsAccessibles(pyramidesource, pyramidedestination);
            return true;
        } else {
            System.out.println("ce n'est pas votre tour");
            return false;}
        }
    }

