/**
*       Command class helps read and initialize commands
*       from the input file. Commands are read one by
*       one as per the requirement of the driver class
*/

public class Command {

    // strings for input commmand comparison
    public static final String INSERT = "insert";
    public static final String PRINT = "PrintBuilding";

    // command type constants
    private static int UNKNOWN_COMMAND = -1;
    public static final int INSERT_COMMAND = 0;
    public static final int PRINT_BUILDING = 1;
    public static final int PRINT_BUILDING_IN_RANGE = 2;

    /**** important class variables ***/
    private int type = -1;
    private int timeOfExecution;
    private int buildingNum1;
    private int buildingNum2;
    private int totalExecutionTime;
    private String log = "";
    private boolean error = false;

    
    // Command constructor
    public Command(String line) {

        String[] arr = line.split(":");
        try {
            this.timeOfExecution = Integer.parseInt(arr[0]);
        }
        catch (Exception e) {
            // TODO:: handle the case when its not an integer
        }

        // lets initialize command and its variables
        String[] commandArray = line.split("[:(,]");

        // remove last bracket
        String temp = commandArray[commandArray.length-1];
        commandArray[commandArray.length-1] = temp.substring(0,temp.length()-1);

        // first parameter is execution time
        this.timeOfExecution = Integer.parseInt(commandArray[0]);

        if (commandArray[1].trim().toLowerCase().equals(INSERT)) {
            this.type = Command.INSERT_COMMAND;
            this.buildingNum1 = Integer.parseInt(commandArray[2]);
            this.totalExecutionTime = Integer.parseInt((commandArray[3]));
        }
        else {

            this.type = Command.UNKNOWN_COMMAND;
            this.buildingNum1 = Integer.parseInt(commandArray[2]);
            if (commandArray.length == 3) {
                this.type = Command.PRINT_BUILDING;
                this.buildingNum1 = Integer.parseInt(commandArray[2]);
            }
            else {
                this.type = Command.PRINT_BUILDING_IN_RANGE;
                this.buildingNum1 = Integer.parseInt((commandArray[2]));
                this.buildingNum2 = Integer.parseInt((commandArray[3]));
            }
        }
    }

    /**** important getter, setter and utility functions for Command class ****/
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBuildingNum1() {
        return buildingNum1;
    }

    public void setBuildingNum1(int buildingNum1) {
        this.buildingNum1 = buildingNum1;
    }

    public int getTotalExecutionTime() {
        return totalExecutionTime;
    }

    public void setTotalExecutionTime(int totalExecutionTime) {
        this.totalExecutionTime = totalExecutionTime;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getTimeOfExecution() {
        return timeOfExecution;
    }

    public void setTimeOfExecution(int timeOfExecution) {
        this.timeOfExecution = timeOfExecution;
    }

    public int getBuildingNum2() {
        return buildingNum2;
    }

    public void setBuildingNum2(int buildingNum2) {
        this.buildingNum2 = buildingNum2;
    }
}
