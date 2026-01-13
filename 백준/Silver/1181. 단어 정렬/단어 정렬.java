import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] word_arr = new String[n];

        for(int i=0; i<n; i++){
            word_arr[i] = br.readLine();
        }

        // comparator 재정의
        Arrays.sort(word_arr, new Comparator<String>() {
            @Override
            public int compare(String word1, String word2){
                // 길이가 같은지 검사 -> 사전 순으로 정렬
                if(word1.length() == word2.length()){
                    return word1.compareTo(word2);
                }
                else { // 길이가 다르다면? 길이 순으로 정렬
                    return word1.length() - word2.length();
                }
            }
        });

        System.out.println(word_arr[0]);

        // 두 번째 단어부터 앞 단어와 비교하며 다를 때만 출력
        for (int i = 1; i < n; i++) {
            if (!word_arr[i].equals(word_arr[i - 1])) {
                System.out.println(word_arr[i]);
            }
        }
    }
}