package nl.bioninf;

import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class SingleInstanceClassifier{

    public static void main(String[] args) {
        SingleInstanceClassifier attempt = new SingleInstanceClassifier();
        attempt.gatherData();
    }

    protected Instances gatherData() {
      // This is testing data
        ArrayList<Double> args = new ArrayList<Double>() {
            {
                add(53.0);
                add(65.7);
                add(1456.0);
                add(55.54);
                add(87.32);
            }};
        ArrayList<Attribute> pass_on = createAttributes("Gentoo", "FEMALE", args);
        Instances data = createInstances(pass_on,"Gentoo", "FEMALE", args);
        System.out.println(data);
        return data;

    }


    protected ArrayList<Attribute> createAttributes(String penguin_species, String penguin_sex, List<Double> singleCaseValues) {

        ArrayList<Attribute> attributeList = new ArrayList<Attribute>(3);

        // Create a list with the given values for the species
        ArrayList<String> species_list = new ArrayList<String>();
        species_list.add("Adelie");
        species_list.add("Chinstrap");
        species_list.add("Gentoo");
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

    private Instances createInstances(ArrayList<Attribute> attributeList, String penguin_species, String penguin_sex,
                                      List<Double> singleCaseValues){
        Instances data = new Instances("SingleCaseData",attributeList,1);
        Instance inst = new DenseInstance(data.numAttributes());
        inst.setValue(attributeList.get(0), penguin_species);
        inst.setValue(attributeList.get(6), penguin_sex);
        for (int i = 1; i < attributeList.size()-1; i++) {
            inst.setValue(attributeList.get(i), singleCaseValues.get(i-1));

        }
        data.add(inst);
        inst.setDataset(data);
        data.setClassIndex(data.numAttributes() - 1);
        return data;

    }



}