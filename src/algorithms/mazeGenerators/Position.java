package algorithms.mazeGenerators;

// a class defining what is a position, row and column with getters and to string

import java.io.Serializable;

public class Position implements Serializable {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    public Position(Position position)
    {
        this.row = position.row;
        this.column = position.column;
    }

    public int getRowIndex()
    {
        return this.row;
    }
    public int getColumnIndex()
    {
        return this.column;
    }


    @Override
    public String toString() {
        return "Position{" +
                "" + row +
                "," + column +
                '}';
    }
}
