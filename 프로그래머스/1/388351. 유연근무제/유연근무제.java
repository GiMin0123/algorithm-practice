import java.util.Arrays;
class Solution {
    private int toMin(int t) {
        // HHMM → 분
        return (t / 100) * 60 + (t % 100);
    }
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int n = schedules.length;
        boolean[] ok = new boolean[n];
        Arrays.fill(ok, true);
        
       for (int j = 0; j < 7; j++) {
            int dow = ((startday - 1 + j) % 7) + 1; // 1=월 ... 7=일
            if (dow == 6 || dow == 7) continue;     // 주말은 무시

            for (int i = 0; i < n; i++) {
                int cutoff = toMin(schedules[i]) + 10;   // 희망시각 + 10분
                int log    = toMin(timelogs[i][j]);      // 해당 일자 출근 기록
                if (log > cutoff) ok[i] = false;         // 지각하면 탈락
            }
        }
        
        int answer = 0;
        for (boolean pass : ok){
             if (pass==true) {
                 answer++;
             }
        }
        return answer;
    }
}