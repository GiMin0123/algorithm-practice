import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {1, 0, 1}; 
    static int[] dy = {0, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        int[][] dp = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0];

        for(int i=0; i<n; i++){

            for(int j=0; j<m; j++){

                for(int k=0; k<3; k++){
                    int ny = dy[k] + i;
                    int nx = dx[k] + j;

                    if(ny <n && nx<m ){
                        dp[ny][nx] = Math.max(dp[ny][nx], dp[i][j] + arr[ny][nx]);
                    }
                } 
            }
        }      

        System.out.println(dp[n-1][m-1]);   
    }
}