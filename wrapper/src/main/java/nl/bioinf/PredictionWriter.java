package testing;

import weka.core.Instances;

import java.io.FileWriter;
import java.io.IOException;

public class PredictionWriter {
    Instances data;
    String filename;

    public PredictionWriter(Instances data, String filename){
        this.data = data;
        this.filename = filename;
    }

    public void writeToFile(){
        try {
            FileWriter writer = new FileWriter(filename);
            for (int index = 0; index < data.numInstances(); index++){
                writer.write(String.valueOf(data.get(index) + "\n"));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("A problem occurred while writing to file");
        }
    }

}
