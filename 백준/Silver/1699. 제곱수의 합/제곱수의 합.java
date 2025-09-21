import java.util.*;
import java.io.*;
public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        int n = Integer.parseInt(st.nextToken());

        // n 이하의 모든 제곱수 전처리 (1^2, 2^2, ...)
        int r = (int) Math.sqrt(n);
        int[] squares = new int[r];
        for (int i = 1; i <= r; i++) {
            squares[i - 1] = i * i;
        }

        // dp[i] = i를 만드는 최소 항 개수
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 상한선(최악값)으로 시작
            int best = i;

            // 가능한 모든 제곱수 s에 대해 최솟값 갱신
            for (int s : squares) {
                 // 더 큰 제곱수는 불가
                if (s > i) { 
                    break;
                }

                // s를 한 번 쓰고, 남은 i - s의 최적해 + 1
                int cand = dp[i - s] + 1;

                 // 최소 항 개수 갱신
                if (cand < best) {
                    best = cand;
                } 
            }
            dp[i] = best;
        }

        System.out.println(dp[n]);
    }
}