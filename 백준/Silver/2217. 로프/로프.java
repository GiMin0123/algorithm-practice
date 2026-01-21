import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] ropes = new int[n];
        
        for(int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
        
        // 로프의 중량을 오름차순으로 정렬 
        Arrays.sort(ropes);
        
        int max_weight = 0;
        
        // 가장 약한 로프부터 하나씩 기준점(k개의 로프 중 최소 중량)으로 잡음
        for(int i = 0; i < n; i++) {
            int count = n - i;
            int current_weight = ropes[i] * count;
            
            // 계산된 중량이 기존 최대 중량보다 크다면 갱신
            if(current_weight > max_weight) {
                max_weight = current_weight;
            }
        }
        
        System.out.println(max_weight);
    }
}