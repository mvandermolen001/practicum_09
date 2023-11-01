package nl.bioinf;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;

public class OptionProvider {
    private static final String HELP = "help";

    final List<Double> SingleCaseDoubles = new ArrayList<>();

    private final String[] clArguments;
    private Options options;
    private CommandLine commandLine;

    private String filepath;
    private String species;
    private String sex;


    public OptionProvider(final String[] args){
        this.clArguments = args;
        start();
    }

    private void start() {
        buildOptions();
        processCommandLine();
    }

    private void buildOptions(){
        this.options = new Options();
        Option helpOption = new Option("h", HELP, false, "Prints this message");
        Option fileOption = new Option("f", "FILEPATH", true,"Requests the path to a batch file, this file has to be arff.");
        Option SingleCase = new Option("s", "SINGLECASE", true, "Requests the" +
                " information needed to classify a single case. Fill in your values and separate them with a ','." +
                "Please make sure the argument uses the following order: Species, culmen depth, culmen length, bodymass," +
                "delta N15, delta N13, Sex");
        Option writeOption = new Option("w","write", false, "If option given, the result will be written to a file");
        SingleCase.setArgs(8);
        SingleCase.setValueSeparator(',');

        options.addOption(helpOption);
        options.addOption(fileOption);
        options.addOption(SingleCase);
        options.addOption(writeOption);
    }

    private void processCommandLine(){
        CommandLineParser parser = new DefaultParser();
        try {
            this.commandLine = parser.parse(this.options, this.clArguments);
            if (this.commandLine.hasOption("s")){
                List<String> singlecaseString = List.of(commandLine.getOptionValues("s"));
                turnToDouble(singlecaseString.subList(1, 6));
                this.species = singlecaseString.get(0);
                this.sex = singlecaseString.get(7);
            }
            if (this.commandLine.hasOption("f")){
                this.filepath = commandLine.getOptionValue("f");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean requestHelp() {
        return this.commandLine.hasOption(HELP);
    }

    public boolean hasWrite(){
        return this.commandLine.hasOption("w");
    }

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("WrapClassifier", options);
    }

    private void turnToDouble(List<String> sOptions){
        for (String item:sOptions) {
            SingleCaseDoubles.add(Double.parseDouble(item));
        }
    }

    public ArrayList<Double> getSingleCaseDoubles() {
        return (ArrayList<Double>) SingleCaseDoubles;
    }

    public String getFilepath() {
        return filepath;
    }

    public String getSpecies() {
        return species;
    }

    public String getSex() {
        return sex;
    }
}
