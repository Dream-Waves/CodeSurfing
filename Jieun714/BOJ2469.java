package Jieun714;
/**
 * 문제: k명의 참가자들이 사다리 타기를 통하여 어떤 순서를 결정한다. 참가자들은 알파벳 대문자 첫 k개로 표현되며, 사다리 타기를 시작할 때의 순서는 아래 그림과 같이 항상 알파벳 순서대로이다.
 *      사다리 타기는 세로 막대를 타고 내려오는 중에 가로막대를 만나면 그 쪽으로 옮겨 가면서 끝까지 내려가는 과정이다.  따라서 사다리 타기의 규칙 특성상 아래 그림과 같이 두 가로 막대가 직접 연결될 수는 없으므로 이 상황은 이 문제에서 고려할 필요가 없다.
 *      우리는 하나의 가로 줄이 감추어진 사다리를 받아서 그 줄의 각 칸에 가로 막대를 적절히 넣어서 참가자들의 최종 순서가 원하는 순서대로 나오도록 만들려고 한다.
 *      입력에서 사다리의 전체 모양은 각 줄에 있는 가로 막대의 유무로 표현된다. 각 줄에서 가로 막대가 없는 경우에는 ‘*’(별)문자, 있을 경우에는 ‘-’(빼기) 문자로 표시된다. 그리고 감추어진 특정 가로 줄은 길이 k-1인 ‘?’ (물음표) 문자열로 표시되어 있다.
 * 입력: 첫 줄에는 참가한 사람의 수 k가 나온다(3 ≤ k ≤ 26). 그 다음 줄에는 가로 막대가 놓일 전체 가로 줄의 수를 나타내는 n이 나온다(3 ≤ n ≤ 1,000). 그리고 세 번째 줄에는 사다리를 타고 난 후 결정된 참가자들의 최종 순서가 길이 k인 대문자 문자열로 들어온다.
 *      k와 n, 그리고 도착순서 문자열이 나타난 다음, 이어지는 n개의 줄에는 앞서 설명한 바와 같이 ‘*’와 ‘-’ 문자로 이루어진 길이 k-1인 문자열이 주어진다. 그 중 감추어진 가로 줄은 길이가 k-1인 ‘?’ 문자열로 표시되어 있다.
 * 출력: 입력 파일 세 번째 줄에서 지정한 도착순서가 해당 사다리에서 만들어질 수 있도록 감추어진 가로 줄을 구성해야 한다.
 *      여러분은 감추어진 가로 줄의 상태를 재구성하여 이를 ‘*’(별) 문자와 ‘-’(빼기) 문자로 구성된 길이 k-1인 문자열로 만들어 출력하면 된다.
 *      그런데 어떤 경우에는 그 감추어진 가로 줄을 어떻게 구성해도 원하는 순서를 얻을 수 없는 경우도 있다.  이 경우에는  ‘x’(소문자 엑스)로 구성된 길이 k-1인 문자열을 출력해야 한다.
 * 해결: 구현
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2469 {
    public static int k, n, idx;
    public static char [][] map;
    public static char [] start, end;
    public static StringBuilder sb;

    public static char [] calc(int i, char [] arr) {
        char [] newArr = new char[k];
        int now = 0; //현재 사다리를 타는 참가자 인덱스
        while(now < k) {
            if(now == k-1) { //마지막에 위치한 참가자일 경우
                if(map[i][k-2] == '*') newArr[now] = arr[now];
            } else {
                if(map[i][now] == '*') newArr[now] = arr[now];
                else { //사다리를 탈 경우 서로의 위치를 변경
                    newArr[now] = arr[now+1];
                    newArr[now+1] = arr[now];
                    now++;
                }
            }
            now++;
        }

        return newArr;
    }

    //위에서부터 감춰진 사다리까지 계산하는 함수
    public static void topDown() {
        for(int i=0; i<idx; i++) {
            start = calc(i, start);
        }
    }

    //아래에서부터 감춰진 사다리까지 계산하는 함수
    public static void bottomUp() {
        for(int i=n-1; i>idx; i--) {
            end = calc(i, end);
        }
    }

    public static void match() {
        int next = 0;
        while(next < k-1) {
            if(start[next] == end[next]) sb.append("*");
            else {
                if(next != k-1) {
                    if(start[next] == end[next+1]) {
                        sb.append("-");
                        if(next != k-2) sb.append("*");
                        next++;
                    }
                }
            }
            next++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine()); //참가한 사람의 수
        n = Integer.parseInt(br.readLine()); //가로 막대가 놓일 전체 가로 줄의 수
        end = br.readLine().toCharArray();
        map = new char[n][k-1];
        start = new char[k];
        sb = new StringBuilder();

        for(int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
            if(map[i][0] == '?') idx = i; //감춰진 사다리 위치
        }
        for(int i=0; i<k; i++) start[i] = (char) (65+i); //참가자는 알파벳 순서대로 대기

        topDown(); //위에서부터 감춰진 사다리까지 계산
        bottomUp(); //아래부터 감춰진 사다리까지 계산
        match(); //감춰진 사다리 추론

        if(sb.length() != k-1) { //원하는 결과를 얻을 수 없는 경우
            sb.setLength(0); //길이 초기화
            sb.append("x".repeat(Math.max(0, k - 1))); // 'x'를 k-1개 채우기
        }

        System.out.println(sb); //결과 출력
    }
}
