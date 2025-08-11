class Solution {
    public int solution(int n, int w, int num) {
        // 1) 내 상자의 행과 열 찾기
        int r = (num - 1) / w;         // 내 행(0-based)
        int p = (num - 1) % w;         // 그 행 안에서의 순서(0..w-1)
        int c = (r % 2 == 0) ? p : (w - 1 - p); // 지그재그 보정한 실제 열

        // 2) 전체 행 수와 마지막 행의 실제 길이
        int H = (n + w - 1) / w;               // 전체 행 수
        int lastRow = H - 1;
        int lastLen = n - w * (H - 1);         // 마지막 행에 실제로 놓인 개수
        if (lastLen == 0) lastLen = w;

        // 3) r행부터 꼭대기까지, 해당 열에 상자가 있으면 +1
        int count = 0;
        for (int row = r; row <= lastRow; row++) {
            int rowLen = (row == lastRow) ? lastLen : w;

            boolean exists;
            if (row % 2 == 0) {
                // 짝수 행: 왼쪽부터 rowLen개가 채워짐 → 열 0..rowLen-1만 유효
                exists = (c < rowLen);
            } else {
                // 홀수 행: 오른쪽부터 rowLen개가 채워짐 → 열 (w-rowLen)..(w-1)만 유효
                exists = (c >= w - rowLen);
            }

            if (exists) count++;
        }
        return count;
    }
}