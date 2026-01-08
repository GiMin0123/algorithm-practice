import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] number_arr;
    // 연산자 개수 저장: 차례대로 +, -, *, /
    static int[] operator_counts = new int[4];
    
    // 최댓값은 최소로, 최솟값은 최대로 초기화
    static int max_value = Integer.MIN_VALUE;
    static int min_value = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 입력 받기
        n = Integer.parseInt(br.readLine());
        number_arr = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            number_arr[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator_counts[i] = Integer.parseInt(st.nextToken());
        }
        
        // 2. DFS 탐색 시작 (첫 번째 숫자를 초기값으로 넘김, depth는 1부터 시작)
        dfs(number_arr[0], 1);
        
        // 3. 결과 출력
        System.out.println(max_value);
        System.out.println(min_value);
    }
    
    // current_result: 현재까지 연산된 결과
    // depth: 현재 몇 번째 숫자까지 계산했는지 (인덱스 역할)
    public static void dfs(int current_result, int depth) {
        // 종료 조건: 모든 숫자를 다 사용했을 때
        if (depth == n) {
            max_value = Math.max(max_value, current_result);
            min_value = Math.min(min_value, current_result);
            return;
        }
        
        // 4가지 연산자를 하나씩 시도
        for (int i = 0; i < 4; i++) {
            // 사용할 수 있는 연산자가 남아있다면
            if (operator_counts[i] > 0) {
                
                // 연산자 사용 (개수 차감)
                operator_counts[i]--;
                
                // 다음 단계로 진행 (재귀 호출)
                switch (i) {
                    case 0: // 덧셈
                        dfs(current_result + number_arr[depth], depth + 1);
                        break;
                    case 1: // 뺄셈
                        dfs(current_result - number_arr[depth], depth + 1);
                        break;
                    case 2: // 곱셈
                        dfs(current_result * number_arr[depth], depth + 1);
                        break;
                    case 3: // 나눗셈
                        dfs(current_result / number_arr[depth], depth + 1);
                        break;
                }
                
                // 백트래킹: 재귀가 끝나고 돌아오면 연산자 개수 복구
                operator_counts[i]++;
            }
        }
    }
}