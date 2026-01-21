import java.io.*;
import java.util.*;

public class Main {
    static class Hint {
        String number;
        int strike;
        int ball;

        public Hint(String number, int strike, int ball) {
            this.number = number;
            this.strike = strike;
            this.ball = ball;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        ArrayList<Hint> hints = new ArrayList<>();

        // 민혁이가 질문한 숫자와 그에 대한 답변 저장
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String num = st.nextToken();
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            hints.add(new Hint(num, s, b));
        }

        int possible_answer_count = 0;

        // 123부터 987까지 모든 숫자를 후보로 가정하고 검사
        for (int i = 123; i <= 987; i++) {
            String candidate = String.valueOf(i);

            // [조건 1] 숫자 야구 규칙상 0이 포함되면 안 됨
            if (candidate.contains("0")) {
                continue;
            }

            // [조건 2] 각 자리 숫자가 서로 달라야 함 (중복 불가)
            char first = candidate.charAt(0);
            char second = candidate.charAt(1);
            char third = candidate.charAt(2);

            if (first == second || first == third || second == third) {
                continue;
            }

            // 현재 후보 숫자(candidate)가 주어진 모든 힌트를 만족하는지 검사
            boolean is_valid = true;

            for (Hint hint : hints) {
                String target = hint.number;
                int expected_strike = hint.strike;
                int expected_ball = hint.ball;

                int current_strike = 0;
                int current_ball = 0;

                // 스트라이크와 볼 개수
                for (int j = 0; j < 3; j++) {
                    // 1. 스트라이크 판정: 자릿수와 숫자가 모두 같을 때
                    if (candidate.charAt(j) == target.charAt(j)) {
                        current_strike++;
                    }
                    // 2. 볼 판정: 숫자는 포함되어 있지만 자릿수가 다를 때
                    else if (candidate.contains(String.valueOf(target.charAt(j)))) {
                        current_ball++;
                    }
                }

                // 만약 계산한 스트라이크/볼 개수가 힌트와 다르다면 정답이 아님
                if (current_strike != expected_strike || current_ball != expected_ball) {
                    is_valid = false;
                    break;
                }
            }

            // 모든 힌트를 통과했다면 가능한 정답 개수 증가
            if (is_valid) {
                possible_answer_count++;
            }
        }

        System.out.println(possible_answer_count);
    }
}