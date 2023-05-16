package Controller;

import Model.Pion;
import Model.Pyramide;

import java.util.ArrayList;

public class LesCoutsAccessibles
{
    Pion source;
    Pion destination;
    ArrayList<>lesCoutsAccessibles = new ArrayList<>();
    public LesCoutsAccessibles(Pion source, Pion destination, ArrayList<> lesCoutsAccessibles)
    {
        this.source = source;
        this.destination = destination;
        this.lesCoutsAccessibles = lesCoutsAccessibles;
    }
    public Cout(Pion source, Pion destination, ArrayList<> lesCoutsAccessibles){
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




    public void afficherCoutsAccessibles(Pyramide pyramideJoueur, Pyramide K3)
    {
        Pion comparedPion;
        Pion comparedPion2;
        for (int i = 0; i < pyramideJoueur.getHight(); i++)
        {
            for (int j = 0; j <=i; j++)
            {
                if (pyramideJoueur.getPion(i, j).estAccessible()==false)
                {
                    comparedPion = pyramideJoueur.getPion(i, j);
                    for (int k = 0; k < K3.getHight(); k++)
                    {
                        for (int l = 0; l <=k; l++)
                        {
                            if (K3.getPion(k, l).estAccessible()==false)
                            {
                                comparedPion2 = K3.getPion(k, l);
                                if (new GameController().testDeplacementPion(comparedPion, comparedPion2, K3))
                                {
                                    this.lesCoutsAccessibles.add(new LesCoutsAccessibles(comparedPion, comparedPion2, lesCoutsAccessibles));
                                    //afficher les couts accessibles dans la console
                                    System.out.println("Pion source : " + comparedPion.getX() + " " + comparedPion.getY());
                                    System.out.println("Pion destination : " + comparedPion2.getX() + " " + comparedPion2.getY());

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
