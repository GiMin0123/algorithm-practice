import java.util.*;

class Solution {
    // 4방 탐색
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = { 0, 0,-1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5]; // 대기실 5개

        for (int i = 0; i < 5; i++) {           // 각 대기실
            // 5x5 문자 격자 구성
            char[][] g = new char[5][5];
            for (int r = 0; r < 5; r++) {
                String row = places[i][r];      // 길이 5 문자열
                for (int c = 0; c < 5; c++) {
                    g[r][c] = row.charAt(c);
                }
            }

            // 대기실 i가 거리두기 준수하면 1, 아니면 0
            answer[i] = isValid(g) ? 1 : 0;
        }
        return answer;
    }

    // 대기실 한 개가 규칙을 지키는지 여부
    private boolean isValid(char[][] g) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (g[r][c] == 'P') {
                    if (violates(g, r, c)) return false; // 하나라도 위반이면 실패
                }
            }
        }
        return true;
    }

    // (sr,sc)에서 맨해튼 거리 2 이내에 칸막이(X) 없이 다른 'P'가 있는지 탐색
    private boolean violates(char[][] g, int sr, int sc) {
        boolean[][] vis = new boolean[5][5];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc, 0});
        vis[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], d = cur[2];

            // 시작점은 제외하고, 사람 발견 시 위반
            if (d > 0 && g[r][c] == 'P') return true;

            // 거리 2까지만 확장
            if (d == 2) continue;

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k], nc = c + dc[k];
                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                if (vis[nr][nc]) continue;
                if (g[nr][nc] == 'X') continue; // 칸막이는 통과 불가

                vis[nr][nc] = true;
                q.offer(new int[]{nr, nc, d + 1});
            }
        }
        return false; // 주변 2칸 내 위반 없음
    }
}