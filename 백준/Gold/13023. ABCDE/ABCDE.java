import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<Integer>[] adjacency_list; // 친구 관계 저장
    static boolean[] visited;
    static boolean arrive_flag = false; // 깊이 5에 도달했는지 확인하는 플래그

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 사람의 수
        m = Integer.parseInt(st.nextToken()); // 친구 관계의 수

        // 인접 리스트 초기화
        adjacency_list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacency_list[i] = new ArrayList<>();
        }

        // 친구 관계 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjacency_list[a].add(b);
            adjacency_list[b].add(a);
        }

        visited = new boolean[n];

        // 0번 사람부터 n-1번 사람까지 돌아가며 시작점으로 지정
        for (int i = 0; i < n; i++) {
            dfs(i, 1); 

            // 만약 깊이 5인 관계를 찾았다면
            if (arrive_flag) {
                System.out.println(1);
                return;
            }
        }

        // 끝까지 못 찾았다면
        System.out.println(0);
    }

    // DFS
    public static void dfs(int current_node, int depth) {
        // 깊이가 5가 되면
        if (depth == 5) {
            arrive_flag = true;
            return;
        }

        visited[current_node] = true; // 현재 노드 방문 처리

        for (int next_node : adjacency_list[current_node]) {
            // 아직 방문하지 않은 친구라면
            if (!visited[next_node]) {
                dfs(next_node, depth + 1);
                
                // 탐색하고 돌아왔는데 이미 정답을 찾았다면
                if (arrive_flag) {
                    return;
                }
            }
        }
        
        visited[current_node] = false;
    }
}