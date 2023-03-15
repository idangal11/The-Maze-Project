package algorithms.search;

import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

class BestFirstSearchTest {



    @Test
    void solveMaze() throws Exception{
        SearchableMaze maze = new SearchableMaze(new MyMazeGenerator().generate(10,10));
        ArrayList<AState> solutionPath = new BestFirstSearch<>().solve(maze).getSolutionPath();
        if (solutionPath.get(solutionPath.size()-1).equal(maze.getGoalState())) {
            fail("Solution didn't reach goal");
        }
    }


    @Test
    void getName() throws Exception {
        Assertions.assertEquals("BestFirstSearch", new BestFirstSearch().getName());
    }

    @Test
    void solveNull() throws Exception {
        try {
            new BestFirstSearch<>().solve(null);
        } catch (Exception e) {
            fail("Does not work on null maze...");

        }
    }

}