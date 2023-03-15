package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Queue;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable s);

    int getNumberOfVisitedNodes();

    int getNumberOfNodesEvaluated();

    String getName();
}
