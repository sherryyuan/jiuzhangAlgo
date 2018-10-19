package com.salesforce;

import java.util.*;

public class SolutionsII {
    // version 1: BFS
    class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;
        int islands = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    markByBFS(grid, i, j);
                    islands++;
                }
            }
        }

        return islands;
    }

    private void markByBFS(boolean[][] grid, int x, int y) {
        // magic numbers!
        int[] directionX = {0, 1, -1, 0};
        int[] directionY = {1, 0, 0, -1};

        Queue<Coordinate> queue = new LinkedList<>();

        queue.offer(new Coordinate(x, y));
        grid[x][y] = false;

        while (!queue.isEmpty()) {
            Coordinate coor = queue.poll();
            for (int i = 0; i < 4; i++) {
                Coordinate adj = new Coordinate(
                        coor.x + directionX[i],
                        coor.y + directionY[i]
                );
                if (!inBound(adj, grid)) {
                    continue;
                }
                if (grid[adj.x][adj.y]) {
                    grid[adj.x][adj.y] = false;
                    queue.offer(adj);
                }
            }
        }
    }

    private boolean inBound(Coordinate coor, boolean[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        return coor.x >= 0 && coor.x < n && coor.y >= 0 && coor.y < m;
    }

    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    //merge interval

    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        //edge cases
        if (intervals == null) {
            return null;
        }
        List<Interval> result = new ArrayList();
        //sort the intervals with start index
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (last.end < current.start) {
                result.add(last);
                last = current;
            } else {
                last.end = Math.max(last.end, current.end);
                ;
            }
        }

        result.add(last);
        return result;
    }


}


