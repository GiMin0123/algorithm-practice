import java.util.*;
import java.io.*;
public class Main {
   
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n = 물품의 수, k = 무게 제한
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k+1];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(w>k) continue;

            for(int target=k; target>=w; target--){
                dp[target] = Math.max(dp[target], dp[target-w]+v);
            }
        }

        System.out.println(dp[k]);
    }
}