package Controller;

import Model.Pion;
import Model.Pyramide;

import java.util.ArrayList;

public class LesCoutsAccessibles
{
    Pion source;
    Pion destination;
    ArrayList<LesCoutsAccessibles> lesCoutsAccessibles = new ArrayList<LesCoutsAccessibles>();
    public LesCoutsAccessibles(Pion source, Pion destination)
    {
        this.source = source;
        this.destination = destination;
        this.lesCoutsAccessibles = lesCoutsAccessibles;
    }
    public LesCoutsAccessibles(){
        this.source = source;
        this.destination = destination;
        this.lesCoutsAccessibles = lesCoutsAccessibles;
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




    public void afficherCoutsAccessibles(Pyramide pyramideJoueur, Pyramide K3 )
    {
        Pion sourcePion;
        Pion destinationPion;
        System.out.println("Les couts accessibles sont : ");
        for (int i = 0; i < pyramideJoueur.getHight(); i++)
        {
            for (int j = 0; j <=i; j++)
            {
                if (pyramideJoueur.getPion(i, j).estVide()==false)
                {
                    sourcePion = pyramideJoueur.getPion(i, j);
                    if(new GameController().testAvantDeplacement(sourcePion, pyramideJoueur))
                    for (int k = 0; k < K3.getHight(); k++)
                    {
                        for (int l = 0; l <=k; l++)
                        {
                            if (K3.getPion(k, l).estVide()==true)
                            {
                                destinationPion = K3.getPion(k, l);
                                if (new GameController().testDeplacementPionsanschangement1(sourcePion, destinationPion, K3)){
                                    System.out.println("[" + sourcePion.getX() + "," + sourcePion.getY() + "]" + "-->" + "[" + destinationPion.getX() + "," + destinationPion.getY() + "]");
                                    this.lesCoutsAccessibles.add(new LesCoutsAccessibles(sourcePion, destinationPion));
                                    //afficher le contenue de la liste des couts accessibles arraylist

                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public ArrayList<LesCoutsAccessibles> getLesCoutsAccessibles() {
        return lesCoutsAccessibles;
    }

    public void setLesCoutsAccessibles(ArrayList<LesCoutsAccessibles> lesCoutsAccessibles) {
        this.lesCoutsAccessibles = lesCoutsAccessibles;
    }
}
