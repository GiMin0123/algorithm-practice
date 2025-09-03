import java.io.*;
import java.util.*;

public class Main {
	
	static final int[] dy = {-1,1,0,0};
	static final int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[m][n];
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			// K개의 직사각형 적용
			for(int y=y1; y<y2; y++) {
				for(int x=x1; x<x2; x++) {
					arr[y][x] = 1;
				}
			}
		}
		
		boolean[][] visited = new boolean[m][n];
		List<Integer> sizes = new ArrayList<Integer>();
		
		for(int i=0; i<m; i++) {
			
			for(int j=0; j<n; j++) {
				
				if(visited[i][j]== false && arr[i][j]==0 ) {
					int area =bfs(i, j, arr, visited, m,n);
					sizes.add(area);
				}
			}
		}
		
		Collections.sort(sizes);
		
		// 영역 개수
		System.out.println(sizes.size());
		
		// 각 넓이(오름차순)
		for(int i=0; i< sizes.size(); i++) {
			if(i > 0) System.out.print(" ");
			System.out.print(sizes.get(i));
		}
		System.out.println();

	}
	
	static int bfs(int sy, int sx, int[][] arr, boolean[][] visited,int m, int n) {
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
				
				if( nx< 0 || ny < 0 || nx>=n || ny >= m) continue;
				if(visited[ny][nx]) continue;
				if(arr[ny][nx] == 1) continue;
				
				visited[ny][nx]= true;
				q.offer(new int[]{ny,nx});
				count++;
			}
		}
		
		return count;
	}
}
