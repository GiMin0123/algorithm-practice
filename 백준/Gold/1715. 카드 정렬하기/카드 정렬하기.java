import java.util.*;
import java.io.*;

public class Main
{
    
    public static void main(String[] args) throws IOException
    {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       int n = Integer.parseInt(st.nextToken());

       PriorityQueue<Integer> pq = new PriorityQueue<>();

       int k,num1,num2,cost,sum=0;

       for(int i=0; i<n; i++){
           st = new StringTokenizer(br.readLine());
           k = Integer.parseInt(st.nextToken());
           pq.add(k);
       }

        if (n <= 1) {
            System.out.println(0);
            return;
        }

       
        while(pq.size()>=2){
            num1 = pq.poll();
            num2 = pq.poll();
            cost = num1+num2;
            sum+= num1 + num2;
            pq.add(cost);
        }

        System.out.println(sum);
    }
}