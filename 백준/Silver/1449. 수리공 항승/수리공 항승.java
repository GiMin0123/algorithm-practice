import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 구멍 개수
        int l = Integer.parseInt(st.nextToken()); // 테이프 길이

        int[] holes = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            holes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(holes);

        int cnt = 0;
        double cover = 0; // 현재 테이프가 커버 가능한 끝 위치
        for (int pos : holes) {
            if (pos > cover) { 
                // 새 테이프 붙여야 함
                cnt++;
                cover = pos - 0.5 + l; 
            }
        }

        System.out.println(cnt);
    }
}