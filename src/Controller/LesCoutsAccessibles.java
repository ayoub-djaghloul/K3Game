package Controller;

import Model.Pion;

import java.util.ArrayList;

public class LesCoutsAccessibles
{
    Pion source;
    Pion destination;
    ArrayList<LesCoutsAccessibles> lesCoutsAccessibles = new ArrayList<LesCoutsAccessibles>();
    public LesCoutsAccessibles(Pion source, Pion destination, int cout)
    {
        this.source = source;
        this.destination = destination;
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

}
