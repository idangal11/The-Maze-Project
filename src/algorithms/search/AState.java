package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.io.Serializable;
import java.util.Objects;

public abstract class AState implements Serializable {
    //protected String state;
    public int cost;
    private int color = 0;
    public AState cameFrome;

    public AState(int cost)
    {
        this.cost = cost;
    }

    protected AState() {cost = 0;}

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public AState getCameFrome() {
        return cameFrome;
    }

    public void setCameFrome(AState cameFrome) {
        this.cameFrome = cameFrome;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public boolean equal(Object obj) {
        return false;
    }

    public abstract void setState(AState s);
    public abstract int getRow();
    public abstract int getColumn();

    @Override
    public abstract boolean equals(Object o);

    public abstract Position getPosition();

    @Override
    public int hashCode() {
        return Objects.hash(cost, cameFrome);
    }

    public abstract String toString();


}
