import java.util.*;
import java.io.*;

public class Main
{
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n = 보석 개수, k = 가방 개수
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        // 보석 대기열: [무게, 가격] 무게 오름차순 (가벼운 것부터 꺼냄)
        PriorityQueue<int[]> waiting = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            waiting.offer(new int[]{m,v});
        }

        // 가방은 무게 기준 오름차순
        PriorityQueue<Integer> bags = new PriorityQueue<>();

        for(int j=0; j<k; j++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            bags.offer(c);
        }

        // 후보군: 가격 최대 힙 (가방에 들어갈 수 있는 보석들 중 가장 비싼 것부터 꺼냄)
        PriorityQueue<int[]> jewel = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));


        long sum =0L;
        while(!bags.isEmpty()){
            int capacity = bags.poll();
            
            // 현재 가방에 들어갈 수 있는 모든 보석을 후보군으로 이동
            while(!waiting.isEmpty() && waiting.peek()[0] <= capacity){
                jewel.offer(waiting.poll());
            }

            // 후보군 중 가장 비싼 보석을 담는다
            if(!jewel.isEmpty()){
                sum += jewel.poll()[1];
            }
        }

        System.out.println(sum);
    }
}