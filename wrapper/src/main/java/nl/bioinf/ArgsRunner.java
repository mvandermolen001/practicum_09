package nl.bioinf;

import java.util.Arrays;

public class ArgsRunner {

    public static void main(final String[] args) {
        try {
            OptionProvider op = new OptionProvider(args);
            if (op.requestHelp()) {
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
