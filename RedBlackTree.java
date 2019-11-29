import java.util.ArrayList;

/**
* A red black tree holds collection of red black tree nodes
* red black tree nodes further hold reference to a building objects
*/

public class RedBlackTree {

    // defining a null node
    private TreeNode NULL  = new TreeNode(Building.getNullBuilding());

    // defining root of the tree
    private TreeNode root = NULL;

    // constructor for red black tree
    public RedBlackTree() {
        root.left = this.NULL;
        root.right = this.NULL;
        root.parent = this.NULL;
    }

    // function for checking if TreeNode is null or not
    private boolean isNodeNull(TreeNode node) {
        return node.equals(this.NULL);
    }

    // function for inserting a new node into the tree
    public void insertNode(TreeNode node) {
        TreeNode newNode = node;
        TreeNode falloff = new TreeNode(Building.getNullBuilding());
        TreeNode current = root;

        while(!isNodeNull(current)) {
                falloff = current;

                if(newNode.lessThan(current)) {
                    current.setRightCount(current.getRightCount()+1);
                    current = current.right;
                }
                else {
                    current.setLeftCount((current.getLeftCount()+1));
                    current = current.left;
                }
        }

        newNode.setParent(falloff);

        // case when no node in the tree
        if (isNodeNull(falloff)) {
            root = newNode;
        }
        else if(newNode.lessThan(falloff)) {
            falloff.right = newNode;
        }
        else {
            falloff.left = newNode;
        }

        newNode.setLeft(NULL);
        newNode.setRight(NULL);
        newNode.setColor(TreeNode.Red);

        // call fix tree post insert
        fixTreePostInsertion(newNode);
    }


    // function for fixing red black tree post insertion
    private void fixTreePostInsertion(TreeNode newNode) {

        TreeNode uncle = NULL;

        while(newNode.parent.getColor() == TreeNode.Red) {

            // newNode's parent is left child of its parent
            if (newNode.parent == newNode.parent.parent.left) {

                uncle = newNode.parent.parent.right;

                // case 1 : if uncle is red, recolor
                if (uncle.color == TreeNode.Red) {
                    newNode.parent.color = TreeNode.Black;
                    uncle.color = TreeNode.Black;
                    newNode.parent.parent.color = TreeNode.Red;
                    newNode = newNode.parent.parent;
                }

                // case 2 : if uncle is black and new node is right child
                else if (newNode == newNode.parent.right) {
                    newNode = newNode.parent;
                    rotateLeft(newNode);
                }

                // case 3: if uncle is black and new node is left child
                else {
                    newNode.parent.color = TreeNode.Black;
                    newNode.parent.parent.color = TreeNode.Red;
                    rotateRight(newNode.parent.parent);
                }

            }
            // newNode's parent is right child of its parent
            else {

                uncle = newNode.parent.parent.left;

                // case 1: if uncle is red, recolor
                if (uncle.color == TreeNode.Red) {
                    newNode.parent.color = TreeNode.Black;
                    uncle.color = TreeNode.Black;
                    newNode.parent.parent.color = TreeNode.Red;
                    newNode = newNode.parent.parent;
                }

                // case 2 : if uncle is black and new node is right child
                else if(newNode == newNode.parent.left) {
                    newNode = newNode.parent;
                    rotateRight(newNode);
                }

                // case 3 : if uncle is black and new node is left child
                else {
                    newNode.parent.color = TreeNode.Black;
                    newNode.parent.parent.color = TreeNode.Red;
                    rotateLeft(newNode.parent.parent);
                }
            }
        }
        root.color = TreeNode.Black;
    }

    // function for left rotating of red black tree around a given node
    private void rotateLeft(TreeNode node) {

        TreeNode rightSubtree = node.right;
        node.right = rightSubtree.left;

        if (!isNodeNull(rightSubtree.left)) {
            rightSubtree.left.parent = node;
        }

        rightSubtree.parent = node.parent;

        if (isNodeNull(node.parent)) {
            root = rightSubtree;
        }

        else  if (node.parent.left == node){
            node.parent.left = rightSubtree;
        }

        else {
            node.parent.right = rightSubtree;
        }

        rightSubtree.left = node;
        node.parent = rightSubtree;
    }

    // function for right rotating of red black tree around a given node
    private void rotateRight(TreeNode node) {

        TreeNode leftSubtree = node.left;
        node.left = leftSubtree.right;

        if (!isNodeNull(leftSubtree.right)) {
            leftSubtree.right.parent = node;
        }

        leftSubtree.parent = node.parent;

        if(isNodeNull(node.parent)) {
            root = leftSubtree;
        }

        else if(node.parent.right == node) {
            node.parent.right = leftSubtree;
        }

        else {
            node.parent.left = leftSubtree;
        }

        leftSubtree.right = node;
        node.parent = leftSubtree;
    }

