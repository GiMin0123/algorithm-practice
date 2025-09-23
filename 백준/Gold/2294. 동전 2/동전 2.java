import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];

        for(int i=0; i<n; i++){
            coins[i] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(coins);
        int INF = 1_000_000;
        int[] dp = new int[k+1];
        Arrays.fill(dp,INF);
        dp[0]=0;

        for(int coin: coins){
            for (int x = coin; x <= k; x++) {
                if (dp[x - coin] != INF)
                dp[x] = Math.min(dp[x], dp[x - coin] + 1);
            }
        }

        if(dp[k] == INF){
           System.out.println(-1); 
        } else{
           System.out.println(dp[k]); 
        }
    }
}