package nl.bioinf;

import org.apache.commons.cli.*;

public class OptionProvider {
    private static final String HELP = "help";
    private static final String FILEPATH = "filename";
    private static final String SINGLECASE = "SingleCase";
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
        Option SingleCase = new Option("s", SINGLECASE, false, "Requests the information needed to classify a single case.");
        SingleCase.setArgs(6);
        SingleCase.setValueSeparator(',');

        options.addOption(helpOption);
        options.addOption(fileOption);
        options.addOption(SingleCase);
    }

    private void processCommandLine(){
        CommandLineParser parser = new DefaultParser();
        try {
            this.commandLine = parser.parse(this.options, this.clArguments);
            if (!this.commandLine.hasOption("f") || this.commandLine.hasOption("s")){
               throw new IllegalArgumentException("Please use either a batch file or process a single penguin");
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

}
