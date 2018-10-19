import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solutions {

    /*
     * Complete the widestGap function below.
     */

    /**
     * Return the maxGap of N space length array
     *
     * @param n     the length of space
     * @param start the interval starts
     * @param end   the interval ends
     * @return the maxGap in the space
     */
    static int widestGap(int n, int[] start, int[] end) {

        class Interval {
            int start, end;

            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        //edge cases
        if (n == 0 || start == null || end == null || start.length != end.length) {
            return -1;
        }

        //construct the intervalList
        List<Interval> intervalList = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            //invalid input
            if (start[i] > end[i]) {
                return -1;
            }
            intervalList.add(new Interval(start[i], end[i]));
        }

        //sort the intervalList with start index
        intervalList.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        //merge all connect interval
        Interval last = intervalList.get(0);
        //init the maxGap with gap between first.start and start;
        int maxGap = last.start - 1;
        for (int i = 1; i < intervalList.size(); i++) {
            //last.end > curr.start -> merge two interval
            Interval current = intervalList.get(i);
            if (last.end >= current.start) {
                //[2,3][2,4] -> [2, 4]
                last.end = Math.max(last.end, current.end);
            } else {
                //get the gap between two interval
                maxGap = Math.max(maxGap, current.start - last.end - 1);
                last = current;
            }
        }

        if (last.end < n) {
            maxGap = Math.max(maxGap, n - last.end);
        }
        //get the max gap
        return maxGap;
    }


    /*
     * Complete the replace function below.
     */

    /**
     * replace the pattern with Char X
     *
     * @param str     the string need to replace
     * @param pattern the str pattern
     * @return the new string replace the pattern
     */
    static String replace(String str, String pattern) {
        /*
         * Write your code here.
         */
        //edge cases
        if (str == null || pattern == null) {
            return str;
        }
        //convert pattern to lower case to simplify the compare
        pattern = pattern.toLowerCase();
        int length = pattern.length();
        StringBuilder res = new StringBuilder();

        //if the patten repeats, increase the count
        int repeatCounts = 0;
        int start = 0;
        int end = 0;

        while (end < str.length()) {
            // set temp start index as start + pattern repeated times * pattern length
            int tempStart = start + repeatCounts * length;
            // one pattern is valid, increase repeat pattern count
            if (end - tempStart == length) {
                repeatCounts++;
                tempStart += length;
            }
            //if it's match the pattern, move fast to next position
            if (Character.toLowerCase(str.charAt(end)) == pattern.charAt(end - tempStart)) {
                end++;
            } else {
                //append pattern if repeatCount is larger than 0;
                if (repeatCounts >= 1) {
                    res.append("X");
                }

                if (tempStart != end) {
                    // add diff between tempIndex and fast to res
                    res.append(str.substring(tempStart, end));
                }

                res.append(str.charAt(end));
                //move pointer and reset repeatCount;
                end++;
                start = end;
                repeatCounts = 0;
            }
        }
        //handle special case when reach end of the str
        // if repeatCounts != 0 then append pattern
        if (repeatCounts >= 1) {
            //replace the pattern
            res.append('X');
        }
        int tempStart = start + repeatCounts * length;
        // if the end - start != pattern length, append subString
        if ((end - tempStart) != length) {
            //add all extra chars
            res.append(str.substring(tempStart, end));
        }
        return res.toString();
    }


    public static void main(String[] args) {
        //test replace()
        System.out.println("=============test replace(str, str)========================");
        System.out.println("The result should be XdeXdeXde " + Solutions.replace("aBcdeabcdeabcde", "abc"));
        System.out.println("The result should be X " + Solutions.replace("aaaa", "aa"));
        System.out.println("The result should be Xa " + Solutions.replace("aaaaa", "aa"));
        System.out.println("The result should be XA " + Solutions.replace("AaaaA", "aa"));

        System.out.println("The result should be EmptyString " + Solutions.replace("", "aa"));
        System.out.println("The result should be EmptyString " + Solutions.replace("", ""));

        //test widestGap
        System.out.println("=============test widestGap(int, int[], int[])========================");
        int n = 10;
        int[] start = {1, 7};
        int[] end = {2, 9};
        System.out.println("The result should be 4 " + Solutions.widestGap(n, start, end));
        end[1] = 11; //[2,11]
        System.out.println("The result should be 4 " + Solutions.widestGap(n, start, end));
        end[0] = 8; //[8,11] -> no gap
        System.out.println("The result should be 0 " + Solutions.widestGap(n, start, end));
        start[0] = 4; //[4,7]
        end[0] = 5; //[5,11] -> should return the gap between first interval with start position
        System.out.println("The result should be 3 " + Solutions.widestGap(n, start, end));
        start[0] = 5; //[5,7] -> start == end
        System.out.println("The result should be 4 " + Solutions.widestGap(n, start, end));

    }
}