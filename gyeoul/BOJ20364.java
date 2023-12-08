import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ20364 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine()); // 입력
        int n = Integer.parseInt(st.nextToken()) + 1; // 땅 개수 0을 사용하지 않으므로 + 1
        int q = Integer.parseInt(st.nextToken()); // 오리 수
        boolean[] arr = new boolean[n]; // 땅을 점유했는지 확인할 배열
        for (int idx = 0; idx < q; idx++) {
            int raw = Integer.parseInt(bf.readLine()); // 현재 오리가 원하는 땅 번호
            int ans = 0; // 다른 오리의 땅을 밟을 경우 변경
            for (int i = raw; i > 1; i /= 2) if (arr[i]) ans = i; // 수식에 따라 raw부터 1까지 탐색
            arr[raw] = true; // 현재 오리가 원하는 땅을 할당
            bw.write(ans + "\n"); // 정답 출력
        }
        bw.flush();
        bw.close();
    }
}
