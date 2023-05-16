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




    public void afficherCoutsAccessibles(Pyramide pyramideJoueur, Pyramide K3)
    {
        Pion comparedPion;
        Pion comparedPion2;
        System.out.println("Les couts accessibles sont : ");
        for (int i = 0; i < pyramideJoueur.getHight(); i++)
        {
            for (int j = 0; j <=i; j++)
            {
                if (pyramideJoueur.getPion(i, j).estAccessible()==false)
                {
                    comparedPion = pyramideJoueur.getPion(i, j);
                    System.out.println("Pion source : " + comparedPion.getX() + " " + comparedPion.getY());

                    for (int k = 0; k < K3.getHight(); k++)
                    {
                        for (int l = 0; l <=k; l++)
                        {
                            if (K3.getPion(k, l).estAccessible()==true)
                            {
                                comparedPion2 = K3.getPion(k, l);
                                System.out.println("Pion destination : " + comparedPion2.getX() + " " + comparedPion2.getY());
                                if (new GameController().testDeplacementPion(comparedPion, comparedPion2, K3)){
                                    this.lesCoutsAccessibles.add(new LesCoutsAccessibles(comparedPion, comparedPion2));
                                    //afficher les couts accessibles dans la console

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
