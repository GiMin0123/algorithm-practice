import java.io.*;
import java.util.*;
public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine().trim());
        int[][] board = new int[n][n];
        long[][] dp = new long[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0]=1;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                long ways = dp[i][j];
                if(ways==0){
                    continue;
                }
                int v = board[i][j];
                if(v==0){
                     continue;
                }

                int ni = i+v, nj = j+v;
                //아래로 
                if(ni < n){
                    dp[ni][j] += ways;
                }
                //오른쪽
                if(nj < n){
                    dp[i][nj] += ways;
                }
            }
        }

        System.out.println(dp[n-1][n-1]);
    }
}