import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        double sum = 0;
        
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }
        
        System.out.println(Math.round(sum / N));
        
        // 정렬
        Arrays.sort(arr);
        
        System.out.println(arr[N / 2]);
        

        int count = 0;      // 현재 숫자의 빈도
        int max = -1;       // 지금까지 발견된 최대 빈도
        int mod = arr[0];   // 최빈값 결과 변수
        boolean check = false; // 두 번째로 작은 값을 찾았는지 확인한다
        
        for(int i = 0; i < N - 1; i++) {
            if(arr[i] == arr[i + 1]) {
                count++;
            } else {
                count = 0; // 숫자가 달라지면 빈도 초기화
            }
            
            // 현재 빈도가 최대 빈도보다 크다면 (새로운 최빈값 발견)
            if(max < count) {
                max = count;
                mod = arr[i];
                check = true; 
            } 
            else if(max == count && check == true) {
                mod = arr[i];
                check = false;
            }
        }
        System.out.println(mod);
        System.out.println(arr[N - 1] - arr[0]);
    }
}