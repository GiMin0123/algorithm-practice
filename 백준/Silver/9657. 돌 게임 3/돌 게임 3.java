import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        boolean[] dp = new boolean[N + 1]; // dp[i]=현재 차례가 i개에서 승리 가능
        dp[0] = false;
        for (int i = 1; i <= N; i++) {
            boolean win = false;
            if (i - 1 >= 0 && !dp[i - 1]) win = true;
            if (!win && i - 3 >= 0 && !dp[i - 3]) win = true;
            if (!win && i - 4 >= 0 && !dp[i - 4]) win = true;
            dp[i] = win;
        }

        System.out.println(dp[N] ? "SK" : "CY");
    }
}