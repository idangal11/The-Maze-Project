package algorithms.search;
import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    private Position position;


    public MazeState(int stepCost, Position p)
    {
        super(stepCost);
        this.position = new Position(p);
    }
    public MazeState(Position p)
    {
        this.position = new Position(p);
    }


    @Override
    public void setState(AState s) {

    }

    public Position getPosition() {
        return position;
    }

    @Override
    public int getRow() {
        return position.getRowIndex();
    }

    @Override
    public int getColumn() {
        return position.getColumnIndex();
    }

    @Override
    public String toString() {
        return "{" +
                "" + position.getRowIndex()+","+ position.getColumnIndex() +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        MazeState state = (MazeState) obj;
        return(state.getPosition().getRowIndex() == this.getPosition().getRowIndex() &&
                state.getPosition().getColumnIndex() == this.getPosition().getColumnIndex());
    }


}
