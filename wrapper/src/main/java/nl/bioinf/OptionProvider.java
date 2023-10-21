package nl.bioinf;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;

public class OptionProvider {
    private static final String HELP = "help";
    private static final String FILEPATH = "filename";

    final List<Double> SingleCaseDoubles = new ArrayList<Double>();
    private final String[] clArguments;
    private Options options;
    private CommandLine commandLine;

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
        Option fileOption = new Option("f", FILEPATH, false,"Requests the path to a batch file, this file has to be arff.");
        Option SingleCase = new Option("s", "SINGLECASE", false, "Requests the information needed to classify a single case.");
        SingleCase.setArgs(6);
        SingleCase.setValueSeparator(',');

        options.addOption(helpOption);
        options.addOption(fileOption);
        options.addOption(SingleCase);
    }

    private void processCommandLine(){
        CommandLineParser parser = new DefaultParser();
        System.out.println(this.options);
        try {
            this.commandLine = parser.parse(this.options, this.clArguments);
            if (this.commandLine.hasOption("s")){
                String[] singleCaseValues = commandLine.getOptionValues("s");
                turnToDouble(singleCaseValues);
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean requestHelp() {
        return this.commandLine.hasOption(HELP);
    }

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("WrapClassifier", options);
    }

    private void turnToDouble(String[] sOptions){
        for (String item:sOptions) {
            SingleCaseDoubles.add(Double.parseDouble(item));
        }
    }

}
