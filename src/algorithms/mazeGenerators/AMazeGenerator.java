package algorithms.mazeGenerators;

/* an abstract class for creating a maze generator with no specific algorithm */

public abstract class AMazeGenerator implements IMazeGenerator {
    public Maze generate(int rows, int columns) {
        return null;
    }
    // a class that checks the creation time for the maze, works for all algorithms
    public long measureAlgorithmTimeMillis(int rows, int columns){
        long start, end, generateTime;
        start = System.currentTimeMillis();
        generate(rows, columns);
        end = System.currentTimeMillis();
        generateTime = end-start;
        return generateTime;

    }
}
