import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
    	int[][] classes = new int[n][2];
    	
    	for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			classes[i][0] = Integer.parseInt(st.nextToken());
			classes[i][1] = Integer.parseInt(st.nextToken());
		}
		
    	Arrays.sort(classes, (a,b) -> a[0] - b[0]);
    	
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	
    	for(int[] c : classes) {
        	//가장 빨리 끝나는 강의실이 현재 수업 시작 전, 동시에 끝난다면 재사용
    		if(!pq.isEmpty() && pq.peek() <= c[0]) {
        		pq.poll();
    		}
    		
    		//현재 수업 새로 배정
    		pq.offer(c[1]);
    	}
    	
    	System.out.println(pq.size());
    }
}