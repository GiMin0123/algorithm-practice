
import java.io.*;
import java.util.*;

public class Main {

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken()); 
        int n = Integer.parseInt(st.nextToken()); 

        int[][] arr = new int[n][m];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        // 익은 토마토(1) 모두 큐에 추가
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        // BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (arr[nx][ny] == 0) {
                    arr[nx][ny] = arr[x][y] + 1; 
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        // 결과 확인
        int days = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
            	// 안 익은 토마토가 남아있으면
                if (arr[i][j] == 0) { 
                    System.out.println(-1);
                    return;
                }
                days = Math.max(days, arr[i][j]);
            }
        }

        // 처음 익은 토마토가 1이었으므로 -1
        System.out.println(days - 1); 
    }
}