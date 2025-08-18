import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int n = words.length;

        // target이 words에 없으면 변환 불가
        boolean exist = false;
        for (String w : words) if (w.equals(target)) { exist = true; break; }
        if (!exist) return 0;

        // BFS
        boolean[] visited = new boolean[n];
        Deque<int[]> q = new ArrayDeque<>(); // [index, dist]
        // begin은 words에 없을 수 있으므로, begin과 한 글자 차이인 단어들을 큐에 초기 투입
        for (int i = 0; i < n; i++) {
            if (diff1(begin, words[i])) {
                visited[i] = true;
                q.offer(new int[]{i, 1}); // begin -> words[i] 한 번 변환
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int idx = cur[0], dist = cur[1];
            String now = words[idx];

            if (now.equals(target)) return dist;

            for (int nxt = 0; nxt < n; nxt++) {
                if (!visited[nxt] && diff1(now, words[nxt])) {
                    visited[nxt] = true;
                    q.offer(new int[]{nxt, dist + 1});
                }
            }
        }
        return 0; // 도달 불가
    }

    // 두 단어가 정확히 한 글자만 다른지 확인
    private boolean diff1(String a, String b) {
        if (a.length() != b.length()) return false;
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i) && ++diff > 1) return false;
        }
        return diff == 1;
    }
}