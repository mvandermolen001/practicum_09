package nl.bioinf;

import java.io.IOException;
import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * SingleInstanceClassifier classifies the single instances given by the user
 * @author Margriet
 */
public class SingleInstanceClassifier{

    private ArrayList<Double> doubleData = new ArrayList<>();
    private final String species;
    private final String sex;

    /**
     * Constructor for SingleInstanceClassifier.
     * @param doubleData
     *        An array list with double values
     * @param species
     *        the species of a penguin
     * @param sex
     *        the sex of a penguin
     */
    public SingleInstanceClassifier(ArrayList<Double> doubleData, String species, String sex){
        this.doubleData = doubleData;
        this.species = species;
        this.sex = sex;
    }

    /**
     * start controls flow of the process
     * @return predicted weka instances
     */
    public Instances start() {
        ArrayList<Attribute> pass_on = createAttributes();
        Instances data = createInstances(pass_on);
        try {
            return classifyInstance(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * createAttributes creates all the needed attributes for the model to use
     * @return attributeList -
     *         an ArrayList with attributes
     */
    private ArrayList<Attribute> createAttributes() {

        ArrayList<Attribute> attributeList = new ArrayList<Attribute>(3);

        // Create a list with the given values for the species
        ArrayList<String> species_list = new ArrayList<String>();
        species_list.add("ADELIE");
        species_list.add("CHINSTRAP");
        species_list.add("GENTOO");
        // and then create the species attribute with the list
        Attribute species = new Attribute("species", species_list);

        Attribute culmenLength = new Attribute("Culmen.Length..mm.");
        Attribute culmenDepth = new Attribute("Culmen.Depth..mm.");
        Attribute weight = new Attribute("Body.Mass..g.");
        Attribute N15 = new Attribute("Delta.15.N..o.oo.");
        Attribute C13 = new Attribute("Delta.13.C..o.oo.");


        ArrayList<String> sexes = new ArrayList<String>();
        sexes.add("MALE");
        sexes.add("FEMALE");
        // and then create the species attribute with the list
        Attribute sex = new Attribute("sex", sexes);

        attributeList.add(species);
        attributeList.add(culmenLength);
        attributeList.add(culmenDepth);
        attributeList.add(weight);
        attributeList.add(N15);
        attributeList.add(C13);
        attributeList.add(sex);
        return attributeList;
    }

    /**
     * createInstance uses the user-given data to create a weka instance.
     * @param attributeList
     *        an array list with the attributes
     * @return data -
     *         the user-given single case now as a weka instance
     */
    private Instances createInstances(ArrayList<Attribute> attributeList){
        Instances data = new Instances("SingleCaseData",attributeList,1);
        Instance inst = new DenseInstance(data.numAttributes());
        inst.setValue(attributeList.get(0), this.species);
        inst.setValue(attributeList.get(6), this.sex);
        for (int i = 1; i < attributeList.size()-1; i++) {
            inst.setValue(attributeList.get(i), this.doubleData.get(i-1));

        }
        data.add(inst);
        inst.setDataset(data);
        data.setClassIndex(data.numAttributes() - 1);
        return data;

    }

    /**
     * @param data
     *        a weka instance
     * @return predicted instance -
     *         the single case now predicted using the created model
     *
     * @throws IOException
     *         when model fails to load
     */
    private Instances classifyInstance(Instances data) throws IOException {
        WrapClassifier classifier = new WrapClassifier();
        Classifier model = classifier.loadClassifier();
        return classifier.classifyData(model, data);
    }
}
