import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[] t = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) t[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(t);                 // 오름차순 정렬
        int pref = 0;                   // 누적 대기 + 처리 시간
        int sum = 0;                    // 총합
        for (int x : t) {
            pref += x;
            sum  += pref;
        }
        System.out.println(sum);
    }
}