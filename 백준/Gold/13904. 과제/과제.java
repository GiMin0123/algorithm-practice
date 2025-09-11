import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
    	int[][] assign = new int[n][2];
    	
    	for (int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
			assign[i][0] = Integer.parseInt(st.nextToken());
			assign[i][1] = Integer.parseInt(st.nextToken());
		}
		
    	// 이차원 배열 점수 정렬 위한 comparator
    	Arrays.sort(assign, new Comparator<int[]>() {
    		@Override
    		public int compare(int[] o1, int[] o2) {
        		//o1[1]이 더 크면 앞으로 이동
    			if(o1[1] > o2[1]) {
        			return -1;
    			} else if(o1[1] < o2[1]) {
        			return 1;
    			} else {
    				return 0;
    			}
    		}
		});
    	
    	 // 전체 달력 길이 = 최대 마감일
        int maxD = 0;
        for (int[] a : assign) if (a[0] > maxD) maxD = a[0];
        
        // dayUsed[t] = t일차에 과제를 이미 배정했는가?
        boolean[] dayUsed = new boolean[maxD+1];
        		
        int answer = 0;
        for(int[] a: assign) {
            int d = a[0];
            int w = a[1];
            
            // d일부터 1일까지 역으로 탐색하여 비어있는 가장 늦은 날 찾는다
            for(int t = Math.min(d,  maxD); t >= 1; t--) {
                if(!dayUsed[t]) {
                    dayUsed[t]= true;
                    answer += w;
                    break;
                }
            }
        }
        
        System.out.println(answer);
    }
}