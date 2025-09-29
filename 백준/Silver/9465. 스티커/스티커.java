import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine().trim());
        
        for(int i=0; i<t; i++){

            int n = Integer.parseInt(br.readLine().trim());
            int[][] val = new int[2][n];

            for(int j=0; j<2; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<n; k++){
                    val[j][k]=Integer.parseInt(st.nextToken());
                }
            }

            // 초기값
            // dp[i][0]=i열에서 아무 것도 안 뗀 최댓값
            // dp[i][1]=i열 위 스티커를 뗀 최댓값
            // dp[i][2]=i열 아래 스티커를 뗀 최댓값
            int[][] dp = new int[n][3];
            dp[0][0] = 0;
            dp[0][1] = val[0][0];
            dp[0][2] = val[1][0];

            for (int m = 1; m < n; m++) {
                dp[m][0] = Math.max(dp[m-1][0], Math.max(dp[m-1][1], dp[m-1][2]));
                dp[m][1] = Math.max(dp[m-1][0], dp[m-1][2]) + val[0][m];
                dp[m][2] = Math.max(dp[m-1][0], dp[m-1][1]) + val[1][m];
            }

            System.out.println(Math.max(dp[n-1][0], Math.max(dp[n-1][1], dp[n-1][2])));
        }

    }
}