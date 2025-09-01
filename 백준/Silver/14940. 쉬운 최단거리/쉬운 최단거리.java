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

        int[][] map = new int[n][m];
        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, -1);

        int sy = 0, sx = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    sy = i;
                    sx = j;
                }
                if (map[i][j] == 0) {
                    dist[i][j] = 0; // 갈 수 없는 땅은 항상 0
                }
            }
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sy, sx});
        dist[sy][sx] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (map[ny][nx] == 0) continue;       // 못 가는 땅
                if (dist[ny][nx] != -1) continue;     // 이미 방문

                dist[ny][nx] = dist[y][x] + 1;
                q.offer(new int[]{ny, nx});
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}