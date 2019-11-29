import java.io.*;
import java.util.ArrayList;

/**
*   FileHelper class sits at the heart of the file 
*   handling logic of this project. FileHelper helps
*   read and write to input/output files respectively.
*/ 
public class FileHelper {

    /**** important filehelper variables ****/
    private static final String prefix = "/";
    private String inputFileName,outputFileName;
    private BufferedReader fileReader;
    private PrintWriter fileWriter;
    private String error;
    private boolean hasError;
    private String directory;
    private static int lineCount;

    // class constructor
    FileHelper(String inputFileName, String outputFileName){
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        try
        {
            this.directory = System.getProperty("user.dir");
            this.fileReader = new BufferedReader((new FileReader((this.directory +prefix+this.inputFileName))));
            this.fileWriter = new PrintWriter(this.directory+prefix+this.outputFileName);
        }
        catch (Exception e) {
           	System.out.println("error initializing input file, exiting the program, please try again");
		System.exit(0);
        }
    }

    // function for getting next command from file
    public Command getNextCommand() {

        String line = null;
        try {
            line = this.fileReader.readLine();
            if (line == null) {
                return null;
            }
        }
        catch (Exception e) {
	    System.out.println("error reading from input file, please ensure input file exists!");
	    System.exit(0);
        }

        if (line == null || line.trim().equals("")) {
            return null;
        }

        // valid line, let us keep the count
        this.lineCount += 1;

        Command command  = null;
        try {
            /* send line to command object for parsing */
            command = new Command(line);
        }
        catch (Exception e) {
            // check if there was any error
            System.out.print(command.getLog());
            e.printStackTrace();
        }

        if (command != null) {
            return command;
        }

        return null;
    }

    // function for printing search result into the file
    public void printBuilding(TreeNode searched) {
        if (searched == null) {
            this.println("(0,0,0)");
        }
        else {
            String printItem = "("   + Integer.toString(searched.getBuilding().getNum())+
                    ","  + Integer.toString(searched.getBuilding().getTotalTimeTaken())+
                    ","  + Integer.toString(searched.getBuilding().getTotalTimeNeeded())+")";
            this.println(printItem);
        }
    }

    // function for printing building in range
    public void printBuildingInRange(ArrayList<TreeNode> nodeInRanges) {
        if (nodeInRanges.size() == 0) {
            this.println("(0,0,0)");
        }
        else {
            for (int i = 0; i < nodeInRanges.size(); i++) {
                TreeNode node = nodeInRanges.get(i);
                String printItem = "(" + Integer.toString(node.getBuilding().getNum()) +
                        ","  + Integer.toString(node.getBuilding().getTotalTimeTaken())+
                        ","  + Integer.toString(node.getBuilding().getTotalTimeNeeded())+")";
                this.print(printItem);
                if (i+1 != nodeInRanges.size()) {
                    this.print(",");
                }
            }
            this.println("");
        }
    }

    // fucntion for printing building which completed its work
    public void printHeapNode(HeapNode heapNode, int timer) {
        String printItem = "(" + Integer.toString(heapNode.getTreeNode().getBuilding().getNum()) + ","+ Integer.toString(timer)+")" ;
        this.println(printItem);
    }

    /******** important getter and setter function *************/
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public void println(String s) {
        this.fileWriter.println(s);
    }

    public void print(String s) {
        this.fileWriter.print(s);
    }

    public void close() {
        this.fileWriter.close();
    }
}
