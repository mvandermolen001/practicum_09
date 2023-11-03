package nl.bioinf;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Wrapper classifier class. Classifies the given data using the model provided in the resources' folder.
 * @author Margriet
 */
public class WrapClassifier{

    /**
     * Filepath given by user if user chooses for bulk data
     */
    String filepath;

    /**
     * Constructor used when user gives a filepath
     * @param filepath
     *        User given file path
     */
    public WrapClassifier(String filepath){
        this.filepath = filepath;
    }

    /**
     * Constructor used when user chooses single instance classification
     */
    public WrapClassifier(){
    }

    /**
     * Start method for starting the program when bulk data is given.
     * @return the classified instances
     */
    public Instances start(){
        try{
            Classifier model = loadClassifier();
            Instances data = loadData(this.filepath);
            return classifyData(model, data);
        } catch (Exception e){
            throw new RuntimeException("Error occurred during runtime...");
        }
    }

    /**
     * Gathers data from user and stores them in weka instances
     * @param filePath
     *        given filepath
     * @return user data as weka instances
     * @throws IllegalArgumentException
     *         throws when file could not be found.
     */
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

    /**
     * Load classifier from resources' file.
     * @return the Logistic classifier
     * @throws IOException
     *         Throws when method failed to read from file
     */
    public Classifier loadClassifier() throws IOException {
        // Get Logistic Model from the resources file
        URL url = getClass().getResource("/Logistic_Model.model");
        try {
            assert url != null;
            // Return a classifier with the resource file
            return (Classifier) SerializationHelper.read(url.getFile());
        } catch (Exception e) {
            throw new IOException("Could not read from file..." + e.getMessage());
        }
    }

    /**
     * Classify the given data with the given model.
     * @param model
     *        the weka model
     * @param data
     *        weka instances
     * @return labeled instances
     */
    public Instances classifyData(Classifier model, Instances data) {
        Instances labeled = new Instances(data);
        // label each instance from data
        for (int index = 0; index < data.numInstances(); index++) {
            double label;
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
