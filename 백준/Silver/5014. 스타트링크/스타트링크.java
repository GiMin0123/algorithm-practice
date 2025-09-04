
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 총 몇층
		int f = Integer.parseInt(st.nextToken());
		// 시작 층
		int s = Integer.parseInt(st.nextToken());
		// 도착 층
		int g = Integer.parseInt(st.nextToken());
		// 올릴때 몇 층 올릴지
		int u = Integer.parseInt(st.nextToken());
		// 내릴때 몇 층 내릴지
		int d = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[f+1];
		int minCount=Integer.MAX_VALUE;
		
		int count = bfs(s, g, visited, u, d, f);
		if (count == -1) {
		    System.out.println("use the stairs");
		} else {
		    System.out.println(count);
		}
	}
	
	static int bfs(int s, int g, boolean[] visited,int u, int d, int f) {
		
		int[] dist = new int[f+1];
	    Arrays.fill(dist, -1);

	    ArrayDeque<Integer> q = new ArrayDeque<>();
	    q.offer(s);
	    visited[s] = true;
	    dist[s] = 0;
		
		 while (!q.isEmpty()) {
		        int cur = q.poll();

		        if (cur == g) return dist[cur]; // 목표층 도착

		        // 위로 이동
		        int up = cur + u;
		        if (up <= f && !visited[up]) {
		            visited[up] = true;
		            dist[up] = dist[cur] + 1;
		            q.offer(up);
		        }

		        // 아래로 이동
		        int down = cur - d;
		        if (down >= 1 && !visited[down]) {
		            visited[down] = true;
		            dist[down] = dist[cur] + 1;
		            q.offer(down);
		        }
		    }

		    return -1; // 도달 불가능
	}
}
