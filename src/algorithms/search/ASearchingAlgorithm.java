package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.*;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{

    protected Collection<AState> openList;
    protected int visitedNodes;
    protected ISearchable iSearchable;
    protected String name;

    public ASearchingAlgorithm()
    {
        //openList = new LinkedList<AState>();
        visitedNodes = 0;
    }

    @Override
    public Solution solve(ISearchable s){ return null; }

    @Override
    public int getNumberOfVisitedNodes(){ return 0; };

    public int getVisitedNodes() {
        return visitedNodes;
    }
    public Solution findPath(AState goal)
    {
        AState curr = goal;
        Stack<AState> path = new Stack<AState>();
        while(curr != null)
        {
            path.add(curr);
            curr = curr.getCameFrome();
        }
        return new Solution(path);
    }
}
