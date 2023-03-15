package algorithms.search;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    Queue<AState> adj = new LinkedList<AState>();
    String name = "BreadthFirstSearch";

    public BreadthFirstSearch() {
        super();
        openList = new LinkedList<AState>();
    }

    public String getName() {
        return name;
    }

    @Override
    public Solution solve(ISearchable s) {
        if(s == null){
            System.out.println("Entered a null maze \n Please try again...");
            return null;
        }
        AState finalState = s.getGoalState(); //initialize start point
        AState startState = s.getStartState();//initialize final point
        AState currentState;
        boolean[][] nodes = s.getSize();//table for visited vertexes
        nodes[startState.getRow()][startState.getColumn()] = true; //start point already visited
        openList.add(startState);//start point is the first vertex to add to openList

        while(!openList.isEmpty())//while not empty continue check vertexes
        {
            startState = ((Queue<AState>)openList).poll();//get first vertex from openList
            visitedNodes++;
            adj = s.getAllPossibleStates(startState);//get all startState neighbours
            int size = adj.size();
            for (int i=0;i<size;i++) {//find all neighbours path's
                currentState = adj.poll();

                assert currentState != null;
                if (!nodes[currentState.getRow()][currentState.getColumn()]) {//check if already visited
                    nodes[currentState.getRow()][currentState.getColumn()] = true;//mark as visited
                    currentState.setCameFrome(startState);//set parents
                    openList.add(currentState);//add to openList to get new path from this vertex
                }
                if (currentState.equals(finalState))//find the goal vertex
                {
                    return findPath(currentState);//special function to return correct solution
                }
            }
        }
        return findPath(finalState.getCameFrome());
    }

    public int getNumberOfNodesEvaluated()
    {
        return visitedNodes;
    }
}
