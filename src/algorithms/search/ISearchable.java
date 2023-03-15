package algorithms.search;
import java.util.Queue;

public interface ISearchable {

    AState getStartState();
    AState getGoalState();
    Queue<AState> getAllPossibleStates(AState s);
    boolean[][] getSize();


}
