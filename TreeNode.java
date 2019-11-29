/**
 *  Defining a red black node in java
 *  A red black node in java holds necessary
 *  attributes of a red black node.
 */
class TreeNode {

    public static final int Red = 0;
    public static final int Black = 1;
    public Building building;
    public TreeNode parent, left, right;
    public int leftCount;
    public int rightCount;
    public int color;

    // TreeNode constructor
    public TreeNode(Building building) {
        if (building != null) {
            this.building = building;
        }
        else{
            this.building = Building.getNullBuilding();
        }
        leftCount = rightCount = 0;
        parent = left = right = null;
        this.color = Black;
    }

    // function to check if 2 treenodes are equal
    public boolean equals(TreeNode node) {
        return this.building.getNum() == node.building.getNum();
    }

    // simple comparator for red black node based on building number
    public boolean lessThan(TreeNode node) {
        return this.getBuilding().getNum() > node.getBuilding().getNum();
    }

    // simple in range function for a given treenode
    public boolean inRange(int min, int max) {
        int val = this.building.getNum();
        if (min <= val && max >= val) {
            return true;
        }
        return false;
    }

    /*** important utility functions, getters and setters ***/
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public int getRightCount() {
        return rightCount;
    }

    public void setRightCount(int rightCount) {
        this.rightCount = rightCount;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    // print treenode function for debugging
    public void printNodeInformation() {
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("Node Parent -> " + Integer.toString(this.parent.getBuilding().getNum()));
        String color = this.color == TreeNode.Black ? "Black" : "Red";
        System.out.println("Node Color -> " + color );
        this.building.printData();
        System.out.println("------------------------------------------------------------------");
    }
}