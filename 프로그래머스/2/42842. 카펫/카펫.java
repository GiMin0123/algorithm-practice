import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow; // 전체 타일 수

        // total의 약수 쌍을 모두 확인
        for (int h = 3; h <= Math.sqrt(total); h++) {
            if (total % h == 0) {
                int w = total / h; // 가로
                // 조건: (w-2) * (h-2) == yellow
                if ((w - 2) * (h - 2) == yellow) {
                    return new int[]{w, h};
                }
            }
        }
        return new int[0]; 
    }
}