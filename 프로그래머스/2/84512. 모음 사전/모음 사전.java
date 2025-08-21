import java.util.*;

class Solution {
    // 문자의 순서(A,E,I,O,U)를 인덱스로 매핑
    private static final char[] V = {'A','E','I','O','U'};
    private static final Map<Character, Integer> IDX = new HashMap<>();
    static {
        for (int i = 0; i < V.length; i++) IDX.put(V[i], i);
    }

    public int solution(String word) {
        // 각 자리의 "가중치" 미리 계산
        // w[i] = i번째 자리에 문자를 하나 고정했을 때 그 뒤로 만들 수 있는 모든 조합 수 + 자기 자신(빈 선택 포함)
        // 우측부터: w4=1, w3=1+5*1=6, w2=1+5*6=31, w1=1+5*31=156, w0=1+5*156=781
        int[] w = new int[5];
        w[4] = 1;
        for (int i = 3; i >= 0; i--) w[i] = 1 + 5 * w[i+1];

        int ans = 0;
        // 각 자리에서: (해당 문자 인덱스) * 가중치 + 1(자기 자신 선택)
        // 예) "A"는 1, "E"는 A에서 + w0 한 번 건너뛴 뒤 +1, 이런 식으로 누적
        for (int i = 0; i < word.length(); i++) {
            int k = IDX.get(word.charAt(i)); // A=0, E=1, I=2, O=3, U=4
            ans += k * w[i] + 1;
        }
        return ans;
    }
}