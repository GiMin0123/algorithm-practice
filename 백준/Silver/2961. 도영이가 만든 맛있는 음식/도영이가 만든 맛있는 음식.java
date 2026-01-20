import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] ingredients; // [신맛, 쓴맛]을 저장할 2차원 배열
    static int min_diff = Integer.MAX_VALUE; // 최소 차이를 저장할 변수, 최댓값으로 초기화

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        ingredients = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ingredients[i][0] = Integer.parseInt(st.nextToken()); // 신맛 (S)
            ingredients[i][1] = Integer.parseInt(st.nextToken()); // 쓴맛 (B)
        }

        dfs(0, 1, 0, 0);

        System.out.println(min_diff);
    }

    public static void dfs(int depth, int current_sour, int current_bitter, int selected_count) {
        
        // 모든 재료에 대해 선택 여부를 결정했을 때 (종료 조건)
        if (depth == n) {
            // 재료를 적어도 하나 이상 사용해야 함
            if (selected_count > 0) {
                int diff = Math.abs(current_sour - current_bitter);
                // 최소 차이 갱신
                if (diff < min_diff) {
                    min_diff = diff;
                }
            }
            return;
        }

        // 현재 재료를 선택하는 경우
        // 신맛은 곱하고, 쓴맛은 더하고, 선택 개수 증가
        dfs(depth + 1, current_sour * ingredients[depth][0], 
            current_bitter + ingredients[depth][1], selected_count + 1);

        // 현재 재료를 선택하지 않는 경우
        // 맛의 변화 없이 다음 재료로 넘어감
        dfs(depth + 1, current_sour, current_bitter, selected_count);
    }
}