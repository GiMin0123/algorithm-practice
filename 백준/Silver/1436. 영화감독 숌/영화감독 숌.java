import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int end_count=1;

        int movie_title=666;

        while(end_count!=n){
            movie_title++;

            // int를 string으로 변환했을때 666이 포함되어있다면 end_count를 1 올림
            if(String.valueOf(movie_title).contains("666")){
                end_count++;
            }
        }

        System.out.println(movie_title);
    }
}