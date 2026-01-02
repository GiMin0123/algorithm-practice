import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 문서의 개수
			int M = Integer.parseInt(st.nextToken()); // 몇 번째로 인쇄되었는지 궁금한 문서의 초기 위치

			// 배열을 저장하는 큐
			LinkedList<int[]> q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				q.offer(new int[] { i, Integer.parseInt(st.nextToken()) });
			}

			int count = 0; // 인쇄 순서 카운트

			while (!q.isEmpty()) {
				int[] current = q.poll(); // 맨 앞 문서를 꺼냄
				boolean isMax = true; // 현재 문서가 가장 높은 중요도인지 확인

				// 큐 내의 다른 문서들과 중요도 비교
				for (int i = 0; i < q.size(); i++) {
					if (current[1] < q.get(i)[1]) {
						// 더 중요도가 높은 문서가 존재함
						q.offer(current); // 현재 문서를 맨 뒤로 보냄
						for (int j = 0; j < i; j++) {
							q.offer(q.poll()); // 중요도가 높은 문서 전까지의 문서들도 뒤로 보냄
						}
						isMax = false;
						break;
					}
				}

				// 현재 문서보다 중요도가 높은 문서가 없으면 인쇄
				if (isMax == false) continue;

				count++; // 인쇄 횟수 증가
				if (current[0] == M) { // 찾으려는 문서라면 결과 저장 후 종료
					sb.append(count).append('\n');
					break;
				}
			}
		}
		System.out.println(sb);
	}
}