import java.util.*;
import java.io.*;

public class Main
{
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        // 2이상인 양수들에 대한 내림차순
        PriorityQueue<Integer> posMax = new PriorityQueue<>(Collections.reverseOrder());

        // 음수들에 대한 오름차순
        PriorityQueue<Integer> negMin = new PriorityQueue<>();
        int ones=0;
        int zeros=0;
        for(int i = 0; i<n; i++){
            int w = Integer.parseInt(br.readLine());
            if(w>=2){
                posMax.add(w);
            } else if(w == 1){
                ones++;
            } else if(w == 0){
                zeros++;
            } else{
              negMin.add(w);
            }
        }

        long sum = 0L;

        // 2이상인 양수들은 따로 곱해준다
        while(posMax.size()>=2){
           
           int a = posMax.poll();
           int b = posMax.poll();
           sum += 1L*a*b;
        }
        // 하나 남으면 그대로 더함
        if (!posMax.isEmpty()) {
            sum += posMax.poll(); 
        }

        // 1인 양수들은 더해준다.
        sum+=ones;

        // 음수들끼리 따로 곱해주면 양수가 된다.
        while(negMin.size()>=2){
            int a = negMin.poll();
            int b = negMin.poll();
            sum += 1L*a*b;
        }
        // 우선순위큐에 1개 남은 경우
        // 만약 수열에 0이 있다면 곱하기를 통해 0으로 만든다.
        if(negMin.size() == 1){
            if(zeros > 0){
                negMin.poll();
                zeros--;
            } else{
                sum += negMin.poll();
            }
        }
        System.out.println(sum);
    }
}