import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] original_map;
    static int max_safe_area = 0;
    
    // 상하좌우 이동을 위한 방향 배열
    static int[] d_row = {-1, 1, 0, 0};
    static int[] d_col = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        original_map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                original_map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 벽 3개를 세우는 모든 경우의 수 탐색 시작
        make_wall(0);

        System.out.println(max_safe_area);
    }

    // 벽 3개를 세우는 함수
    public static void make_wall(int wall_count) {
        if (wall_count == 3) {
            spread_virus();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (original_map[i][j] == 0) {
                    original_map[i][j] = 1; // 벽 세우기
                    make_wall(wall_count + 1);
                    original_map[i][j] = 0; // 벽 허물기
                }
            }
        }
    }

    // 바이러스를 퍼뜨리고 안전 영역 크기를 계산하는 함수
    public static void spread_virus() {
        int[][] temp_map = new int[n][m];
        Queue<int[]> virus_queue = new LinkedList<>();

        // 맵 복사 및 초기 바이러스 위치 큐에 삽입
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp_map[i][j] = original_map[i][j];
                if (temp_map[i][j] == 2) {
                    virus_queue.add(new int[]{i, j});
                }
            }
        }

        // BFS로 바이러스 확산
        while (!virus_queue.isEmpty()) {
            int[] current_pos = virus_queue.poll();
            int cur_r = current_pos[0];
            int cur_c = current_pos[1];

            for (int i = 0; i < 4; i++) {
                int next_r = cur_r + d_row[i];
                int next_c = cur_c + d_col[i];

                // 범위 체크 및 빈 칸인지 확인
                if (next_r >= 0 && next_r < n && next_c >= 0 && next_c < m) {
                    if (temp_map[next_r][next_c] == 0) {
                        temp_map[next_r][next_c] = 2; 
                        virus_queue.add(new int[]{next_r, next_c});
                    }
                }
            }
        }

        // 안전 영역(0) 개수 세기
        calculate_safe_area(temp_map);
    }

    public static void calculate_safe_area(int[][] temp_map) {
        int safe_count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp_map[i][j] == 0) {
                    safe_count++;
                }
            }
        }
        // 최대 안전 영역 갱신
        max_safe_area = Math.max(max_safe_area, safe_count);
    }
}