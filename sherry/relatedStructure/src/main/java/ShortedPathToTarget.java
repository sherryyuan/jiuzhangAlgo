import java.util.LinkedList;
import java.util.Queue;

public class ShortedPathToTarget {

    class Coordinate {
        int x, y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int[] x_direct = {1, 0, -1, 0};
    int[] y_direct = {0, 1, 0, -1};

    /**
     * @param targetMap:
     * @return: nothing
     */
    public int shortestPath(int[][] targetMap) {
        // Write your code here
        if (targetMap == null) {
            return 0;
        }
        Coordinate start = new Coordinate(0, 0);
        int steps = 0;
        Queue<Coordinate> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[targetMap.length][targetMap[0].length];
        queue.offer(start);
        isVisited[0][0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Coordinate cur = queue.poll();
                if (targetMap[cur.x][cur.y] == 2) {
                    return steps;
                }
                for (int j = 0; j < 4; j++) {
                    Coordinate neighbor = new Coordinate(cur.x + x_direct[j], cur.y + y_direct[j]);
                    if (!isValid(neighbor, targetMap)) {
                        continue;
                    }
                    if (isVisited[neighbor.x][neighbor.y]) {
                        continue;
                    }
                    if (targetMap[neighbor.x][neighbor.y] == 1) {
                        continue;
                    }
                    queue.offer(neighbor);
                    isVisited[neighbor.x][neighbor.y] = true;
                }
            }
            steps++;
        }
        return -1;
    }

    private boolean isValid(Coordinate coor, int[][] targetMap) {
        if (coor.x < 0 || coor.x > targetMap.length - 1) {
            return false;
        }
        if (coor.y < 0 || coor.y > targetMap[0].length - 1) {
            return false;
        }
        return true;
    }
}
