import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        // 최소 한 개 이상 선택 조건을 만족하도록 첫 원소로 초기화
        int first = Integer.parseInt(st.nextToken());
        int cur = first; // 현재 위치에서 끝나는 최대 합
        int best = first; // 전체 최대 합

        for(int i=1; i<n; i++){
            int x = Integer.parseInt(st.nextToken());
            
            cur = Math.max(x, cur + x);   
            best = Math.max(best,cur);
        }
        System.out.println(best);
    }
}