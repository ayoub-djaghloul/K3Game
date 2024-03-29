package Controller;

import Model.CouleurPion;
import Model.Pion;
import Model.Pyramide;
import Model.Table2D;

import java.util.ArrayList;

public class LesCoutsAccessibles
{
    private static final int MAX_DEPTH = 5;
    Pion source;
    Pion destination;
    //arraylist pour stocker le pion source et le pion destination
    ArrayList<Pion> lesPionsSources = new ArrayList<Pion>();
    ArrayList<Pion> lesPionsDestinations = new ArrayList<Pion>();
    ArrayList<LesCoutsAccessibles> lesCoutsAccessible = new ArrayList<LesCoutsAccessibles>();



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
    }
    // TODO : Test des Pions Blancs

 public Pion choisirUnPionAjouer1(Pyramide pyramideJoueur, Pyramide K3, Table2D penalite) {
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
        return null;
    }
    public Pion choisirUnPionAjouerSource1(Pyramide pyramideJoueur, Pyramide K3,Table2D penalite)    {
        Pion sourcePion;
        Pion destinationPion;
        int i = 0;
        while (i<penalite.getWidth()-1){
            if (penalite.getPion(0, i).estVide()==false) {
                sourcePion = penalite.getPion(0, i);
                if(sourcePion.getCouleur()== CouleurPion.BLANC)
                {
                        System.out.println("penalité tableau [" + sourcePion.getX() + "," + sourcePion.getY() + "]" );
                        return sourcePion;}
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
                    {
                        if (new GameController().testAvantDeplacement(sourcePion, pyramideJoueur)) {
                            System.out.println("penalité tableau [" + sourcePion.getX() + "," + sourcePion.getY() + "]" );
                            return sourcePion;}}
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
        return null;
    }

    public Pion choisirUnPionAjouer(Pyramide pyramideJoueur, Pyramide K3, Table2D penalite) {
        return minimax(pyramideJoueur, K3, penalite, 0).destination;
    }

    public Pion choisirUnPionAjouerSource(Pyramide pyramideJoueur, Pyramide K3, Table2D penalite) {
        return minimax(pyramideJoueur, K3, penalite, 0).source;
    }

    private Evaluation minimax(Pyramide pyramideJoueur, Pyramide K3, Table2D penalite, int depth) {
        Evaluation bestMove=new Evaluation();
        bestMove.source = choisirUnPionAjouerSource1(pyramideJoueur, K3, penalite);
        bestMove.destination = choisirUnPionAjouer1(pyramideJoueur, K3, penalite);
        return bestMove;
        }


    private Evaluation evaluateBoard(Pyramide pyramideJoueur, Pyramide K3, Table2D penalite) {
        //minimax heuristique (normalement)
        Evaluation evaluation = new Evaluation();
        evaluation.value = /* calculer la valeur de l'état actuel du tableau*/  0;
        return evaluation;
    }



    private static class Evaluation {
        Pion source;
        Pion destination;
        int value;
    }
    public ArrayList<LesCoutsAccessibles> getLesCoutsAccessible() {
        return lesCoutsAccessible;
    }

    public void setLesCoutsAccessible(ArrayList<LesCoutsAccessibles> lesCoutsAccessible) {
        this.lesCoutsAccessible = lesCoutsAccessible;
    }
}
