package nl.bioinf;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;

/**
 * OptionProvider class for parsing user-given arguments using Apache CLI.
 * @author Margriet
 */
public class OptionProvider {
    private static final String HELP = "help";

    final List<Double> SingleCaseDoubles = new ArrayList<>();

    private final String[] clArguments;
    private Options options;
    private CommandLine commandLine;

    private String filepath;
    private String species;
    private String sex;


    /**
     * Constructor to create clArguments with the given user arguments.
     * @param args
     *      user arguments
     */
    public OptionProvider(final String[] args){
        this.clArguments = args;
        start();
    }

    /**
     * start method to control flow of process
     */
    private void start() {
        buildOptions();
        processCommandLine();
    }

    /**
     * buildOptions methods, responsible for building the options that the user can provide on the command line.
     */
    private void buildOptions(){
        this.options = new Options();
        Option helpOption = new Option("h", HELP, false, "Prints this message");
        Option fileOption = new Option("f", "FILEPATH", true,"Requests the path to a batch file, this file has to be arff.");
        Option SingleCase = new Option("s", "SINGLECASE", true, "Requests the" +
                " information needed to classify a single case. Fill in your values and separate them with a ','." +
                "Please make sure the argument uses the following order: Species, culmen depth, culmen length, bodymass," +
                "delta N15, delta N13, Sex");
        Option writeOption = new Option("w","write", false, "If option given, the result will be written to a file");
        SingleCase.setArgs(7);
        SingleCase.setValueSeparator(',');

        options.addOption(helpOption);
        options.addOption(fileOption);
        options.addOption(SingleCase);
        options.addOption(writeOption);
    }

    /**
     * processCommandLine method, responsible for processing the given user arguments so that they are ready to be used
     * in the future steps.
     */
    private void processCommandLine(){
        CommandLineParser parser = new DefaultParser();
        try {
            this.commandLine = parser.parse(this.options, this.clArguments);
            if (this.commandLine.hasOption("s")){
                List<String> singlecaseString = List.of(commandLine.getOptionValues("s"));
                turnToDouble(singlecaseString.subList(1, 6));
                this.species = singlecaseString.get(0);
                this.sex = singlecaseString.get(6);
            }
            if (this.commandLine.hasOption("f")){
                this.filepath = commandLine.getOptionValue("f");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * requestHelp checks whether help is requested
     * @return boolean
     *         true or false value depended on if the user has provided the help option
     */
    public boolean requestHelp() {
        return this.commandLine.hasOption(HELP);
    }

    /**
     * hasWrite checks whether the write option is provided
     * @return booelean
     *         true or false value depended on if the user has provided the write option
     */
    public boolean hasWrite(){
        return this.commandLine.hasOption("w");
    }

    /**
     * printHelp method for formatting the help option.
     */
    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("WrapClassifier", options);
    }

    /**
     * turnToDouble turns string to double values.
     * @param sOptions
     *        A sublist of the singlecase arguments that need to be doubles.
     */
    private void turnToDouble(List<String> sOptions){
        for (String item:sOptions) {
            SingleCaseDoubles.add(Double.parseDouble(item));
        }
    }

    /**
     * getSingleCaseDoubles returns the singleCaseDoubles list
     * @return an ArrayList with doubles
     */
    public ArrayList<Double> getSingleCaseDoubles() {
        return (ArrayList<Double>) SingleCaseDoubles;
    }

    /**
     * getFilePath gathers the given filepath
     * @return filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * returns the user given species of penguin
     * @return species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * returns the user given sex of the penguin
     * @return sex
     */
    public String getSex() {
        return sex;
    }

}
