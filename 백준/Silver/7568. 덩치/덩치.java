import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;

       int n = Integer.parseInt(br.readLine());

       int[][] people = new int[n][2];

       for(int i=0; i<n; i++){
           st = new StringTokenizer(br.readLine());

           people[i][0] = Integer.parseInt(st.nextToken());
           people[i][1] = Integer.parseInt(st.nextToken());
       }

       for(int i=0; i<n; i++){
           int rank=1;
           for(int j=0; j<n; j++){
               if(people[i][0]<people[j][0] && people[i][1]<people[j][1]){
                   rank++;
                } 
            }
            System.out.print(rank+" ");
        }
        System.out.println("");
        
    }
}