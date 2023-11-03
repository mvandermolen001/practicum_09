package nl.bioinf;

import weka.core.Instances;

import java.io.FileWriter;
import java.io.IOException;

/**
 * PredictionWriter class, writes the predicted instance to a file
 * @author Margriet
 */
public class PredictionWriter {
    Instances data;
    String filename;

    /**
     * Constructor for the PredictionWriter
     * @param data
     *        Weka instances
     * @param filename
     *        The name of the file to write to
     */
    public PredictionWriter(Instances data, String filename){
        this.data = data;
        this.filename = filename;
    }

    /**
     * writeToFile method writes the weka instances to the file
     */
    public void writeToFile(){
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("SPECIES,CULMEN LENGTH,CULMEN DEPTH,BODYWEIGHT, DELTA N15,DELTA C13, SEX" + "\n");
            for (int index = 0; index < data.numInstances(); index++){
                writer.write(data.get(index) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("A problem occurred while writing to file " + e.getMessage());
        }
    }

}
