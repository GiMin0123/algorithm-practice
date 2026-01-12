import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       int n = Integer.parseInt(br.readLine());

       int count=0;

       // 1부터 n까지 한수인지 브루트포스
       for(int i=1; i<=n; i++) {
           if(check_hansu(i)) {
               count++;
           }
        }

        System.out.println(count);
    }

    // 해당 숫자가 등차수열에 포함되는지 판별하는 함수
    public static boolean check_hansu(int num){

        // 100 미만의 수(1~99)는 모두 등차수열이다
        if(num<100) {
            return true;
        }
        else if(num==1000) { // 1000은 등차수열이 아니다
            return false;
        } 
        else { //100이상 1000 미만의 수
            int hundred = num / 100;
            int ten = (num / 10) % 10;
            int one = num % 10;

            //등차수열 조건: (백의자리 - 십의 자리) == (십의 자리 - 일의 자리)
            if((hundred - ten) == (ten - one)) {
                return true;
            } else {
                return false;
            }
        }
    }
}