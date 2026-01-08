import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] ability_map;
    static boolean[] is_start_team; // 스타트 팀 여부를 체크할 배열 (true면 스타트팀, false면 링크팀)
    static int min_diff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        ability_map = new int[n][n];
        is_start_team = new boolean[n];

        // 능력치 맵 입력
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                ability_map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0번 사람부터 시작하여 N/2명을 뽑는 조합 탐색
        make_team(0, 0);

        System.out.println(min_diff);
    }

    // N명 중 N/2명을 뽑아 스타트 팀으로 설정하는 함수 (백트래킹)
    // index: 현재 탐색 중인 사람의 인덱스
    // count: 현재까지 스타트 팀에 뽑힌 사람 수
    public static void make_team(int index, int count) {
        // N/2명이 모두 뽑혔으면 능력치 차이 계산
        if (count == n / 2) {
            calculate_difference();
            return;
        }

        for (int i = index; i < n; i++) {
            // 아직 방문하지 않은 사람이라면 스타트 팀으로 선택
            if (!is_start_team[i]) {
                is_start_team[i] = true;
                make_team(i + 1, count + 1);
                is_start_team[i] = false; // 백트래킹 (선택 해제)
            }
        }
    }

    // 두 팀의 능력치 차이를 계산하는 함수
    public static void calculate_difference() {
        int start_team_score = 0;
        int link_team_score = 0;

        // 모든 사람 쌍(i, j)을 확인하며 점수 합산
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                
                // 두 사람 모두 스타트 팀인 경우
                if (is_start_team[i] && is_start_team[j]) {
                    start_team_score += ability_map[i][j];
                    start_team_score += ability_map[j][i];
                }
                // 두 사람 모두 링크 팀인 경우 (is_start_team이 둘 다 false)
                else if (!is_start_team[i] && !is_start_team[j]) {
                    link_team_score += ability_map[i][j];
                    link_team_score += ability_map[j][i];
                }
            }
        }

        // 차이의 절대값 계산 및 최솟값 갱신
        int val = Math.abs(start_team_score - link_team_score);
        
        // 가지치기: 차이가 0이면 더 이상 줄일 수 없으므로 바로 출력하고 종료해도 됨 (최적화 포인트)
        if (val == 0) {
            System.out.println(val);
            System.exit(0);
        }
        
        min_diff = Math.min(min_diff, val);
    }
}