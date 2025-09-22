import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] dp = new int[n];           // dp[i] = arr[i]로 끝나는 LIS 길이
        int[] prev = new int[n];         // prev[i] = i 직전 원소의 인덱스(없으면 -1)
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxLen = 1, maxIdx = 0;
        for(int i=0; i<n; i++){
            for(int j=0;j<i;j++){
                if(arr[j] < arr[i] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            // 전체 최장 갱신 시 끝 인덱스도 갱신
            if (dp[i] > maxLen) { 
                maxLen = dp[i];
                maxIdx = i;
            }
        }

        System.out.println(maxLen);

        int[] seq = new int[maxLen];
        int idx = maxLen-1;
        for(int cur = maxIdx; cur!= -1; cur=prev[cur]){
            seq[idx--] = arr[cur];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<maxLen; i++){
            if(i>0) {
                sb.append(' ');
            }
            sb.append(seq[i]);
        }
        System.out.println(sb.toString());
    }
}