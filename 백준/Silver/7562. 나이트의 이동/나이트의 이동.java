import java.io.*;
import java.util.*;

public class Main {
    static final int[] dy = {-2, -2, -1, -1, 1, 1, 2, 2};
    static final int[] dx = {-1,  1, -2,  2,-2, 2,-1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            int l = Integer.parseInt(br.readLine().trim());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int ty = Integer.parseInt(st.nextToken());
            int tx = Integer.parseInt(st.nextToken());

            if (sy == ty && sx == tx) {
                sb.append(0).append('\n');
                continue;
            }

            int[][] dist = new int[l][l];
            for (int i = 0; i < l; i++) Arrays.fill(dist[i], -1);

            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{sy, sx});
            dist[sy][sx] = 0;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int y = cur[0], x = cur[1];

                for (int dir = 0; dir < 8; dir++) {
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];

                    if (ny < 0 || nx < 0 || ny >= l || nx >= l) continue;
                    if (dist[ny][nx] != -1) continue;

                    dist[ny][nx] = dist[y][x] + 1;
                    if (ny == ty && nx == tx) {
                        q.clear();
                        break;
                    }
                    q.offer(new int[]{ny, nx});
                }
            }
            sb.append(dist[ty][tx]).append('\n');
        }
        System.out.print(sb);
    }
}
