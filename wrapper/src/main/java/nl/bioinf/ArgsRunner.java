package nl.bioinf;

import java.util.Arrays;

/**
 * Main class of the package. Parses the user arguments using Apache CLI.
 * @author Margriet
 */
public class ArgsRunner {

    /**
     * @param args
     *       the command line arguments given by user
     */
    public static void main(final String[] args) {
        try {
            OptionProvider op = new OptionProvider(args);
            if (op.requestHelp() || op.clArguments.length == 0) {
                op.printHelp();
                return;
            }
            MessageController controller = new MessageController(op);
            controller.start();
        } catch (IllegalStateException e) {
            System.err.println("Something went wrong while processing your command line \""
                    + Arrays.toString(args) + "\"");
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
            OptionProvider op = new OptionProvider(new String[]{});
            op.printHelp();
        }
    }
}
