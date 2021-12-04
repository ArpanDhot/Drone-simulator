package Java.Database;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONTokener;

public class DataHandling {

    public static void save_simulation_state(File f, JSONArray array){
        try (FileWriter file = new FileWriter(f)) {
            file.write(array.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray load_simulation_state(File file){
        try(FileReader reader = new FileReader(file)){
            JSONTokener tokener = new JSONTokener(reader);
            return new JSONArray(tokener);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
