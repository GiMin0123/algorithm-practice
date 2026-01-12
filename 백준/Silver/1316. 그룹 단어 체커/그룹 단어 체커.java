import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       int n = Integer.parseInt(br.readLine());
       int group_count=0;

       for(int i=0; i<n; i++){
           String word = br.readLine();

           // 이 단어가 그룹 단어인지 체크하기 위한 변수들
            boolean[] visited = new boolean[26]; // 알파벳 a~z 방문 여부 체크
            boolean flag = true; // 그룹 단어라고 가정하고 시작
            char prev_char = ' '; // 바로 직전 문자를 저장

           for (int j = 0; j < word.length(); j++) { // j는 단어 내부의 글자 순서
                char current_char = word.charAt(j);
                
                // 직전 문자와 다른 문자가 나왔는데 (연속된 게 아님)
                if (prev_char != current_char) {
                    
                    // 그 문자가 이미 이전에 나왔던 문자라면?
                    if (visited[current_char - 'a']) {
                        flag = false;
                        break;
                    }
                    
                    // 처음 나온 문자라면?
                    visited[current_char - 'a'] = true;
                    prev_char = current_char;
                }
                
                // 직전 문자와 같으면 그냥 통과
            }

            // 끝까지 검사했는데 flag가 true라면 카운트 증가
            if(flag){
                group_count++;
            }
        }

        System.out.println(group_count);
    }
}