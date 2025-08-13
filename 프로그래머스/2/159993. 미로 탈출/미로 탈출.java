import java.util.*;
class Solution {
    static int n,m;
    static int start_x , start_y;
    static int lever_x, lever_y;
    static int end_x, end_y, answer = 0;
    static boolean[][] visited;
    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        char[][] grid = new char[n][m];
    
        //격자 문양으로 변환 및 시작 지점과 레버, 출구 위치 매핑
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                grid[i][j] = maps[i].charAt(j);
                if(grid[i][j] == 'S') {
                    start_x = i;
                    start_y = j;
                } else if(grid[i][j] == 'L'){
                    lever_x = i;
                    lever_y = j;
                } else if(grid[i][j] == 'E'){
                    end_x = i;
                    end_y = j;
                }
                
            }
        }
        
        // 시작점부터 레버까지
        int d1 = bfs(grid, start_x, start_y, lever_x, lever_y); 
        if (d1 == -1) return -1;

        //레버에서 출구까지
        int d2 = bfs(grid, lever_x, lever_y, end_x, end_y);   
        if (d2 == -1) return -1;

        return d1 + d2;
    }
    
    private int bfs(char[][] grid, int sx, int sy, int tx, int ty){
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        
        // visited는 매 BFS마다 새로 초기화
        // S->L 탐색의 방문 흔적이 L->E에 영향을 주면 안 되기 때문
        visited = new boolean[n][m];
        
        // ArrayDeque를 큐로 사용. 원소: {x, y, dist}
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy,0});
        visited[sx][sy]=true;
        
        while(!q.isEmpty()){
            // FIFO로 하나 꺼낸다. BFS이므로 꺼낸 시점의 dist가 그 칸의 최단거리.
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], d = cur[2];
            
            // 목표 좌표에 도달하면 그 순간의 d가 최소 시간.
            if(x == tx && y == ty) {
                return d;
            }
            
            for(int k=0;k<4;k++){
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if(nx < 0 || nx >=n || ny < 0 || ny>=m){
                    continue;
                }
                if(grid[nx][ny] == 'X'){
                    continue;
                }
                if(visited[nx][ny]) {
                    continue;
                }
                
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny, d+1});
            }
        }
        //도달 불가
        return -1;
    }      
}