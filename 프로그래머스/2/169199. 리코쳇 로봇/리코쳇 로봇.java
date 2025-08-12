import java.util.*;
class Solution {
    
    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};
    
    public int solution(String[] board) {
        
        int n = board.length;
        int m = board[0].length();
        char[][] board_2d = new char[n][m];
        int sr = -1, sc = -1; //시작 위치 R
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                char c = board[i].charAt(j);   
                board_2d[i][j] = c;
                if(c == 'R'){
                    sr = i;
                    sc = j;
                }
            }   
        }
        
        boolean[][] visited = new boolean[n][m];
        Deque<int[]> q = new ArrayDeque<>();
        // r,c, 이동횟수
        q.offer(new int[]{sr,sc,0});
        visited[sr][sc] = true;
    
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r=cur[0], c=cur[1], dist=cur[2];
            
            if(board_2d[r][c] == 'G'){
                return dist; //최초 도달이 최소 이동
            }
            
            for(int k=0;k<4;k++){
                int nr = r, nc = c;
                // 장애물이나 벽에 닿기 직전까지 미끄러짐
                while(true){
                    int tr = nr + dx[k];
                    int tc = nc + dy[k];
                    
                    if(tr<0 || tr>=n || tc<0 || tc>=m) {
                        break;
                    }
                    if(board_2d[tr][tc] == 'D'){
                        break;
                    }
                    
                    nr = tr; nc = tc;
                }
                if(!visited[nr][nc]){
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr,nc,dist+1});
                }
            }
        }
        return -1;
            
    }
    
}