import java.util.*;

class Solution {
    // 전역으로 최대 탐험 수를 보관 (DFS 중 갱신)
    int best = 0;

    public int solution(int k, int[][] dungeons) {
        boolean[] used = new boolean[dungeons.length];
        dfs(k, dungeons, used, 0);
        return best;
    }

    /**
     * DFS(백트래킹)
     * @param stamina   현재 남은 피로도
     * @param dungeons  [필요 최소 피로도, 소모 피로도] 쌍의 목록
     * @param used      각 던전 방문 여부
     * @param cnt       지금까지 탐험한 던전 수
     */
    private void dfs(int stamina, int[][] dungeons, boolean[] used, int cnt) {
        // 현재까지의 탐험 수로 최댓값 갱신
        if (cnt > best) best = cnt;

        // 모든 던전에 대해, 아직 안 갔고, 입장 가능하면 시도
        for (int i = 0; i < dungeons.length; i++) {
            if (used[i]) continue;

            int need = dungeons[i][0];  // 입장에 필요한 최소 피로도
            int cost = dungeons[i][1];  // 입장 시 소모되는 피로도

            if (stamina >= need) {      // 입장 가능
                used[i] = true;         // 선택
                dfs(stamina - cost, dungeons, used, cnt + 1);
                used[i] = false;        // 백트래킹
            }
        }

       
    }
}