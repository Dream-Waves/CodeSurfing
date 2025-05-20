package Jieun714;
/**
 * 문제: 호석이는 짝수랑 홀수 중에서 이니셜이 같은 홀수를 더 좋아한다. 운전을 하던 호석이는 앞차의 번호판이 홀수로 가득할 때 사랑스러움을 느낄 정도이다. 전화번호도 홀수만 있고 싶다. 그렇게 홀수 홀릭에 빠진 호석이는 가지고 있는 수 N을 일련의 연산을 거치면서, 등장하는 숫자들에서 홀수를 최대한 많이 많이 보고 싶다.
 *      하나의 수가 주어졌을 때 호석이는 한 번의 연산에서 다음과 같은 순서를 거친다.
 *      - 수의 각 자리 숫자 중에서 홀수의 개수를 종이에 적는다.
 *      - 수가 한 자리이면 더 이상 아무것도 하지 못하고 종료한다.
 *      - 수가 두 자리이면 2개로 나눠서 합을 구하여 새로운 수로 생각한다.
 *      - 수가 세 자리 이상이면 임의의 위치에서 끊어서 3개의 수로 분할하고, 3개를 더한 값을 새로운 수로 생각한다.
 *      시작할 때 호석이가 가진 수를 N 이라고 했을 때, 만들 수 있는 최종값 중 최솟값과 최댓값을 구해주자.
 * 입력: 첫번째 줄에 호석이가 처음 시작할 때 가지고 있는 수 N 이 주어진다.
 * 츨력: 첫 번째 줄에 호석이가 만들 수 있는 최종값 중에서 최솟값과 최댓값을 순서대로 공백으로 구분하여 출력한다.
 * 해결: 브루트포스 알고리즘
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20164 {
    public static int min = Integer.MAX_VALUE; //최종값 중 최솟값
    public static int max = Integer.MIN_VALUE; //최종값 중 최댓값

    public static void find(String str, int n) {
        // 현재 수의 홀수 개수 계산
        for(int i=0; i<str.length(); i++) {
            if(Integer.parseInt(String.valueOf(str.charAt(i)))%2 == 1) n += 1;
        }

        if(str.length() == 1) { //현재 수가 한자리 수일 경우
            min = Math.min(min, n);
            max = Math.max(max, n);
        } else if(str.length() == 2) { //현재 수가 두자리 수일 경우
            int next = Integer.parseInt(String.valueOf(str.charAt(0)))
                    + Integer.parseInt(String.valueOf(str.charAt(1)));
            find(String.valueOf(next), n); //각 자리의 수를 더한 뒤 재귀함수 호출
        } else {
            // 모든 경우의 수를 탐색하기 위해 이중 for문을 통해 문자열을 자른 뒤 재귀함수 호출
            for(int i = 1; i < str.length()-1; i++) {
                for(int j = i+1; j < str.length(); j++) {
                    int next = Integer.parseInt(str.substring(0, i))
                            + Integer.parseInt(str.substring(i, j))
                            + Integer.parseInt(str.substring(j));
                    find(String.valueOf(next), n);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        find(s, 0); //탐색 시작
        System.out.println(min + " " + max); //결과 출력
    }
}

