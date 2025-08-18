import java.util.*;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        boolean[][] visited = new boolean[m][n];
        int numberOfArea = 0;       // 영역의 개수
        int maxSizeOfOneArea = 0;   // 최대 영역 크기

        // 4방향 이동 벡터 (상, 하, 좌, 우)
        final int[] dr = {-1, 1, 0, 0};
        final int[] dc = { 0, 0,-1, 1};

        // 모든 칸을 훑으며 아직 방문하지 않았고 색이 있는(>0) 칸에서 BFS 시작
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (picture[r][c] == 0 || visited[r][c]) continue;

                numberOfArea++; // 새 영역 발견
                int color = picture[r][c];
                int size = 0;

                // BFS 큐에는 좌표만 저장하면 충분(거리 필요 없음)
                ArrayDeque<int[]> q = new ArrayDeque<>();
                visited[r][c] = true;     // 큐에 넣기 전에 방문 처리(중복 삽입 방지)
                q.offer(new int[]{r, c});

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int cr = cur[0], cc = cur[1];
                    size++; // 현재 영역 크기 증가

                    // 4방향 확장
                    for (int k = 0; k < 4; k++) {
                        int nr = cr + dr[k], nc = cc + dc[k];
                        // 경계 체크
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                        // 이미 방문했거나 색이 다르거나 배경이면 skip
                        if (visited[nr][nc]) continue;
                        if (picture[nr][nc] != color) continue;

                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }

                // 최대 영역 크기 갱신
                if (size > maxSizeOfOneArea) maxSizeOfOneArea = size;
            }
        }

        return new int[]{numberOfArea, maxSizeOfOneArea};
    }
}