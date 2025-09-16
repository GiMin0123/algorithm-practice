import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        int[][] board = new int[n][m];
        
        for(int i=0; i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] direction = new int[k];
        for(int i=0; i<k; i++){
            direction[i] = Integer.parseInt(st.nextToken());
        }

        // dice[0]=top, [1]=bottom, [2]=north, [3]=south, [4]=east, [5]=west
        int[] dice = new int[6];
        for (int i = 0; i < k; i++) {
            int d = direction[i];
            int nx = x, ny = y;

            if (d == 1) ny++;      // 동
            else if (d == 2) ny--; // 서
            else if (d == 3) nx--; // 북
            else if (d == 4) nx++; // 남

            // 지도 범위 체크
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            // 주사위 회전
            int t = dice[0], b = dice[1], nn = dice[2], ss = dice[3], e = dice[4], w = dice[5];
            if (d == 1) {          // 동
                dice[0] = w; dice[1] = e; dice[4] = t; dice[5] = b;
            } else if (d == 2) {   // 서
                dice[0] = e; dice[1] = w; dice[4] = b; dice[5] = t;
            } else if (d == 3) {   // 북
                dice[0] = ss; dice[1] = nn; dice[2] = t; dice[3] = b;
            } else {               // 남
                dice[0] = nn; dice[1] = ss; dice[2] = b; dice[3] = t;
            }
            if (d == 1 || d == 2) { dice[2] = nn; dice[3] = ss; }
            else { dice[4] = e; dice[5] = w; }

            x = nx; y = ny;

            // 지도와 주사위 바닥 교환
            if (board[x][y] == 0) {
                board[x][y] = dice[1];
            } else {
                dice[1] = board[x][y];
                board[x][y] = 0;
            }

            // 이동 후 윗면 즉시 출력
            System.out.println(dice[0]);
        }
    }
}