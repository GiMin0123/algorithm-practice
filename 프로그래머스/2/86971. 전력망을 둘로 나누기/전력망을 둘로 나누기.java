import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        // 1. 간선을 ArrayList로 구성
        ArrayList<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] w : wires) {
            int u = w[0], v = w[1];
            g[u].add(v);
            g[v].add(u);
        }

        int ans = n; 
        // 2. 모든 간선을 하나씩 끊는다 가정하고 BFS 실행
        for (int[] w : wires) {
            int u = w[0], v = w[1];
            int size = bfsCount(1, u, v, g, n); 
            int diff = Math.abs(n - 2 * size);
            ans = Math.min(ans, diff);
        }
        return ans;
    }

    // 간선 (banU, banV)를 제외하고 BFS로 한쪽 컴포넌트 크기를 계산
    private int bfsCount(int start, int banU, int banV, ArrayList<Integer>[] g, int n) {
        boolean[] vis = new boolean[n + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        vis[start] = true;
        q.offer(start);
        int cnt = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt++;

            for (int nxt : g[cur]) {
                // 끊은 간선은 무시
                if ((cur == banU && nxt == banV) || (cur == banV && nxt == banU)) continue;
                if (vis[nxt]) continue;
                vis[nxt] = true;
                q.offer(nxt);
            }
        }
        return cnt;
    }
}