package algorithms.mazeGenerators;
import java.util.Random;

/* a simple maze generator fills every second rows and
    creates a hole for passage
 */

public class SimpleMazeGenerator extends AMazeGenerator {

    public Maze generate(int row, int culomn)
    {
        //Position [][] a = new Position[4][4];
        Position startPosition =new Position(0,0);
        Position goalPosition = new Position(row-1, culomn-1);

        Maze matrix = new Maze(row, culomn, startPosition, goalPosition);
        Random rand = new Random();

        int rundomNumber;
        int flag = 1;
        int randRow = rand.nextInt(culomn)%2;

        for (int i = 0; i < row; i++)
        {
            flag = 1;
            for (int j = 0; j < culomn; j += 1)
            {
                if((j==row-1 || j==0) && i > 1)
                    matrix.maze[i][j] = 1;

                if (i % 2 == randRow)
                {
                    matrix.maze[i][j] = 1;
                    if(flag == 1 && j==culomn-1)
                    {
                        rundomNumber = rand.nextInt(culomn);
                        matrix.maze[i][rundomNumber] = 0;
                        rundomNumber = rand.nextInt(culomn);
                        matrix.maze[i][rundomNumber] = 0;
                        //save = rundomNumber;
                        flag = 0;
                    }
                }
            }
        }

        return matrix;
    }

}
