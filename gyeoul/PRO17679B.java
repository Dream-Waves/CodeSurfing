import java.util.*;

public class PRO17679B {

    private int M;
    private int N;
    private final int[] dr = new int[]{0, 1, 1};
    private final int[] dc = new int[]{1, 0, 1};
    private final HashSet<Node> set = new HashSet<>();
    private ArrayList<ArrayList<Character>> arr;

    class Node implements Comparable {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return r == node.r && c == node.c;
        }

        @Override
        public int hashCode() {
            int result = r;
            result = 31 * result + c;
            return result;
        }

        @Override
        public int compareTo(Object o) {
            Node n = (Node) o;
            return r == n.r ? n.c - c : n.r - r;
        }
    }

    private ArrayList<ArrayList<Character>> rotate(String[] str) {
        ArrayList<ArrayList<Character>> res = new ArrayList<>();
        char[][] tmp = new char[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                tmp[j][i] = str[M - i - 1].charAt(j);
            }
        }
        for (int i = 0; i < N; i++) {
            ArrayList<Character> t = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                t.add(tmp[i][j]);
            }
            res.add(t);
        }
        return res;
    }

    void dfs(Node t) {
        if (t.r >= 0 && t.r < N && t.c >= 0 && t.c < M) {
            for (int i = 0; i < 3; i++) {
                int dy = dr[i] + t.r;
                int dx = dc[i] + t.c;
                if (dy < 0 || dy >= arr.size() || dx < 0 || dx >= arr.get(dy).size()) {
                    return;
                }
                if (!arr.get(dy).get(dx).equals(arr.get(t.r).get(t.c))) {
                    return;
                }
            }
            set.add(t);
            for (int i = 0; i < 3; i++) {
                int dy = dr[i] + t.r;
                int dx = dc[i] + t.c;
                set.add(new Node(dy, dx));
            }
        }
    }

    public int solution(int m, int n, String[] board) {
        M = m;
        N = n;
        int answer = 0;
        arr = rotate(board);
        while (true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dfs(new Node(i, j));
                }
            }
            if (set.isEmpty()) {
                break;
            } else {
                answer += set.size();
                Node[] tmp = set.toArray(new Node[0]);
                Arrays.sort(tmp);
                for (Node it : tmp) {
                    arr.get(it.r).remove(it.c);
                }
                set.clear();
            }

        }

        return answer;
    }
}

