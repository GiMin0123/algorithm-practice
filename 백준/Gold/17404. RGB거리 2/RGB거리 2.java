import java.io.*;
import java.util.*;
public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine().trim());

        int[][] houses = new int[n][3];
        int[][] dp = new int[n][3];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            houses[i][0] = Integer.parseInt(st.nextToken());
            houses[i][1] = Integer.parseInt(st.nextToken());
            houses[i][2] = Integer.parseInt(st.nextToken());
        }

        int INF = 1_000_000;
        int answer = INF;

        // 첫 집 색을 f로 고정 (0=R,1=G,2=B)
        for(int f=0; f<3; f++){
            
            // 초기화: 첫 집은 f 색만 허용, 나머지는 불가(INF)
            for(int c=0; c<3; c++){
                if(c==f){
                    dp[0][c]=houses[0][c];
                } else{
                    dp[0][c]=INF;
                }
            }

            // 전이
            for(int i=1; i<n; i++){
                dp[i][0] = houses[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
                dp[i][1] = houses[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
                dp[i][2] = houses[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
            }
            // 마지막 집은 첫 집과 다른 색만 허용
            int ansF = INF;
            if (f != 0) {
                if (dp[n-1][0] < ansF) ansF = dp[n-1][0];
            }
            if (f != 1) {
                if (dp[n-1][1] < ansF) ansF = dp[n-1][1];
            }
            if (f != 2) {
                if (dp[n-1][2] < ansF) ansF = dp[n-1][2];
            }
            if (ansF < answer) answer = ansF;
        }

        System.out.println(answer);
    }
}