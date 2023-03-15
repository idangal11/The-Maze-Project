package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DepthFirstSearch extends ASearchingAlgorithm{

    /* a DFS searching algo through the maze */

    ArrayList<AState> adj = new ArrayList<AState>();
    boolean[][] nodes;
    Queue<AState> sol = new LinkedList<AState>();

    public DepthFirstSearch()
    {
        super();
        openList = new ArrayList<AState>();
    }

    public void setNodes(boolean[][] nodes) {
        this.nodes = nodes;
    }

    public Solution solve(ISearchable s)
    {
        if(s == null){
            System.out.println("Entered a null maze \n Please try again");
            return null;
        }
        AState finalState = s.getGoalState();
        AState startState = s.getStartState();
        setNodes(s.getSize());
        AState last = dfs(startState,s,finalState);
        return findPath(last);
    }
    /* a recursive function running a DFS search through the maze, for each node in possible move we send recursively
        and then check the rest making a depth first call
     */

    public AState dfs(AState startState, ISearchable s,AState finalState) {
        if (startState.equals(finalState)) {
            sol.add(startState);
            return startState;
        }
        nodes[startState.getRow()][startState.getColumn()] = true;
        Queue<AState> adj = s.getAllPossibleStates(startState);
        for (AState check: adj){
            if (check.getColor() >= 1){
                adj.remove(check);
            }
        }
        for (AState state : adj) {
            state.setColor(1);
            visitedNodes++;
            if (!nodes[state.getRow()][state.getColumn()]) {
                state.setCameFrome(startState);
                AState temp = dfs(state, s, finalState);

                if (temp.equals(finalState)) {
                    return temp;
                }
                nodes[state.getRow()][state.getColumn()] = true;
                state.setColor(2);
            }
        }
        return startState;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
}
