package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.Queue;

public class SearchableMaze extends ASearchable {
    public Maze maze;
    public Queue<AState> q;

    public SearchableMaze(Maze maze)
    {
        this.maze = maze;
    }

    @Override
    public AState getStartState() {
        this.maze.getStartPosition().toString();
        return new MazeState(this.maze.getStartState());
    }

    @Override
    public AState getGoalState() {
        return new MazeState(this.maze.getGoalState());
    }

    public boolean[][] getSize()
    {
        int r = this.maze.getRows();
        int l = this.maze.getRows();
        return new boolean[r][l];
    }

    public Queue<AState> getAllPossibleStates(AState state)
    {
        if (!(state instanceof MazeState))
            return null;
        this.q = new LinkedList<AState>();
        MazeState mazeState = (MazeState) state;
        Position p = mazeState.getPosition();

        if ((p.getColumnIndex() == 0) && (p.getRowIndex() < maze.getRows()-1))
        {
            rightPosition(p.getRowIndex(), p.getColumnIndex(),p, mazeState.getCost());
            diagonalRightBottomPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            bottomPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            return q;
        }
        // position in column 0 and column max
        if (p.getColumnIndex() == 0 && p.getRowIndex() == maze.getRows()-1)
        {
            rightPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            upPosition(p.getRowIndex(),p.getColumnIndex(),p, mazeState.getCost());
            diagonaRightUpperPosition(p.getRowIndex(),p.getColumnIndex(),p, mazeState.getCost());
            return q;
        }
        // position in row 0 and column is not max
        if(p.getRowIndex() == 0 && p.getColumnIndex() < maze.getRows()-1)
        {
            rightPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            leftPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            bottomPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            diagonalRightBottomPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            diagonalLeftBottomPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            return q;
        }
        // position in max column
        if (p.getColumnIndex() == maze.getColumns()-1 && p.getRowIndex() < maze.getRows()-1)
        {
            leftPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            diagonalLeftBottomPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            bottomPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            diagonalLeftUpperPosition(p.getRowIndex(),p.getColumnIndex(),p, mazeState.getCost());
            upPosition(p.getRowIndex(),p.getColumnIndex(),p, mazeState.getCost());
            return q;
        }
        // position in max column and  max row
        if (p.getRowIndex() == maze.getRows()-1 && p.getColumnIndex() == maze.getRows()-1)
        {
            leftPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            diagonalLeftUpperPosition(p.getRowIndex(),p.getColumnIndex(),p, mazeState.getCost());
            upPosition(p.getRowIndex(),p.getColumnIndex(),p, mazeState.getCost());
            return q;
        }
        // position in max row
        if(p.getRowIndex() == maze.getRows() - 1 && p.getColumnIndex() != 0 && p.getColumnIndex() != maze.getColumns()-1)
        {
            rightPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            leftPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            upPosition(p.getRowIndex(),p.getColumnIndex(),p, mazeState.getCost());
        }
        else
        {
            rightPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            leftPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            upPosition(p.getRowIndex(),p.getColumnIndex(),p, mazeState.getCost());
            bottomPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            diagonalRightBottomPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            diagonalLeftBottomPosition(p.getRowIndex(), p.getColumnIndex(), p, mazeState.getCost());
            diagonalLeftUpperPosition(p.getRowIndex(),p.getColumnIndex(),p, mazeState.getCost());
            diagonaRightUpperPosition(p.getRowIndex(),p.getColumnIndex(),p, mazeState.getCost());
            return q;
        }
        return q;
    }

    private void rightPosition(int r, int c, Position p, int cost)
    {
        r = p.getRowIndex();
        c = p.getColumnIndex()+1;
        Position position = new Position(r, c);
        MazeState rightPosition = new MazeState(cost + 10, position);
        if (maze.getMaze()[r][c] == 0)
            q.add(rightPosition);

    }

    private void leftPosition(int r, int c, Position p, int cost)
    {
        r = p.getRowIndex();
        c = p.getColumnIndex()-1;
        Position position = new Position(r, c);
        MazeState leftPosition = new MazeState(cost +10, position);
        if (maze.getMaze()[r][c] == 0)
            q.add(leftPosition);
    }

    private void bottomPosition(int r, int c, Position p,int cost)
    {
        r = p.getRowIndex()+1;
        c = p.getColumnIndex();
        Position position = new Position(r, c);
        MazeState bottomPosition = new MazeState(cost + 10,position);
        if (maze.getMaze()[r][c] == 0)
            q.add(bottomPosition);
    }
    private void upPosition(int r, int c, Position p,int cost)
    {
        r = p.getRowIndex()-1;
        c = p.getColumnIndex();
        Position position = new Position(r, c);
        MazeState upperPosition = new MazeState(cost + 10,position);
        if (maze.getMaze()[r][c] == 0)
            q.add(upperPosition);
    }

    private void diagonalRightBottomPosition(int r, int c, Position p,int cost)
    {
        r = p.getRowIndex()+1;
        c = p.getColumnIndex()+1;
        Position position = new Position(r, c);
        MazeState diagonalRightBottomPosition = new MazeState(cost +15,position);
        if (maze.getMaze()[r][c] == 0 && (maze.getMaze()[r-1][c] == 0 || maze.getMaze()[r][c-1] == 0))
            q.add(diagonalRightBottomPosition);
    }

    private void diagonalLeftBottomPosition(int r, int c, Position p,int cost)
    {
        r = p.getRowIndex()+1;
        c = p.getColumnIndex()-1;
        Position position = new Position(r, c);
        MazeState diagonalLeftBottomPosition = new MazeState(cost +15,position);
        if (maze.getMaze()[r][c] == 0 && (maze.getMaze()[r-1][c] == 0 || maze.getMaze()[r][c+1] == 0))
            q.add(diagonalLeftBottomPosition);
    }
    private void diagonaRightUpperPosition(int r, int c, Position p,int cost)
    {
        r = p.getRowIndex() - 1;
        c = p.getColumnIndex() + 1;
        Position position = new Position(r, c);
        MazeState diagonaRightUpperPosition = new MazeState(cost +15,position);
        if (maze.getMaze()[r][c] == 0 && (maze.getMaze()[r][c-1] == 0 || maze.getMaze()[r+1][c] == 0))
            q.add(diagonaRightUpperPosition);
    }

    private void diagonalLeftUpperPosition(int r, int c, Position p,int cost)
    {
        r = p.getRowIndex() - 1;
        c = p.getColumnIndex() - 1;
        Position position = new Position(r, c);
        MazeState diagonalLeftUpperPosition = new MazeState(cost +15,position);
        if (maze.getMaze()[r][c] == 0 && (maze.getMaze()[r+1][c] == 0 || maze.getMaze()[r][c+1] == 0))
            q.add(diagonalLeftUpperPosition);
    }

}
