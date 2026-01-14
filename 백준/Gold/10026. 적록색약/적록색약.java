import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static boolean[][] visited;
    static char[][] map;
    
    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        int general_count=0;       // 일반인
        int blind_count=0;         // 적녹색약 

        for(int i=0; i<n; i++){
            String word = br.readLine();

            for(int j=0; j<n; j++){
                map[i][j] = word.charAt(j);
            }    
        }

        //일반인 탐색
        visited = new boolean[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                // 아직 방문하지 않았다면 새로운 구역 발견
                if(!visited[i][j]){
                    dfs(i,j);
                    general_count++;
                }
            }
        }

        //적록색약 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'G') {
                    map[i][j] = 'R'; // 초록색을 빨간색으로 변경
                }
            }
        }

        visited = new boolean[n][n]; 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j); // 같은 로직의 DFS 재사용
                    blind_count++;
                }
            }
        }

        System.out.println(general_count + " " + blind_count);
    }

    public static void dfs(int r, int c){
        visited[r][c] = true;
        char current_color = map[r][c];

        for(int i=0; i<4; i++){
            int next_r = r + dx[i];
            int next_c = c + dy[i];

            if (next_r >= 0 && next_r < n && next_c >= 0 && next_c < n) {
                // 아직 방문 안 했고 && 다음 칸이 현재 색과 같다면 이동
                if (!visited[next_r][next_c] && map[next_r][next_c] == current_color) {
                    dfs(next_r, next_c);
                }
            }
        }
    }
}