package com.jiuzhang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solutions {

    public boolean isPossible(int a, int b, int c, int d) {

        int firstPair_min = Math.min(a, b);
        int firstPair_max = Math.max(a, b);
        //convert [a, b] -> [c, d]
        // if c < min(a,b) || d < min(a,b) -> return false
        //[3,4]->[1,5] -> false
        if ((c < firstPair_min || d < firstPair_min)) {
            return false;
        }
        if (isIntegerCouldDeCompose(c, firstPair_min, firstPair_max)) {
            return isIntegerCouldDeCompose(d, firstPair_min, firstPair_max);
        }
        return false;
    }

    private boolean isIntegerCouldDeCompose(int element, int firstPair_min, int firstPair_max) {
        while (element - firstPair_max >= 0) {
            element -= firstPair_max;
        }
        while (element - firstPair_min >= 0) {
            element -= firstPair_min;
        }
        if (element != 0) {
            return false;
        }
        return true;
    }


    public List<Integer> qualifyCandidateSize(int[] scores, int[] lowerBounds, int[] upperBounds) {

        //edge cases
        //lowerBounds size is not equale to upperBounds Size
        if (!isValidInput(scores, lowerBounds, upperBounds)) {
            return null;
        }
        int boundarySize = lowerBounds.length;
        List<Integer> result = new ArrayList<>();

        //sort the scores
        Arrays.sort(scores);

        for (int i = 0; i < boundarySize; i++) {
            result.add(getQualifyCandidateNum(scores, lowerBounds[i], upperBounds[i]));
        }

        return result;
    }

    private int getQualifyCandidateNum(int[] scores, int lowerBounds, int upperBounds) {
        // edge cases
        if (lowerBounds >= upperBounds) {
            return 0;
        }

        int startIndex = getFirstIndexGraterThanTarget(scores, lowerBounds);
        //if startIndex is out of index, return 0;
        if (startIndex >= scores.length) {
            return 0;
        }

        int endIndex = getFirstIndexGraterThanTarget(scores, upperBounds);
        //if endIndex is out of index, the endIndex is last index
        if (endIndex >= scores.length) {
            endIndex = scores.length - 1;
        }
        return endIndex - startIndex + 1;
    }

    private int getFirstIndexGraterThanTarget(int[] scores, int target) {
        int left = 0;
        int right = scores.length - 1;
        //get first index of lowerBounds
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        //if left(4) < target(2) -> 2|4 -> left is the first > target
        if (scores[left] >= target) {
            return left;
        } else if (scores[right] == target) {
            return right;
        } else {
            return right + 1;
        }
    }

    private boolean isValidInput(int[] scores, int[] lowerBounds, int[] upperBounds) {
        if (scores == null || lowerBounds == null || upperBounds == null) {
            return false;
        }

        if (lowerBounds.length != upperBounds.length) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solutions solutions = new Solutions();
        //validate method isPossible()
        System.out.println("assert the result is true = " + solutions.isPossible(3, 2, 5, 8));
        System.out.println("assert the result is true = " + solutions.isPossible(2, 3, 5, 8));
        System.out.println("assert the result is false = " + solutions.isPossible(9, 2, 5, 8));
        System.out.println("assert the result is true = " + solutions.isPossible(3, 2, 6, 8));
        System.out.println("assert the result is false = " + solutions.isPossible(7, 5, 17, 8));
        //validate method qualifyCandidateSize();
        int[] scores = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] lowerBound = {1, 4};
        int[] upperBound = {3, 8};

        System.out.println("assert the result is [3, 5] " + solutions.qualifyCandidateSize(scores, lowerBound, upperBound));
        scores[1] = 10;//[2,3,4,5,6,7,8,10]
        upperBound[1] = 11; //[3, 11]
        System.out.println("assert the result is [2, 6] " + solutions.qualifyCandidateSize(scores, lowerBound, upperBound));
        //[2,3,4,5,6,7,8,10]
        lowerBound[0] = 0;
        System.out.println("assert the result is [2, 6] " + solutions.qualifyCandidateSize(scores, lowerBound, upperBound));

        int[] scores1 = {1, 2, 3};
        System.out.println("assert the result is 0 " + solutions.getFirstIndexGraterThanTarget(scores1, 0));
        System.out.println("assert the result is 3 " + solutions.getFirstIndexGraterThanTarget(scores1, 4));
        System.out.println("assert the result is 2 " + solutions.getFirstIndexGraterThanTarget(scores1, 3));

    }
}
