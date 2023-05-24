package Controller;

import Model.CouleurPion;
import Model.Pion;
import Model.Pyramide;
import Model.Table2D;

import java.util.ArrayList;

public class LesCoutsAccessibles
{
    Pion source;
    Pion destination;
    //arraylist pour stocker le pion source et le pion destination
    ArrayList<Pion> lesPionsSources = new ArrayList<Pion>();
    ArrayList<Pion> lesPionsDestinations = new ArrayList<Pion>();
    ArrayList<LesCoutsAccessibles> lesCoutsAccessible = new ArrayList<LesCoutsAccessibles>();

    Feedback example = new Feedback();

    public LesCoutsAccessibles(Pion source, Pion destination)
    {
        this.source = source;
        this.destination = destination;
        this.lesCoutsAccessible = lesCoutsAccessible;
    }
    public LesCoutsAccessibles(){
        this.source = source;
        this.destination = destination;
        this.lesCoutsAccessible = lesCoutsAccessible;
    }

    public Pion getSource()
    {
        return source;
    }
    public Pion getDestination()
    {
        return destination;
    }

    public void setSource(Pion source)
    {
        this.source = source;
    }
    public void setDestination(Pion destination)
    {
        this.destination = destination;
    }




    public void afficherCoutsAccessibles(Table2D penalite,Pyramide pyramideJoueur, Pyramide K3 ) {
        boolean possibility = false;
        Pion sourcePion;
        Pion destinationPion;
        System.out.println("Les couts accessibles sont : ");
        for (int i = 0; i < pyramideJoueur.getHight(); i++) {
            for (int j = 0; j <=i; j++) {
                if (pyramideJoueur.getPion(i, j).estVide()==false) {
                    sourcePion = pyramideJoueur.getPion(i, j);
                    if(sourcePion.getCouleur()== CouleurPion.BLANC){
                        example.showFeedback("tu peux jouer le blanc",50000);
                    possibility = true;}
                    else
                    if (new GameController().testAvantDeplacement(sourcePion, pyramideJoueur)){
                        for (int k = 0; k < K3.getHight(); k++) {
                            for (int l = 0; l <= k; l++) {
                                if (K3.getPion(k, l).estVide() == true) {
                                    destinationPion = K3.getPion(k, l);
                                    if (new GameController().testDeplacementPionsanschangement1(sourcePion, destinationPion, K3)) {
                                        System.out.println("[" + sourcePion.getX() + "," + sourcePion.getY() + "]" + "-->" + "[" + destinationPion.getX() + "," + destinationPion.getY() + "]");
                                        this.lesPionsSources.add(sourcePion);
                                        this.lesPionsDestinations.add(destinationPion);
                                        this.lesCoutsAccessible.add(new LesCoutsAccessibles(sourcePion, destinationPion));
                                        //afficher le contenue de la liste des couts accessibles arraylist
                                        possibility = true;
                                    }
                                }
                            }
                        }
                }
                }
            }
        }
            /*for (int j = 0; j < penalite.getWidth()-1; j++) {
                if (penalite.getPion(0, j).estVide()==false) {
                    sourcePion = penalite.getPion(0, j);
                        for (int k = 0; k < K3.getHight(); k++) {
                            for (int l = 0; l <= k; l++) {
                                if (K3.getPion(k, l).estVide() == true) {
                                    destinationPion = K3.getPion(k, l);
                                    if (new GameController().testDeplacementPionsanschangement1(sourcePion, destinationPion, K3)) {
                                        System.out.println("penalité tableau [" + sourcePion.getX() + "," + sourcePion.getY() + "]" + "-->" + "[" + destinationPion.getX() + "," + destinationPion.getY() + "]");
                                        this.lesPionsSources.add(sourcePion);
                                        this.lesPionsDestinations.add(destinationPion);
                                        this.lesCoutsAccessible.add(new LesCoutsAccessibles(sourcePion, destinationPion));
                                        //afficher le contenue de la liste des couts accessibles arraylist
                                        possibility = true;
                                    }
                                }
                            }
                        }

                }

        }*/

        example.setVisible(true);
        if (possibility == false){
            example.showFeedback("l'autre joueur qui a gangné",2000);
        }else{
            example.showFeedbackArraylist(lesPionsSources, lesPionsDestinations,2000);
        }

    }
    // TODO : Test des Pions Blancs
    public Pion choisirUnPionAjouer(Pyramide pyramideJoueur, Pyramide K3, Table2D penalite) {
        Pion sourcePion;
        Pion destinationPion;
        int i = 0;
        while (i < pyramideJoueur.getHight()) {
            int j = 0;
            while(j <=i) {
                if (pyramideJoueur.getPion(i, j).estVide()==false) {
                    sourcePion = pyramideJoueur.getPion(i, j);
                    if(new GameController().testAvantDeplacement(sourcePion, pyramideJoueur)) {
                        int k = 0;
                        while (k < K3.getHight()) {
                            int l = 0;
                            while (l <= k) {
                                if (K3.getPion(k, l).estVide() == true) {
                                    destinationPion = K3.getPion(k, l);
                                    if (new GameController().testDeplacementPionsanschangement1(sourcePion, destinationPion, K3)) {
                                        System.out.println("[" + sourcePion.getX() + "," + sourcePion.getY() + "]" + "-->" + "[" + destinationPion.getX() + "," + destinationPion.getY() + "]");
                                        source=sourcePion;
                                        return destinationPion;
                                    }
                                }
                                l++;
                            }
                            k++;
                        }
                    }
                }
                j++;
            }
            i++;
        }
        i = 0;
        while (i<penalite.getWidth()-1){
            if (penalite.getPion(0, i).estVide()==false) {
                sourcePion = penalite.getPion(0, i);
                int k = 0;
                while (k < K3.getHight()) {
                    int l = 0;
                    while (l <= k) {
                        if (K3.getPion(k, l).estVide() == true) {
                            destinationPion = K3.getPion(k, l);
                            if (new GameController().testDeplacementPionsanschangement1(sourcePion, destinationPion, K3)) {
                                System.out.println("penalité tableau [" + sourcePion.getX() + "," + sourcePion.getY() + "]" + "-->" + "[" + destinationPion.getX() + "," + destinationPion.getY() + "]");
                                source=sourcePion;
                                return destinationPion;
                            }
                        }
                        l++;
                    }
                    k++;
                }
            }
            i++;
        }
        example.setVisible(true);
        example.showFeedback("l'autre joueur qui a gangné",10000);
        return null;
    }
    public Pion choisirUnPionAjouerSource(Pyramide pyramideJoueur, Pyramide K3,Table2D penalite)
    {
        Pion sourcePion;
        Pion destinationPion;
        int i = 0;
        while (i<penalite.getWidth()-1){
            if (penalite.getPion(0, i).estVide()==false) {
                sourcePion = penalite.getPion(0, i);
                if(sourcePion.getCouleur()== CouleurPion.BLANC)
                    return sourcePion;
                int k = 0;
                while (k < K3.getHight()) {
                    int l = 0;
                    while (l <= k) {
                        if (K3.getPion(k, l).estVide() == true) {
                            destinationPion = K3.getPion(k, l);
                            if (new GameController().testDeplacementPionsanschangement1(sourcePion, destinationPion, K3)) {
                                System.out.println("penalité tableau [" + sourcePion.getX() + "," + sourcePion.getY() + "]" + "-->" + "[" + destinationPion.getX() + "," + destinationPion.getY() + "]");
                                return sourcePion;
                            }
                        }
                        l++;
                    }
                    k++;
                }
            }
            i++;
        }
        i=0;
        while (i < pyramideJoueur.getHight()) {
            int j = 0;
            while(j <=i) {
                if (pyramideJoueur.getPion(i, j).estVide()==false) {
                    sourcePion = pyramideJoueur.getPion(i, j);
                    if(sourcePion.getCouleur()== CouleurPion.BLANC)
                        return sourcePion;
                        if(new GameController().testAvantDeplacement(sourcePion, pyramideJoueur)) {
                        int k = 0;
                        while (k < K3.getHight()) {
                            int l = 0;
                            while (l <= k) {
                                if (K3.getPion(k, l).estVide() == true) {
                                    destinationPion = K3.getPion(k, l);
                                    if (new GameController().testDeplacementPionsanschangement1(sourcePion, destinationPion, K3)) {
                                        System.out.println("[" + sourcePion.getX() + "," + sourcePion.getY() + "]" + "-->" + "[" + destinationPion.getX() + "," + destinationPion.getY() + "]");
                                        return sourcePion;
                                    }
                                }
                                l++;
                            }
                            k++;
                        }
                    }
                }
                j++;
            }
            i++;
        }

        example.setVisible(true);
        example.showFeedback("l'autre joueur qui a gangné",10000);
        return null;
    }

    public ArrayList<LesCoutsAccessibles> getLesCoutsAccessible() {
        return lesCoutsAccessible;
    }

    public void setLesCoutsAccessible(ArrayList<LesCoutsAccessibles> lesCoutsAccessible) {
        this.lesCoutsAccessible = lesCoutsAccessible;
    }
}
