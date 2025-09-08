import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[][] iv = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            iv[i][0] = Integer.parseInt(st.nextToken()); // start
            iv[i][1] = Integer.parseInt(st.nextToken()); // end
        }

        Arrays.sort(iv, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

        int cnt = 0, last = -1_000_000_000;
        for (int[] e : iv) {
            if (e[0] >= last) {
                cnt++;
                last = e[1];
            }
        }
        System.out.println(cnt);
    }
}
