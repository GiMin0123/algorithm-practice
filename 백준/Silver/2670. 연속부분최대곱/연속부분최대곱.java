import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = null;
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            a[i] = Double.parseDouble(st.nextToken());
        }

        double cur = a[0], ans = cur;
        for (int i = 1; i < N; i++) {
            cur = Math.max(a[i], cur * a[i]);
            ans = Math.max(ans, cur);
        }

        System.out.printf("%.3f%n", ans + 1e-12);
    }
}
