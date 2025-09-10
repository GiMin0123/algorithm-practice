import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        long[] dist = new long[n - 1];
        long[] price = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) dist[i] = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) price[i] = Long.parseLong(st.nextToken());

        long ans = 0L;
        long minPrice = price[0];

        for (int i = 0; i < n - 1; i++) {
            // 현재까지 가장 싼 주유소 가격으로 다음 구간 거리만큼 비용 추가
            ans += minPrice * dist[i];
            // 다음 도시 도착 후, 최소 가격 갱신
            if (price[i + 1] < minPrice) minPrice = price[i + 1];
        }

        System.out.println(ans);
    }
}
