import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int row, column;

        public Node(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    static int n;
    static int[][] map;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static boolean[][] visited;

    static int dfs(int row, int column) {
        int houseCount = 1;
        ArrayDeque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(row, column));
        visited[row][column] = true;

        while(!deque.isEmpty()) {
            Node tmp = deque.poll();

            for (int i = 0; i < 4; i++) {
                int nRow = tmp.row + direction[i][0];
                int nColumn = tmp.column + direction[i][1];

                if(nRow >= 0 && nRow < n && nColumn >= 0 && nColumn < n) {
                    if(!visited[nRow][nColumn] && map[nRow][nColumn] == 1) {
                        deque.add(new Node(nRow, nColumn));
                        visited[nRow][nColumn] = true;
                        houseCount++;
                    }
                }
            }
        }
        return houseCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine().strip();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    int count = dfs(i, j);
                    answer.add(count);
                }
            }
        }
        
        answer.sort(Comparator.naturalOrder());

        System.out.println(answer.size());
        for (Integer i : answer) {
            System.out.println(i);
        }
    }
}