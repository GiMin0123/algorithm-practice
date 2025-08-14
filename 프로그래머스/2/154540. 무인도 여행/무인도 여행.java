import java.util.*;
class Solution {
    static boolean[][] visited;
    static int n,m;
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        char[][] grid = new char[n][m];
        for(int i =0;i<n;i++){
            grid[i] = maps[i].toCharArray();
        }

        visited = new boolean[n][m];
        List<Integer> sums = new ArrayList<>();
        
        // 모든 칸을 훑으며 아직 방문하지 않은 '육지(숫자)'에서 BFS 시작
        for(int i=0;i<n;i++){    
            for(int j=0;j<m;j++){
                if(!visited[i][j] && grid[i][j] !='X'){
                    sums.add(bfs(grid, i, j));
                }
            }
        }
        
        if(sums.isEmpty()){
            return new int[]{-1};
        }
        Collections.sort(sums);
        int[] answer = new int[sums.size()];
        for(int i=0; i<sums.size(); i++){
            answer[i] = sums.get(i);
        }
        return answer;
        
    }
    
    private int bfs(char[][] grid,int x, int y){
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[x][y] = true;
        //queue에는 x,y,value(grid[x][y]) 값들이 들어가면 되나?
        //이 문제는 “거리”가 아니라 “합”이므로 큐 원소는 (x,y)만 있으면 충분하다
        q.offer(new int[]{x,y});
        
        int sum = 0;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            
            // 현재 칸의 값 더하기 ('1'~'9' 보장)
            sum += grid[r][c] - '0';
            
            for(int k=0;k<4;k++){
                int nx = r + dx[k], ny = c+dy[k];
                if(nx<0 || nx>=n || ny<0 || ny>=m){
                    continue;
                }
                if(visited[nx][ny] == true){
                    continue;
                }
                if(grid[nx][ny] == 'X'){
                    continue;
                }
                visited[nx][ny]=true;
                q.offer(new int[]{nx,ny});
            }
        }    
        return sum;
    }
}