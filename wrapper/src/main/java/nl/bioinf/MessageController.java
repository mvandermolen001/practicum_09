package nl.bioinf;

/**
 * MessageController class, controls flow of program and throws messages to give user feedback on process
 * @author Margriet
 */
public class MessageController {
    private final OptionProvider optionProvider;

    /**
     * Constructor for MessageController, it needs one instance of the optionProvider class.
     * @param optionProvider
     *        an instance of the optionProvider class
     */
    public MessageController(OptionProvider optionProvider) {
        this.optionProvider = optionProvider;
    }

    /**
     * start method to start the process. Prints lines to ensure user gets feedback on the program.
     */
    public void start() {
        if (optionProvider == null) {
            throw new IllegalStateException("Please make sure to provide an optionProvider.");
        }
        if (optionProvider.getFilepath() == null){
            System.out.println("No filepath provided, moving to attempt single instance classifications...");
        }
        else{
            System.out.println("Predicting data...");
            WrapClassifier classifier = new WrapClassifier(optionProvider.getFilepath());
            if (optionProvider.hasWrite()) {
                PredictionWriter writer = new PredictionWriter(classifier.start(), "predicted_data.csv");
                writer.writeToFile();
                System.out.println("Successfully wrote data to file.");
            }
            else{
                System.out.println(classifier.start());
            }
        }
        if (optionProvider.getSingleCaseDoubles().isEmpty()){
            System.out.println("No single case arguments provided");
        }
        else{
            System.out.println("Predicting single instance...");
            SingleInstanceClassifier singleClassifier = new SingleInstanceClassifier(optionProvider.getSingleCaseDoubles(),
                    optionProvider.getSpecies(), optionProvider.getSex());
            if(optionProvider.hasWrite()){
                PredictionWriter writer = new PredictionWriter(singleClassifier.start(), "predicted_instance.csv");
                writer.writeToFile();
                System.out.println("Successfully wrote instance to file.");
            }
            else {
                System.out.println(singleClassifier.start());
            }
        }
        System.out.println("Task completed.");
    }
}
