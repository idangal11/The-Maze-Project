package algorithms.search;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class Solution implements Serializable {
    private final ArrayList<AState> solution = new ArrayList<>();


    public Solution(Stack<AState> path)
    {
        this.setSolutions(path);
    }

   public void setSolutions(Stack<AState> path)
   {
       while(!path.isEmpty())
       {
           this.solution.add(path.pop());
       }
   }

   public ArrayList<AState> getSolutionPath()
   {
       return this.solution;

   }
}
