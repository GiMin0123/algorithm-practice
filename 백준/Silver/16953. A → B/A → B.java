import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long count = 1;

        while(b>a){
            // b의 마지막 자리수가 1인 경우 다시 1을 오른쪽에서 빼준다
            if(b%10==1){
                b/=10;
                count++;
            }
            // 만약 2로 나눠지는 경우 그냥 2로 나눔 
            else if(b%2 == 0){
                b/=2;
                count++;
            } 
            //둘다 불가능한 경우 아예 진행 불가
            else{
                System.out.println(-1);
                return;
            }
        }

        if(b==a) {
            System.out.println(count);
        } else{
            System.out.println(-1);
        }
    }
}