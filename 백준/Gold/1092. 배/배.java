import java.util.*;
import java.io.*;

public class Main
{
    
    public static void main(String[] args) throws IOException
    {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       int n = Integer.parseInt(st.nextToken());

        int[] cranes = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine().trim());
        int[] boxes = new int[m];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++){
          boxes[i] = Integer.parseInt(st.nextToken());
        } 

        Arrays.sort(cranes); // 오름차순
        Arrays.sort(boxes);  // 오름차순

        // 만약 박스무게가 크레인 무게제한보다 크다면 들지 못한다. 
        if (boxes[m - 1] > cranes[n - 1]) {
            System.out.println(-1);
            return;
        }

        // 내림차순으로 구히기 위해 뒤에서 접근
        boolean[] used = new boolean[m];
        int moved = 0;
        int minutes = 0;

        // 가장 큰 크레인부터 순회
        while(moved < m){
            int j = m-1;
            for(int i = n-1; i>=0; i--){
                while(j >= 0){
                    if(!used[j] && cranes[i] >= boxes[j]){
                        used[j] = true;
                        moved++;
                        j--;
                        break;
                    }
                    // 이 박스는 못 들거나 이미 사용됨 → 더 가벼운 쪽으로 이동
                    j--; 
                }
                if(moved == m) break;
            }

            minutes++;
        }

        System.out.println(minutes);
    }
}