
import java.io.*;
import java.util.*;

public class Main {
	
	static final int[] dy = {-1,1,0,0};
	static final int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j]= Integer.parseInt(st.nextToken()); 
			}
		}
		
		boolean[][] visited = new boolean[n][m];
		int maxArea=0;
		int count=0;
		
		for(int i=0; i<n; i++) {
			
			for(int j=0; j<m; j++) {
				
				if(visited[i][j]== false && arr[i][j]==1 ) {
					int area =bfs(i, j, arr, visited, n,m);
					count++;
					if(area>maxArea) maxArea = area;
				}
			}
		}
		
		System.out.println(count);
		System.out.println(maxArea);

	}
	
	static int bfs(int sy, int sx, int[][] arr, boolean[][] visited,int n, int m) {
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[sy][sx]= true;
		q.offer(new int[] {sy,sx});
		
		int count=1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0], x = cur[1];
			
			for(int i=0;i<4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if( nx<0 || ny<0 || nx>=m || ny>=n) continue;
				if(visited[ny][nx]) continue;
				if(arr[ny][nx]== 0 ) continue;
				
				visited[ny][nx]=true;
				q.offer(new int[] {ny,nx});
				count++;
			}
		}
		
		return count;
	}
}
