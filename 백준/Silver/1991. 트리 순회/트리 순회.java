import java.io.*;
import java.util.*;

public class Main {
    static int[] left = new int[26];
    static int[] right = new int[26];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char l = st.nextToken().charAt(0);
            char r = st.nextToken().charAt(0);

            int idx = root - 'A';
            left[idx] = (l == '.') ? -1 : l - 'A';
            right[idx] = (r == '.') ? -1 : r - 'A';
        }

        // 전위 순회
        preorder(0);
        System.out.println();
        // 중위 순회
        inorder(0);
        System.out.println();
        // 후위 순회
        postorder(0);
        System.out.println();
    }

    static void preorder(int x) {
        if (x == -1) return;
        System.out.print((char)(x + 'A'));
        preorder(left[x]);
        preorder(right[x]);
    }

    static void inorder(int x) {
        if (x == -1) return;
        inorder(left[x]);
        System.out.print((char)(x + 'A'));
        inorder(right[x]);
    }

    static void postorder(int x) {
        if (x == -1) return;
        postorder(left[x]);
        postorder(right[x]);
        System.out.print((char)(x + 'A'));
    }
}
