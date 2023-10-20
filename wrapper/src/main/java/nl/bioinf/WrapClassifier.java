package nl.bioinf;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WrapClassifier {
    public static void main(String[] args){
        WrapClassifier wrapper = new WrapClassifier();
        wrapper.start();
    }

    private void start(){
        //DELETE THIS TEST ONE PLEASE DONT FORGET
        String test = "/homes/mvandermolen/thema_09/practicum_09/data/adjusted_penguin_data.arff";
        System.out.println("Starting WrapClassifier...");
        try{
            Classifier model = loadClassifier();
            Instances data = loadData(test);
            System.out.println(data.toSummaryString());
            Instances predictions = classifyData(model, data);
            System.out.println(predictions);
        } catch (Exception e){
            throw new RuntimeException("Error occurred during runtime...");
        }
        // Create predictions
    }

    private Instances loadData(String filePath) throws Exception {
        // Make sure the path given actually exists
        if (! new File(filePath).exists()){
            throw new IllegalArgumentException("Could not find the given file...");
        }
        ConverterUtils.DataSource reader = new ConverterUtils.DataSource(filePath);
        Instances data = reader.getDataSet();
        // Sets the class, what the program will predict. Typically, this is the last attribute.
        data.setClassIndex(data.numAttributes() - 1);
        return data;
    }

    private Classifier loadClassifier() throws IOException {
        // Get Logistic Model from the resources file
        URL url = getClass().getResource("/Logistic_Model.model");
        try {
            // Double check that it isn't null
            assert url != null;
            // Return a classifier with the resource file
            return (Classifier) SerializationHelper.read(url.getFile());
        } catch (Exception e) {
            throw new IOException("Could not read from file..." + e.getMessage());
        }
    }

    private Instances classifyData(Classifier model, Instances data) {
        Instances labeled = new Instances(data);
        for (int index = 0; index < data.numInstances(); index++) {
            double label = 0;
            try {
                label = model.classifyInstance(data.instance(index));
            } catch (Exception e) {
                throw new RuntimeException("Something went wrong while classifying.." + e.getMessage());
            }
            labeled.instance(index).setClassValue(label);
        }
        return labeled;
    }


}