    // Red black tree deletion
    public boolean delete(TreeNode node) {

        TreeNode zed = search(node);
        TreeNode ex = new TreeNode(Building.getNullBuilding());
        TreeNode why = new TreeNode(Building.getNullBuilding());

        if (isNodeNull(zed.left) || isNodeNull(zed.right)) {
            why = zed;
        }

        else why = findSuccessor(zed);

        if(!isNodeNull(why.left)) {
            ex = why.left;
        }
        else {
            ex = why.right;
        }

        ex.parent = why.parent;

        if (isNodeNull(why.parent)) {
            root = ex;
        }

        else if (!isNodeNull(why.parent.left) && why.parent.left == why) {
            why.parent.left = ex;
        }
        else if (!isNodeNull(why.parent.right) && why.parent.right == why) {
            why.parent.right = ex;
        }

        if (!why.equals(zed)) {
            zed.building = why.building;
        }

        if (why.color == TreeNode.Black) {
            fixTreePostDeletion(ex);
        }
        return true;
    }

    // fix red black tree post deletion
    private void fixTreePostDeletion(TreeNode ex) {

        TreeNode doublew;
        int buildnum = ex.building.getNum();
        while ((!ex.equals(root)) && ex.color == TreeNode.Black) {

            // if ex is left child
            if (ex.equals(ex.parent.left)) {

                doublew = ex.parent.right;

                // case 1
                if (doublew.color == TreeNode.Red) {
                        doublew.color = TreeNode.Black;
                        ex.parent.color = TreeNode.Red;
                        rotateLeft(ex.parent);
                        doublew = ex.parent.right;
                }

                // case 2
                else if (doublew.left.color == TreeNode.Black && doublew.right.color == TreeNode.Black) {
                    doublew.color = TreeNode.Red;
                    ex = ex.parent;
                }
                else {
                        // case 3
                        if (doublew.right.color == TreeNode.Black) {
                            doublew.left.color = TreeNode.Black;
                            doublew.color = TreeNode.Red;
                            rotateRight(doublew);
                            doublew = ex.parent.right;
                        }

                        // case 4 :
                        doublew.color = ex.parent.color;
                        ex.parent.color = TreeNode.Black;
                        doublew.right.color = TreeNode.Black;
                        rotateLeft(ex.parent);
                        ex = root;
                }
            }

            // if ex is right child
            else {
                    doublew = ex.parent.left;

                    // case 1
                    if (doublew.color == TreeNode.Red) {
                        doublew.color  = TreeNode.Black;
                        ex.parent.color = TreeNode.Red;
                        rotateRight(ex.parent);
                        doublew = ex.parent.left;
                    }

                    // case 2
                    else if (doublew.right.color  == TreeNode.Black &&  doublew.left.color == TreeNode.Black) {
                        doublew.color = TreeNode.Red;
                        ex = ex.parent;
                    }
                    else {
                            // case 3
                            if (doublew.left.color == TreeNode.Black) {
                                    doublew.right.color = TreeNode.Black;
                                    doublew.color = TreeNode.Red;
                                    rotateLeft(doublew);
                                    doublew = ex.parent.left;
                            }

                            // case 4
                            doublew.color = ex.parent.color;
                            ex.parent.color = TreeNode.Black;
                            doublew.left.color = TreeNode.Black;
                            rotateRight(ex.parent);
                            ex = root;

                    }
            }
        }

        ex.color = TreeNode.Black;
    }

    // function for searching red black tree for a given node
    public TreeNode search(TreeNode node) {

        TreeNode curr = root;

        while(!isNodeNull(curr)) {

            if (curr.equals(node)) {
                return curr;
            }

            else if(curr.lessThan(node)) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }

        return null;
    }

    // function for searching nodes in range in a red black tree
    public ArrayList<TreeNode> searchInRange(int min , int max) {
        ArrayList<TreeNode> nodesInRange = new ArrayList<>();
        TreeNode curr = root;
        return searchHelper(min, max, curr, nodesInRange);
    }

    // recursive search helper for searching nodes in range
    public ArrayList<TreeNode> searchHelper(int min, int max, TreeNode node, ArrayList nodesInRange) {

        if (!node.equals(this.NULL)) {

            if (!isNodeNull(node.left) && node.left.inRange(min,max)) {
                searchHelper(min, max, node.left, nodesInRange);
            }
            if (node.inRange(min, max)) {
                nodesInRange.add(node);
            }
            if (!isNodeNull(node.right) && node.right.inRange(min,max)) {
                searchHelper(min, max, node.right, nodesInRange);
            }
        }

        return nodesInRange;
    }

    // function for finding inorder successor during deletion of a red black node
    private TreeNode findSuccessor(TreeNode ex) {

        if (!isNodeNull(ex.left)) {
            return  findMinimumOfRightSubtree(ex.right);
        }

        TreeNode why =  ex.parent;

        while(!isNodeNull(why) && ex.equals(why.right)) {
            ex = why;
            why = why.parent;
        }

        return why;
    }

    // function for finding minimum of right subtree
    private TreeNode findMinimumOfRightSubtree(TreeNode node) {
        while (!isNodeNull(node.left)) {
            node = node.left;
        }
        return node;
    }

    // print tree in preorder mode
    public void printPreorder(TreeNode r){
        if (!r.equals(this.NULL)) {
            printPreorder(r.left);
            r.printNodeInformation();
            printPreorder(r.right);
        }
    }

    // get root of red black tree
    public TreeNode getRoot() {
        return root;
    }

    // set root of red black tree
    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
