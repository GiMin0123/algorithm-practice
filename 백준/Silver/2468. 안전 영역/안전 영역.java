import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[][] a = new int[n][n];
        int maxh = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                if (a[i][j] > maxh) maxh = a[i][j];
            }
        }

        int ans = 0;
        // 강수량 h일 때 잠기지 않는 곳: a[y][x] > h
        for (int h = 0; h <= maxh; h++) {
            boolean[][] vis = new boolean[n][n];
            int cnt = 0;

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (a[y][x] > h && !vis[y][x]) {
                        bfs(y, x, h, a, vis, n);
                        cnt++;
                    }
                }
            }
            if (cnt > ans) ans = cnt;
        }

        System.out.println(ans);
    }

    static void bfs(int sy, int sx, int h, int[][] a, boolean[][] vis, int n) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        vis[sy][sx] = true;
        q.offer(new int[]{sy, sx});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];
            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                if (vis[ny][nx]) continue;
                if (a[ny][nx] <= h) continue; // 잠긴 곳은 제외
                vis[ny][nx] = true;
                q.offer(new int[]{ny, nx});
            }
        }
    }
}
