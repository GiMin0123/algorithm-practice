import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); // 세로
        int m = Integer.parseInt(st.nextToken()); // 가로
        int b = Integer.parseInt(st.nextToken()); // 블록 개수
    
        int minTime = Integer.MAX_VALUE;
        int maxHeight = -1;

        int[][] arr = new int[n][m];

        // 지도 입력 받기
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());    
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int h=0; h <= 256; h++){
            
            int remove = 0; // 블록 제거 개수
            int add = 0;    // 블록 추가 개수
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
            
                    int currentHeight = arr[i][j];
            
                    if (currentHeight > h) {
                        remove += (currentHeight - h);
                    }
                    else if (currentHeight < h) { 
                        add += (h - currentHeight);
                    }
                }
            }

            // 불가능 한 경우 걸러내기
            if(b + remove >= add){
                int time = (remove * 2) + add;

                // 최소 시간 및 높이 갱신
                if(time < minTime){
                    minTime = time;
                    maxHeight = h;
                } 
                // 시간은 같은데, 지금 높이가 더 높다면 갱신
                else if(time == minTime){
                    maxHeight = h;
                }
            }
        }

        System.out.println(minTime + " " + maxHeight);
    }
}