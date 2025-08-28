import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine().trim();
            for (int j = 0; j < m; j++) {
                maze[i][j] = (s.charAt(j) == '1') ? 1 : 0;
            }
        }

        int[][] dist = new int[n][m];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        // 시작점 (0,0)
        dist[0][0] = 1;
        q.offer(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            if (y == n - 1 && x == m - 1) {
                System.out.println(dist[y][x]);
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (maze[ny][nx] == 0) continue;      // 벽
                if (dist[ny][nx] != 0) continue;      // 이미 방문

                dist[ny][nx] = dist[y][x] + 1;
                q.offer(new int[]{ny, nx});
            }
        }

        // 문제 조건상 도달 불가 케이스는 주어지지 않지만, 안전하게 처리
        System.out.println(-1);
    }
}