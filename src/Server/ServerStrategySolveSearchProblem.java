
package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Properties;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient); // a maze solution
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient); // a maze
            Maze maze = (Maze) fromClient.readObject();
            String mazeName = maze.toString();
            File file = new File("resources/" + mazeName + ".sol");
            Solution solution = null;
            if (file.exists()){
                ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));
                solution = (Solution) read.readObject();
            }else {
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                Configurations config = Configurations.getInstance();
                Properties prop = config.readConfig();
                String msName = prop.getProperty("mazeSearchingAlgorithm");
                if (msName.equals("BestFirstSearch")) {
                    solution = new BestFirstSearch<>().solve(searchableMaze);
                }
                if (msName.equals("BreadthFirstSearch")) {
                    solution = new BreadthFirstSearch().solve(searchableMaze);
                }
                if (msName.equals("DepthFirstSearch")) {
                    solution = new DepthFirstSearch().solve(searchableMaze);
                }
                if (solution == null) {
                    System.out.printf("Error with config file");
                    fromClient.close();
                    toClient.close();
                    return;
                }
                String tempDirectoryPath = System.getProperty("java.io.tmpdir");
                ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("resources/" + mazeName + ".sol"));
                save.writeObject(solution);
                save = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath + mazeName + ".mz"));
                save.writeObject(maze);
            }
            toClient.writeObject(solution);
            toClient.flush();
            fromClient.close();
            toClient.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



