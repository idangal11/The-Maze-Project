package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Maze implements Serializable {
    protected int rows;
    protected int columns;
    protected int[][] maze;
    protected Position startPosition;
    protected Position goalPosition;

    //added colors to paint maze print for easier debugging
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public Maze(byte[] b) {//TODO MAZE CONST
        int sum=0;
        ArrayList<Integer> a = new ArrayList<>();
        for (int num : b) {
            a.add(num);
        }
        int j=0;
        int c;
        while((c=a.get(j)) != 0)
        {
            sum+=c;
            j++;
        }
        this.rows=sum;
        j++;
        sum=0;
        while((c=a.get(j)) != 0)
        {
            sum+=c;
            j++;
        }
        this.columns=sum;
        j++;
        int row1=a.get(j);
        j++;
        int col1=a.get(j);
        this.startPosition=new Position(row1,col1);

        j++;
        int row2=a.get(j);
        sum=0;
        j++;
        while((c=a.get(j)) != 0)
        {
            sum+=c;
            j++;
        }
        int col2=sum;

        this.goalPosition=new Position(row2,col2-1);
        j+=4;
        this.maze = new int[rows][columns];
        for(int h = 0; h < this.rows; h++)
        {
            for(int u = 0; u < this.columns; u++)
            {
                maze[h][u]=a.get(j);
                j++;
                //bb[counter] = (byte)maze[i][j];
                //counter++;
            }
        }
    }
    public byte[] toByteArray(){
        int counter;
        int c=0;
        //byte[] b = new byte[rows*columns+6];
        ArrayList<Integer> b = new ArrayList<>();
         int s = this.rows;
         if (s > 127) {
             while (s > 127) {
                 b.add(127);
                 //c++;
                 s -= 127;
             }
             b.add(s);
         }
         else{
             b.add(this.rows);
             //c++;
         }
        b.add(0);
        s = this.columns;
        if (s > 127) {
            while (s > 127) {
                b.add(127);
                //c++;
                s -= 127;
            }
            b.add(s);
        }
        else{
            b.add(this.rows);
            //c++;
        }
        b.add(0);

        //b[c] = (byte)this.startPosition.getRowIndex();
        b.add(this.startPosition.getRowIndex());
        //c++;
        //b[c] = (byte)this.startPosition.getColumnIndex();
        b.add(this.startPosition.getColumnIndex());
        //c++;
        //b[c] = (byte)this.goalPosition.getRowIndex();

        b.add(this.goalPosition.getRowIndex());
        //c++;
        //b[c] = (byte)this.goalPosition.getColumnIndex();
        s = this.columns;
        if (s > 127) {
            while (s > 127) {
                b.add(127);
                //c++;
                s -= 127;
            }
            b.add(s);
        }
        else{
            b.add(s);
        }
        b.add(0);
        b.add(3);
        b.add(3);
        b.add(3);
        //b.add(this.goalPosition.getColumnIndex());

        byte[] bb = new byte[b.size()+rows*columns];
        counter = b.size();
        for(int index=0;index<b.size();index++)
        {
            int num = b.get(index);
            bb[index]= (byte)num;
        }

        for(int i = 0; i < this.rows; i++)
        {
            for(int j = 0; j < this.columns; j++)
            {
                bb[counter] = (byte)maze[i][j];
                counter++;
            }
        }

        return bb;
    }


    // definition of mazes, rows, columns and positions

    public Maze(int rows, int columns,Position start,Position goal) {
        this.startPosition = start;
        this.goalPosition = goal;
        if(rows < 2 || columns < 2)
            throw new IllegalArgumentException("Minimum size is 2*2");
        this.rows = rows;
        this.columns = columns;
        maze = new int[rows][columns];
    }
    // getters and setters
    public int getRows() {
        return rows;
    }

    public int[][] getMaze() {
        return maze;
    }

    public int getColumns() {
        return columns;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    // a print method to print the maze, painting 0 and 1, S and E in different colors.
    public void print(){
        for(int i = 0; i < this.rows ; i++){
            for(int j = 0; j < this.columns; j++){
                if (i == this.startPosition.getRowIndex() && j == startPosition.getColumnIndex()){
                    System.out.print(ANSI_CYAN + "S" + ANSI_RESET);
                }else if(i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex()){
                    System.out.print(ANSI_PURPLE +  "E" + ANSI_RESET);
                }else {
                    if (maze[i][j] == 0){
                        System.out.print(ANSI_GREEN + maze[i][j] + ANSI_RESET);
                    }else{
                        System.out.print(ANSI_RED + maze[i][j] + ANSI_RESET);
                    }
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public Position getStartState()
    {
        return startPosition;
    }
    public Position getGoalState() {
        return goalPosition;
    }

    /*@Override
    public String toString() {

        return ("Maze"+a+"."+startPosition.toString()+"."+goalPosition.toString());
    }*/
}
