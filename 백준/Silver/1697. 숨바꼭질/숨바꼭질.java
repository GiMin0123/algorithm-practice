import java.io.*;
import java.util.*;

public class Main {
    static final int max = 100000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n == k) {
            System.out.println(0);
            return;
        }

        int[] dist = new int[max + 1];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        dist[n] = 0;
        q.offer(n);

        while (!q.isEmpty()) {
            int cur = q.poll();

            // 세 가지 이동: -1, +1, *2
            int[] nexts = {cur - 1, cur + 1, cur * 2};
            for (int next : nexts) {
                if (next < 0 || next > max) continue;
                if (dist[next] != -1) continue; // 방문됨

                dist[next] = dist[cur] + 1;
                if (next == k) {
                    System.out.println(dist[next]);
                    return;
                }
                q.offer(next);
            }
        }
    }
}
