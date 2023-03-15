
package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import java.io.*;
import java.util.Properties;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try{
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient); // needs to be compressed maze
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient); // size 2 int array [rows, columns]
            int[] mazeDim = (int[]) fromClient.readObject();
            if (mazeDim.length != 2){
                throw new RuntimeException("Input array to large... please try again");
            }

            Configurations config = Configurations.getInstance();
            Properties prop = config.readConfig();
            String mgName = prop.getProperty("mazeGeneratingAlgorithm");
            Maze maze = null;
            switch (mgName){
                case "MyMazeGenerator":
                    maze = new MyMazeGenerator().generate(mazeDim[0], mazeDim[1]);
                    break;
                case "SimpleMazeGenerator":
                    maze = new SimpleMazeGenerator().generate(mazeDim[0], mazeDim[1]);
                    break;
                case "EmptyMazeGenerator":
                    maze = new EmptyMazeGenerator().generate(mazeDim[0], mazeDim[1]);
            }
            System.out.println(maze);
            maze.print();
            // compress Maze
            ByteArrayOutputStream arr = new ByteArrayOutputStream();
            OutputStream out = new SimpleCompressorOutputStream(arr);

            out.write(maze.toByteArray());

            out.flush();
            byte[] bytes = arr.toByteArray();

            toClient.writeObject(bytes);
            toClient.flush();
            fromClient.close();
            toClient.close();
            out.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}



 /*OutputStream out = new SimpleCompressorOutputStream(new
                    FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();*/
