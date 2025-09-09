import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine().trim());
        }
        Arrays.sort(a);                      // 오름차순 정렬
        long cnt = 0;

        for (int i = n - 1; i >= 0; i--) {  // 큰 동전부터
            if (a[i] > k) continue;
            cnt += k / a[i];
            k   %= a[i];
            if (k == 0) break;
        }
        System.out.println(cnt);
    }
}