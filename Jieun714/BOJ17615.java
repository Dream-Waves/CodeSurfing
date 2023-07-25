package Jieun714;
/**
 * 문제: 빨간색 볼과 파란색 볼이 <그림 1>에서 보인 것처럼 일직선상에 섞여 놓여 있을 때, 볼을 옮겨서 같은 색 볼끼리 인접하게 놓이도록 하려고 한다. 볼을 옮기는 규칙은 다음과 같다.
 *      바로 옆에 다른 색깔의 볼이 있으면 그 볼을 모두 뛰어 넘어 옮길 수 있다. 즉, 빨간색 볼은 옆에 있는 파란색 볼 무더기를 한 번에 뛰어 넘어 옮길 수 있다. 유사하게, 파란색 볼은 옆에 있는 빨간색 볼 무더기를 한 번에 뛰어 넘어 옮길 수 있다.
 *      옮길 수 있는 볼의 색깔은 한 가지이다. 즉, 빨간색 볼을 처음에 옮겼으면 다음에도 빨간색 볼만 옮길 수 있다. 유사하게, 파란색 볼을 처음에 옮겼으면 다음에도 파란색 볼만 옮길 수 있다.
 *      일직선상에 놓여 있는 볼에 관한 정보가 주어질 때, 규칙에 따라 볼을 이동하여 같은 색끼리 모으되 최소 이동횟수를 찾는 프로그램을 작성하시오.
 * 입력: 첫 번째 줄에는 볼의 총 개수 N이 주어진다. (1 ≤ N ≤ 500,000) 다음 줄에는 볼의 색깔을 나타내는 문자 R(빨간색 볼) 또는 B(파란색 볼)가 공백 없이 주어진다. 문자열에는 R 또는 B 중 한 종류만 주어질 수도 있으며, 이 경우 답은 0이 된다.
 * 출력: 최소 이동횟수를 출력한다.
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17615 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //볼의 총 개수
        char [] color = br.readLine().toCharArray(); //볼의 색깔을 담는 배열
        int min = N; //최소 이동횟수
        int [] cntArr = new int[4]; //이동횟수를 담는 배열. rLeft, rRight, lLeft, lRight
        int rCnt = 0; // R공 갯수
        int bCnt = 0; // B공 개수
        for(char c: color){
            if(c=='R') rCnt++;
            else bCnt++;
        }

        // 1. R공을 모두 왼쪽으로
        for(char c: color){
            if(c=='R') cntArr[0]++;
            else break;
        }
        min = Math.min(min, rCnt-cntArr[0]); //왼쪽으로 몰았을 때 최솟값

        // 2. R공을 모두 오른쪽으로
        for(int i=N-1; i>=0; i--){
            if(color[i]=='R') cntArr[1]++;
            else break;
        }
        min = Math.min(min, rCnt-cntArr[1]); //오른쪽으로 몰았을 때 최솟값

        // 3. B공을 모두 왼쪽으로
        for(char c: color){
            if(c=='B') cntArr[2]++;
            else break;
        }
        min = Math.min(min, bCnt-cntArr[2]); //왼쪽으로 몰았을 때 최솟값

        // 4. B공을 모두 오른쪽으로
        for(int i=N-1; i>=0; i--){
            if(color[i]=='B') cntArr[3]++;
            else break;
        }
        min = Math.min(min, bCnt-cntArr[3]); //오른쪽으로 몰았을 때 최솟값

        System.out.println(min); //결과 출력
    }
}