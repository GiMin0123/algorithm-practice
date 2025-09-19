import java.io.*;
import java.util.*;

public class Main {
    // tip: arguments are passed via the field below this editor
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = new int[i+1];
            for(int j=0;j<i+1;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i= n-2; i>=0;i--){
            for(int j=0; j<=i; j++){
                arr[i][j] += Math.max(arr[i+1][j], arr[i+1][j+1]);
            }
        }

        System.out.println(arr[0][0]);
    }
}