import java.util.*;
class Solution {
    private int toSec(String mmss){
        int m = Integer.parseInt(mmss.substring(0,2));
        int s = Integer.parseInt(mmss.substring(3,5));
        return m*60 +s;
    }
    private String toMMSS(int sec){
        int m = sec / 60, s = sec % 60;
        return String.format("%02d:%02d", m,s);
    }
    private int skipOpening(int cur, int opStart, int opEnd){
        // op_start <= cur <= op_end라면 오프닝 끝으로 넘어간다
        return (cur >= opStart && cur <= opEnd) ? opEnd : cur;
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int v = toSec(video_len);
        int cur = toSec(pos);
        int s = toSec(op_start);
        int e = toSec(op_end);
        
        // 시작 시점이 오프닝이면 즉시 스킵
        cur = skipOpening(cur,s,e);
        
        for(String cmd : commands){
            if(cmd.equals("prev")){
                cur = Math.max(0, cur-10);
            }
            else{ //next
                cur = Math.min(v,cur + 10);    
            }
            
            //이동 후 오프닝에 들어오면 다시 스킵
            cur = skipOpening(cur,s,e);
        }
        
        return toMMSS(cur);
    }
}