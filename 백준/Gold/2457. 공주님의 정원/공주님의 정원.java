import java.io.*;
import java.util.*;

public class Main {
    static int key(int mm, int dd) { return mm * 100 + dd; }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        // flowers: {start_mmdd, end_mmdd}
        ArrayList<int[]> flowers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());

            int s = key(sm, sd);
            int e = key(em, ed);
            // 3/1 이전에 피어서 12/1 이후까지 유효한 구간만 의미가 있음
            // 그리디 과정에서 자연히 걸러지므로 필터는 선택사항이지만,
            // e <= 301 인 구간은 영향 없음
            flowers.add(new int[]{s, e});
        }

        flowers.sort((a, b) -> {
            // 시작 날짜가 같으면 종료 날짜를 비교 (내림차순)
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            // 시작 날짜가 다르면 시작 날짜를 기준으로 오름차순
            return a[0] - b[0];
        });

        final int TARGET_START = 301;   // 3/01
        final int TARGET_END   = 1201;  // 12/01 (미만까지 덮기)
        int cur = TARGET_START;
        int used = 0;
        int i = 0;
        int best = cur;

        while (cur < TARGET_END) {
            boolean extended = false;

            // cur 이전(포함)에 시작하는 모든 구간을 보며 가장 멀리 가는 end를 찾음
            while (i < flowers.size() && flowers.get(i)[0] <= cur) {
                int e = flowers.get(i)[1];
                if (e > best) {
                    best = e;
                    extended = true;
                }
                i++;
            }

            if (!extended) { // 더 이상 확장 불가 → 실패
                System.out.println(0);
                return;
            }

            used++;
            cur = best;
        }

        System.out.println(used);
    }
}