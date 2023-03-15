package Server;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configurations {
    private static Configurations instance = null;

    private Configurations() {

    }

    public static Configurations getInstance(){
        if (instance == null){
            instance = new Configurations();
        }

        return instance;
    }

    public Properties readConfig(){
        try (InputStream input = Configurations.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();
            if (input == null){
                System.out.println("Config file not found");
                return null;
            }

            prop.load(input);
            return prop;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


    public void writeConfig(String poolSize, String mgAlgo, String msAlgo){
        try (OutputStream output = new FileOutputStream("resources/config.properties")) {
            Properties prop = new Properties();

            prop.setProperty("threadPoolSize", poolSize);
            prop.setProperty("mazeGeneratingAlgorithm", mgAlgo);
            prop.setProperty("mazeSearchingAlgorithm", msAlgo);

            prop.store(output, null);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Configurations config = Configurations.getInstance();
        config.writeConfig("2","MyMazeGenerator", "BestFirstSearch");

    }

}
