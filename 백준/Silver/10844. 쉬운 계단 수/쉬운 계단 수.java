import java.io.*;
import java.util.*;

public class Main {
    final static long MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        // 값이 매우 커질 수 있으므로 long 타입 사용
        long[][] dp = new long[n + 1][10];
        
        // 0으로 시작하는 수는 없으므로 1부터 9까지만 1로 설정
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }
        
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                
                // 마지막 숫자가 0인 경우
                // 이전 자리(i-1) 숫자는 반드시 1이어야 함
                if (j == 0) {
                    dp[i][0] = dp[i - 1][1] % MOD;
                }
                
                // 마지막 숫자가 9인 경우
                // 이전 자리(i-1) 숫자는 반드시 8이어야 함
                else if (j == 9) {
                    dp[i][9] = dp[i - 1][8] % MOD;
                }
                
                // 그 외 1 ~ 8인 경우
                else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
                }
            }
        }
        
        // 길이가 N인 모든 계단 수의 합 구하기
        long total_count = 0;
        for (int i = 0; i <= 9; i++) {
            total_count = (total_count + dp[n][i]) % MOD;
        }
        
        System.out.println(total_count);
    }
}