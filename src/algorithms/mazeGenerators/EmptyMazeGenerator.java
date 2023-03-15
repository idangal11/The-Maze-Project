package algorithms.mazeGenerators;

/* a class which creates a maze sized rows, columns and makes all values 0, a maze with no walls */

public class EmptyMazeGenerator extends AMazeGenerator {
    public Maze generate(int rows, int columns){

        Position startPosition = new Position(0,0);
        Position goalPosition = new Position(rows-1,columns-1);

        Maze maze = new Maze(rows,columns, startPosition, goalPosition);
        return maze;
    }
}
