import java.util.*;

class Solution {
    public long solution(String expression) {
        // 1) 수식 파싱: 숫자들, 연산자들 분리
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        parse(expression, nums, ops);

        // 2) 실제로 등장한 연산자만 수집 (예: +, -, * 중 일부만 있을 수 있음)
        LinkedHashSet<Character> opSet = new LinkedHashSet<>(ops); // 순서는 상관없지만 중복 제거
        List<Character> uniqueOps = new ArrayList<>(opSet);

        // 3) 등장 연산자의 모든 우선순위 순열을 만들고, 각 순열로 계산해 최대 절댓값 갱신
        List<List<Character>> orders = new ArrayList<>();
        permute(uniqueOps, 0, orders);

        long best = 0;
        for (List<Character> order : orders) {
            best = Math.max(best, Math.abs(evalByOrder(nums, ops, order)));
        }
        return best;
    }

    /** expression을 숫자 리스트와 연산자 리스트로 분리한다. */
    private void parse(String exp, List<Long> nums, List<Character> ops) {
        long cur = 0;
        boolean building = false;
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (ch >= '0' && ch <= '9') {
                // 숫자 구성 (long으로 누적)
                cur = cur * 10 + (ch - '0');
                building = true;
            } else {
                // 연산자 만나면 직전 숫자를 nums에 push
                if (building) {
                    nums.add(cur);
                    cur = 0;
                    building = false;
                }
                ops.add(ch); // '+', '-', '*'
            }
        }
        // 마지막 숫자 반영
        if (building) nums.add(cur);
    }

    /** uniqueOps를 제자리 순열(백트래킹)로 모두 생성하여 orders에 담는다. */
    private void permute(List<Character> uniqueOps, int idx, List<List<Character>> orders) {
        if (idx == uniqueOps.size()) {
            orders.add(new ArrayList<>(uniqueOps));
            return;
        }
        for (int i = idx; i < uniqueOps.size(); i++) {
            Collections.swap(uniqueOps, idx, i);
            permute(uniqueOps, idx + 1, orders);
            Collections.swap(uniqueOps, idx, i);
        }
    }

    /**
     * 주어진 우선순위(order)대로 연산을 차례로 "접어" 나가 최종 값을 반환.
     * 방법: nums/ops를 복사 → 가장 높은 우선순위 연산자부터 리스트를 순회하며
     * 해당 연산자 발견 시 즉시 계산하여 nums/ops를 제자리 축소.
     */
    private long evalByOrder(List<Long> nums, List<Character> ops, List<Character> order) {
        // 원본 보존을 위해 복사본 사용
        List<Long> a = new ArrayList<>(nums);
        List<Character> o = new ArrayList<>(ops);

        for (char target : order) {
            // o를 왼쪽→오른쪽으로 스캔하며 target인 곳에서 즉시 계산/축소
            for (int i = 0; i < o.size(); ) {
                if (o.get(i) == target) {
                    long left = a.get(i);
                    long right = a.get(i + 1);
                    long val = apply(left, right, target);

                    // a[i] <- val, a[i+1] 제거 / o[i] 제거
                    a.set(i, val);
                    a.remove(i + 1);
                    o.remove(i);
                    // i는 그대로(축소되었으므로 다음 인덱스가 자동으로 당겨짐)
                } else {
                    i++; // 대상 연산자가 아니면 다음으로
                }
            }
        }
        // 모든 연산을 소진하면 숫자 하나만 남는다.
        return a.get(0);
    }

    /** 두 피연산자와 연산자에 대해 실제 연산 수행 (long) */
    private long apply(long x, long y, char op) {
        switch (op) {
            case '+': return x + y;
            case '-': return x - y;
            case '*': return x * y;
        }
        throw new IllegalArgumentException("unknown op: " + op);
    }
}
