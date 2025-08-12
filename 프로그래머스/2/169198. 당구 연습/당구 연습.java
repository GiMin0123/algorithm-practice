import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        for(int i=0; i < balls.length; i++){
            int a = balls[i][0];
            int b = balls[i][1];
            int best = Integer.MAX_VALUE;
            
            // 왼쪽 벽 반사: (-a, b)
            if(!(startY == b && startX > a)){
                int dx = startX - (-a);
                int dy = startY - b;
                best = Math.min(best, dx*dx + dy*dy);
            }
            // 오른쪽 벽 반사
            if(!(startY == b && startX < a)){
                int dx = startX - (2*m-a);
                int dy = startY - b;
                best = Math.min(best, dx*dx + dy*dy);
            }
            // 아래 벽 반사: (a, -b)
            if (!(startX == a && startY > b)) {
                int dx = startX - a;
                int dy = startY - (-b);
                best = Math.min(best, dx * dx + dy * dy);
            }
            // 위쪽 벽 반사: (a, 2n - b)
            if (!(startX == a && startY < b)) {
                int dx = startX - a;
                int dy = startY - (2 * n - b);
                best = Math.min(best, dx * dx + dy * dy);
            }

            answer[i] = best;
        }
        
        
        return answer;
    }
}