package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch<costComparator> extends BreadthFirstSearch{
    //best first search is type of breath first search. Extend breath class and use breath algorithm for solution

    public BestFirstSearch(){
        super();
        Comparator<AState> costComparator = new Comparator<AState>() {
            @Override
            //define suitable comparator for priority queue
            public int compare(AState s1, AState s2) {
                if (s1.getCost() < s2.getCost()) {
                    return -1;
                } else if (s1.getCost() > s2.getCost()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        //override openList with priority queue
        openList = new PriorityQueue<>(costComparator);
    }


    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
