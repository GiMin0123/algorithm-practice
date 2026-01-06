import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       int n = Integer.parseInt(st.nextToken());
       int m = Integer.parseInt(st.nextToken());

       // char배열이 아닌 boolean 배열을 통해 비교만 빠르게 한다 (W이면 true B면 false로 저장)
       boolean[][] board = new boolean[n][m];

       for(int i=0; i<n; i++){
           String line = br.readLine();

           for(int j=0; j<m; j++){
               char c = line.charAt(j);

               // W면 true B면 false
               if(c=='W'){
                   board[i][j] = true;
               } else{
                   board[i][j] = false;
               }
           }
        }

        int min_cost=Integer.MAX_VALUE;
        for(int i=0; i<=n-8; i++){
            for(int j=0; j<=m-8; j++){

                // 비용 계산을 위한 초기화
                int count = 0;
                
                // 기준 색깔: 처음엔 true(흰색)라고 가정
                boolean check = true;
                
                // 8*8 사각형을 boolean 배열과 비교
                for(int k=i; k<i+8; k++) {
                    for(int p=j; p<j+8; p++) {
                        
                        // 내 칸(board)이 예상한 색(check)과 다르면 칠해야 함
                        if(board[k][p] != check) {
                            count++;
                        }
                        
                        // 다음 칸은 색이 바뀌어야 돼서 뒤집기
                        check = !check;
                    }
                    // 줄이 바뀔 때도 색 뒤집기
                    check = !check;
                }

                /* 
                체스판은 딱 2가지 경우가 있다
                전체 칸 수는 64개이므로,
                (검은색 시작 비용) = 64 - (흰색 시작 비용)로 계산하면 된다
                */
                count = Math.min(count, 64 - count);
                
                // 전체 최솟값 갱신
                min_cost = Math.min(min_cost, count);
            }
        }

        System.out.println(min_cost);

    }
}