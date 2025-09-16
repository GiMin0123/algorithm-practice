import java.io.*;
import java.util.*;

public class Main {
    static void toggle(int[] a, int idx) {
        a[idx] = 1 - a[idx];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine().trim());           // 스위치 수
        int[] s = new int[n + 1];                                  // 1-indexed
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) s[i] = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine().trim());            // 학생 수
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (gender == 1) {
                // 남학생: 번호의 배수 스위치 토글
                for (int pos = k; pos <= n; pos += k) toggle(s, pos);
            } else {
                // 여학생: 기준에서 좌우 대칭으로 확장하며 같은 상태면 같이 토글
                toggle(s, k);
                int l = k - 1, r = k + 1;
                while (l >= 1 && r <= n && s[l] == s[r]) {
                    toggle(s, l);
                    toggle(s, r);
                    l--; r++;
                }
            }
        }

        // 출력: 20개마다 줄바꿈
        for (int i = 1; i <= n; i++) {
            sb.append(s[i]);
            if (i % 20 == 0 || i == n) sb.append('\n');
            else sb.append(' ');
        }

        System.out.print(sb.toString());
    }
}
