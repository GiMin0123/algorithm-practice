import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine().trim());
        
        int[] numbers = new int[n];
        int[][] dp = new int[n][n];

        // 수열 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine().trim());

        // 길이 1: 자기 자신은 항상 팰린드롬
        for(int i=0; i<n; i++){
            dp[i][i] = 1;
        }

        // 길이 2: 두 값이 같으면 팰린드롬
        for(int i=0; i+1<n; i++){
            if(numbers[i] == numbers[i+1]){
                dp[i][i+1] = 1;
            }
        }

        // 길이 3 이상: 양 끝이 같고, 내부(s+1..e-1)가 팰린드롬이면 팰린드롬
        for(int len=3; len<=n; len++){
            for(int s=0; s+len-1<n; s++){
                int e = s+len-1;
                if(numbers[s] == numbers[e] && dp[s+1][e-1]==1){
                    dp[s][e]=1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            sb.append(dp[s][e]).append('\n');
        }
        System.out.print(sb.toString());

    }
}