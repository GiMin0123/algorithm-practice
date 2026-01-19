import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] seq_arr = new int[n];
        int[] dp = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            seq_arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int total_max_length = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (seq_arr[j] < seq_arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }

            if (total_max_length < dp[i]) {
                total_max_length = dp[i];
            }
        }

        System.out.println(total_max_length);
    }
}