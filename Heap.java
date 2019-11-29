import java.util.ArrayList;
import java.util.List;

/***
*       Class Heap is a collection of heap nodes. Heap Nodes
*       hold reference to tree nodes which inturn hold reference
*       to building objects. This is how they are thus, interconnected.
*/
public class Heap {

    // heap array
    private List<HeapNode> info =  new ArrayList<>();

    // function for pushing node into the heap
    public void push(TreeNode treeNode) {

        int end = info.size();
        HeapNode heapNode = new HeapNode(treeNode);
        info.add(heapNode);

        while (end!=0) {

            int prev = end;
            end = (end-1)/2;
            HeapNode node = info.get(end);
            if (node.greaterThan(heapNode)) {
                info.set(end, heapNode);
                info.set(prev, node);
            }
            else {
                break;
            }
        }
    }

    // function for popping tree node from heap
    public HeapNode pop() {

        HeapNode firstNode = info.get(0);
        HeapNode lastNode = info.get(info.size()-1);
        info.set(0,lastNode);
        info.remove(info.size()-1);

        int k = 0;
        while (true) {
            if ((2*k) + 2 > info.size()) {
                break;
            }
            else if ((2*k)+2 == info.size()) {
                HeapNode left = info.get((2*k)+1);

                if (lastNode.greaterThan(left)) {
                    info.set((2*k)+1, lastNode);
                    info.set(k, left);
                }
                break;
            }
            else {

                HeapNode left = info.get((2*k)+1);
                HeapNode right = info.get((2*k)+2);

                if (left.lessThan(right) && lastNode.greaterThan(left)) {
                    info.set((2*k)+1,lastNode);
                    info.set(k, left);
                    k= (2*k)+1;
                }
                else if(lastNode.greaterThan(right)){
                    info.set((2*k)+2, lastNode);
                    info.set(k, right);
                    k = (2*k) +2;
                }
                else {
                    break;
                }
            }
        }
        return firstNode;
    }

    /***** utility functions, getters and setter for the class *********/
    public boolean freeNode(HeapNode heapNode, RedBlackTree tree) {
        return tree.delete(heapNode.getTreeNode());

    }

    public int size() {
        return this.info.size();
    }

    public List<HeapNode> getInfo() {
        return info;
    }

    public void setInfo(List<HeapNode> info) {
        this.info = info;
    }

    public HeapNode getMinimum() {
        return info.get(0);
    }

    public boolean isEmpty() {
        return info.size() == 0;
    }

    // functions for printing nodes in heap
    public void printHeap() {

        System.out.println("--------------- printing heap----------------------------");
        System.out.print("[");
        for (HeapNode node:info) {
            System.out.print(node.getTreeNode().getBuilding().getNum()+", ");
        }
        System.out.print("]");
        System.out.println("\n");
    }
}
