import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * This class is implement the balanced search tree data structure
 */
public class myBst {

    private class Node {
        Node left, right;
        int value;

        Node(int value) {
            this.value = value;
        }

    }
    //construct binary search tree from an array

    public Node constructBST(int[] arr) {
        Node root = new Node(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            constructBST(root, arr[i]);
        }
        return root;
    }

    private void constructBST(Node root, int i) {
        if (root.value > i) {
            if (root.left == null) {
                root.left = new Node(i);
            } else {
                constructBST(root.left, i);
            }
        } else {
            if (root.right == null) {
                root.right = new Node(i);
            } else {
                constructBST(root.right, i);
            }
        }

    }

    private class ArrayNode implements Comparable<ArrayNode> {
        int row, col, val;

        ArrayNode(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

        @Override
        public int compareTo(ArrayNode o) {
            return this.val - o.val;
        }
    }

    /**
     * @param array: a 2D array
     * @return: the minimum difference
     */
    public int minimumDifference(int[][] array) {
        // Write your code here
        if (array == null) {
            return 0;
        }

        int n = array.length;
        int m = array[0].length;
        int minDiff = Integer.MAX_VALUE;
        PriorityQueue<ArrayNode> minQueue = new PriorityQueue<>();
        //init priority queue
        ArrayNode initMax = new ArrayNode(-1, -1, Integer.MIN_VALUE);
        ArrayNode initMin = new ArrayNode(-1, -1, Integer.MAX_VALUE);
        for (int i = 0; i < array.length; i++) {
            // add all min in here save max one ->
            if (initMax.val < array[i][0]) {
                initMax = new ArrayNode(i, 0, array[i][0]);
            }
//            if (initMin.val > array[i][0]) {
//                initMin = new ArrayNode(i, 0, array[i][0]);
//            }
            minQueue.add(new ArrayNode(i, 0, array[i][0]));
        }
        while (minQueue.size() >= array.length) {
            ArrayNode currentMin = minQueue.poll();
            minDiff = Math.min(initMax.val - currentMin.val, minDiff);
            if (currentMin.col < m - 1) {
                minQueue.add(new ArrayNode(currentMin.row, currentMin.col + 1, array[currentMin.row][currentMin.col + 1]));
                if (array[currentMin.row][currentMin.col + 1] > initMax.val) {
                    initMax = new ArrayNode(currentMin.row, currentMin.col + 1, array[currentMin.row][currentMin.col + 1]);
                }
            }
        }
        return minDiff;
    }
}
