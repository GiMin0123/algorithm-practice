
import java.io.*;
import java.util.*;

public class Main {
	
	static final int[] dy = {-1,1,0,0};
	static final int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		boolean[][] visited = new boolean[n][n];
		int[][] arr= new int[n][n];
		
		for(int i =0; i<n; i++) {
			String line = br.readLine().trim();
			for(int j=0; j<n; j++) {
				arr[i][j]= line.charAt(j) - '0';
			}
		}
		
		//단지 수를 세기 위한 count
		List<Integer> sizes = new ArrayList<>();
		
		// 완전 탐색을 이용하여 집이 있는곳과 이미 방문했던 곳인지를 조건으로 bfs
		for(int y=0; y<n; y++) {
			for(int x=0; x<n; x++) {
				if(arr[y][x]==1 && visited[y][x]==false) {
					int sz = bfs(y, x, arr, visited, n);
					sizes.add(sz);
				}
			}
		}
		
		Collections.sort(sizes);
		System.out.println(sizes.size());
		for(int v : sizes) {
			System.out.println(v);
		}

	}
	
	static int bfs(int sy, int sx, int[][] arr, boolean[][] visited, int n) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[sy][sx]= true;
		q.offer(new int[]{sy,sx});
		int count =1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y= cur[0], x = cur[1];
			
			for(int dir = 0; dir<4;dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if( nx< 0 || ny < 0 || nx>=n || ny >= n) continue;
				if(visited[ny][nx]) continue;
				if(arr[ny][nx] == 0) continue;
				
				visited[ny][nx]= true;
				q.offer(new int[]{ny,nx});
				count++;
			}
		}
		
		return count;
	}
}
