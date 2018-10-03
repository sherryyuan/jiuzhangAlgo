import java.util.*;


/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */


public class KWayMerge {

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = next;
        }
    }

    private class Element {
        int row;
        int col;
        int val;
        public Element(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public Comparator<Element> comparator = new Comparator<Element>() {
        @Override
        public int compare(Element o1, Element o2) {
            return o1.val - o2.val;// accent
        }
    };

    public Integer[] getKWayMerge(int[][] arrays){

        if(arrays == null) {
            return null;
        }

        //get the length of array;
        List<Integer> resultList = new ArrayList<>();
        Queue<Element> minList = new PriorityQueue<Element>(comparator);
        //init the list
        for(int i = 0; i < arrays.length; i++){
            minList.add(new Element(i,0,arrays[i][0]));
        }

        while(!minList.isEmpty()) {
            Element e = minList.poll();
            if(e.col < arrays[e.row].length) {
                minList.offer(new Element(e.row, e.col + 1, arrays[e.row][e.col + 1]));
            }
            resultList.add(e.val);
        }


        return (Integer[])resultList.toArray();

    }

    Comparator<ListNode> listNodeComp = new Comparator<ListNode>(){

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    };

    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        // write your code here

        if(lists == null) {
            return null;
        }

        ListNode tail = new ListNode(Integer.MAX_VALUE);
        ListNode buffer = tail;
        Queue<ListNode> minList = new PriorityQueue<>(listNodeComp);
        for(int i = 0; i < lists.size(); i++) {
            minList.offer(lists.get(i));
        }
        while(!minList.isEmpty()) {
            ListNode temp = minList.poll();
            tail.next = temp;
            minList.offer(temp.next);
        }

        return buffer.next;

    }
}
