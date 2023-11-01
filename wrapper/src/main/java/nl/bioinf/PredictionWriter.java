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
            writer.write("SPECIES,CULMEN LENGTH,CULMEN DEPTH,BODYWEIGHT, DELTA N15,DELTA C13, SEX" + "\n");
            for (int index = 0; index < data.numInstances(); index++){
                writer.write(data.get(index) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("A problem occurred while writing to file");
        }
    }

}
