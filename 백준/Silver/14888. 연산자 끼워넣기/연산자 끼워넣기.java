import java.io.*;
import java.util.*;

public class Main {

    static int[] permutation, result;
    static ArrayList<Integer> operators;
    static boolean[] visitedOperator;
    static int maxValue = Integer.MIN_VALUE;
    static int minValue = Integer.MAX_VALUE;

    static void solution(int n, int end, int index) {
        if(index == end) {
            int sum = permutation[0];
            for (int i = 0; i < n-1; i++) {
                if(result[i] == 0) {
                    sum += permutation[i+1];
                } else if(result[i] == 1) {
                    sum -= permutation[i+1];
                } else if(result[i] == 2) {
                    sum *= permutation[i+1];
                } else {
                    if(sum < 0) {
                        sum = -(Math.abs(sum) / permutation[i+1]);
                    } else {
                        sum /= permutation[i+1];
                    }
                }
            }
            maxValue = Math.max(maxValue, sum);
            minValue = Math.min(minValue, sum);
            return ;
        }

        for (int i = 0; i < n - 1; i++) {
            if(!visitedOperator[i]) { // 방문하지 않은 노드인 경우
                visitedOperator[i] = true;
                result[index] = operators.get(i); // 해당 인덱스에 대한 노드 선택.
                solution(n, end, index + 1);

                visitedOperator[i] = false; // 자식 노드 방문 시 방문하지 않은 것으로 변경. (백트래킹)
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        permutation = new int[n];
        result = new int[n-1];
        operators = new ArrayList<>(); // 0 : +, 1 : -, 2 : *, 3 : %
        visitedOperator = new boolean[n-1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            permutation[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                operators.add(i);
            }
        }

        solution(n, n-1, 0);

        System.out.println(maxValue);
        System.out.println(minValue);
    }
}
