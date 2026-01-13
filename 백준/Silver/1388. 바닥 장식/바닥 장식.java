import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] floor_map;
    static boolean[][] visited;
    static int plank_count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); 
        m = Integer.parseInt(st.nextToken());

        floor_map = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String row_str = br.readLine();
            for (int j = 0; j < m; j++) {
                floor_map[i][j] = row_str.charAt(j);
            }
        }

        // 전체 맵 순회
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 아직 방문하지 않은 판자라면 새로운 판자로 카운트하고 DFS 시작
                if (!visited[i][j]) {
                    dfs(i, j);
                    plank_count++;
                }
            }
        }

        System.out.println(plank_count);
    }

    public static void dfs(int r, int c) {
        visited[r][c] = true;
        char current_shape = floor_map[r][c];

        // 현재 모양이 - 일 때
        if (current_shape == '-') {
            // 오른쪽으로만 확인
            int next_c = c + 1;
            
            // 범위 안에 있고, 다음 칸도 - 라면 계속 탐색
            if (next_c < m && floor_map[r][next_c] == '-' && !visited[r][next_c]) {
                dfs(r, next_c);
            }
        }
        
        // 2. 현재 모양이 | 일 때
        else if (current_shape == '|') {
            // 아래쪽으로만 확인 (r + 1)
            int next_r = r + 1;
            
            // 범위 안에 있고, 다음 칸도 | 라면 계속 탐색
            if (next_r < n && floor_map[next_r][c] == '|' && !visited[next_r][c]) {
                dfs(next_r, c);
            }
        }
    }
}