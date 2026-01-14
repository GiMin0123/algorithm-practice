import java.io.*;
import java.util.*;

public class Main {
    static int w, h;
    static int[][] map;
    static boolean[][] visited;
    
    // 8방향 탐색 (상, 하, 좌, 우 + 대각선 4개)
    static int[] d_row = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] d_col = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken()); // 너비 (열 개수, col)
            h = Integer.parseInt(st.nextToken()); // 높이 (행 개수, row)

            // 종료 조건: 0 0 입력 시
            if (w == 0 && h == 0) {
                break;
            }

            map = new int[h][w]; // 행이 h개, 열이 w개
            visited = new boolean[h][w];

            // 맵 입력 받기
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int island_count = 0;

            // 전체 맵 탐색
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    // 땅(1)이고 아직 방문하지 않았다면 탐색 시작
                    if (map[i][j] == 1 && !visited[i][j]) {
                        dfs(i, j);
                        island_count++;
                    }
                }
            }

            System.out.println(island_count);
        }
    }

    public static void dfs(int r, int c) {
        visited[r][c] = true;

        // 8방향 탐색
        for (int i = 0; i < 8; i++) {
            int next_r = r + d_row[i];
            int next_c = c + d_col[i];

            // 맵 범위 체크 (h, w 주의)
            if (next_r >= 0 && next_r < h && next_c >= 0 && next_c < w) {
                // 연결된 땅(1)이고 방문하지 않았으면 이동
                if (map[next_r][next_c] == 1 && !visited[next_r][next_c]) {
                    dfs(next_r, next_c);
                }
            }
        }
    }
}