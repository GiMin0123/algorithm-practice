import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 계단의 개수 입력
        int n = Integer.parseInt(br.readLine());
        
        // 계단 점수 배열 (1번 인덱스부터 사용하기 위해 N+1 크기 할당)
        int[] stairs_score = new int[n + 1];
        
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            stairs_score[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = stairs_score[1];
        
        // N >= 2일 때만 2번째 계단 처리
        if (n >= 2) {
            dp[2] = stairs_score[1] + stairs_score[2];
        }

        // 3번째 계단부터 점화식 적용
        for (int i = 3; i <= n; i++) {
            
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairs_score[i - 1]) + stairs_score[i];
        }

        System.out.println(dp[n]);
    }
}