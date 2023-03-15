package algorithms.mazeGenerators;

// an interface for maze generators

public interface IMazeGenerator {
    Maze generate(int rows, int columns);
    long measureAlgorithmTimeMillis(int rows, int columns);
}

