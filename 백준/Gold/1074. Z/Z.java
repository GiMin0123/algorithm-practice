import java.io.*;
import java.util.*;

public class Main {
    
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()); 
        int c = Integer.parseInt(st.nextToken()); 

        int size = (int) Math.pow(2, n);

        find_z(size, r, c);

        System.out.println(count);
    }

    public static void find_z(int size, int r, int c) {
        // 종료 조건 사이즈가 1이 된다면
        if (size == 1) {
            return;
        }

        int half = size / 2;

        // 1사분면 : r, c 모두 half보다 작음
        if (r < half && c < half) {
            find_z(half, r, c);
        }
        // 2사분면 : r은 작지만, c는 half 이상
        else if (r < half && c >= half) {
            count += size * size / 4; // 1사분면 크기만큼 더하고 건너뜀
            find_z(half, r, c - half);
        }
        // 3사분면 : r은 half 이상, c는 작음
        else if (r >= half && c < half) {
            count += (size * size / 4) * 2; // 1, 2사분면 크기만큼 더하고 건너뜀
            find_z(half, r - half, c); 
        }
        // 4사분면 : r, c 모두 half 이상
        else {
            count += (size * size / 4) * 3; // 1, 2, 3사분면 크기만큼 더하고 건너뜀
            find_z(half, r - half, c - half); 
        }
    }
}