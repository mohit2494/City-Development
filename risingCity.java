
// import required utilities
import java.util.ArrayList;

/*
*   This class represents the driving class of the project.
*   1. We first initialize a Min Heap, a Red Black Tree and a File Helper
*   2. We then read a command from the file and wait for its execution.
*   3. We pop a node from our Min Heap and keep working on it for 5 days, unless a command interrupts.
*   4. We stop when our commands are finished and our heap is empty showing all buildings are complete.
*/

public class risingCity {

    // output file name - doesn't change thus static
    private static String outputFileName =  "output_file.txt";

    // main thread entry into the project
    public static void main(String[] args) {

        // get input file name from the command line
        String inputFileName = args[0];

        // initialize a read black tree
        RedBlackTree tree = new RedBlackTree();

        // initialize a min heap
        Heap heap = new Heap();

        // we use heap node to insert into the heap
        HeapNode heapNode = null;

        // filehelper object helps read from / output to a file
        FileHelper filehelper = null;

        // initialize filehelper
        try {
            filehelper = new FileHelper(inputFileName, outputFileName);
        }
        catch (Exception e) {
            System.out.println("Error initializing input file. Please check if input file present");
            System.exit(0);
        }

        /*** initliazing variables ***/

        // global timer
        int timer = 0;

        // continuous working day
        int continuousDay = 0;

        // boolean if commands finished
        boolean commandsFinished = false;

        // boolean whether next command is to be fetched or not
        boolean fetchNextCommand = true;

        // initialize command
        Command command = null;

        // keep going until all commands are read
        while (true) {

            // initializing work cycle
            if (timer!=0){
                if (heapNode == null && !heap.isEmpty()) {
                    heapNode = heap.pop();
                }

                if (heapNode != null) {
                    heapNode.incrementTimeTaken();
                    continuousDay += 1;
                }
            }

            // fetch next command by checking boolean
            if (fetchNextCommand && !commandsFinished) {
                command = filehelper.getNextCommand();
                fetchNextCommand = false;
                if (command == null) {
                    commandsFinished = true;
                }
            }

            // check if the current command in the buffer is to be executed at this timer
            if ((command != null) && command.getTimeOfExecution() == timer) {
                
                // if insert command, then create a new node
                if (command.getType() == Command.INSERT_COMMAND) {
                    // create a node
                    Building building = new Building(command.getBuildingNum1(), command.getTimeOfExecution(), command.getTotalExecutionTime());
                    TreeNode node = new TreeNode(building);
                    tree.insertNode(node);
                    heap.push(node);
                }

                // if print building, then search and print
                else if(command.getType() == Command.PRINT_BUILDING) {
                    // will have only one number as simple print command
                    int searchKey = command.getBuildingNum1();
                    TreeNode node = new TreeNode(Building.getNullBuilding());
                    node.building.setNum(searchKey);
                    TreeNode searched = tree.search(node);
                    filehelper.printBuilding(searched);
                }


                // if print building in range
                else if(command.getType() == Command.PRINT_BUILDING_IN_RANGE) {
                    ArrayList<TreeNode> nodeInRanges = tree.searchInRange(command.getBuildingNum1(), command.getBuildingNum2());
                    filehelper.printBuildingInRange(nodeInRanges);
                }
                fetchNextCommand = true;
            }

            // do heap operations here
            if (heapNode != null) {

                // if current building's work is finished
                if (heapNode.isOver()) {
                    filehelper.printHeapNode(heapNode, timer);
                    heap.freeNode(heapNode, tree);
                    heapNode = null;
                    continuousDay = 0;
                }

                // if we have worked for 5 days continuously
                else if(continuousDay == 5) {
                    heap.push(heapNode.getTreeNode());
                    heapNode = null;
                    continuousDay = 0;
                }

            }
            timer += 1;

            // if commands are finished and there's no current building and the heap is also empty
            // we are done
            if (commandsFinished && heapNode == null && heap.isEmpty()){
                break;
            }
        }
        // close read and write files
        filehelper.close();
    }
}
