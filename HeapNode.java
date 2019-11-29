/*
*   A heapnode is a node for the heap tree
*   It holds a reference of red black tree node
*   This is how a node from a red black tree corresponds
*   to a node in the heap
*/
public class HeapNode {

    // Red black tree node reference 
    private TreeNode treeNode;

    // initialize heap node
    public HeapNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    // helps increment time given to a particular building
    public boolean incrementTimeTaken() {
        try {
            int timeTakenByBuilding = this.getTreeNode().getBuilding().getTotalTimeTaken();
            this.getTreeNode().getBuilding().setTotalTimeTaken(timeTakenByBuilding+1);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // function for checking if the building we are working on right now complete
    public boolean isOver() {
        return this.treeNode.getBuilding().isFinished();
    }

    // get time consumed by a given building
    public int getTimeTaken() {
        return this.getTreeNode().getBuilding().getTotalTimeTaken();
    }

    /**** custom comparators for heapifying *******/
    public boolean greaterThan(HeapNode h) {
        
        int t1 = this.getTreeNode().getBuilding().getTotalTimeTaken();
        int t2 = h.getTreeNode().getBuilding().getTotalTimeTaken();

        if ( t1 == t2) {
            int num1 = this.getTreeNode().getBuilding().getNum();
            int num2 = h.getTreeNode().getBuilding().getNum();
            return num1 > num2;
        }
        else {
            return t1 > t2;
        }
    }
    
    public boolean lessThan(HeapNode h) {
        int t1 = this.getTreeNode().getBuilding().getTotalTimeTaken();
        int t2 = h.getTreeNode().getBuilding().getTotalTimeTaken();
        if (t1 == t2) {
            int num1 = this.getTreeNode().getBuilding().getNum();
            int num2 = h.getTreeNode().getBuilding().getNum();
            return num1 < num2;
        }
        else {
            return t1 < t2;
        }
    }

    // function for getting tree node reference
    public TreeNode getTreeNode() {
        return treeNode;
    }

    // function for getting treenode reference
    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }
}
