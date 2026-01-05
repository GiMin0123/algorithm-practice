import java.io.*;
import java.util.*;

public class Main
{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        boolean[][] paper = new boolean[100][100]; //도화지 (100*100)
        int totalArea = 0; // 색종이 영역 넓이

        // 각각의 도화지를 저장
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 가로 시작 거리
            int y = Integer.parseInt(st.nextToken()); // 세로 시작 거리

            // 시작점 (X,Y)부터 10칸씩 칠하기
            for(int j=x; j<x+10; j++){
                for(int k=y; k<y+10; k++){
                    //만약 아직 색칠이 안 된 부분이라면
                    if(!paper[j][k]){
                        paper[j][k] = true;
                        totalArea++;
                    }
                }
            }
        }

        System.out.println(totalArea);


    }
}