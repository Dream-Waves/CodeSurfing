package Jieun714;
/**
 * 문제:새로 문을 연 회전 초밥 음식점이 불경기로 영업이 어려워서, 다음과 같이 두 가지 행사를 통해서 매상을 올리고자 한다. 원래 회전 초밥은 손님이 마음대로 초밥을 고르고, 먹은 초밥만큼 식대를 계산하지만,
 *     벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공한다. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고, 1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공한다.
 *     만약 이 번호에 적혀진 초밥이 현재 벨트 위에 없을 경우, 요리사가 새로 만들어 손님에게 제공한다. 회전 초밥 음식점의 벨트 상태, 메뉴에 있는 초밥의 가짓수, 연속해서 먹는 접시의 개수,
 *     쿠폰 번호가 주어졌을 때, 손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하는 프로그램을 작성하시오.
 *
 * 입력: 첫 번째 줄에는 회전 초밥 벨트에 놓인 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c가 각각 하나의 빈 칸을 사이에 두고 주어진다. 단, 2 ≤ N ≤ 30,000, 2 ≤ d ≤ 3,000, 2 ≤ k ≤ 3,000 (k ≤ N), 1 ≤ c ≤ d이다.
 *      두 번째 줄부터 N개의 줄에는 벨트의 한 위치부터 시작하여 회전 방향을 따라갈 때 초밥의 종류를 나타내는 1 이상 d 이하의 정수가 각 줄마다 하나씩 주어진다.
 * 출력: 주어진 회전 초밥 벨트에서 먹을 수 있는 초밥의 가짓수의 최댓값을 하나의 정수로 출력한다.
 *
 * 해결: 슬라이딩 윈도우
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[4]; // 0:접시의 수, 1: 초밥의 가짓수, 2:연속해서 먹는 접시의 수, 3:쿠폰 번호
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        ArrayList<Integer> sushi = new ArrayList<>(); // 초밥의 종류
        for (int i = 0; i < arr[0]; i++) {
            sushi.add(Integer.parseInt(br.readLine()));
        }

        int[] isVisited = new int[arr[1] + 1]; //초밥의 가짓수를 체크하기 위한 배열
        int cnt = 0; //서로 다른 초밥의 가짓수
        for (int i = 0; i < arr[2]; i++) {
            if (isVisited[sushi.get(i)] == 0) cnt++;
            isVisited[sushi.get(i)]++; //초밥 먹을 횟수 증가
        }

        int max = cnt;
        for (int i = 1; i < arr[0] + arr[2]; i++) {
            if (max <= cnt) {
                if (isVisited[arr[3]] == 0) //쿠폰 번호에 해당하는 초밥을 먹지 않았다면
                    max = cnt + 1;
                else
                    max = cnt;
            }
            isVisited[sushi.get((i - 1) % arr[0])]--; //윈도우에서 벗어난 초밥 종류 감소
            if (isVisited[sushi.get((i - 1) % arr[0])] == 0) cnt--; //초밥 종류가 윈도우에서 없어졌다면, 전체 초밥 종류 감소
            if (isVisited[sushi.get((i + arr[2] - 1) % arr[0])] == 0) cnt++; //새로 추가된 초밥이 첨 먹는 초밥일때 증가
            isVisited[sushi.get((i + arr[2] - 1) % arr[0])]++;
        }

        System.out.println(max); //결과
    }
}
