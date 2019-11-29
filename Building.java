/****
*   Building class is the core of a TreeNode.
*   A treeNode is then passed as a reference to a heapNode.
*/
public class Building {
    
    //    buildingNum: unique integer identifier for each building.
    private int num;

    //    executed_time: total number of days spent so far on this building.
    private int timeOfExecution;

    //    total_time: the total number of days needed to complete the construction of the building.
    private int totalTimeNeeded;

    // total time taken until now
    private int totalTimeTaken;

    // constructor
    Building(int num, int timeOfExecution, int totalTimeNeeded) {
        this.num = num;
        this.timeOfExecution = timeOfExecution;
        this.totalTimeTaken = 0;
        this.totalTimeTaken = 0;
        this.totalTimeNeeded = totalTimeNeeded;
    }

    // function for creating a null building for creating a null tree node
    public static  Building getNullBuilding() {
        return new Building(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    /**** important getter, setters and utility functions *****/
    public boolean isFinished() {
        return this.totalTimeTaken == this.totalTimeNeeded;
    }

    public int getTotalTimeTaken() {
        return totalTimeTaken;
    }

    public void setTotalTimeTaken(int totalTimeTaken) {
        this.totalTimeTaken = totalTimeTaken;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTimeOfExecution() {
        return timeOfExecution;
    }

    public void setTimeOfExecution(int timeOfExecution) {
        this.timeOfExecution = timeOfExecution;
    }

    public int getTotalTimeNeeded() {
        return totalTimeNeeded;
    }

    public void setTotalTimeNeeded(int totalTimeNeeded) {
        this.totalTimeNeeded = totalTimeNeeded;
    }

    // function for printing building data for debugging purposes
    public void printData() {
        System.out.println("Building Number    -> " + Integer.toString(this.num));
        System.out.println("Time of Execution  -> " + Integer.toString(this.timeOfExecution));
        System.out.println("Total Time Taken   -> " + Integer.toString((this.totalTimeTaken)));
        System.out.println("Total Time Needed  -> " + Integer.toString(this.totalTimeNeeded));
    }
}
