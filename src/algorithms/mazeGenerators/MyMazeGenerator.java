package algorithms.mazeGenerators;

import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {
 /* a maze generator which works based on the recursive division algorithm which at each turn
    selects a random column/row at the maze and passes a wall through it, then does the same for both sides
    until the maze reaches the wished resolution
  */

     @Override
    public Maze generate(int rows, int columns) {
         if(rows < 2 || columns < 2)
             throw new IllegalArgumentException("Minimum size is 2*2");
         Random rand = new Random();
         int s=rows;
         int c=columns;
         if(s > 127)
         {
             s = rand.nextInt(120);
         }
         else
         {
             s = rand.nextInt(rows-2)+1;
         }
         if(c > 127)
         {
             c = rand.nextInt(120);
         }
         else
         {
             c = rand.nextInt(rows-2)+1;
         }
         int r = columns;

        Position start = new Position(s, 0);
        Position end = new Position(c, columns-1);
         Maze maze = new Maze(rows, columns, start, end);

         for(int i = 0; i < rows; i++){
            if (i != maze.startPosition.getRowIndex()){
                maze.maze[i][0] = 1;
            }
            if (i != maze.goalPosition.getRowIndex()){
                maze.maze[i][columns-1] = 1;
            }
        }
        for (int j = 0; j < columns; j++){
            maze.maze[0][j] = 1;
            maze.maze[rows-1][j] = 1;
        }
        recursiveDivisionGenerator(maze, 1, columns-2, 1, rows-2, 0);
        return maze;
    }


    private  void recursiveDivisionGenerator(Maze m, int x, int width, int y, int height, int lastHole){
        if (width  <= 2 || height <= 2){
            m.maze[m.startPosition.getRowIndex()][m.startPosition.getColumnIndex()+ 1] = 0;
            m.maze[m.goalPosition.getRowIndex()][m.goalPosition.getColumnIndex() - 1] = 0;
            return;
        }
        boolean verFlag = width > height;
        Random rand = new Random();
        int wall;
        if (verFlag){   // choose random X to pass vertical wall through
            wall = x + 1 + rand.nextInt(width-2);
            for(int i = y; i < y+height; i++){  // build wall
                m.maze[i][wall] = 1;
            }
            if (m.maze[y+height][wall] == 0){
                m.maze[y+height-1][wall] = 0;
            }
            if (m.maze[y-1][wall] == 0){
                m.maze[y][wall] = 0;
            }
            int hole = y + rand.nextInt(height-2)  ;     // Create passage through wall
            m.maze[hole][wall] = 0;
            recursiveDivisionGenerator(m,x,(wall-x),y,height,hole);     // Recurse to left part
            recursiveDivisionGenerator(m,(wall+1),(x+width-wall-1), y, height,hole);      // Recurse to right part
        } else{     // choose random Y to pass horizontal wall through
            wall = y + 1 + rand.nextInt(height-2);
            for(int i = x; i < x+width; i++){       // build wall and make sure there are no obstructions
                m.maze[wall][i] = 1;
            }
            if (m.maze[wall][x+width] == 0){
                m.maze[wall][x+width-1] = 0;
            }
            if (m.maze[wall][x-1] == 0){
                m.maze[wall][x] = 0;
            }
            int hole = x+ rand.nextInt(width-2) ;       // Create passage through wall
            m.maze[wall][hole] = 0;
            recursiveDivisionGenerator(m,x, width, y, (wall-y), hole);
            recursiveDivisionGenerator(m,x,width,(wall+1), (y+height-wall-1), hole);
        }
    }
}

