import java.io.*;
import java.util.StringTokenizer;

public class BOJ14719 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in)); // 입력버퍼
        var bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력버퍼
        var st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken()); // H 입력
        int w = Integer.parseInt(st.nextToken()); // W 입력
        int[] arr = new int[w]; // 블럭의 높이를 저장할 배열
        int sum = 0; // 고인 물의 칸 수를 저장할 변수
        st = new StringTokenizer(br.readLine());
        br.close(); // 입력버퍼 닫기
        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); // 블럭 높이 저장
        }
        for (int i = 1; i <= h; i++) { // 각 높이마다 한줄씩 빈칸과 블럭 검사
            int j = 0; // 각 칸의 높이 변수
            int k = 0; // 고인 물의 칸수(빈칸)를 계산할 변수
            while (j < w) { // 현재 검사하는 높이에서 처음 만나는 블럭이 있는곳까지 j 전진
                // (첫 블럭을 만나기 전까진 물이 고이지 않음)
                if (arr[j] >= i) break;
                else j++;
            }
            while (j < w) {
                if (arr[j] >= i) { // 블럭을 만나면
                    sum += k; // 고인 물의 칸수를 sum에 추가
                    k = 0; // 지금까지 센 빈칸을 초기화
                } else { // 빈칸이면
                    k++; // 물이 고일 수 있으므로 k + 1
                }
                j++; // j 전진
            }
        }
        bw.write("" + sum); // 버퍼에 답 작성
        bw.flush(); // 버퍼 출럭
        bw.close(); // 출력버퍼 닫기
    }
}
