import java.io.*;
import java.util.*;

class Point{
    int x,y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main {

    static ArrayList<Point> houses;
    static ArrayList<Point> chickens;

    // 전체 맵 크기(n), 남길 치킨집 개수(m)
    static int n,m;
    static int[][] map;

    // 방문 체크용 배열 (어떤 치킨집을 골랐는지 표시)
    static boolean[] open; 
    
    // 결과값(도시의 치킨 거리 최솟값) 저장용. 처음엔 아주 큰 값으로 초기화
    static int min_result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        // 리스트 초기화
        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        map = new int[n][n];


        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                // 입력 받은 값
                map[i][j] = Integer.parseInt(st.nextToken());

                // 1이면 집 리스트에 추가
                if (map[i][j] == 1) {
                    houses.add(new Point(i, j));
                }
                // 2면 치킨집 리스트에 추가
                else if (map[i][j] == 2) {
                    chickens.add(new Point(i, j));
                }
            }
        }
        open = new boolean[chickens.size()];
        dfs(0,0);

        System.out.println(min_result);
    }
    
    public static void dfs(int start, int count){
        
        //만약 m개의 치킨집을 다 골랐따면?
        if(count==m){
            //도시의 치킨 거리 계산
            int current_cityDistance = 0;

            for(int i=0; i< houses.size(); i++){
                int temp = Integer.MAX_VALUE;

                //선택된 치킨집들과의 거리 비교
                for(int j=0; j<chickens.size(); j++){
                    if(open[j]){
                        int dist = Math.abs(houses.get(i).x - chickens.get(j).x) + Math.abs(houses.get(i).y - chickens.get(j).y);

                        //가장 가까운 거리로 갱신
                        temp = Math.min(temp, dist);
                    }
                }

                current_cityDistance += temp;
            }

            //이번 조합의 결과가 최소값보다 작으면 갱신
            min_result = Math.min(min_result, current_cityDistance);
            return;
        }

        // start부터 시작해서 중복 없이 m개를 고름
        for(int i= start; i<chickens.size(); i++){
            open[i] = true;
            dfs(i+1, count + 1);
            open[i] = false;
        }
    }
}